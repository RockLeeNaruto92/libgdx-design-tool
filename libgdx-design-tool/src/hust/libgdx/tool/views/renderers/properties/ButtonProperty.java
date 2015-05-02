package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class ButtonProperty extends ActorProperty {
	private static ButtonProperty _instance;
	
	private TextField padLeft, padRight, padTop, padBottom;
	private CheckBox  check, disable;
	
	public ButtonProperty(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}
	
	public static ButtonProperty getInstance(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		if (_instance == null){
			_instance = new ButtonProperty(stage, skin, location, size, controller);
		}
		
		return _instance;
	}

	@Override
	public void createOtherField(Skin skin, Vector2 parentSize) {
		createCheckBoxDisable(skin, parentSize);
		createCheckBoxCheck(skin, parentSize);
		createTextFieldPadLeft(skin, parentSize);
		createTextFieldPadRight(skin, parentSize);
		createTextFieldPadTop(skin, parentSize);
		createTextFieldPadBottom(skin, parentSize);
	}
	
	protected void createCheckBoxDisable(Skin skin, Vector2 parentSize){
		String[] labels = {Word.DISABLE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		disable = Utility.createCheckboxField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.DISABLE, colspans);
	}
	
	protected void createTextFieldPadLeft(Skin skin, Vector2 parentSize){
		String[] labels = {Word.PAD, Word.LEFT};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.PAD_RANGE.x, Constant.PAD_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		padLeft = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.PAD_LEFT, colspans);
	}
	
	protected void createTextFieldPadRight(Skin skin, Vector2 parentSize){
		String[] labels = {Word.NULL, Word.RIGHT};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 1, 1, 1};
		float[] sliderInfo = {Constant.PAD_RANGE.x, Constant.PAD_RANGE.y, Constant.SLIDER_STEP};
		
		padRight = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.PAD_RIGHT, colspans);
	}
	
	protected void createTextFieldPadTop(Skin skin, Vector2 parentSize){
		String[] labels = {Word.NULL, Word.TOP};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.PAD_RANGE.x, Constant.PAD_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		padTop = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.PAD_TOP, colspans);
	}
	
	protected void createTextFieldPadBottom(Skin skin, Vector2 parentSize){
		String[] labels = {Word.NULL, Word.BOTTOM};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.PAD_RANGE.x, Constant.PAD_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		padBottom = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.PAD_BOTTOM, colspans);
	}
	
	protected void createCheckBoxCheck(Skin skin, Vector2 parentSize){
		String[] labels = {Word.CHECK};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		check = Utility.createCheckboxField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.CHECK, colspans);
	}
	
	@Override
	public void setActorProperty(Actor actor) {
		super.setActorProperty(actor);
		Button obj = (Button)actor;
		
		disable.setChecked(obj.isDisabled());
		check.setChecked(obj.isChecked());
		padLeft.setText(obj.getPadLeft() + "");
		padRight.setText(obj.getPadRight() + "");
		padTop.setText(obj.getPadTop() + "");
		padBottom.setText(obj.getPadBottom() + "");
	}
}
