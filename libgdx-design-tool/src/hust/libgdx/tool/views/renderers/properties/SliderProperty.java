package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class SliderProperty extends ActorProperty {
	private static SliderProperty _instance;
	
	private TextField min, max, step, init;
	private CheckBox disable;
	
	public SliderProperty(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}
	
	public static SliderProperty getInstance(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		if (_instance == null){
			_instance = new SliderProperty(stage, skin, location, size, controller);
		}
		
		return _instance;
	}
	

	@Override
	public void createOtherField(Skin skin, Vector2 parentSize) {
		createCheckBoxDisable(skin, parentSize);
		createTextFieldMax(skin, parentSize);
		createTextFieldMin(skin, parentSize);
		createTextFieldStep(skin, parentSize);
		createTextFieldInit(skin, parentSize);
	}
	
	protected void createCheckBoxDisable(Skin skin, Vector2 parentSize){
		String[] labels = {Word.CHECK};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		
		disable = Utility.createCheckboxField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.CHECK);
	}
	
	private void createTextFieldMin(Skin skin, Vector2 parentSize){
		String[] labels = {Word.MIN_VALUE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		
		min = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.MIN);
	}
	
	private void createTextFieldMax(Skin skin, Vector2 parentSize){
		String[] labels = {Word.MAX_VALUE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		
		max = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.MAX);
	}
	
	private void createTextFieldStep(Skin skin, Vector2 parentSize){
		String[] labels = {Word.STEP_VALUE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		
		step = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.STEP);
	}
	
	private void createTextFieldInit(Skin skin, Vector2 parentSize){
		String[] labels = {Word.INIT_VALUE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		
		init = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.INIT);
	}
	
	@Override
	public void setActorProperty(Actor actor) {
		super.setActorProperty(actor);
		Slider object = (Slider)actor;
		
		disable.setDisabled(object.isDisabled());
		min.setText(object.getMinValue() + "");
		max.setText(object.getMaxValue() + "");
		step.setText(object.getStepSize() + "");
		init.setText(object.getValue() + "");
	}
}
