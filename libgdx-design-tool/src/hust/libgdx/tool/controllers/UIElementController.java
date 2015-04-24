package hust.libgdx.tool.controllers;

import java.util.ArrayList;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.views.HomeScreen;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UIElementController extends Controller{
	private static int i = 0;
	
	private Actor currentActor;
	private ArrayList<Actor> selectedActors;
	private HomeScreen screen;
	private ArrayList<Actor> actors;
	private UIElementType selectActorType = UIElementType.EMPTY;
	private Vector2 currentTouchPos = new Vector2();
	private Vector2 beforeTouchPos = new Vector2();
	private Rectangle selectedBound;
	
	public UIElementController(){
		actors = new ArrayList<Actor>();
		selectedActors = new ArrayList<Actor>();
		selectedBound = new Rectangle();
	}
	
	public void setScreen(HomeScreen screen){
		this.screen = screen;
	}
	
	private Rectangle getSelectedBound(){
		float minX = Float.MAX_VALUE; 
		float minY = Float.MAX_VALUE;
		float maxX = Float.MIN_VALUE;
		float maxY = Float.MIN_VALUE;
		
		for (Actor actor : selectedActors) {
			if (actor.getX() > maxX) maxX = actor.getX();
			if (actor.getX() < minX) minX = actor.getX();
			if (actor.getY() > maxY) maxY = actor.getY();
			if (actor.getY() < minY) minY = actor.getY();
		}
		
		selectedBound.set(minX, minY, maxX - minX, maxY - minY);
		return selectedBound;
	}
	
	public void onTouchDown(Object type, float x, float y){
		selectActorType = (UIElementType)type;
		currentTouchPos.set(x, y);
		
		// tao 1 actor moi
		currentActor = createNewActor(selectActorType, screen.getRender().getSkin());		
		// add to selector elements
		selectedActors.add(currentActor);
	}
	
	public void onTouchUp(float x, float y){
		System.out.println("Touch up at " + x + "-" + y);
		if (selectActorType != UIElementType.EMPTY){
			if (isTouchingInEditor())
				drop(x, y);
			else{
				screen.getRender().removeActors(selectedActors);
				freeNewActor();
			}
			
			selectActorType = UIElementType.EMPTY;
		}
	}
	
	public void onTouchMove(float x, float y){
		beforeTouchPos.set(currentTouchPos);
		currentTouchPos.set(x, y);
		
		if (selectedActors.isEmpty()) return;
		if (isTouchingInEditor()){
			drag(x, y);
		}
	}
	
	public void drag(float x, float y){
		Vector2 distance = new Vector2(currentTouchPos.x - beforeTouchPos.x, currentTouchPos.y - beforeTouchPos.y);
		
		// add actor to editor stage if actor in dragdroppart when create new actor
		if (screen.getRender().isInEditor(currentTouchPos) && !screen.getRender().isContainActors(selectedActors)){
			for (Actor actor : selectedActors) {
				screen.getRender().addNewActor(actor, x, y);
			}
		}
		
		// get distance of touch position and bottom right point of select bound
		
		// set new position for new actor with editor
		screen.getRender().setActorsLocation(selectedActors, distance);
	}
	
	public void drop(float x, float y){
		currentActor = createNewActor(selectActorType, screen.getRender().getSkin());
		
		// add new actor to list of actors
		actors.add(currentActor);
		freeNewActor();
		
		// show property
		
		// update outline
	}
	
	private boolean isTouchingInEditor(){
		return screen.getRender().isInEditor(currentTouchPos);
	}
	
	private Actor createNewActor(UIElementType type, Skin skin){
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
		
		if (actor != null){
			actor.setName(name);
			actor.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					currentActor = tempActor;
					// clear all selected actors
					selectedActors.clear();
					
					System.out.println("current actor: " + tempActor.getName() + "-" + selectedActors.isEmpty());
					selectedActors.add(currentActor);
					return super.touchDown(event, x, y, pointer, button);
				}

				@Override
				public void enter(InputEvent event, float x, float y,
						int pointer, Actor fromActor) {
					System.out.println("Enter actor " + name + ": " + event.getStageX() +"-" + event.getStageY());
					super.enter(event, x, y, pointer, fromActor);
				}

				@Override
				public void exit(InputEvent event, float x, float y,
						int pointer, Actor toActor) {
					System.out.println("Exit actor " + name + ": " + event.getStageX() +"-" + event.getStageY());
					super.exit(event, x, y, pointer, toActor);
				}
			});
		}
		
		return actor;
	}
	
	private void freeNewActor(){
		currentActor = null;
	}
	
	private static String getDefaultName(UIElementType type){
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
}