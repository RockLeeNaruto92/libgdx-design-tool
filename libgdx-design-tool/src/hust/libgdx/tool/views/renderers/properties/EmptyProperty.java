package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class EmptyProperty extends Property {
	private Label label;

	public EmptyProperty(Stage stage, Skin skin, Vector2 location,
			Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}

	@Override
	public void createProperties(Skin skin, Vector2 parentSize) {
		label = new Label(Word.PROPERTY_EMPTY, skin);
		label.setFontScale(Constant.FONT_SCALE);
		
		getParent().row();
		float width = (Constant.PROPERTY_COLUMN_1 + Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4) * parentSize.x;
		float height = Constant.PROPERTY_ROW_HEIGHT * parentSize.y;
		getParent().add(label).colspan(3).width(width).height(height);
	}

}
