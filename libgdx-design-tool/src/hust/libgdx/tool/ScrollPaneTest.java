package hust.libgdx.tool;

import hust.libgdx.tool.constants.Constant;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ScrollPaneTest implements ApplicationListener {
	private Stage stage;
	private Stage stage2;
	private Table container;
	OrthographicCamera camera;
	SpriteBatch batch;

	public void create () {
		stage = new Stage();
		System.out.println(stage.getCamera().position);
		
		Skin skin = new Skin(Gdx.files.internal("data/skin.json"));
		Gdx.input.setInputProcessor(stage);

		// Gdx.graphics.setVSync(false);

		container = new Table();
		stage.addActor(container);
		container.setFillParent(true);

		Table table = new Table();
		// table.debug();

		final ScrollPane scroll = new ScrollPane(table, skin);

//		InputListener stopTouchDown = new InputListener() {
//			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//				event.stop();
//				return false;
//			}
//		};
		
		table.pad(10).defaults().expandX().space(4);
		for (int i = 0; i < 100; i++) {
			table.row();
			table.add(new Label(i + "uno", skin)).expandX().fillX();

			TextButton button = new TextButton(i + "dos", skin);
			table.add(button);
			button.addListener(new ClickListener() {
				public void clicked (InputEvent event, float x, float y) {
					System.out.println("click " + x + ", " + y);
				}
			});

			Slider slider = new Slider(0, 100, 1, false, skin);
//			slider.addListener(stopTouchDown); // Stops touchDown events from propagating to the FlickScrollPane.
			table.add(slider);

			table.add(new Label(i + "tres long0 long1 long2 long3 long4 long5 long6 long7 long8 long9 long10 long11 long12", skin));
		}

		final TextButton flickButton = new TextButton("Flick Scroll", skin.get("toggle", TextButtonStyle.class));
		flickButton.setChecked(true);
		flickButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				scroll.setFlickScroll(flickButton.isChecked());
			}
		});

		final TextButton fadeButton = new TextButton("Fade Scrollbars", skin.get("toggle", TextButtonStyle.class));
		fadeButton.setChecked(true);
		fadeButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				scroll.setFadeScrollBars(fadeButton.isChecked());
			}
		});

		final TextButton smoothButton = new TextButton("Smooth Scrolling", skin.get("toggle", TextButtonStyle.class));
		smoothButton.setChecked(true);
		smoothButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				scroll.setSmoothScrolling(smoothButton.isChecked());
			}
		});

		final TextButton onTopButton = new TextButton("Scrollbars On Top", skin.get("toggle", TextButtonStyle.class));
		onTopButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				scroll.setScrollbarsOnTop(onTopButton.isChecked());
			}
		});

		container.add(scroll).expand().fill().colspan(4);
		container.row().space(10).padBottom(10);
		container.add(flickButton).right().expandX();
		container.add(onTopButton);
		container.add(smoothButton);
		container.add(fadeButton).left().expandX();
		
		stage2 = new Stage();
		stage2.setDebugAll(true);
//		Label label = new Label("Text examoke", skin);
////		label.debug();
//		stage2.addActor(label);
//		OrthographicCamera camera = new OrthographicCamera(0, 0);
//		System.out.println("Position: " + camera.position);
//		System.out.println("camera size: " + camera.viewportWidth + "-" + camera.viewportHeight);
//		
//		camera.translate(camera.viewportWidth/2, camera.viewportHeight/2);
//		stage2.setViewport(new ScreenViewport(camera));
//		stage2.setDebugAll(true);
		
		
		Label label = new Label("Text examoke", skin);
		stage2.addActor(label);
		System.out.println(stage2.getViewport().getWorldWidth() + "-" + stage2.getViewport().getWorldHeight());
		
		Camera camera = stage2.getCamera();
		System.out.println("Position: " + camera.position);
		camera.position.set(0, 0, 0);
	}

	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		stage2.act(Gdx.graphics.getDeltaTime());
		stage2.draw();
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