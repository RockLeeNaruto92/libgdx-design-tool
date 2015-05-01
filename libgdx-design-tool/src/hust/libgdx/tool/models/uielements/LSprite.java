package hust.libgdx.tool.models.uielements;

import hust.libgdx.tool.views.renderers.HomeRenderer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LSprite extends Actor {
	private Sprite sprite;
	
	public LSprite(Sprite sprite) {
		super();
		
		this.sprite = sprite;
		setSize(sprite.getWidth(), sprite.getHeight());
	}
	
	public LSprite(){
		super();
		
		sprite = new Sprite(HomeRenderer.DEFAULT_SPRITE_TEXTURE);
		setSize(sprite.getWidth(), sprite.getHeight());
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
	}
}
