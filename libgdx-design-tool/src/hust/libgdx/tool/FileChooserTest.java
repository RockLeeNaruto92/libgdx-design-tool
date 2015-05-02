package hust.libgdx.tool;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class FileChooserTest implements ApplicationListener {
	private Stage stage;
	private Stage stage2;
	private Table container;
	OrthographicCamera camera;
	SpriteBatch batch;

	public void create() {

		Gdx.input.setInputProcessor(new InputAdapter() {
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				System.out.println("Touch down");
				final JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "*.png, *.jpg";
					}
					
					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) return true;
						if (f.getName().endsWith(".png") || f.getName().endsWith(".jpg"))
							return true;
						return false;
					}
				});
				
				int val = fileChooser.showOpenDialog(null);
				if (val == JFileChooser.APPROVE_OPTION){
					System.out.println("ok");
				}else {
					System.out.println("lol");
				}
	
				return true;
			}
		});
	}

	public void render() {
	}

	public void resize(int width, int height) {
	}

	public void dispose() {
	}

	public boolean needsGL20() {
		return false;
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}