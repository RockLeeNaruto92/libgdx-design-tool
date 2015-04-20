package hust.libgdx.tool;
import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.NodeElement;
import hust.libgdx.tool.utilities.Utility.NodeType;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ScrollPaneTest implements ApplicationListener {
	private Stage stage;
	private Table container;

	public void create () {
		stage = new Stage();
		Skin skin = new Skin(Gdx.files.internal("data/skin.json"));
		Gdx.input.setInputProcessor(stage);

		// Gdx.graphics.setVSync(false);

		container = new Table();
		stage.addActor(container);
		container.setX(200);
		container.setY(200);
		
		container.setWidth(100);
		container.setHeight(100);

		Table table = new Table();
		// table.debug();

		final ScrollPane scroll = new ScrollPane(table, skin);

		InputListener stopTouchDown = new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				event.stop();
				return false;
			}
		};

		table.pad(10).defaults().expandX().space(4);		
		FileHandle file = Gdx.files.absolute("D:\\Example");
		NodeElement root;
		
		if (file.isDirectory()){
			root = new NodeElement(NodeType.FOLDER, file.name(), new ArrayList<Utility.NodeElement>());
		}else {
			root = new NodeElement(NodeType.FILE, file.name(), null);
		}
		
		root = Utility.getListFile("D:\\Example", "Example", root);
		root.retrive(0);
		for (int i = 0; i < 100; i++){
			table.row();
			Tree tree = Utility.createTreeFromArrayList(root, skin);
			table.add(tree);
		}
		table.debugAll();

		container.add(scroll).expand().fill().colspan(4);
		container.row().space(10).padBottom(10);
	}

	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);

		// Gdx.gl.glViewport(100, 100, width - 200, height - 200);
		// stage.setViewport(800, 600, false, 100, 100, width - 200, height - 200);
	}

	public void dispose () {
		stage.dispose();
	}

	public boolean needsGL20 () {
		return false;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}