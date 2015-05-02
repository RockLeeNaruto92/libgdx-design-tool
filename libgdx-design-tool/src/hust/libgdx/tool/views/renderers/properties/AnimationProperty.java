package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.customs.CPlayMode;
import hust.libgdx.tool.models.uielements.LAnimation;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class AnimationProperty extends ActorProperty {
	private static AnimationProperty _instance;
	
	private TextField rotation, timePerFrame;
	private SelectBox<Object> playMode;
	
	public AnimationProperty(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}
	
	public static AnimationProperty getInstance(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		if (_instance == null){
			_instance = new AnimationProperty(stage, skin, location, size, controller);
		}
		
		return _instance;
	}

	@Override
	public void createOtherField(Skin skin, Vector2 parentSize) {
		createTextFieldRotation(skin, parentSize);
		createTextFieldTimePerFrame(skin, parentSize);
		createSelectBoxPlayMode(skin, parentSize);
	}
	
	private void createTextFieldRotation(Skin skin, Vector2 parentSize){
		String[] labels = {Word.ROTATION};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.ROTATION_RANGE.x, Constant.ROTATION_RANGE.y, Constant.SLIDER_STEP};
		int[] colspans = {1, 2, 1};
		
		rotation = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.ROTATION, colspans);
	}
	
	private void createTextFieldTimePerFrame(Skin skin, Vector2 parentSize){
		String[] labels = {Word.TIME_PER_FRAME};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		timePerFrame = Utility.createTextFieldWithOutSlider(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.TIME_PER_FRAME, colspans);
	}
	
	private void createSelectBoxPlayMode(Skin skin, Vector2 parentSize){
		String[] labels = {Word.PLAY_MODE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		int[] colspans = {1, 3};
		
		playMode = Utility.createSelectBox(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.PLAY_MODE, CPlayMode.playModes, colspans);
	}
	
	@Override
	public void setActorProperty(Actor actor) {
		super.setActorProperty(actor);
		LAnimation obj = (LAnimation)actor;
		
		rotation.setText(obj.getRotation() + "");
		timePerFrame.setText(obj.getTimePerFrame() + "");
		playMode.setSelectedIndex(CPlayMode.getIndex(obj.getPlayMode()));
	}
}
