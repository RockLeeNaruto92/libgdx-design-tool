package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.controllers.UIElementController;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public abstract class Property {
	private Actor object;
	private Rectangle bound;
	private Table container, parent;
	private UIElementController controller;
	
	public Property(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		this.controller = controller;
		
		bound = new Rectangle();
		setBound(location, size);
		
		createContainer(stage, bound);
		createProperties(skin, size);
		createScroll(skin);
	}
	
	private void createContainer(Stage stage, Rectangle bound){
		container = new Table();
		container.setBounds(bound.x, bound.y, bound.width, bound.height);
		container.align(Align.topLeft);
		stage.addActor(container);

		parent = new Table();
		parent.setFillParent(false);
	}
	
	private void createScroll(Skin skin){
		ScrollPane scroll = new ScrollPane(parent, skin);
		container.row();
		container.add(scroll);
	}
	
	
	private void setBound(Vector2 location, Vector2 size){
		bound.set(location.x, location.y, size.x, size.y);
	}
	
	public void setObject(Actor object){
		this.object = object;
	}
	
	public Actor getObject(){
		return object;
	}
	
	public Table getParent() {
		return parent;
	}
	
	public UIElementController getController(){
		return controller;
	}
	
	public boolean contains(Vector2 point){
		return bound.contains(point);
	}
	
	public boolean contains(float x, float y){
		return bound.contains(x, y);
	}
	
	public abstract void createProperties(Skin skin, Vector2 parentSize);
}
