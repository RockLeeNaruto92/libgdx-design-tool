package hust.libgdx.tool.models.uielements;

import hust.libgdx.tool.views.renderers.HomeRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LSprite extends Actor {
	private Sprite sprite;
	private TextureRegionDrawable trd;
	private Color color = Color.WHITE;
	private boolean flipX, flipY;
	private float rotation = 0;
	
	public LSprite(Sprite sprite) {
		super();
		
		this.sprite = sprite;
		setSize(sprite.getWidth(), sprite.getHeight());
		trd = new TextureRegionDrawable(new TextureRegion(sprite.getTexture()));
	}
	
	public LSprite(){
		super();
		
		sprite = new Sprite(HomeRenderer.DEFAULT_SPRITE_TEXTURE);
		setSize(sprite.getWidth(), sprite.getHeight());
	}
	
	public Drawable getDrawble(){
		return trd;
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		trd.setRegion(new TextureRegion(sprite.getTexture()));
		this.sprite = sprite;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isFlipX() {
		return flipX;
	}

	public void setFlipX(boolean flipX) {
		this.flipX = flipX;
	}

	public boolean isFlipY() {
		return flipY;
	}

	public void setFlipY(boolean flipY) {
		this.flipY = flipY;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		float originX = getX() + getWidth() / 2;
		float originY = getY() + getHeight() / 2;
		
		batch.setColor(color);
		batch.draw(sprite, getX(), getY(), originX, originY, getWidth(), getHeight(), getScaleX(), getScaleY(), rotation);
	}
}
