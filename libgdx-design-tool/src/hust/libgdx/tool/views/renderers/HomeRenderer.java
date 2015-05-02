package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.uielements.LAnimation;
import hust.libgdx.tool.models.uielements.LSprite;
import hust.libgdx.tool.views.renderers.properties.AnimationProperty;
import hust.libgdx.tool.views.renderers.properties.ButtonProperty;
import hust.libgdx.tool.views.renderers.properties.CheckBoxProperty;
import hust.libgdx.tool.views.renderers.properties.EmptyProperty;
import hust.libgdx.tool.views.renderers.properties.ImageProperty;
import hust.libgdx.tool.views.renderers.properties.LabelProperty;
import hust.libgdx.tool.views.renderers.properties.Property;
import hust.libgdx.tool.views.renderers.properties.SliderProperty;
import hust.libgdx.tool.views.renderers.properties.SpriteProperty;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class HomeRenderer extends ApplicationRenderer{
	public static Texture DEFAULT_SPRITE_TEXTURE;
	
	private Skin skin;
	
	private Stage mainStage;
	private Menu menu;
	private PackageExplore packageExplore;
	private Editor editor;
	private MessageDialog messgaeDialog;
	private Property property;
	private ArrayList<Property> properties;
	
	private boolean displayBound;
	private boolean isSelecting = true;
	
	private UIElementController controller;
	
	public HomeRenderer(UIElementController controller) {
		super();
		this.controller = controller;
		properties = new ArrayList<>();
		
		createStages();
		
		loadDatas();
		
		createMenuPart();
		createPalletePart();
		createPropertyPart();
		createOutlinePart();
		createEditorPart();
		createMessageDialog();
	}
	
	private void createStages(){
		mainStage = new Stage();
	}
	
	private void loadDatas(){
		skin = new Skin(Gdx.files.internal("data/skin.json"));
		
		DEFAULT_SPRITE_TEXTURE = new Texture(Gdx.files.internal("data/default/sprite.png"));
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
		
		// load all instance of property
		properties.add(EmptyProperty.getInstance(mainStage, skin, location, size, controller));
		properties.add(LabelProperty.getInstance(mainStage, skin, location, size, controller));
		properties.add(CheckBoxProperty.getInstance(mainStage, skin, location, size, controller));
		properties.add(ButtonProperty.getInstance(mainStage, skin, location, size, controller));
		properties.add(SliderProperty.getInstance(mainStage, skin, location, size, controller));
		properties.add(ImageProperty.getInstance(mainStage, skin, location, size, controller));
		properties.add(SpriteProperty.getInstance(mainStage, skin, location, size, controller));
		properties.add(AnimationProperty.getInstance(mainStage, skin, location, size, controller));
		
		property = properties.get(0);
		property.show(true);
	}

	private void createOutlinePart() {
	}

	private void createEditorPart() {
		editor = new Editor(skin);
	}
	
	private void createMessageDialog(){
//		messgaeDialog = new MessageDialog(mainStage, skin);
	}

	@Override
	public void render() {
		editor.render();
		batch.begin();
		// draw all
		drawMenu();
		drawPalette();
		drawProperty();
		drawPreview();
		drawOutline();
		drawDesign();
		
		// end draw
		batch.end();
		
		mainStage.act(Gdx.graphics.getDeltaTime());
		mainStage.draw();
		
		// draw bound of selected elector
		if (displayBound) drawBound(controller.getSelectedBound(false), isSelecting);
	}

	private void drawBound(Rectangle bound, boolean isSelecting){
		if (isSelecting) drawBound(bound);
		else editor.drawBound(bound);
	}
	
	private void drawBound(Rectangle bound){
		shape.setAutoShapeType(true);
		shape.begin();
		shape.setColor(Color.RED);
		shape.rect(bound.x, bound.y, bound.width, bound.height);
		
		shape.end();
	}

	private void drawMenu() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.MENU_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.MENU_SIZE.y;
		drawBound.x = 0;
		drawBound.y = Constant.SCREEN_SIZE.y - drawBound.height;

//		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		drawBound(drawBound);
	}

	private void drawPalette() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.PALETTE_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.PALETTE_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.PALETTE_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.PALETTE_LOCATION.y;

//		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		drawBound(drawBound);
		
	}

	private void drawProperty() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.PROPERTY_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.PROPERTY_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.PROPERTY_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.PROPERTY_LOCATION.y;

//		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		drawBound(drawBound);
	}

	private void drawPreview() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.PREVIEW_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.PREVIEW_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.PREVIEW_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.PREVIEW_LOCATION.y;

//		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		drawBound(drawBound);
	}

	private void drawOutline() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.OUTLINE_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.OUTLINE_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.OUTLINE_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.OUTLINE_LOCATION.y;

//		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		drawBound(drawBound);
		
	}

	private void drawDesign() {
		Rectangle drawBound = new Rectangle();
		
		drawBound.width = Constant.SCREEN_SIZE.x * Constant.DESIGN_SIZE.x;
		drawBound.height = Constant.SCREEN_SIZE.y * Constant.DESIGN_SIZE.y;
		drawBound.x = Constant.SCREEN_SIZE.x * Constant.DESIGN_LOCATION.x;
		drawBound.y = Constant.SCREEN_SIZE.y * Constant.DESIGN_LOCATION.y;

//		batch.draw(sprite, drawBound.x, drawBound.y, drawBound.width, drawBound.height);
		drawBound(drawBound);
		
	}
	
	public void setSelecting(boolean isSelecting) {
		this.isSelecting = isSelecting;
	}

	public Skin getSkin() {
		return skin;
	}

	public boolean isInEditor(Vector2 currentTouchPos) {
		return editor.contains(currentTouchPos);
	}
	
	public boolean isInEditor(float x, float y){
		return editor.contains(x, y);
	}
	
	public boolean isInProperty(Vector2 currentTouchPos){
		return property.contains(currentTouchPos);
	}
	
	public boolean isInProperty(float x, float y){
		return property.contains(x, y);
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
		return editor.contains(actor);
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

	public void zoomEditor(boolean zoomin) {
		if (zoomin) editor.zoomin();
		else editor.zoomout();
	}
	
	public void setPropertyObject(Actor actor){
		property.setObject(actor);
	}
	
	public Property getPropertyView(Actor actor){
		property.show(false);
		
		if (actor == null) property = properties.get(0);
		else if (actor instanceof Label) property = properties.get(1);
		else if (actor instanceof CheckBox) property = properties.get(2);
		else if (actor instanceof Button) property = properties.get(3);
		else if (actor instanceof Slider) property = properties.get(4);
		else if (actor instanceof Image) property = properties.get(5);
		else if (actor instanceof LSprite) property = properties.get(6);
		else if (actor instanceof LAnimation) property = properties.get(7);
		else property = null;
		
		property.show(true);
		return property;
	}
}
