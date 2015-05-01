package hust.libgdx.tool.models.uielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class LLabel extends Label {
	private int align;

	public LLabel(CharSequence text, Skin skin) {
		super(text, skin);
		
		align = Align.left;
	}

	@Override
	public void setAlignment(int alignment) {
		align = alignment;
		super.setAlignment(alignment);
	}
	
	public int getAlign(){
		return align;
	}
}
