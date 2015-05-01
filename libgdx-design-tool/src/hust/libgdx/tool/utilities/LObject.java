package hust.libgdx.tool.utilities;

import hust.libgdx.tool.constants.Constant;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class LObject extends Actor{
	private ArrayList<Animation> animations;
	private ArrayList<String> states;
	private Animation currentAnimation;
	private String currentState;
	private float stateTime = 0f;

	public LObject(Skin skin) {
		animations = new ArrayList<Animation>();
		
		Array<TextureRegion> defaultFrames = new Array<TextureRegion>();
		
		for (int i = 1; i <= Constant.DEFAULT_ANIMATION_FRAME_NUMS; i++)
			defaultFrames.add(skin.getRegion("anim-" + i));
		setBounds(0, 0, defaultFrames.get(0).getRegionWidth(), defaultFrames.get(0).getRegionHeight());
		
		currentAnimation = new Animation(Constant.DEFAULT_ANIMATION_FRAME_DURATION, defaultFrames);
		currentAnimation.setPlayMode(PlayMode.LOOP);
		animations.add(currentAnimation);
		
		states = new ArrayList<String>();
	}
	
	public void addAnimation(Animation anim){
		animations.add(anim);
	}
	
	public void removeAnimation(Animation anim){
		animations.remove(anim);
	}
	
	public void addState(String state){
		states.add(state);
	}
	
	public void removeState(String state){
		states.add(state);
	}
	
	public boolean hasState(String state){
		for (String cState : states) {
			if (state == cState)
				return true;
		}
		
		return false;
	}
	
	public Animation getCurrentAnimation() {
		return currentAnimation;
	}

	public void setCurrentAnimation(Animation currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		stateTime += Gdx.graphics.getDeltaTime();
		batch.draw(currentAnimation.getKeyFrame(stateTime), getX(), getY(), getWidth(), getHeight());
	}
}
