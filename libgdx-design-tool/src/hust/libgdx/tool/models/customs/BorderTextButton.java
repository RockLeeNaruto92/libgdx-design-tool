package hust.libgdx.tool.models.customs;

import hust.libgdx.tool.constants.Constant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class BorderTextButton extends TextButton{
	private Sprite border;

	public BorderTextButton(String text, Skin skin) {
		super(text, skin);
		init();
	}
	
	public BorderTextButton(String text, Skin skin, String styleName){
		super(text, skin, styleName);
		init();
	}
	
	private void init(){
		border = new Sprite(new Texture(Gdx.files.internal("data/border.png")));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if (border == null) return;
		batch.draw(border, getX(), getY(), Constant.BORDER_WIDTH, getHeight());
		batch.draw(border, getX(), getY(), getWidth(), Constant.BORDER_WIDTH);
		batch.draw(border, getX(), getY() + getHeight() - Constant.BORDER_WIDTH, getWidth(), Constant.BORDER_WIDTH);
		batch.draw(border, getX() + getWidth() - Constant.BORDER_WIDTH, getY(), Constant.BORDER_WIDTH, getHeight());		
	}
}
