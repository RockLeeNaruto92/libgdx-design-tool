package hust.libgdx.tool.views.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ApplicationRenderer {
	public SpriteBatch batch = new SpriteBatch();
	
	public abstract void render();
}
