package hust.libgdx.tool.views.renderers.dialogs;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.models.customs.BorderWindow;
import hust.libgdx.tool.utilities.LObject;
import hust.libgdx.tool.utilities.Utility;

import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Style;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class ObjectCreationDialog extends BorderWindow{
	private SelectBox<LObject> list;
	private TextField name;
	private Rectangle bound;
	private ShapeRenderer shape;
	private Sprite line;
	
	public ObjectCreationDialog(String title, Skin skin, String styleName) {
		super(title, skin, styleName);
		init(skin);
	}
	
	public void setBound(float x, float y, float width, float height){
		bound.set(x, y, width, height);
		setBounds(x, y, width, height);
	}
	
	private void init(Skin skin){
		// pad
		padTop(23);
		
		// set align
		align(Align.top);
		// create shaperender to draw
		shape = new ShapeRenderer();
		
		row();
		
		// add radio button
		final Button existedBtn = new Button(skin);
		existedBtn.setChecked(true);
		add(existedBtn).align(Align.center).width(Constant.DIALOG_COLUMN_0_WIDTH * getWidth() - 20)
				.height(Constant.DIALOG_ROW_HEIGHT * getHeight()).pad(10);
		
		// add label to window
		Label label = new Label(Word.OBJECTS, skin);
		label.setFontScale(Constant.FONT_SCALE);
		add(label).align(Align.left).width(Constant.DIALOG_COLUMN_1_WIDTH * getWidth() - 20)
				.height(Constant.DIALOG_ROW_HEIGHT * getHeight()).pad(10);
		
		// add select box to window
		list = createList(skin);
		add(list).align(Align.center).width(Constant.DIALOG_COLUMN_2_WIDTH * getWidth() - 20)
				.height(Constant.DIALOG_ROW_HEIGHT * getHeight());

		
		row();
		
		// add radio Button
		final Button newBtn = new Button(skin);
		newBtn.setChecked(false);
		add(newBtn).align(Align.center).width(Constant.DIALOG_COLUMN_0_WIDTH * getWidth() - 20)
				.height(Constant.DIALOG_ROW_HEIGHT * getHeight()).pad(10);
		
		label = new Label(Word.CREATE_NEW_OBJECT, skin);
		label.setFontScale(Constant.FONT_SCALE);
		add(label).align(Align.left).width(Constant.DIALOG_COLUMN_1_WIDTH * getWidth() - 20)
				.height(Constant.DIALOG_ROW_HEIGHT * getHeight() - 20).pad(10);
		
		// create text field to input object name
		name = new TextField("", skin);
		name.setDisabled(true);
		add(name).align(Align.center).width(Constant.DIALOG_COLUMN_2_WIDTH * getWidth() - 20)
				.height(Constant.DIALOG_ROW_HEIGHT * getHeight()).pad(10);
		
	}
	
	public void show(boolean show){
		setVisible(show);
	}
	
	private SelectBox<LObject> createList(Skin skin){
		list = new SelectBox<>(skin);
		
		setListItems(null);
		return list;
	}
	
	public void setListItems(ArrayList<LObject> objects){
		if (objects == null) return;
		
		Array<LObject> array = new Array<>();
		
		for (int i = 0; i < objects.size(); i++)
			array.add(objects.get(i));
		
		list.setItems(array);
	}
	
	public void reset(){
		list.clearItems();
	}
}