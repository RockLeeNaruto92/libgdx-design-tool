package hust.libgdx.tool.models.customs;

import hust.libgdx.tool.controllers.UIElementController;
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

public abstract class CustomTree{
	private Table table, container;
	private Stage stage;
	private Skin skin;
	private Vector2 location;
	private Vector2 size;
	private ArrayList<NodeElement> roots;
	private Tree tree;
	private UIElementController controller;
	
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
	
	public CustomTree(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		this.stage = stage;
		this.skin = skin;
		this.location = location;
		this.size = size;
		this.controller = controller;
		
		init();
	}
	
	public UIElementController getController(){
		return controller;
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
		
		createTreeNodes();
		
		table = new Table(skin);
		table.row();
		table.add(tree);
		table.align(Align.topLeft);
	}
	
	protected void createTreeNodes() {
		for (NodeElement root : roots) {
			// create label
			Label nodeLabel = createNodeLabel(root);
			// create root node
			Node rootNode = new Node(nodeLabel);
			rootNode.setObject(root);
			// add node to tree
			tree.add(rootNode);
			// create root node childs
			if (root.getChilds() == null) continue;
			for (NodeElement node : root.getChilds()) 
				createChildNode(node, rootNode);
		}
	}
	
	private void createChildNode(NodeElement object, Node parent){
		// create child node label
		Label label = createNodeLabel(object);
		Node node = new Node(label);
		node.setObject(object);
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
	
	public void setRoots(ArrayList<NodeElement> roots){
		this.roots = roots;
	}
	
	public Node getNode(NodeElement node){
		return tree.findNode(node);
	}
	
	public NodeElement getNodeElement(Object object){
		for (NodeElement node : roots) {
			NodeElement result = getNodeElement(node, object);
			
			if (result != null) return result;
		}
		
		return null;
	}
	
	public NodeElement getNodeElement(NodeElement node, Object object){
		if (node.getObject() == object) return node;
		
		for (NodeElement child : node.getChilds()) {
			NodeElement result = getNodeElement(child, object);
			
			if (result != null) return result;
		}
		
		return null;
	}
	
	public void removeAllNode(){
		for (Node node : tree.getRootNodes()){
			node.removeAll();
			tree.remove(node);
		}
	}
}
