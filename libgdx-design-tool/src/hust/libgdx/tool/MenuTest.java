package hust.libgdx.tool;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuTest implements ApplicationListener{
	Stage stage;
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Skin skin = new Skin(Gdx.files.internal("data/skin.json"));

		Table table = new Table(skin);
//		table.setFillParent(true);
		stage.addActor(table);
		table.setWidth(Utility.getActualValue(Constant.MENU_SIZE.x, true));
		table.setHeight(Utility.getActualValue(Constant.MENU_SIZE.y, false));
//		table.setX(Utility.getActualValue(Constant.MENU_LOCATION.x, true));
//		table.setY(Utility.getActualValue(Constant.MENU_LOCATION.y, false));
		table.setX(0);
		table.setY(100);
		
		TextButton button = new TextButton("fdsafdjsalfkhdsjakflhdalsfdsfdsafdsaffdsafdsafdsaffsdfadfs", skin);
		float width = button.getStyle().font.getBounds(button.getText()).width;
		System.out.println(width);
		button.getLabel().setFontScale(0.5f);
		width = button.getStyle().font.getBounds(button.getText()).width * 0.5f;
		System.out.println(width);
		
		table.add(button).left().height(table.getHeight()).width(width);
		
		table.debugAll();
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
