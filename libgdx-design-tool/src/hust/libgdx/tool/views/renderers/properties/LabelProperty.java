package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.customs.CAlign;
import hust.libgdx.tool.models.uielements.LLabel;
import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.ImageTableField;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class LabelProperty extends ActorProperty {
	private static LabelProperty _instance;
	
	private TextField text, font;
	private TextField fontScaleX, fontScaleY;
	private SelectBox<Object> align;
	private CheckBox wrap, ellipsis;
	private TextField color;
	private ImageTableField background;

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
	public void createOtherField(Skin skin, Vector2 parentSize) {
		createTextFieldText(skin, parentSize);
		createSelectBoxAlign(skin, parentSize);
		createCheckBoxWrap(skin, parentSize);
		createFontScaleX(skin, parentSize);
		createFontScaleY(skin, parentSize);
		createCheckBoxEllipsis(skin, parentSize);
		createTextFieldFont(skin, parentSize);
		createTextFieldFontColor(skin, parentSize);
		createImageBackground(skin, parentSize);
	}
	
	private void createImageBackground(Skin skin, Vector2 parentSize) {
		String[] labels = {Word.BACKGROUND};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		background = Utility.createImageField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.IMAGE, colspans, 0);
	}

	private void createTextFieldFontColor(Skin skin, Vector2 parentSize) {
		
	}

	private void createTextFieldFont(Skin skin, Vector2 parentSize) {
		String[] labels = {Word.FONT};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 2, 1};
		
		font = Utility.createFontField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.FONT, colspans, 0);
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
	
	private void createCheckBoxWrap(Skin skin, Vector2 parentSize){
		String[] labels = {Word.WRAP};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		wrap = Utility.createCheckboxField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.WRAP, colspans);
	}
	
	private void createFontScaleX(Skin skin, Vector2 parentSize){
		String[] labels = {Word.FONT_SCALE, Word.X};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.FONT_SCALE_RANGE.x, Constant.FONT_SCALE_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		fontScaleX = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.FONT_SCALE_X, colspans);
	}
	
	private void createFontScaleY(Skin skin, Vector2 parentSize){
		String[] labels = {Word.NULL, Word.Y};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.FONT_SCALE_RANGE.x, Constant.FONT_SCALE_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 1, 1, 1};
		
		fontScaleY = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.FONT_SCALE_Y, colspans);
	}
	
	private void createCheckBoxEllipsis(Skin skin, Vector2 parentSize){
		String[] labels = {Word.ELLIPSIS};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		ellipsis = Utility.createCheckboxField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.ELLIPSIS, colspans);
	}
	
	@Override
	public void setActorProperty(Actor actor) {
		super.setActorProperty(actor);
		LLabel obj = (LLabel)actor;
		
		text.setText(obj.getText() + "");
		align.setSelectedIndex(CAlign.getIndex(obj.getAlign()));
		wrap.setChecked(obj.isWrap());
		fontScaleX.setText(obj.getFontScaleX() + "");
		fontScaleY.setText(obj.getFontScaleY() + "");
		ellipsis.setChecked(obj.isEllipsis());
		font.setText(obj.getStyle().font.toString());
		
		Drawable backgroundImg = obj.getStyle().background == null ? getSkin().getDrawable("window-top") : obj.getStyle().background;
		background.setImage(backgroundImg);
	}
}
