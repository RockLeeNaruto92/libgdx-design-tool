package hust.libgdx.tool.models.customs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ColorTextField extends BorderTextField {
	private ShapeRenderer shape;
	private Color color = Color.WHITE;

	public ColorTextField(String text, Skin skin) {
		super(text, skin);
		
		setDisabled(true);
		shape = new ShapeRenderer();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		super.draw(batch, parentAlpha);
	}
}
