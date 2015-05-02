package hust.libgdx.tool.models.uielements;

import hust.libgdx.tool.constants.Constant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class LAnimation extends Actor{
	private Animation anim;
	private float stateTime = 0f;
	private Array<TextureRegion> keyFrames;
	
	public LAnimation(Skin skin){
		createDefaultAnimation(skin);
		setBounds(0, 0, anim.getKeyFrame(0).getRegionWidth(), anim.getKeyFrame(0).getRegionHeight());
	}
	
	private void createDefaultAnimation(Skin skin){
		keyFrames = new Array<TextureRegion>();
		
		for (int i = 1; i < Constant.DEFAULT_ANIMATION_FRAMES_NUM + 1; i++)
			keyFrames.add(skin.getRegion("anim-" + i));

		anim = new Animation(Constant.DEFAULT_ANIMATION_FRAME_DURATION, keyFrames);
	}
	
	public Animation getAnim() {
		return anim;
	}

	public void setAnim(Animation anim) {
		this.anim = anim;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		stateTime += Gdx.graphics.getDeltaTime();
		batch.draw(anim.getKeyFrame(stateTime), getX(), getY(), getWidth(), getHeight());
	}
	
	public PlayMode getPlayMode(){
		return anim.getPlayMode();
	}
	
	public void setPlayMode(PlayMode playMode){
		anim.setPlayMode(playMode);
	}

	public float getTimePerFrame() {
		return anim.getFrameDuration();
	}
	
	public void setTimePerFrame(float time){
		anim.setFrameDuration(time);
	}
}
