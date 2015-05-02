package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.models.customs.CustomTree;
import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.NodeElement;
import hust.libgdx.tool.utilities.Utility.NodeType;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PackageExplore extends CustomTree {
	private boolean visible = false;
	private TextButton btnPackageExplore;
	
	private String folderPath;
	private Table table, container;
	
//	public PackageExplore(Stage stage, Skin skin, Vector2 location, Vector2 size){
//		this.folderPath = folderPath;
//	}
	public PackageExplore(Stage stage, Skin skin, Vector2 location, Vector2 size) {
		super(stage, skin, location, size);
	}
	
	private NodeElement createRootNodeElement(){
		FileHandle file = Gdx.files.absolute(folderPath);
		NodeElement root;
		if (file.isDirectory()){
			root = new NodeElement(NodeType.FOLDER, file.name(), new ArrayList<Utility.NodeElement>());
		}else {
			root = new NodeElement(NodeType.FILE, file.name(), null);
		}
		
		root.retrive(0);
		
		return root;
	}

	@Override
	public void onTouchDown(UIElementType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchUp(UIElementType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchDrag() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<NodeElement> readTreeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Label createNodeLabel(NodeElement node) {
		// TODO Auto-generated method stub
		return null;
	}
}
