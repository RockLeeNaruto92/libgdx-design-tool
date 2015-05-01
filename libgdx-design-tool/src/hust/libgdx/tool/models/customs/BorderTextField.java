package hust.libgdx.tool.models.customs;

import hust.libgdx.tool.constants.Constant;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class BorderTextField extends TextField{
	private TextureRegion border;

	public BorderTextField(String text, final Skin skin) {
		super(text, skin);
		
		border = skin.getRegion("border-normal");
	}
	
	

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	
		batch.draw(border, getX(), getY(), Constant.TEXTFIELD_PAD, getHeight());
		batch.draw(border, getX(), getY(), getWidth(), Constant.TEXTFIELD_PAD);
		batch.draw(border, getX() + getWidth() - Constant.TEXTFIELD_PAD, getY(), Constant.TEXTFIELD_PAD, getHeight());
		batch.draw(border, getX(), getY() + getHeight() - Constant.TEXTFIELD_PAD, getWidth(), Constant.TEXTFIELD_PAD);
	}
}
