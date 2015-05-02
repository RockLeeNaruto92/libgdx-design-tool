package hust.libgdx.tool.views;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.views.renderers.HomeRenderer;

public class HomeScreen extends ApplicationScreen{
	private UIElementController controller;
	private HomeRenderer render;

	public HomeScreen() {
		super();
		
		controller = new UIElementController();
		controller.setScreen(this);
		setController(controller);
		
		render = new HomeRenderer(controller);
		addListener();
	}
	
	private void addListener(){
		addProcessor(render.getStage());
		addProcessor(render.getEditorStage());
	}
	
	public HomeRenderer getRender(){
		return render;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		float y = Constant.SCREEN_SIZE.y - screenY;
		controller.onTouchUp(screenX, y);
		
		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float y = Constant.SCREEN_SIZE.y - screenY;
		controller.onTouchMove(screenX, y);
		
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public void render(float delta) {
		render.render();
		
		super.render(delta);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		controller.onTouchDown(screenX, Constant.SCREEN_SIZE.y - screenY);
		
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean keyTyped(char character) {
		switch ((int)character) {
		case '=':
			controller.zoom(false);
			break;
		case '-':
			controller.zoom(true);
			break;
		case 127: // delete
			controller.deleteSelectedActors();
			break;
		default:
			break;
		}
		
		return super.keyTyped(character);
	}

	public void enable(boolean enable) {
		if (enable){
			removeProcessor(render.getStage());
			removeProcessor(render.getEditorStage());
		} else {
			addProcessor(render.getStage());
			addProcessor(render.getEditorStage());
		}
	}
	
}
