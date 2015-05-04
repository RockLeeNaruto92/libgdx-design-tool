package hust.libgdx.tool.utilities;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.customs.BorderTextField;
import hust.libgdx.tool.models.customs.ColorTextField;
import hust.libgdx.tool.models.customs.FileChooser;
import hust.libgdx.tool.views.renderers.properties.ActorProperty;
import hust.libgdx.tool.views.renderers.properties.ActorPropertyType;
import hust.libgdx.tool.views.renderers.properties.Property;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Utility {
	public enum NodeType{
		FILE, FOLDER, UIELEMENT_GROUP, UIELEMENT
	}
	
	public static Rectangle getActualBound(Vector2 location, Vector2 size){
		Rectangle bound = new Rectangle();
		
		bound.width = Constant.SCREEN_SIZE.x * size.x;
		bound.height = Constant.SCREEN_SIZE.y * size.y;
		bound.x = Constant.SCREEN_SIZE.x * location.x;
		bound.y = Constant.SCREEN_SIZE.y * location.y;
		
		return bound;
	}
	
	public static float getActualValue(float arg, boolean width){
		if (width)
			return Constant.SCREEN_SIZE.x * arg;
		else
			return Constant.SCREEN_SIZE.y * arg;
	}
	
	public static CheckBox createSubMenuCheckbox(String text, Skin skin, String styleName){
		CheckBox checkbox = new CheckBox(text, skin, styleName);
		checkbox.getLabel().setFontScale(Constant.FONT_SCALE);
		checkbox.getLabelCell().left();
		checkbox.getImageCell()
			.size(Constant.VERTICAL_GROUP_IMAGE_WIDTH, Constant.VERTICAL_GROUP_IMAGE_HEIGHT)
			.padLeft(Constant.VERTICAL_GROUP_PAD_RIGHT).padRight(Constant.VERTICAL_GROUP_PAD_RIGHT).left();
		checkbox.left();
		
		return checkbox;
	}
	
	public static TextField createTextFieldWithSlider(Table parent,
			Vector2 parentSize, String[] labels, float[] widths,
			float sliderInfo[], Skin skin, final ActorProperty property,
			final UIElementController controller, final ActorPropertyType type, int[] colspans) {
		int i;
		
		parent.row();
		
		// create all label and add to parent table
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			parent.add(label).align(Align.left)
					.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
					.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
					.pad(Constant.PROPERTY_CELL_PAD)
					.colspan(colspans[i]);
		}
		
		// create text field
		final TextField textfield = new BorderTextField(Word.NULL, skin);
		parent.add(textfield).align(Align.left)
				.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(colspans[i++]);
		
		
		// create slider
		final Slider slider = new Slider(sliderInfo[0], sliderInfo[1], sliderInfo[2], false, skin);
		slider.getStyle().knob.setMinHeight(parentSize.y * Constant.PROPERTY_ROW_HEIGHT);
		slider.getStyle().knob.setMinWidth(parentSize.x * Constant.SLIDER_KNOB_WIDTH);
		parent.add(slider).align(Align.left)
				.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(colspans[i++]);
		
		// add listener to slider, when slider changed -> change textfield value and set object value
		slider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// change textfield value
				textfield.setText(slider.getValue() + "");
				// set object value
				Actor object = property.getObject();
				if (object == null) return;
				controller.setObjectProperty(object, type, slider.getValue());
			}
		});
		
		textfield.addListener(new InputListener(){
			@Override
			public boolean keyTyped(InputEvent event, char character) {
				// check validation
				
				// set object value if validation is true
				return super.keyTyped(event, character);
			}
		});
		
		return textfield;
	}
	
	public static TextField createTextFieldWithOutSlider(Table parent,
			Vector2 parentSize, String[] labels, float[] widths, Skin skin,
			final ActorProperty property, final UIElementController controller,
			final ActorPropertyType type, int[] colspans) {
		int i;
		
		parent.row();
		
		// create all label and add to parent table
		for (i = 0; i < labels.length; i++) {
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);

			parent.add(label).align(Align.left)
					.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
					.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
					.pad(Constant.PROPERTY_CELL_PAD)
					.colspan(colspans[i]);
		}

		// create text field
		final TextField textfield = new BorderTextField(Word.NULL, skin);
		parent.add(textfield).align(Align.left)
				.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(colspans[i++]);
		
		// add listener to text field, if textfield change value -> check validation and set value for object
		textfield.addListener(new InputListener(){
			@Override
			public boolean keyTyped(InputEvent event, char character) {
				// validation
				
				Actor object = property.getObject();
				if (object == null) return super.keyTyped(event, character);
				controller.setObjectProperty(object, type, textfield.getText());
				
				return super.keyTyped(event, character);
			}
		});
		
		return textfield;
	}
	
	public static CheckBox createCheckboxField(Table parent,
			Vector2 parentSize, String[] labels, float[] widths, Skin skin,
			final ActorProperty property, final UIElementController controller,
			final ActorPropertyType type, int[] colspans){
		int i;
		
		parent.row();
		
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			parent.add(label).align(Align.left)
					.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
					.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
					.pad(Constant.PROPERTY_CELL_PAD)
					.colspan(colspans[i]);
		}
		
		final CheckBox checkbox = new CheckBox(Word.NULL, skin);
		parent.add(checkbox).align(Align.left)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(colspans[i++]);
		checkbox.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Actor object = property.getObject();
				if (object == null) return;
				controller.setObjectProperty(object, type, checkbox.isChecked());
			}
		});
		
		return checkbox;
	}
	
	public static SelectBox<Object> createSelectBox(Table parent,
			Vector2 parentSize, String[] labels, float[] widths, Skin skin,
			final ActorProperty property, final UIElementController controller,
			final ActorPropertyType type, Object[] objects, int[] colspans){
		int i;
		
		parent.row();
		
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			parent.add(label).align(Align.left)
					.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
					.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
					.pad(Constant.PROPERTY_CELL_PAD)
					.colspan(colspans[i]);
		}
		
		final SelectBox<Object> sb = new SelectBox<Object>(skin);
		sb.setItems(objects);
		parent.add(sb).align(Align.left)
				.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(colspans[i++]);
		sb.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Actor object = property.getObject();
				if (object == null) return;
				controller.setObjectProperty(object, type, sb.getSelectedIndex());
			}
		});
		
		return sb;
	}
	
	public static ImageTableField createImageField(Table parent,
			Vector2 parentSize, String[] labels, float[] widths, Skin skin,
			final Property property, final UIElementController controller,
			final ActorPropertyType type, int[] colspans, final int ordinal){
		int i;
		ImageTableField field = new ImageTableField(skin, parentSize);
		float rowHeight = parentSize.y * Constant.PROPERTY_ROW_HEIGHT;
		int colspan = 0;
		
		for (int index : colspans) 
			colspan += index;
		
		parent.row();
		
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			field.addLabel(label, widths[i] * parentSize.x, rowHeight);
		}
		
		final Image image = new Image(skin, "anim-1");
		float imageWidth = widths[i++] * parentSize.x;
		field.addImage(image, imageWidth);
		
		// create set button
		TextButton btn = new TextButton(Word.SET, skin);
		field.addButton(btn, widths[i++] * parentSize.x, rowHeight);
		
		btn.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Actor object = property.getObject();
				if (object == null) return false;
				
				FileChooser fileChooser = FileChooser.getInstance();
				
				controller.disableStage(true);
				fileChooser.show(true, new String[]{"png", "jpg"});
				
				String resultPath = fileChooser.getResultPath();
				
				if (resultPath != null){
					Texture texture = new Texture(Gdx.files.absolute(resultPath));
					controller.setObjectProperty(object, ActorPropertyType.IMAGE, new Object[]{ordinal, texture});
					image.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
				}
				
				controller.disableStage(false);
				
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		
		parent.add(field).align(Align.left)
			.width(parentSize.x)
			.height(imageWidth)
			.colspan(colspan)
			.pad(Constant.PROPERTY_CELL_PAD);
		
		return field;
	}
	
	public static TextField createFontField(Table parent, Vector2 parentSize, String[] labels, float[] widths, Skin skin,
			final ActorProperty property, final UIElementController controller,
			final ActorPropertyType type, int[] colspans, final int ordinal){
		int i;
		
		parent.row();
		
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			parent.add(label).align(Align.left)
					.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
					.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
					.pad(Constant.PROPERTY_CELL_PAD)
					.colspan(colspans[i]);
		}
		
		final TextField textfield = new ColorTextField(Word.NULL, skin);
		textfield.setDisabled(true);
		parent.add(textfield).align(Align.left)
				.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(colspans[i++]);
		
		
		TextButton btn = new TextButton(Word.SET, skin);
		parent.add(btn).align(Align.left)
			.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
			.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
			.pad(Constant.PROPERTY_CELL_PAD)
			.colspan(colspans[i++]);
		btn.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Actor object = property.getObject();
				if (object == null) return false;
				
				FileChooser fileChooser = FileChooser.getInstance();
				
				controller.disableStage(true);
				fileChooser.show(true, new String[]{"png", "jpg"});
				
				String resultPath = fileChooser.getResultPath();
				
				if (resultPath != null){
					BitmapFont font = new BitmapFont(Gdx.files.absolute(resultPath));
					controller.setObjectProperty(object, ActorPropertyType.FONT, new Object[]{ordinal, font});
					textfield.setText(font.toString());
				}
				
				controller.disableStage(false);
				
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		return textfield;
	}
	
	public static TextField createColorField(Table parent,
			Vector2 parentSize, String[] labels, float[] widths, Skin skin,
			final ActorProperty property, final UIElementController controller,
			final ActorPropertyType type, int[] colspans){
		int i;
		
		parent.row();
		
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			parent.add(label).align(Align.left)
					.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
					.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
					.pad(Constant.PROPERTY_CELL_PAD)
					.colspan(colspans[i]);
		}
		
		final TextField textfield = new ColorTextField(Word.NULL, skin);
		textfield.setDisabled(true);
		parent.add(textfield).align(Align.left)
				.width(widths[i] * parentSize.x - 2 * Constant.PROPERTY_CELL_PAD)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(colspans[i++]);
		
		TextButton btn = new TextButton(Word.SET, skin);
		parent.add(btn).align(Align.left)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(colspans[i++]);
		
		return textfield;
	}
	
	public static class ImageTableField extends Table{
		private Image image;
		private TextButton btn;
		
		public ImageTableField(Skin skin, Vector2 size){
			setSize(size.x, size.y);
		}
		
		public void addLabel(Label label, float width, float rowHeight){
			add(label).align(Align.left)
				.width(width).height(rowHeight);
		}
		
		public void addImage(Image image, float width){
			this.image = image;
			add(image).align(Align.left)
				.width(width)
				.height(width);
		}
		
		public void addButton(Button btn, float width, float height){
			add(btn).align(Align.left)
				.width(width).height(height);
		}
		
		public void setImage(Drawable drawable){
			image.setDrawable(drawable);
		}
		
		public Button getButton(){
			return btn;
		}
	}
	
	public static class NodeElement {
		private NodeType type;
		private String name;
		private Object object;
		private ArrayList<NodeElement> childs;
		
		
		public NodeElement(NodeType type, String name, ArrayList<NodeElement> childs){
			this.type = type;
			this.name = name;
			this.childs = childs;
		}
		
		public NodeElement(NodeType type, String name, Object object,
				ArrayList<NodeElement> childs) {
			super();
			this.type = type;
			this.name = name;
			this.object = object;
			this.childs = childs;
		}

		public NodeType getType() {
			return type;
		}

		public void setType(NodeType type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ArrayList<NodeElement> getChilds() {
			return childs;
		}

		public void setChilds(ArrayList<NodeElement> childs) {
			this.childs = childs;
		}
		
		public void addChild(NodeElement node){
			childs.add(node);
		}
		
		public void removeChild(NodeElement node){
			childs.remove(node);
		}
		
		public Object getObject() {
			return object;
		}

		public void setObject(Object object) {
			this.object = object;
		}

		@Override
		public String toString(){
			return type + " : " + name; 
		}
		
		public void retrive(int numtab){
			for (int i = 0; i < numtab; i++) System.out.print("\t");
			System.out.println(toString());
			if (childs != null){
				for (NodeElement child : childs) {
					child.retrive(numtab + 1);
				}
			}
		}
	}
}
