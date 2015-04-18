package hust.libgdx.tool;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "my-gdx-game";
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		cfg.width = screenSize.width;
		cfg.height = screenSize.height;
		
		System.out.println(screenSize);
		
		new LwjglApplication(new LibgdxDesignTool(), cfg);
	}
}
