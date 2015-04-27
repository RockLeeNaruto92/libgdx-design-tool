package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class ActorProperty extends Property{
	private TextField x, y, width, height;
	
	public ActorProperty(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		super(stage, skin, location, size, controller);
	}
	
	private void createTextFieldX(Skin skin, Vector2 parentSize){
		String[] labels = {Word.LOCATION, Word.X};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.X_RANGE.x, Constant.X_RANGE.y, Constant.SLIDER_STEP};
		
		x = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.X);
	}
	
	private void createTextFieldY(Skin skin, Vector2 parentSize){
		String[] labels = {"", Word.Y};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.Y_RANGE.x, Constant.Y_RANGE.y, Constant.SLIDER_STEP};
		
		y = Utility.createTextFieldWithSlider(getParent(), parentSize, labels, widths, sliderInfo, skin, this, getController(), ActorPropertyType.Y);
	}
	
	private void createTextFieldWidth(){
		
	}
	
	private void createTextFieldHeight(){
		
	}
	
	private void createOtherField(){
		
	}
	
	public void refresh(){
		setActorProperty(getObject());
	}
	
	public void setActorProperty(Actor actor){
		x.setText(actor.getX() + "");
	}

	@Override
	public void createProperties(Skin skin, Vector2 parentSize) {
		createTextFieldX(skin, parentSize);
	}
}