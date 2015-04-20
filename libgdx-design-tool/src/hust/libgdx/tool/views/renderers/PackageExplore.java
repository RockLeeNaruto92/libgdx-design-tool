package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.NodeElement;
import hust.libgdx.tool.utilities.Utility.NodeType;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class PackageExplore {
	private boolean visible = false;
	private TextButton btnPackageExplore;
	
	private String folderPath;
	private Table table, container;
	
	public PackageExplore(String folderPath, Stage stage, Skin skin, Vector2 location, Vector2 size){
		this.folderPath = folderPath;
		
		init(stage, skin, location, size);
	}
	
	private void init(Stage stage, Skin skin, Vector2 location, Vector2 size){
		NodeElement root = createRootNodeElement();
		
		btnPackageExplore = new TextButton(Word.PALETTE, skin);
		
		container = new Table();
		container.setX(location.x);
		container.setY(location.y);
		container.setWidth(size.x);
		container.setHeight(size.y);
		stage.addActor(container);
		
		container.add(btnPackageExplore);
		container.row();
		
		table = new Table();
		
		ScrollPane scroll = new ScrollPane(table, skin);
		table.pad(10).defaults().expandX().space(4);
		
		table.row();
		Tree tree = Utility.createTreeFromArrayList(root, skin);
		table.add(tree).align(Align.topLeft);
		
		table.align(Align.topLeft);
		
		container.add(scroll).expand().fill();
		container.row().space(10).padBottom(10);
		container.align(Align.topLeft);
		container.debugAll();
		
		container.setVisible(false);
	}
	
	private NodeElement createRootNodeElement(){
		System.out.println(folderPath);
		FileHandle file = Gdx.files.absolute(folderPath);
		NodeElement root;
		if (file.isDirectory()){
			root = new NodeElement(NodeType.FOLDER, file.name(), new ArrayList<Utility.NodeElement>());
		}else {
			root = new NodeElement(NodeType.FILE, file.name(), null);
		}
		
		root = Utility.getListFile(folderPath, "Example", root);
		root.retrive(0);
		
		return root;
	}
}
