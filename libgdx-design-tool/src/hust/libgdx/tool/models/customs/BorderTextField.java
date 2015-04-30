package hust.libgdx.tool.models.customs;

import hust.libgdx.tool.constants.Constant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class BorderTextField extends TextField {
	private Sprite border;
	
	public BorderTextField(String text, Skin skin) {
		super(text, skin);
		init(skin, "default");
	}

	public BorderTextField(String text, Skin skin, String styleName) {
		super(text, skin, styleName);
	}
	
	private void init(Skin skin, String styleName){
		border = new Sprite(new Texture(Gdx.files.internal("data/border.png")));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		// draw border
		if (border == null) return;
		batch.draw(border, getX(), getY(), Constant.BORDER_WIDTH, getHeight());
		batch.draw(border, getX(), getY(), getWidth(), Constant.BORDER_WIDTH);
		batch.draw(border, getX(), getY() + getHeight() - Constant.BORDER_WIDTH, getWidth(), Constant.BORDER_WIDTH);
		batch.draw(border, getX() + getWidth() - Constant.BORDER_WIDTH, getY(), Constant.BORDER_WIDTH, getHeight());		
	}
}
