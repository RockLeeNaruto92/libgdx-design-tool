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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public abstract class ActorProperty extends Property{
	private TextField x, y, width, height;
	private TextField name;
	private CheckBox visible, debug;
	
	public ActorProperty(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		super(stage, skin, location, size, controller);
	}
	
	private void createTextFieldName(Skin skin, Vector2 parentSize){
		String[] labels = {Word.NAME};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		name = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.NAME, colspans);
	}
	
	private void createTextFieldX(Skin skin, Vector2 parentSize){
		String[] labels = {Word.LOCATION, Word.X};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.X_RANGE.x, Constant.X_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		x = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.X, colspans);
	}
	
	private void createTextFieldY(Skin skin, Vector2 parentSize){
		String[] labels = {Word.NULL, Word.Y};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.Y_RANGE.x, Constant.Y_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		y = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.Y, colspans);
	}
	
	private void createTextFieldWidth(Skin skin, Vector2 parentSize){
		String[] labels = {Word.SIZE, Word.WIDTH};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.WIDTH_RANGE.x, Constant.WIDTH_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		width = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.WIDTH, colspans);
	}
	
	private void createTextFieldHeight(Skin skin, Vector2 parentSize){
		String[] labels = {Word.NULL, Word.HEIGHT};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.HEIGHT_RANGE.x, Constant.HEIGHT_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		height = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.HEIGHT, colspans);
	}
	
	private void createCheckboxVisibleField(Skin skin, Vector2 parentSize){
		String[] labels = {Word.VISIBLE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, 0};
		int[] colspans = {1, 3};
		
		visible = Utility.createCheckboxField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.VISIBLE, colspans);
	}
	
	private void createCheckboxDebugField(Skin skin, Vector2 parentSize) {
		String[] labels = {Word.DEBUG};
		float[] widths = {Constant.PROPERTY_COLUMN_1, 0};
		int[] colspans = {1, 1, 1, 1};
		
		debug = Utility.createCheckboxField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.VISIBLE, colspans);
	}
	
	public abstract void createOtherField(Skin skin, Vector2 parentSize);
	
	@Override
	public void refresh(){
		setActorProperty(getObject());
	}
	
	public void setActorProperty(Actor actor){
		name.setText(actor.getName());
		x.setText(actor.getX() + "");
		y.setText(actor.getY() + "");
		width.setText(actor.getWidth() + "");
		height.setText(actor.getHeight() + "");
		visible.setChecked(actor.isVisible());
		debug.setChecked(actor.getDebug());
	}

	@Override
	public void createProperties(Skin skin, Vector2 parentSize) {
		createTextFieldName(skin, parentSize);
		createTextFieldX(skin, parentSize);
		createTextFieldY(skin, parentSize);
		createTextFieldWidth(skin, parentSize);
		createTextFieldHeight(skin, parentSize);
		createCheckboxVisibleField(skin, parentSize);
		createCheckboxDebugField(skin, parentSize);
		createOtherField(skin, parentSize);
	}
}