package hust.libgdx.tool.controllers;

import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.views.HomeScreen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UIElementController extends Controller{
	private static int i = 0;
	
	private Actor newActor;
	private HomeScreen screen;
	
	public UIElementController(){
	}
	
	public void setScreen(HomeScreen screen){
		this.screen = screen;
	}
	
	public void onTouchDown(Object type){
		if (type instanceof UIElementType)
			newActor = createNewActor((UIElementType)type, screen.getRender().getSkin());
	}
	
	public void onTouchUp(){
		// if (touchupPoint)
	}
	
	public void onTouchMove(){
		
	}
	
	public void drag(){
		
	}
	
	public void drop(){
		
	}
	
	public Actor createNewActor(UIElementType type, Skin skin){
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
