package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.customs.CAlign;
import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.ImageTableField;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class CheckBoxProperty extends ButtonProperty {
	private static CheckBoxProperty _instance;
	
	private TextField text;
	private SelectBox<Object> align;
	private ImageTableField on, off, onDisabled, offDisabled, over;

	public CheckBoxProperty(Stage stage, Skin skin, Vector2 location,
			Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}
	
	public static CheckBoxProperty getInstance(Stage stage, Skin skin, Vector2 location,
			Vector2 size, UIElementController controller){
		if (_instance == null)
			_instance = new CheckBoxProperty(stage, skin, location, size, controller);
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
		createTextFieldText(skin, parentSize);
		createSelectBoxAlign(skin, parentSize);
		createStyle(skin, parentSize);
	}
	
	
	@Override
	protected void createStyle(Skin skin, Vector2 parentSize) {
		super.createStyle(skin, parentSize);
		
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 , Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 1, 2};
		
		on = Utility.createImageField(getParent(), parentSize, new String[]{Word.ON}, widths, skin, this, getController(), ActorPropertyType.IMAGE, colspans, 6);
		onDisabled = Utility.createImageField(getParent(), parentSize, new String[]{Word.ON_DISABLED}, widths, skin, this, getController(), ActorPropertyType.IMAGE, colspans, 7);
		over = Utility.createImageField(getParent(), parentSize, new String[]{Word.OVER}, widths, skin, this, getController(), ActorPropertyType.IMAGE, colspans, 8);
		off = Utility.createImageField(getParent(), parentSize, new String[]{Word.OFF}, widths, skin, this, getController(), ActorPropertyType.IMAGE, colspans, 9);
		offDisabled = Utility.createImageField(getParent(), parentSize, new String[]{Word.OFF_DISABLED}, widths, skin, this, getController(), ActorPropertyType.IMAGE, colspans, 10);
	}

	private void createTextFieldText(Skin skin, Vector2 parentSize){
		String[] labels = {Word.TEXT};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		text = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.TEXT, colspans);
	}
	
	private void createSelectBoxAlign(Skin skin, Vector2 parentSize){
		String[] labels = {Word.ALIGN};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		align = Utility.createSelectBox(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.ALIGN, CAlign.aligns, colspans);
	}

	@Override
	public void setActorProperty(Actor actor) {
		super.setActorProperty(actor);
		
		CheckBox object = (CheckBox)actor;
		
		text.setText(object.getText() + "");
		align.setSelectedIndex(CAlign.getIndex(object.getAlign()));
		
		setDrawable(object.getStyle().checkboxOn, on);
		setDrawable(object.getStyle().checkboxOff, off);
		setDrawable(object.getStyle().checkboxOnDisabled, onDisabled);
		setDrawable(object.getStyle().checkboxOffDisabled, offDisabled);
		setDrawable(object.getStyle().checkboxOver, over);
	}
}
