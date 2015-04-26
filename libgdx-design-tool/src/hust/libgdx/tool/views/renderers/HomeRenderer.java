package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.controllers.UIElementController;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
	
	private boolean displayBound;
	
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
	}
	
	private void createStages(){
		mainStage = new Stage();
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
		drawPalette();
		drawProperty();
		drawPreview();
		drawOutline();
//		drawDesign();
		
		// end draw
		batch.end();
		
		mainStage.act(Gdx.graphics.getDeltaTime());
		mainStage.draw();
		
		// draw bound of selected elector
		if (displayBound) drawBound(controller.getSelectedBound(false));
		
		editor.render();
	}
	
	private void drawBound(Rectangle bound){
		shape.setAutoShapeType(true);
		shape.begin();
		shape.setColor(Color.RED);
		shape.rect(bound.x, bound.y, bound.width, bound.height);
		
//		System.out.println("Draw rect " + bound);
		
		shape.end();
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
	
	public Stage getStage() {
		return mainStage;
	}
	
	public Stage getEditorStage(){
		return editor.getStage();
	}

	public void addNewActor(Actor newActor, float x, float y) {
		editor.addNewActor(newActor, x, y);
	}
	
	public void removeActor(Actor actor){
		editor.removeActor(actor);
	}
	
	public void removeActors(ArrayList<Actor> actors) {
		for (Actor actor : actors) {
			editor.removeActor(actor);
		}
	}
	
	public boolean isContainActor(Actor actor){
		return editor.contain(actor);
	}
	
	public boolean isContainActors(ArrayList<Actor> actors){
		for (Actor actor : actors) {
			if (!isContainActor(actor))
				return false;
		}
		
		return true;
	}

	public void setActorLocation(Actor newActor, float x, float y) {
		editor.setActorLocation(newActor, x, y);
	}
	
	public void setActorsLocation(ArrayList<Actor> actors, Vector2 distance){
		for (Actor actor : actors) {
			actor.setX(actor.getX() + distance.x);
			actor.setY(actor.getY() + distance.y);
		}
	}

	public boolean isDisplayBound() {
		return displayBound;
	}

	public void setDisplayBound(boolean displayBound) {
		this.displayBound = displayBound;
	}
	
	public Vector2 getRelativePointWithEditor(float screenX, float screenY){
		return editor.getStagePoint(screenX, screenY);
	}
}
