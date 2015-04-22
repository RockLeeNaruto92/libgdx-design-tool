package hust.libgdx.tool.controllers;

import java.util.ArrayList;

import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.views.HomeScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UIElementController extends Controller{
	private static int i = 0;
	
	private Actor newActor;
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
		
		if (isTouchingInEditor()){
			drag(x, y);
		}
	}
	
	public void drag(float x, float y){
		if (newActor == null)
			newActor = createNewActor(selectActorType, screen.getRender().getSkin());
		
		// set new position for new actor with editor
		screen.getRender().setActorLocation(newActor, x, y);
	}
	
	public void drop(float x, float y){
		newActor = createNewActor(selectActorType, screen.getRender().getSkin());
		
		// add new actor to list of actors
		actors.add(newActor);
		// add actor to editor stage
		screen.getRender().addNewActor(newActor, x, y);
		
		// show property
	}
	
	private boolean isTouchingInEditor(){
		return screen.getRender().isInEditor(currentTouchPos);
	}
	
	private Actor createNewActor(UIElementType type, Skin skin){
		Actor actor = null;
		String name;
		
		switch (type) {
		case LABEL:
			actor = new Label(getDefaultName(type), skin);
			break;

		default:
			break;
		}
		
		if (actor != null){
			name = getDefaultName(type);
			actor.setName(name);
		}
		
		return actor;
	}
	
	private void freeNewActor(){
		newActor = null;
	}
	
	private static String getDefaultName(UIElementType type){
		switch (type) {
		case LABEL:
			return Word.LABEL_PATTERN_NAME + (i++);
		default:
			break;
		}
		
		return null;
	}
}