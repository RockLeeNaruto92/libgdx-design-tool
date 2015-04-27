package hust.libgdx.tool.controllers;

import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.views.HomeScreen;
import hust.libgdx.tool.views.renderers.properties.ActorProperty;
import hust.libgdx.tool.views.renderers.properties.ActorPropertyType;
import hust.libgdx.tool.views.renderers.properties.Property;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UIElementController extends Controller {
	enum Action {
		CREATE, SELECTING, SELECTED, NONE, RESIZE
	}
	
	enum Direction {
		N, S, E, W, 
		NE, NW, SE, SW, CENTER, NONE
	}

	private static int i = 0;

	private Action currentAction = Action.NONE;
	private Direction resizeDirection = Direction.CENTER;
	private Actor currentActor;
	private ArrayList<Actor> selectedActors;
	private HomeScreen screen;
	private ArrayList<Actor> actors;
	private UIElementType selectActorType = UIElementType.EMPTY;
	private Vector2 currentTouchPos = new Vector2();
	private Vector2 beforeTouchPos = new Vector2();
	private Rectangle selectedBound;

	public UIElementController() {
		actors = new ArrayList<Actor>();
		selectedActors = new ArrayList<Actor>();
		selectedBound = new Rectangle();
	}

	public void setScreen(HomeScreen screen) {
		this.screen = screen;
	}

	/**
	 * Get selected bound
	 * 
	 * @param choose
	 *            : if (true) -> caculate selected bound from selected actors
	 * @return
	 */
	public Rectangle getSelectedBound(boolean choose) {
		if (choose) {
			float minX = Float.MAX_VALUE;
			float minY = Float.MAX_VALUE;
			float maxX = Float.MIN_VALUE;
			float maxY = Float.MIN_VALUE;
			
			for (Actor actor : selectedActors) {
				if (actor.getX() + actor.getWidth() > maxX)
					maxX = actor.getX() + actor.getWidth();
				if (actor.getX() < minX)
					minX = actor.getX();
				if (actor.getY() + actor.getHeight() > maxY)
					maxY = actor.getY() + actor.getHeight();
				if (actor.getY() < minY)
					minY = actor.getY();
			}
			
			selectedBound.set(minX, minY, maxX - minX, maxY - minY);
		}
		return selectedBound;
	}

	/**
	 * Process when touch down on Palette node label
	 * 
	 * @param type
	 * @param x
	 * @param y
	 */
	public void onTouchDown(Object type, float x, float y) {
		setCurrentAction(Action.CREATE);
		
		// clear selected actors
		selectedActors.clear();

		selectActorType = (UIElementType) type;
		currentTouchPos.set(x, y);

		// tao 1 actor moi
		currentActor = createNewActor(selectActorType, screen.getRender().getSkin());
		// add to selector elements
		selectedActors.add(currentActor);
	}

	/**
	 * Process when touch on main stage
	 * 
	 * @param x
	 * @param y
	 */
	public void onTouchDown(float x, float y) {
		if (!screen.getRender().isInEditor(new Vector2(x, y))) {
			displayBound(false);
		} else {
			if (selectedBound.contains(x, y) && currentAction == Action.SELECTED){
				screen.getRender().setSelecting(false);
			} else {
				screen.getRender().setSelecting(true);
				setCurrentAction(Action.SELECTING);
				// set selected bound x, y
				selectedBound.set(x, y, 0, 0);
			}
			
			currentTouchPos.set(x, y);
			displayBound(true);
		}
	}

	public void onTouchUp(float x, float y) {
		switch (currentAction) {
		case CREATE:
			if (isTouchingInEditor()){
				drop(x, y);
				selectedBound = getSelectedBound(true);
				displayBound(true);
				screen.getRender().setSelecting(false);
				setPropertyView(selectedActors.get(0));
			}
			else {
				screen.getRender().removeActors(selectedActors);
				freeNewActor();
				// show errors
			}
			setCurrentAction(Action.SELECTED);
			break;
			
		case SELECTING:
			Vector2 currentRelativePoint = screen.getRender().getRelativePointWithEditor(x, y);
			Vector2 beforeRelativePoint = screen.getRender().getRelativePointWithEditor(selectedBound.x, selectedBound.y);
			
			selectedBound.width = Math.abs(currentRelativePoint.x - beforeRelativePoint.x);
			selectedBound.height = Math.abs(currentRelativePoint.y - beforeRelativePoint.y);
			selectedBound.x = Math.min(currentRelativePoint.x, beforeRelativePoint.x);
			selectedBound.y = Math.min(currentRelativePoint.y, beforeRelativePoint.y);
			
			// get actors in select bound
			selectedActors = getActorsInSelectedBound(true);
			
			if (selectedActors.isEmpty())
				selectedActors = getActorsInSelectedBound(false);
			
			if (selectedActors.isEmpty()){
				displayBound(false);
				setCurrentAction(Action.NONE);
			} else {
				displayBound(true);
				screen.getRender().setSelecting(false);
				
				selectedBound = getSelectedBound(true);
				setCurrentAction(Action.SELECTED);
			}
			break;
			
		case SELECTED:
			selectedBound = getSelectedBound(true);
			screen.getRender().setSelecting(false);
			displayBound(true);
			break;
			
		case RESIZE:
			setCurrentAction(Action.NONE);
			break;

		default:
			break;
		}
	}

	public void onTouchMove(float x, float y) {
		beforeTouchPos.set(currentTouchPos);
		currentTouchPos.set(x, y);
		
		switch (currentAction) {
		case CREATE:
			if (selectedActors.isEmpty()) return;
			drag(x, y);
			setPropertyView(selectedActors.get(0));
			break;
		case SELECTING:
			selectedBound.width = x - selectedBound.x;
			selectedBound.height = y - selectedBound.y;
			displayBound(true);
			break;
		case SELECTED:
			drag(x, y);
			selectedBound = getSelectedBound(true);
			if (selectedActors.size() == 1)
				setPropertyView(selectedActors.get(0));
			
			break;
		case RESIZE:
			resizeActor(currentActor, x, y);
			if (selectedActors.size() == 1)
				setPropertyView(selectedActors.get(0));
			break;
		default:
			break;
		}
	}

	public void drag(float x, float y) {
		Vector2 relativeCurrentPoint = screen.getRender().getRelativePointWithEditor(currentTouchPos.x, currentTouchPos.y);
		Vector2 relativeBeforePoint = screen.getRender().getRelativePointWithEditor(beforeTouchPos.x, beforeTouchPos.y);
		Vector2 distance = new Vector2(relativeCurrentPoint.x - relativeBeforePoint.x, relativeCurrentPoint.y - relativeBeforePoint.y);

		// add actor to editor stage if actor in dragdroppart when create new actor
		if (screen.getRender().isInEditor(currentTouchPos) && !screen.getRender().isContainActors(selectedActors)) {
			for (Actor actor : selectedActors)
				screen.getRender().addNewActor(actor, relativeCurrentPoint.x, relativeCurrentPoint.y);
		}
		
		// set new position for new actor with editor
		screen.getRender().setActorsLocation(selectedActors, distance);
	}

	public void drop(float x, float y) {
		// add new actor to list of actors
		actors.add(currentActor);
		freeNewActor();

		// show property

		// update outline
	}

	private boolean isTouchingInEditor() {
		return screen.getRender().isInEditor(currentTouchPos);
	}

	private Actor createNewActor(UIElementType type, Skin skin) {
		Actor actor = null;
		final String name = getDefaultName(type);

		switch (type) {
		case LABEL:
			actor = new Label(name, skin);
			break;
		case CHECKBOX:
			actor = new CheckBox(name, skin);
			break;

		default:
			break;
		}

		final Actor tempActor = actor;

		if (actor != null) {
			actor.setName(name);
			actor.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// check mouse position
					resizeDirection = getMousePositionRelativeActor(tempActor, x, y);
					if (resizeDirection != Direction.CENTER){
						setCurrentAction(Action.RESIZE);
						currentActor = tempActor;
						setResizeDirection(resizeDirection);
						screen.getRender().setSelecting(false);
					} else if (currentAction != Action.SELECTED){
						selectedActors.clear();
						setCurrentAction(Action.SELECTING);
						displayBound(true);
					}
					
					return super.touchDown(event, x, y, pointer, button);
				}

				@Override
				public boolean mouseMoved(InputEvent event, float x, float y) {
					// TODO 
					// set cursor image
					
					
					return super.mouseMoved(event, x, y);
				}
			});
		}

		return actor;
	}

	private void freeNewActor() {
		currentActor = null;
	}

	private void displayBound(boolean display) {
		screen.getRender().setDisplayBound(display);
	}

	private static String getDefaultName(UIElementType type) {
		switch (type) {
		case LABEL:
			return Word.LABEL_PATTERN_NAME + (i++);
		case CHECKBOX:
			return Word.CHECKBOX_PATTERN_NAME + (i++);
		default:
			break;
		}

		return null;
	}

	public void setCurrentAction(Action action) {
		currentAction = action;
	}
	
	private void setResizeDirection(Direction resizeDirection){
		this.resizeDirection = resizeDirection;
	}

	/**
	 * if contain --> get all actor that is contained by selectBound
	 * else get actor contain selectbound
	 * @param contain
	 * @return
	 */
	private ArrayList<Actor> getActorsInSelectedBound(boolean contain) {
		selectedActors.clear();
		if (contain)
			for (Actor actor : actors) {
				if (selectedBound.contains(actor.getX(), actor.getY())
						&& selectedBound.contains(
								actor.getX() + actor.getWidth(), actor.getY()
										+ actor.getHeight()))
					selectedActors.add(actor);
			}
		else 
			for (Actor actor : actors){
				Rectangle bound = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
				if (bound.contains(selectedBound)){
					selectedActors.add(actor);
					break;
				}
			}

		return selectedActors;
	}
	
	public void deleteSelectedActors(){
		for (Actor actor : selectedActors)
			actors.remove(actor);
		
		screen.getRender().removeActors(selectedActors);
		selectedActors.clear();
		displayBound(false);
	}
	
	private Direction getMousePositionRelativeActor(Actor actor, float x, float y){
		float width = actor.getWidth();
		float height = actor.getHeight();
		float pad = 10;
		
		if (x < pad)
			if (y < pad) return Direction.SW;
			else if (y > height - pad) return Direction.NW;
			else return Direction.W;
		else if (x > width - pad)
			if (y < pad) return Direction.SE;
			else if (y > height - pad) return Direction.NE;
			else return Direction.E;
		else 
			if (y < pad) return Direction.S;
			else if (y > height - pad) return Direction.N;
			else return Direction.CENTER;
	}
	
	public void zoom(boolean zoomin){
		screen.getRender().zoomEditor(zoomin);
	}
	
	private void resizeActor(Actor actor, float x, float y){
		Vector2 relativePoint = screen.getRender().getRelativePointWithEditor(x, y);
		
		x = relativePoint.x;
		y = relativePoint.y;
		
		switch (resizeDirection) {
		case N:
			actor.setHeight(y - actor.getY());
			break;
		case S:
			actor.setHeight(actor.getHeight() + actor.getY() - y);
			actor.setY(y);
			break;
		case W:
			actor.setWidth(actor.getWidth() + actor.getX() - x);
			actor.setX(x);
			break;
		case E:
			actor.setWidth(x - actor.getX());
			break;
		case NE:
			actor.setWidth(x - actor.getX());
			actor.setHeight(y - actor.getY());
			break;
		case NW:
			actor.setHeight(y - actor.getY());
			actor.setWidth(actor.getWidth() + actor.getX() - x);
			actor.setX(x);
			break;
		case SE:
			actor.setHeight(actor.getHeight() + actor.getY() - y);
			actor.setY(y);
			actor.setWidth(x - actor.getX());
			break;
		case SW:
			actor.setHeight(actor.getHeight() + actor.getY() - y);
			actor.setY(y);
			actor.setWidth(actor.getWidth() + actor.getX() - x);
			actor.setX(x);
			break;
		default:
			break;
		}
		
		selectedBound = getSelectedBound(true);
	}

	public void setObjectProperty(Actor object, ActorPropertyType type, Object value) {
		switch (type) {
		case NAME:
			object.setName((String)value);
			break;
		case X:
			object.setX((float)value);
			break;
		case Y:
			object.setY((float)value);
			break;
		case WIDTH:
			object.setWidth((float)value);
			break;
		case HEIGHT:
			object.setHeight((float)value);
			break;
		default:
			break;
		}
	}
	
	public void setPropertyView(Actor actor){
		if (actor == null) return;
		
		ActorProperty property = (ActorProperty)screen.getRender().getPropertyView();
		
		property.setObject(actor);
		property.refresh();
	}
}