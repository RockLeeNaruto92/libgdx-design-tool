package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class HomeRenderer extends ApplicationRenderer {
	private Texture texture = new Texture(Gdx.files.internal("data/black.png"));
	private Sprite sprite = new Sprite(texture);
	private Skin skin;
	
	private Stage stage;
	private TextButton fileMenu, windowsMenu;
	private TextButton packageTab, paletteTab;
	private TextButton designTab, sourceTab;
	private TextButton property, outline, preview;
	private HorizontalGroup menuGroup;
	
	public HomeRenderer() {
		super();
		
		stage = new Stage();
		
		loadDatas();
		
		createMenuPart();
		createPalletePart();
		createPropertyPart();
		createOutlinePart();
		createEditorPart();
	}
	
	private void loadDatas(){
		skin = new Skin(Gdx.files.internal("data/skin.json"));
	}

	private void createMenuPart() {
		menuGroup = new HorizontalGroup();
		
		fileMenu = new TextButton(Word.FILE, skin);
		fileMenu.getLabel().setFontScale(0.7f);
		
		menuGroup.addActor(fileMenu);
		
		windowsMenu = new TextButton(Word.WINDOWS, skin);
		windowsMenu.getLabel().setFontScale(0.7f);
		menuGroup.addActor(windowsMenu);
		
		menuGroup.padLeft(10);
		menuGroup.padTop(0);
		menuGroup.space(20f);
		menuGroup.setDebug(true);
		
		Rectangle bound = getActualBound(Constant.MENU_LOCATION, Constant.MENU_SIZE);
		menuGroup.setX(bound.x);
		menuGroup.setY(bound.y);
		
		stage.addActor(menuGroup);
	}

	private void createPalletePart() {
	}

	private void createPropertyPart() {
	}

	private void createOutlinePart() {
	}

	private void createEditorPart() {
		// TODO Auto-generated method stub
		
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
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
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
	
	private Rectangle getActualBound(Vector2 location, Vector2 size){
		Rectangle bound = new Rectangle();
		
		bound.width = Constant.SCREEN_SIZE.x * size.x;
		bound.height = Constant.SCREEN_SIZE.y * size.y;
		bound.x = Constant.SCREEN_SIZE.x * location.x;
		bound.y = Constant.SCREEN_SIZE.y * location.y;
		
		return bound;
	}
}
