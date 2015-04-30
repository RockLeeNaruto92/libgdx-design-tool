package hust.libgdx.tool.views.renderers.dialogs;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.customs.BorderTextButton;
import hust.libgdx.tool.models.customs.BorderTextField;
import hust.libgdx.tool.models.customs.BorderWindow;
import hust.libgdx.tool.utilities.LObject;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;

public class ObjectCreationDialog extends BorderWindow{
	private SelectBox<LObject> list;
	private BorderTextField name;
	private Rectangle bound;
	private Button exist, cNew;
	private BorderTextButton ok, cancel;
	private Table table;
	private UIElementController controller;
	
	public ObjectCreationDialog(String title, Skin skin, String styleName) {
		super(title, skin, styleName);
		init(skin);
	}
	
	public void setBound(float x, float y, float width, float height){
		bound.set(x, y, width, height);
		setBounds(x, y, width, height);
	}
	
	private void init(Skin skin){
		align(Align.topLeft);
		padTop(2 * Constant.WINDOW_PAD);
		setModal(true);
		setMovable(false);
		setResizable(false);
		
		table = new Table();
		row();
		add(table).width(getWidth());
		
		createRow1(table, skin);
		createRow2(table, skin);
		createRow3(skin);
		addListener();
	}
	
	private void addListener(){
		exist.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (!exist.isChecked()) {
					list.setDisabled(false);
					name.setDisabled(true);
					cNew.setChecked(false);
				} else 
					exist.setChecked(true);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		cNew.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (!cNew.isChecked()){
					name.setDisabled(false);
					list.setDisabled(true);
					exist.setChecked(false);
				} else 
					cNew.setChecked(true);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		ok.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (controller == null) return;
				controller.onCreationObjectDlgOkBtnClick();
				
				super.touchUp(event, x, y, pointer, button);
			}
		});
		
		cancel.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				setVisible(false);
				
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}
	
	public void show(boolean show){
		setVisible(show);
	}
	
	private void createRow1(Table table, Skin skin){
		float width;
		float height = Constant.DIALOG_ROW_HEIGHT * getHeight();
		
		table.row();
		
		// create button radio
		width = Math.min(Constant.DIALOG_COLUMN_0_WIDTH * getWidth() - 2 * Constant.WINDOW_PAD, skin.getDrawable("radio-checked").getMinWidth());
		exist = createRadioButton(table, skin, width, Align.center, Constant.WINDOW_PAD, true);
		
		// create label
		width = Constant.DIALOG_COLUMN_1_WIDTH * getWidth() - Constant.WINDOW_PAD * 2;
		createLabel(table, skin, Word.OBJECTS, width, height, Align.left, Constant.WINDOW_PAD);
		
		// create selectbox
		width = Constant.DIALOG_COLUMN_2_WIDTH * getWidth() - Constant.WINDOW_PAD * 2;
		createList(table, skin, width, height, Align.left, Constant.WINDOW_PAD, false);
	}
	
	private void createRow2(Table table, Skin skin){
		float width;
		float height = Constant.DIALOG_ROW_HEIGHT * getHeight();
		
		table.row();
		
		// create button radio
		width = Math.min(Constant.DIALOG_COLUMN_0_WIDTH * getWidth() - 2 * Constant.WINDOW_PAD, skin.getDrawable("radio-checked").getMinWidth());
		cNew = createRadioButton(table, skin, width, Align.center, Constant.WINDOW_PAD, false);
		
		// create label
		width = Constant.DIALOG_COLUMN_1_WIDTH * getWidth() - Constant.WINDOW_PAD * 2;
		createLabel(table, skin, Word.CREATE_NEW_OBJECT, width, height, Align.left, Constant.WINDOW_PAD);
		
		// create textfield
		width = Constant.DIALOG_COLUMN_2_WIDTH * getWidth() - Constant.WINDOW_PAD * 2;
		name = createNameField(table, skin, width, height, Align.left, Constant.WINDOW_PAD, true);
	}
	
	private void createRow3(Skin skin) {
		float width = 0.5f * getWidth() - 2 * Constant.WINDOW_PAD;
		float height = Constant.DIALOG_ROW_HEIGHT * getHeight();
		Table table = new Table();
		
		row();
		
		add(table).width(getWidth()).height(height);
		
		ok = createTextButton(table, skin, Word.OK, width, height, Align.left, Constant.WINDOW_PAD);
		cancel = createTextButton(table, skin, Word.CANCEL, width, height, Align.left, Constant.WINDOW_PAD);
	}
	
	private BorderTextButton createTextButton(Table table, Skin skin, String text, float width, float height, int align, float pad){
		BorderTextButton btn = new BorderTextButton(text, skin);
		
		table.add(btn).align(align).width(width).height(height).pad(pad);
		
		return btn;
	}
	
	private BorderTextField createNameField(Table table, Skin skin, float width, float height, int align, float pad, boolean disabled){
		BorderTextField tf = new BorderTextField(Word.NULL, skin);
		
		tf.setDisabled(disabled);
		table.add(tf).align(align).width(width).height(height).pad(pad);
		
		return tf;
	}
	
	private Button createRadioButton(Table table, Skin skin, float width, int align, float pad, boolean checked){
		Button btn = new Button(skin, "radio");
		
		btn.setChecked(checked);
		table.add(btn).align(align).width(width).pad(pad);
		
		return btn;
	}
	
	private Label createLabel(Table table, Skin skin, String text, float width, float height, int align, float pad){
		Label label = new Label(text, skin);
		
		label.setFontScale(Constant.FONT_SCALE);
		table.add(label).align(align).width(width).height(height).pad(pad);
		
		return label;
	}
	
	private SelectBox<LObject> createList(Table table, Skin skin, float width, float height, int align, float pad, boolean disabled){
		list = new SelectBox<>(skin);
		list.setDisabled(disabled);
		setListItems(null);
		table.add(list).align(align).width(width).pad(pad);
		
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
	
	public void setController(UIElementController controller){
		this.controller = controller;
	}
}