package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.ImageTableField;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class LabelStyleProperty extends StyleProperty{
	private static LabelStyleProperty _instance;
	private TextField font, fontColor;
	private ImageTableField background;
	
	public LabelStyleProperty(Stage stage, Skin skin, Vector2 location,
			Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}
	
	public static LabelStyleProperty getInstace(Stage stage, Skin skin, Vector2 location,
			Vector2 size, UIElementController controller){
		if (_instance == null)
			_instance = new LabelStyleProperty(stage, skin, location, size, controller);
		
		return _instance;
	}

	@Override
	public void createProperties(Skin skin, Vector2 parentSize) {
		createTextFieldFont(skin, parentSize);
		createTextFieldColor(skin, parentSize);
		createImageBackground(skin, parentSize);
	}

	private void createImageBackground(Skin skin, Vector2 parentSize) {
		String[] labels = {Word.BACKGROUND};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 1, 2};

		background = Utility.createImageField(getParent(), getSize(), labels, widths, skin, this, getController(), ActorPropertyType.IMAGE, colspans, 0);
	}

	private void createTextFieldFont(Skin skin, Vector2 parentSize) {
		String[] labels = {Word.BACKGROUND};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 1, 2};
		
		
	}

	private void createTextFieldColor(Skin skin, Vector2 parentSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObjectProperty(Object object) {
		LabelStyle style = (LabelStyle)object;
		
		
	}
}
