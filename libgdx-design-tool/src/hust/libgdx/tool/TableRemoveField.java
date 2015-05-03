package hust.libgdx.tool;

import hust.libgdx.tool.utilities.Utility.ImageTableField;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class TableRemoveField implements ApplicationListener{
	Stage stage;
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Skin skin = new Skin(Gdx.files.internal("data/skin.json"));

		Table table = new Table();
		
		Image image = new Image(skin.getDrawable("anim-1"));
		Image img2 = new Image(skin.getDrawable("anim-2"));
		
		table.row();
		ImageTableField field = new ImageTableField(skin, new Vector2(100, 100));
		field.addImage(image, 200);
		field.addLabel(new Label("Label ", skin), 100);
		table.add(field).align(Align.left).width(300).height(100);
		
		
		table.row();
		field = new ImageTableField(skin, new Vector2(100, 100));
		field.addLabel(new Label("Label ", skin), 100);
		field.addImage(img2, 200);
		table.add(field).align(Align.left).width(300).height(100);
		table.debugAll();
		
//		field.remove();
//		table.getCells().removeIndex(table.getCells().size - 1);
		
		stage.addActor(table);
		table.setY(400);
		table.setX(200);
		
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
