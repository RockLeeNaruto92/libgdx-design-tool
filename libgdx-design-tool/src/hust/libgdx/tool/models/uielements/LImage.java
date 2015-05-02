package hust.libgdx.tool.models.uielements;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class LImage extends Image{
	private int align = Align.center;
	private Scaling scaling = Scaling.stretch;
	
	public LImage(Skin skin, String img) {
		super(skin, img);
	}

	@Override
	public void setScaling(Scaling scaling) {
		this.scaling = scaling;
		super.setScaling(scaling);
	}
	
	public Scaling getScaling(){
		return scaling;
	}

	@Override
	public void setAlign(int align) {
		this.align = align;
		super.setAlign(align);
	}
	
	public int getAlign(){
		return align;
	}
}
