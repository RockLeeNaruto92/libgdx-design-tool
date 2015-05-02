package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.uielements.LImage;
import hust.libgdx.tool.models.uielements.LSprite;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SpriteProperty extends ActorProperty {
	private static SpriteProperty _instance;
	
	private TextField rotation, color;
	private TextField scaleX, scaleY;
	private Image image;
	

	public SpriteProperty(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}
	
	public static SpriteProperty getInstance(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		if (_instance == null){
			_instance = new SpriteProperty(stage, skin, location, size, controller);
		}
		
		return _instance;
	}

	@Override
	public void createOtherField(Skin skin, Vector2 parentSize) {
		createTextFieldRotation(skin, parentSize);
		createTextFieldScaleX(skin, parentSize);
		createTextFieldScaleY(skin, parentSize);
//		createTextFieldColor(skin, parentSize);
		createImageImage(skin, parentSize);
	}
	
	private void createTextFieldRotation(Skin skin, Vector2 parentSize){
		String[] labels = {Word.ROTATION};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.ROTATION_RANGE.x, Constant.ROTATION_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 2, 1};
		
		rotation = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.ROTATION, colspans);
	}
	
	private void createTextFieldScaleX(Skin skin, Vector2 parentSize){
		String[] labels = {Word.SCALE, Word.X};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 2, 1};
		
		scaleX = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.SCALE_X, colspans);
	}
	
	private void createTextFieldScaleY(Skin skin, Vector2 parentSize){
		String[] labels = {Word.NULL, Word.Y};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 2, 1};
		
		scaleY = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.SCALE_Y, colspans);
	}
	
	private void createImageImage(Skin skin, Vector2 parentSize) {
		String[] labels = {Word.IMAGE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		
		image = Utility.createImageField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.IMAGE);
	}
	
	private void createTextFieldColor(Skin skin, Vector2 parentSize){
		String[] labels = {Word.COLOR};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		
		color = Utility.createColorField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.COLOR);
	}

	@Override
	public void setActorProperty(Actor actor) {
		super.setActorProperty(actor);
		LSprite obj = (LSprite)actor;
		
		image.setDrawable(obj.getDrawble());
		scaleX.setText(obj.getScaleX() + "");
		scaleY.setText(obj.getScaleY() + "");
//		rotation.setText(obj.getRotation() + "");
	}
}
