package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.controllers.UIElementController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LabelStyleProperty extends StyleProperty{
	
	public LabelStyleProperty(Stage stage, Skin skin, Vector2 location,
			Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}

	@Override
	public void createProperties(Skin skin, Vector2 parentSize) {
		
	}

	@Override
	public void setObjectProperty(Object object) {
		
	}
}
