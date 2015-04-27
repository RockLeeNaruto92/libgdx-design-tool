package hust.libgdx.tool.utilities;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.views.renderers.properties.ActorProperty;
import hust.libgdx.tool.views.renderers.properties.ActorPropertyType;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

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
	
	public static CheckBox createSubMenuCheckbox(String text, Skin skin){
		CheckBox checkbox = new CheckBox(text, skin);
		checkbox.getLabel().setFontScale(Constant.FONT_SCALE);
		checkbox.getCells().get(0)
			.size(Constant.VERTICAL_GROUP_IMAGE_WIDTH, Constant.VERTICAL_GROUP_IMAGE_HEIGHT)
			.padRight(Constant.VERTICAL_GROUP_PAD_RIGHT);
		
		return checkbox;
	}
	
	public static TextField createTextFieldWithSlider(Table parent,
			Vector2 parentSize, String[] labels, float[] widths,
			float sliderInfo[], Skin skin, final ActorProperty property,
			final UIElementController controller, final ActorPropertyType type) {
		int i;
		
		parent.row();
		
		// create all label and add to parent table
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			parent.add(label).align(Align.left)
					.width(widths[i] * parentSize.x)
					.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
					.pad(Constant.PROPERTY_CELL_PAD);
		}
		
		// create text field
		final TextField textfield = new TextField(Word.NULL, skin);
		parent.add(textfield).align(Align.left)
				.width(widths[i++] * parentSize.x)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD);
		
		
		// create slider
		float sliderWidth = 0;
		while (i < widths.length) sliderWidth += widths[i++] * parentSize.x;
		
		final Slider slider = new Slider(sliderInfo[0], sliderInfo[1], sliderInfo[2], false, skin);
		slider.getStyle().knob.setMinHeight(parentSize.y * Constant.PROPERTY_ROW_HEIGHT);
		slider.getStyle().knob.setMinWidth(parentSize.x * Constant.SLIDER_KNOB_WIDTH);
		parent.add(slider).align(Align.left)
				.width(sliderWidth)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD);
		
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
			final ActorPropertyType type) {
		int i;
		
		parent.row();
		
		// create all label and add to parent table
		for (i = 0; i < labels.length; i++) {
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);

			parent.add(label).align(Align.left)
					.width(widths[i] * parentSize.x)
					.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
					.pad(Constant.PROPERTY_CELL_PAD);
		}

		// create text field
		final TextField textfield = new TextField(Word.NULL, skin);
		parent.add(textfield).align(Align.left)
				.width(widths[i++] * parentSize.x)
				.height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT)
				.pad(Constant.PROPERTY_CELL_PAD)
				.colspan(Constant.PROPERTY_COLUMNS - labels.length);
		
		// add listener to text field, if textfield change value -> check validation and set value for object
		textfield.addListener(new InputListener(){
			@Override
			public boolean keyTyped(InputEvent event, char character) {
				
				return super.keyTyped(event, character);
			}
			
		});
		
		return textfield;
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
