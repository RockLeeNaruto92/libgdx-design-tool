package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.utilities.Utility.NodeElement;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public abstract class CustomTree {
	private Table table, container;
	private Stage stage;
	private Skin skin;
	private Vector2 location;
	private Vector2 size;
	private ArrayList<NodeElement> roots;
	private Tree tree;
	
	public abstract void onTouchDown(UIElementType type);
	public abstract void onTouchUp(UIElementType type);
	public abstract void onTouchDrag();
	public abstract ArrayList<NodeElement> readTreeInfo();
	public abstract Label createNodeLabel(NodeElement node);
	
	public CustomTree(Stage stage, Skin skin, Vector2 location, Vector2 size){
		this.stage = stage;
		this.skin = skin;
		this.location = location;
		this.size = size;
		
		init();
	}
	
	private void init(){
		roots = readTreeInfo();
		createContainer();
		createTree();
		createScroll();
	}
	
	private void createContainer(){
		container = new Table();
		container.setX(location.x);
		container.setY(location.y);
		container.setWidth(size.x);
		container.setHeight(size.y);
		container.align(Align.topLeft);
		
		stage.addActor(container);
	}
	
	private void createScroll(){
		ScrollPane scrollPane = new ScrollPane(table, skin);
		
		container.add(scrollPane).expand().fill().align(Align.topLeft);
	}
	
	private void createTree(){
		tree = new Tree(skin);
		
		for (NodeElement root : roots) {
			// create label
			Label nodeLabel = createNodeLabel(root);
			// create root node
			Node rootNode = new Node(nodeLabel);
			// add node to tree
			tree.add(rootNode);
			// create root node childs
			for (NodeElement node : root.getChilds()) 
				createChildNode(node, rootNode);
		}

		table = new Table(skin);
		table.row();
		table.add(tree);
		table.align(Align.topLeft);
		
	}
	
	private void createChildNode(NodeElement object, Node parent){
		// create child node label
		Label label = createNodeLabel(object);
		Node node = new Node(label);
		parent.add(node);
		
		if (object.getChilds() == null) return;
		for (NodeElement child : object.getChilds()) {
			createChildNode(child, node);
		}
	}
	
	public Table getTable() {
		return table;
	}
	
	public Table getContainer() {
		return container;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Skin getSkin() {
		return skin;
	}
	
	public Vector2 getLocation() {
		return location;
	}
	
	public Vector2 getSize() {
		return size;
	}
	
	public ArrayList<NodeElement> getRoots() {
		return roots;
	}
//	
//	public Tree createTreeFromArrayList(CustomTree container,
//			ArrayList<NodeElement> roots, Skin skin, UIElementType[] types) {
//		Tree tree = new Tree(skin);

//		for (NodeElement root : roots) {
//			UIElementType type = (root.getChilds() == null) ? types[index++] : null;
//			System.out.println(index);
//
//			Node rootNode = new Node(createTreeNodeLabel(container,
//					root.getName(), skin, type));
//			tree.add(rootNode);
//			// create childs node
//			for (NodeElement element : root.getChilds()) {
//				createNodeOfTree(container, element, skin, rootNode, types);
//			}
//		}
//
//		return tree;
//	}

//	private Label createTreeNodeLabel(final CustomTree tree, String text,
//			Skin skin, final UIElementType type) {
//		Label label = new Label(text, skin);
//		label.setFontScale(Constant.FONT_SCALE);
//
//		if (type == null)
//			return label;
//
//		label.addListener(new InputListener() {
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//				tree.onTouchDown(type);
//				return super.touchDown(event, x, y, pointer, button);
//			}
//
//			@Override
//			public void touchUp(InputEvent event, float x, float y,
//					int pointer, int button) {
//				super.touchUp(event, x, y, pointer, button);
//			}
//
//			@Override
//			public void touchDragged(InputEvent event, float x, float y,
//					int pointer) {
//				super.touchDragged(event, x, y, pointer);
//			}
//		});
//
//		return label;
//	}

//	private Node createNodeOfTree(CustomTree tree, NodeElement object,
//			Skin skin, Node parent, UIElementType[] types) {
////		Node node = new Node(createTreeNodeLabel(tree, object.getName(), skin,
////				types[index]));
////
////		parent.add(node);
////		if (object.getChilds() == null)
////			return node;
////		for (NodeElement element : object.getChilds()) {
////			createNodeOfTree(tree, element, skin, node, types);
////		}
////
////		return node;
//	}
}
