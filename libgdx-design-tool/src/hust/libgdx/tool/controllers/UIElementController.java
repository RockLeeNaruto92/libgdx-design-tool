package hust.libgdx.tool.controllers;

import java.util.ArrayList;

import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.views.HomeScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UIElementController extends Controller{
	private static int i = 0;
	
	private Actor currentActor;
	private HomeScreen screen;
	private ArrayList<Actor> actors;
	private UIElementType selectActorType = UIElementType.EMPTY;
	private Vector2 currentTouchPos = new Vector2();
	
	public UIElementController(){
		actors = new ArrayList<Actor>();
	}
	
	public void setScreen(HomeScreen screen){
		this.screen = screen;
	}
	
	public void onTouchDown(Object type, float x, float y){
		selectActorType = (UIElementType)type;
		currentTouchPos.set(x, y);
	}
	
	public void onTouchUp(float x, float y){
		System.out.println("Touch up at " + x + "-" + y);
		if (selectActorType != UIElementType.EMPTY){
			if (isTouchingInEditor())
				drop(x, y);
			else
				freeNewActor();
			
			selectActorType = UIElementType.EMPTY;
		}
	}
	
	public void onTouchMove(float x, float y){
		currentTouchPos.set(x, y);

		if (selectActorType == UIElementType.EMPTY) return;
		if (isTouchingInEditor()){
			drag(x, y);
		}
	}
	
	public void drag(float x, float y){
		if (currentActor == null)
			currentActor = createNewActor(selectActorType, screen.getRender().getSkin());
		
		// set new position for new actor with editor
		screen.getRender().setActorLocation(currentActor, x, y);
	}
	
	public void drop(float x, float y){
		currentActor = createNewActor(selectActorType, screen.getRender().getSkin());
		
		// add new actor to list of actors
		actors.add(currentActor);
		// add actor to editor stage
		screen.getRender().addNewActor(currentActor, x, y);
		freeNewActor();
		
		// show property
	}
	
	private boolean isTouchingInEditor(){
		return screen.getRender().isInEditor(currentTouchPos);
	}
	
	private Actor createNewActor(UIElementType type, Skin skin){
		Actor actor = null;
		String name = getDefaultName(type);
		
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
		
		if (actor != null)
			actor.setName(name);
		
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