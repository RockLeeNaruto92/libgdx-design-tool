package hust.libgdx.tool.utilities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LObject extends Actor{
	private ArrayList<Animation> animations;
	private ArrayList<String> states;
	private Animation currentAnimation;
	private String currentState;
	private float stateTime = 0;

	public LObject() {
		animations = new ArrayList<Animation>();
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
	}
}
