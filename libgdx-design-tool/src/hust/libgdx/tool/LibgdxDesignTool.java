package hust.libgdx.tool;

import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.NodeElement;
import hust.libgdx.tool.utilities.Utility.NodeType;
import hust.libgdx.tool.views.renderers.HomeRenderer;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LibgdxDesignTool implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	Stage stage;
	HomeRenderer render;
	
	@Override
	public void create() {
		stage = new Stage();
		render = new HomeRenderer();
		
		Gdx.input.setInputProcessor(stage);
		
		FileHandle file = Gdx.files.absolute("D:\\Example");
		NodeElement root;
		if (file.isDirectory()){
			root = new NodeElement(NodeType.FOLDER, file.name(), new ArrayList<Utility.NodeElement>());
		}else {
			root = new NodeElement(NodeType.FILE, file.name(), null);
		}
		
		
		ArrayList<NodeElement> list = Utility.getListFile("D:\\Example", "Example", root).getChilds();
		root.retrive(0);
		
		// Example of table
		Table table = new Table();
		ScrollPane scroll = new ScrollPane(table);
		scroll.setPosition(200, 200);
		
		stage.addActor(table);
		table.setX(100);
		table.setY(200);
		System.out.println(table.getX() + "-" + table.getY());
		
		Skin skin = new Skin(Gdx.files.internal("data/skin.json"));
		Tree tree = Utility.createTreeFromArrayList(root, skin);
		
		table.add(tree).fill();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		render.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

