package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.controllers.UIElementController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LabelProperty extends ActorProperty {
	private static LabelProperty _instance;

	public LabelProperty(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}
	
	public static LabelProperty getInstance(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		if (_instance == null){
			_instance = new LabelProperty(stage, skin, location, size, controller);
		}
		
		return _instance;
	}

	@Override
	public void createOtherField() {
		
	}

	@Override
	public void setActorProperty(Actor actor) {
		// TODO Auto-generated method stub
		super.setActorProperty(actor);
	}
}
