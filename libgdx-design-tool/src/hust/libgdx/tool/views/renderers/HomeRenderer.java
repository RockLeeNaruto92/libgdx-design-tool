package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.controllers.UIElementController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class HomeRenderer extends ApplicationRenderer{
	private Texture texture = new Texture(Gdx.files.internal("data/black.png"));
	private Sprite sprite = new Sprite(texture);
	private Skin skin;
	
	private Stage mainStage;
	private Menu menu;
	private PackageExplore packageExplore;
	private Editor editor;
	
	private UIElementController controller;
	
	public HomeRenderer(UIElementController controller) {
		super();
		this.controller = controller;
		
		createStages();
		
		loadDatas();
		
		createMenuPart();
		createPalletePart();
		createPropertyPart();
		createOutlinePart();
		createEditorPart();
		
		InputMultiplexer im = new InputMultiplexer(mainStage, editor.getStage());
		Gdx.input.setInputProcessor(im);
	}
	
	private void createStages(){
		mainStage = new Stage();
		mainStage.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				System.out.println("Touch down on main stage " + event.getStageX() + "-" + event.getStageY());
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				System.out.println("Touch up on main stage " + event.getStageX() + "-" + event.getStageY());
				super.touchUp(event, x, y, pointer, button);
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				System.out.println("Touch drag on main stage " + event.getStageX() + "-" + event.getStageY());
				super.touchDragged(event, x, y, pointer);
			}

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("Mouse move in mainstage " + event.getStageX() + "-" + event.getStageY());
				return super.mouseMoved(event, x, y);
			}
			
			
		});
	}
	
	private void loadDatas(){
		skin = new Skin(Gdx.files.internal("data/skin.json"));
	}

	private void createMenuPart() {
		menu = new Menu(mainStage, skin);
	}

	private void createPalletePart() {
		Vector2 location = new Vector2();
		Vector2 size = new Vector2();
		
		location.x = Constant.PALETTE_LOCATION.x * Constant.SCREEN_SIZE.x;
		location.y = Constant.PALETTE_LOCATION.y * Constant.SCREEN_SIZE.y;
		
		size.x = Constant.PALETTE_SIZE.x * Constant.SCREEN_SIZE.x;
		size.y = Constant.PALETTE_SIZE.y * Constant.SCREEN_SIZE.y;
		
//		packageExplore = new PackageExplore("D:\\Example", mainStage, skin, location, size);
		System.out.println("Pallete location: " + location);
		
		new Palette(mainStage, skin, location, size, controller);
	}

	private void createPropertyPart() {
		Vector2 location = new Vector2();
		Vector2 size = new Vector2();
		
		location.x = Constant.PROPERTY_LOCATION.x * Constant.SCREEN_SIZE.x;
		location.y = Constant.PROPERTY_LOCATION.y * Constant.SCREEN_SIZE.y;
		
		size.x = Constant.PROPERTY_SIZE.x * Constant.SCREEN_SIZE.x;
		size.y = Constant.PROPERTY_SIZE.y * Constant.SCREEN_SIZE.y;
		
//		new ActorProperty(stage, skin, location, size);
	}

	private void createOutlinePart() {
	}

	private void createEditorPart() {
		editor = new Editor(skin);
	}

	@Override
	public void render() {
		batch.begin();
		// draw all
		drawMenu();
//		drawPalette();
//		drawProperty();
//		drawPreview();
//		drawOutline();
//		drawDesign();
		// end draw
		batch.end();
		
		mainStage.act(Gdx.graphics.getDeltaTime());
		mainStage.draw();
		
		editor.render();
	}

	private void drawMenu() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.MENU_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.MENU_SIZE.y;
		drawBound.x = 0;
		drawBound.y = Constant.SCREEN_SIZE.y - drawBound.height;

		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
	}

	private void drawPalette() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.PALETTE_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.PALETTE_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.PALETTE_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.PALETTE_LOCATION.y;

		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		
	}

	private void drawProperty() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.PROPERTY_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.PROPERTY_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.PROPERTY_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.PROPERTY_LOCATION.y;

		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
	}

	private void drawPreview() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.PREVIEW_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.PREVIEW_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.PREVIEW_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.PREVIEW_LOCATION.y;

		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
	}

	private void drawOutline() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.OUTLINE_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.OUTLINE_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.OUTLINE_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.OUTLINE_LOCATION.y;

		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		
	}

	private void drawDesign() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.DESIGN_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.DESIGN_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.DESIGN_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.DESIGN_LOCATION.y;

		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		
	}

	public Skin getSkin() {
		return skin;
	}

	public boolean isInEditor(Vector2 currentTouchPos) {
		return editor.contain(currentTouchPos);
	}

	public void addNewActor(Actor newActor, float x, float y) {
		editor.addNewActor(newActor, x, y);
	}

	public void setActorLocation(Actor newActor, float x, float y) {
		editor.setActorLocation(newActor, x, y);
	}
}
