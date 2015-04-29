package hust.libgdx.tool;

import hust.libgdx.tool.utilities.LObject;
import hust.libgdx.tool.views.renderers.dialogs.ObjectCreationDialog;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ListDialogTest implements ApplicationListener {
	private Stage stage;
	SpriteBatch batch;
	ObjectCreationDialog dialog;

	public void create () {
		stage = new Stage();
		System.out.println(stage.getCamera().position);
		
		Skin skin = new Skin(Gdx.files.internal("data/skin.json"));
		Gdx.input.setInputProcessor(stage);
		
//		dialog = new MessageDialog("", skin);
		
		dialog = new ObjectCreationDialog("", skin, "default");
		ArrayList<LObject> objects = new ArrayList<>();
		
		for (int i = 0; i < 10; i++){
			LObject object = new LObject();
			object.setName("Lobject " + i);
			objects.add(object);
		}
		dialog.reset();
		
		dialog.setListItems(objects);
		
		stage.addActor(dialog);
		
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