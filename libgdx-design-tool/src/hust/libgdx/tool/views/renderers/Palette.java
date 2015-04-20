package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.NodeElement;
import hust.libgdx.tool.utilities.Utility.NodeType;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class Palette {
	private TextButton paletteTab;
	
	private Table table, container;
	
	private ArrayList<NodeElement> roots;
	private static String[][] UI_ELEMENT_LIST = {
		{"Basic elements", "Checkbox", "Label", "Slider"},
		{"Structured elements", "Window", "Scroll Pane"},
		{"Static images", "Image", "Sprite"},
		{"Animation", "Animation"}
	};
	
	public Palette(Stage stage, Skin skin, Vector2 location, Vector2 size) {
		init(stage, skin, location, size);
	}
	
	private void init(Stage stage, Skin skin, Vector2 location, Vector2 size){
		readUiElementList();
		
		container = new Table();
		container = new Table();
		container.setX(location.x);
		container.setY(location.y);
		container.setWidth(size.x);
		container.setHeight(size.y);
		stage.addActor(container);
		
		table = new Table();
		
		ScrollPane scroll = new ScrollPane(table, skin);
		table.pad(10).defaults().expandX().space(4);
		
		table.row();
		Tree tree = Utility.createTreeFromArrayList(roots, skin);
		table.add(tree).align(Align.topLeft);
		
		table.align(Align.topLeft);
		
		container.add(scroll).expand().fill();
		container.row().space(10).padBottom(10);
		container.align(Align.topLeft);
		container.debugAll();
	}
	
	private void readUiElementList(){
		roots = new ArrayList<NodeElement>();
		
		for (int i = 0; i < UI_ELEMENT_LIST.length; i++){
			String[] group = UI_ELEMENT_LIST[i];
			
			NodeElement root = new NodeElement(NodeType.UIELEMENT_GROUP, group[0], new ArrayList<NodeElement>());
			roots.add(root);
			
			for (int j = 1; j < group.length; j++){
				NodeElement childNode = new NodeElement(NodeType.UIELEMENT, group[j], null);
				root.getChilds().add(childNode);
			}
		}
		
		for (NodeElement node : roots) {
			node.retrive(0);
		}
	}
}
