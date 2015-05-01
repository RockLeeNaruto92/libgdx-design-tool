package hust.libgdx.tool.views;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.utilities.LObject;
import hust.libgdx.tool.views.renderers.HomeRenderer;

import java.util.ArrayList;

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
		if (character == '='){
			controller.zoom(true);
		}
		else if (character == '-'){
			controller.zoom(false);
		}
		
		return super.keyTyped(character);
	}
	
	public void showObjectCreationDialog(ArrayList<LObject> objects){
		render.showObjectCreationDialog(objects);
	}
	
	public void hideObjectCreationDialog(){
		render.hideObjectCreationDialog();
	}
}
