package hust.libgdx.tool.utilities;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.views.renderers.CustomTree;

import java.util.ArrayList;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
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
	
	public static NodeElement getListFile(String path, String name, NodeElement parent){
		FileHandle dir;
		
		if (Gdx.app.getType() == ApplicationType.Android)
			dir = Gdx.files.internal(path);
		else
			dir = Gdx.files.absolute(path);
		
		for (FileHandle file : dir.list()) {
			if (!file.isDirectory())
				parent.childs.add(new NodeElement(NodeType.FILE, file.name(), null));
			else {
				NodeElement folderNode = new NodeElement(NodeType.FOLDER, file.name(), new ArrayList<Utility.NodeElement>());
				parent.childs.add(getListFile(path + "/" + file.name(), file.name(), folderNode));
			}
		}
		
		return parent;
	}
	
	public static Tree createTreeFromArrayList(NodeElement root, Skin skin, UIElementType[] types){
		Tree tree = new Tree(skin);
//		int index = 0;
//		// create root node
//		Node rootNode = new Node(createTreeNodeLabel(root.name, skin, null));
//		tree.add(rootNode);
//		
//		// create childs node
//		for (NodeElement element : root.childs) {
////			createNodeOfTree(element, skin, rootNode, );
//		}
		
		
		return tree;
	}
	
	
	
	public static TextField createTextFieldWithSlider(Table parent, Vector2 parentSize, String[] labels, float[] widths, float sliderInfo[], Skin skin){
		int i;
		
		parent.row();
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			parent.add(label).align(Align.topLeft).width(widths[i] * parentSize.x);
		}
		
		final TextField textfield = new TextField("", skin);
		parent.add(textfield).align(Align.topLeft).width(widths[i++] * parentSize.x);
		
		float sliderWidth = 0;
		while (i < widths.length) sliderWidth += widths[i++] * parentSize.x;

		final Slider slider = new Slider(sliderInfo[0], sliderInfo[1], sliderInfo[2], false, skin);
		slider.getStyle().knob.setMinHeight(parentSize.y * Constant.PROPERTY_ROW_HEIGHT);
		slider.getStyle().knob.setMinWidth(parentSize.x * Constant.SLIDER_KNOB_WIDTH);
		parent.add(slider).align(Align.topLeft).width(sliderWidth).height(parentSize.y * Constant.PROPERTY_ROW_HEIGHT);
		
		slider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				textfield.setText(slider.getValue() + "");
				// set object value
				
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
	
	public static TextField createTextFieldWithOutSlider(Table parent, Vector2 parentSize, String[] labels, float[] widths, Skin skin){
		int i;
		
		parent.row();
		for (i = 0; i < labels.length; i++){
			Label label = new Label(labels[i], skin);
			label.setFontScale(Constant.FONT_SCALE);
			
			parent.add(label).align(Align.topLeft).width(widths[i] * parentSize.x);
		}
		
		final TextField textfield = new TextField("", skin);
		parent.add(textfield).align(Align.topLeft).width(widths[i++] * parentSize.x);
		
		textfield.addListener(new InputListener(){

			@Override
			public boolean keyTyped(InputEvent event, char character) {
				System.out.println(textfield.getText());
				return super.keyTyped(event, character);
			}
			
		});
		
		return textfield;
	}
	
//	public static Button createCheckbox(Table parent, Vector2 parentSize, String[] labels, float[] widths, Skin skin){
//		int i;
//		
//		parent.row();
//		for (i = 0; i < labels.length; i++){
//			Label label = new Label(labels[i], skin);
//			label.setFontScale(Constant.FONT_SCALE);
//			
//			parent.add(label).align(Align.topLeft).width(widths[i] * parentSize.x);
//		}
//		
//		
//	}
	
	public static class NodeElement {
		NodeType type;
		String name;
		
		ArrayList<NodeElement> childs;
		
		public NodeElement(NodeType type, String name, ArrayList<NodeElement> childs){
			this.type = type;
			this.name = name;
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
