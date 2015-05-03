package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.controllers.UIElementController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class StyleProperty extends Property {

	public StyleProperty(Stage stage, Skin skin, Vector2 location,
			Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void refresh() {
		setObjectProperty(getObject());
	}
	
	public abstract void setObjectProperty(Object object);

}
