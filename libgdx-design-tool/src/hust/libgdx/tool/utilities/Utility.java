package hust.libgdx.tool.utilities;

import java.util.ArrayList;

import hust.libgdx.tool.constants.Constant;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

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
	
	public static Tree createTreeFromArrayList(NodeElement root, Skin skin){
		Tree tree = new Tree(skin);
		// create root node
		Node rootNode = new Node(createTreeNodeLabel(root.name, skin));
		tree.add(rootNode);
		
		// create childs node
		for (NodeElement element : root.childs) {
			createNodeOfTree(element, skin, rootNode);
		}
		
		
		return tree;
	}
	
	private static Label createTreeNodeLabel(String text, Skin skin ){
		Label label = new Label(text, skin);
		label.setFontScale(Constant.FONT_SCALE);
		
		return label;
	}
	
	private static Node createNodeOfTree(NodeElement object, Skin skin, Node parent){
		Node node = new Node(createTreeNodeLabel(object.name, skin));
		
		parent.add(node);
		if (object.childs == null) return node;
		for (NodeElement element : object.childs) {
			createNodeOfTree(element, skin, node);
		}
		
		return node;
	}

	private static Drawable getIcon(NodeType type, Skin skin) {
		switch (type) {
		case FILE:
			break;

		default:
			break;
		}
		return null;
	}
	
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
