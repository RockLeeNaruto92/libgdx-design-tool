package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Editor implements InputProcessor {
	private Stage stage;
	private Skin skin;
	private Rectangle bound;
	
	public Editor(Skin skin){
		this.skin = skin;
		stage = new Stage();
		
		bound = new Rectangle();
		bound.x = Utility.getActualValue(Constant.DESIGN_LOCATION.x, true);
		bound.y = Utility.getActualValue(Constant.DESIGN_LOCATION.y, false);
		bound.width = Utility.getActualValue(Constant.DESIGN_SIZE.x, true);
		bound.height = Utility.getActualValue(Constant.DESIGN_SIZE.y, false);
	}
	
	public Stage getStage(){
		return stage;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public void render(){
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public boolean contain(Vector2 currentTouchPos) {
		return bound.contains(currentTouchPos);
	}

	public void addNewActor(Actor newActor, float x, float y) {
		// set location for new Actor 
		setActorLocation(newActor, x, y);
		
		stage.addActor(newActor);
	}

	public void setActorLocation(Actor newActor, float x, float y) {
		newActor.setX(x + bound.x);
		newActor.setY(y + bound.y);
	}
}
