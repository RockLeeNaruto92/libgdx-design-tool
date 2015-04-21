package hust.libgdx.tool;

import hust.libgdx.tool.views.HomeScreen;
import hust.libgdx.tool.views.renderers.HomeRenderer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LibgdxDesignTool implements ApplicationListener {
	Stage stage;
	HomeScreen homeScreen;
	
	@Override
	public void create() {
		stage = new Stage();
		
		
		homeScreen = new HomeScreen();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();

		homeScreen.render(Gdx.graphics.getDeltaTime());
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