package hust.libgdx.tool.models.uielements;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class LLabel extends Label {
	private int align;
	private boolean wrap, ellipsis;

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

	@Override
	public void setWrap(boolean wrap) {
		this.wrap = wrap;
		super.setWrap(wrap);
	}
	
	public boolean isWrap(){
		return wrap;
	}

	@Override
	public void setEllipsis(boolean ellipsis) {
		this.ellipsis = ellipsis;
		super.setEllipsis(ellipsis);
	}
	
	public boolean isEllipsis(){
		return ellipsis;
	}
}
