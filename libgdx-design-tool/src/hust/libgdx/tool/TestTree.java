package hust.libgdx.tool;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
		table.setFillParent(true);
		stage.addActor(table);
		table.setX(100);
		table.setY(-200);

		final Tree tree = new Tree(skin);
		final Node moo1 = new Node(new Label("moo1", skin));
		final Node moo2 = new Node(new Label("moo2", skin));
		final Node moo3 = new Node(new Label("moo3", skin));
		final Node moo4 = new Node(new Label("moo4", skin));
		final Node moo5 = new Node(new Label("moo5", skin));
		
		
		tree.add(moo1);
		moo1.add(moo5);
		tree.debugAll();
//		tree.add(moo2);
//		moo2.add(moo3);
//		moo3.add(moo4);
//		tree.add(moo5);

		moo5.getActor().addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				tree.remove(moo4);
			}
		});

		table.add(tree).fill().expand();
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
