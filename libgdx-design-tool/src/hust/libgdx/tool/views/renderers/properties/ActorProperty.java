package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class ActorProperty {
	private Actor object;
	
	private TextField x, y, width, height;
	private Table table, container;
	
	public ActorProperty(Stage stage, Skin skin, Vector2 location, Vector2 size){
		container = new Table();
		container.setX(location.x);
		container.setY(location.y);
		container.setWidth(size.x);
		container.setHeight(size.y);
		container.align(Align.topLeft);
		stage.addActor(container);
		
		table = new Table();
		table.setFillParent(true);
		ScrollPane scroll = new ScrollPane(table, skin);
		
		createTextFieldX(skin, size);
		createTextFieldY(skin, size);
		createTextFieldWidth();
		createTextFieldHeight();
		createOtherField();
		
		container.row();
		container.add(scroll);
		container.debugAll();
	}
	
	private void createTextFieldX(Skin skin, Vector2 parentSize){
		String[] labels = {Word.LOCATION, Word.X};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.X_RANGE.x, Constant.X_RANGE.y, Constant.SLIDER_STEP};
		
		x = Utility.createTextFieldWithSlider(table, parentSize, labels, widths, sliderInfo, skin);
	}
	
	private void createTextFieldY(Skin skin, Vector2 parentSize){
		String[] labels = {"", Word.X};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2, Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		float[] sliderInfo = {Constant.Y_RANGE.x, Constant.Y_RANGE.y, Constant.SLIDER_STEP};
		
		y = Utility.createTextFieldWithSlider(table, parentSize, labels, widths, sliderInfo, skin);
	}
	
	private void createTextFieldWidth(){
		
	}
	
	private void createTextFieldHeight(){
		
	}
	
	private void createOtherField(){
		
	}
}