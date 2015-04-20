package hust.libgdx.tool;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TestTree implements ApplicationListener{
	Stage stage;
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Skin skin = new Skin(Gdx.files.internal("data/skin.json"));

		Table table = new Table();
//		table.setFillParent(true);
		stage.addActor(table);
		table.setWidth(400);
		table.setHeight(400);
		

		final Tree tree = new Tree(skin);
		final Node moo1 = new Node(new Label("this is moo1 node of tree this is node node node node node node node", skin));
		final Node moo2 = new Node(new Label("moo2", skin));
		final Node moo3 = new Node(new Label("moo3", skin));
		final Node moo4 = new Node(new Label("moo4", skin));
		final Node moo5 = new Node(new Label("moo5", skin));
		final Node moo6 = new Node(new Label("moo6", skin));
		final Node moo7 = new Node(new Label("moo7", skin));
		final Node moo8 = new Node(new Label("moo8", skin));
		
//		table.debugAll();
		
		tree.add(moo1);
		moo1.add(moo5);
		tree.add(moo2);
		moo2.add(moo3);
		moo3.add(moo4);
		tree.add(moo5);
		tree.add(moo6);
		tree.add(moo7);
		tree.add(moo8);
		tree.debugAll();

		moo5.getActor().addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				tree.remove(moo4);
			}
		});

//		table.add(tree).fill().expand();
		ScrollPane scrollPane = new ScrollPane(tree, skin);
	}

	public void render () {
		// System.out.println(meow.getValue());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void dispose () {
		stage.dispose();
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
