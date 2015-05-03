package hust.libgdx.tool.controllers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.models.customs.CAlign;
import hust.libgdx.tool.models.customs.CPlayMode;
import hust.libgdx.tool.models.customs.CScaling;
import hust.libgdx.tool.models.uielements.LAnimation;
import hust.libgdx.tool.models.uielements.LImage;
import hust.libgdx.tool.models.uielements.LLabel;
import hust.libgdx.tool.models.uielements.LSprite;
import hust.libgdx.tool.views.HomeScreen;
import hust.libgdx.tool.views.renderers.properties.ActorPropertyType;
import hust.libgdx.tool.views.renderers.properties.Property;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UIElementController extends Controller {
	enum Action {
		CREATE, SELECTING, SELECTED, NONE, RESIZE, MODIFY_IN_PROPERTY
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
		if (screen.getRender().isInEditor(x, y)){
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
			
		} else {
			displayBound(false);
			
			if (screen.getRender().isInProperty(x, y))
				setCurrentAction(Action.MODIFY_IN_PROPERTY);
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
				setPropertyView(null);
			} else {
				displayBound(true);
				screen.getRender().setSelecting(false);
				
				selectedBound = getSelectedBound(true);
				setCurrentAction(Action.SELECTED);
				
				if (selectedActors.size() == 1)
					setPropertyView(selectedActors.get(0));
			}
			break;
			
		case SELECTED:
			selectedBound = getSelectedBound(true);
			screen.getRender().setSelecting(false);
			displayBound(true);
			break;

		default:
			setCurrentAction(Action.NONE);
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
			actor = new LLabel(name, skin);
			break;
		case CHECKBOX:
			actor = new CheckBox(name, skin);
			break;
		case IMAGE:
			actor = new LImage(skin, Constant.DEFAULT_IMAGE);
			break;
		case SPRITE:
			actor = new LSprite();
			break;
		case ANIMATION:
			actor = new LAnimation(skin);
			break;
		case BUTTON:
			actor = new Button(skin);
			break;
		case SLIDER:
			actor = new Slider(Constant.SLIDER_DEFAULT_RANGE.x, Constant.SLIDER_DEFAULT_RANGE.y, Constant.SLIDER_STEP, false, skin);

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
		case IMAGE:
			return Word.IMAGE_PATTERN_NAME + (i++);
		case SPRITE:
			return Word.SPRITE_PATTERN_NAME + (i++);
		case ANIMATION:
			return Word.ANIMATION_PATTERN_NAME + (i++);
		case BUTTON:
			return Word.BUTTON_PATTERN_NAME + (i++);
		case SLIDER:
			return Word.SLIDER_PATTERN_NAME + (i++);
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
		case VISIBLE:
			object.setVisible((boolean)value);
			break;
		case DEBUG:
			object.setDebug((boolean)value);
			break;
		default:
			break;
		}
		
		if (object instanceof Label) setLabelProperty((LLabel)object, type, value);
		else if (object instanceof CheckBox) setCheckBoxProperty((CheckBox)object, type, value);
		else if (object instanceof Button) setButtonProperty((Button)object, type, value);
		else if (object instanceof Slider) setSliderProperty((Slider)object, type, value);
		else if (object instanceof Image) setImageProperty((LImage)object, type, value);
		else if (object instanceof LSprite) setSpriteProperty((LSprite)object, type, value);
		else if (object instanceof LAnimation) setAnimationProperty((LAnimation)object, type, value);
	}
	
	private void setAnimationProperty(LAnimation object,
			ActorPropertyType type, Object value) {
		switch (type) {
		case PLAY_MODE:
			object.setPlayMode(CPlayMode.getPlayMode((int)value));
			break;
		case ROTATION:
			object.setRotation((float)value);
			break;
		case TIME_PER_FRAME:
			object.setTimePerFrame(Float.parseFloat((String)value));
			break;
		case KEY_FRAMES_COUNT:
			object.setCount(Integer.parseInt((String)value));
			break;
		case IMAGE:
			Object[] result = (Object[]) value;
			object.setKeyFrame((int)result[0], (Texture)result[1]);
			break;
		default:
			break;
		}
	}

	private void setSpriteProperty(LSprite object, ActorPropertyType type,
			Object value) {
		switch (type) {
		case COLOR:
			object.setColor((Color)value);
			break;
		case SCALE_X:
			object.setScaleX(Float.parseFloat((String)value));
			break;
		case SCALE_Y:
			object.setScaleY(Float.parseFloat((String)value));
			break;
		case ROTATION:
			object.setRotation((float)value);
			break;
		case IMAGE:
			Object[] result = (Object[]) value;
			object.setImage((Texture)result[1]);
			break;

		default:
			break;
		}
	}

	private void setImageProperty(LImage object, ActorPropertyType type,
			Object value) {
		switch (type) {
		case ALIGN:
			object.setAlign(CAlign.getAlign((int)value));
			break;
		case SCALING:
			object.setScaling(CScaling.getScaling((int)value));
			break;
		case IMAGE:
			Object[] result = (Object[]) value;
			TextureRegionDrawable trd = new TextureRegionDrawable(new TextureRegion((Texture)result[1]));
			object.setDrawable(trd);
			break;
		default:
			break;
		}
	}

	private void setSliderProperty(Slider object, ActorPropertyType type,
			Object value) {
		switch (type) {
		case MAX:
			object.setRange(object.getMinValue(), Float.parseFloat((String)value));
			break;
		case MIN:
			object.setRange(Float.parseFloat((String)value), object.getMaxValue());
			break;
		case STEP:
			object.setStepSize(Float.parseFloat((String)value));
			break;
		case INIT:
			object.setValue(Float.parseFloat((String)value));
			break;
		default:
			break;
		}
	}

	private void setCheckBoxProperty(CheckBox object, ActorPropertyType type, Object value){
		setButtonProperty(object, type, value);
		
		switch (type) {
		case TEXT:
			object.setText((String)value);
			break;
		case ALIGN:
			object.align(CAlign.getAlign((int)value));
			break;
		default:
			break;
		}
	}
	
	private void setButtonProperty(Button object, ActorPropertyType type, Object value){
		switch (type) {
		case DISABLE:
			object.setDisabled((boolean)value);
			break;
		case CHECK:
			object.setChecked((boolean)value);
			break;
		case PAD_LEFT:
			object.padLeft((float)value);
			break;
		case PAD_RIGHT:
			object.padRight((float)value);
			break;
		case PAD_TOP:
			object.padTop((float)value);
			break;
		case PAD_BOTTOM:
			object.padBottom((float)value);
			break;
		default:
			break;
		}
	}
	
	private void setLabelProperty(LLabel object, ActorPropertyType type,
			Object value) {
		switch (type) {
		case TEXT:
			object.setText((String)value);
			break;
		case ALIGN:
			object.setAlignment(CAlign.getAlign((int)value));
			break;
		case WRAP:
			object.setWrap((boolean)value);
			break;
		case FONT_SCALE_X:
			object.setFontScaleX((float)value);
			break;
		case FONT_SCALE_Y:
			object.setFontScaleY((float)value);
			break;
		case ELLIPSIS:
			object.setEllipsis((boolean)value);
			break;
		case FONT:{
			Object[] result = (Object[])value;
			object.getStyle().font = (BitmapFont)result[1];
			break;
		}
		case IMAGE:{
			Object[] result = (Object[])value;
			object.getStyle().background = new TextureRegionDrawable(new TextureRegion((Texture)result[1]));
			break;
		}
		default:
			break;
		}
	}

	public void setPropertyView(Actor actor){
		Property property = screen.getRender().getPropertyView(actor);
		
		if (actor != null) property.setObject(actor);
		property.refresh();
	}

	public void disableStage(boolean disable) {
		screen.enable(!disable);
	}
}