package hust.libgdx.tool.models.customs;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class BorderWindow extends Window{
	private Sprite border, top;
	
	public BorderWindow(String title, Skin skin, String styleName) {
		super(title, skin, styleName);
		
		border = new Sprite(new Texture(Gdx.files.internal("data/border.png")));
		top = new Sprite(new Texture(Gdx.files.internal("data/window-top.png")));
		
		// set bound
		float width = Utility.getActualValue(Constant.DIALOG_WIDTH, true);
		float height = Utility.getActualValue(Constant.DIALOG_HEIGHT, false);
		float x = (Constant.SCREEN_SIZE.x - width) / 2;
		float y = (Constant.SCREEN_SIZE.y - height) / 2;
		setBounds(x, y, width, height);

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		// draw top bound
		if (top == null) return;
		batch.draw(top, getX(), getY() + getHeight() - getPadTop(), getWidth(), getPadTop());
		
		// draw border
		if (border == null) return;
		batch.draw(border, getX(), getY(), Constant.BORDER_WIDTH, getHeight());
		batch.draw(border, getX(), getY(), getWidth(), Constant.BORDER_WIDTH);
		batch.draw(border, getX(), getY() + getHeight() - Constant.BORDER_WIDTH, getWidth(), Constant.BORDER_WIDTH);
		batch.draw(border, getX() + getWidth() - Constant.BORDER_WIDTH, getY(), Constant.BORDER_WIDTH, getHeight());
	}
}
