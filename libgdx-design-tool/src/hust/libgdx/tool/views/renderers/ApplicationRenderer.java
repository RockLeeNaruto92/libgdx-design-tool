package hust.libgdx.tool.views.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class ApplicationRenderer {
	public SpriteBatch batch = new SpriteBatch();
	public ShapeRenderer shape = new ShapeRenderer();
	
	public abstract void render();
}
