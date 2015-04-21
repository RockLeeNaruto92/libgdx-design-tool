package hust.libgdx.tool.views;

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
	}
	
	public HomeRenderer getRender(){
		return render;
	}

	@Override
	public void render(float delta) {
		render.render();
		
		super.render(delta);
	}
}
