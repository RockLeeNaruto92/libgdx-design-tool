package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class Editor{
	private Stage stage;
	private Rectangle bound;
	private OrthographicCamera camera;
	private Sprite sprite;
	private ShapeRenderer shape;
	
	public Editor(Skin skin){
		sprite = new Sprite(new Texture(Gdx.files.internal("data/black.png")));
		
		createStageWithCamera();
		
		bound = new Rectangle();
		bound.x = Utility.getActualValue(Constant.DESIGN_LOCATION.x, true);
		bound.y = Utility.getActualValue(Constant.DESIGN_LOCATION.y, false);
		bound.width = Utility.getActualValue(Constant.DESIGN_SIZE.x, true);
		bound.height = Utility.getActualValue(Constant.DESIGN_SIZE.y, false);
		
		shape = new ShapeRenderer();
	}
	
	private void createStageWithCamera(){
		stage = new Stage();
		
		camera = new OrthographicCamera(stage.getWidth(), stage.getHeight());
		camera.setToOrtho(false);
		camera.project(camera.position);
		
		stage.setDebugAll(true);
		stage.getViewport().setCamera(camera);
	}
	
	public Rectangle getBound(){
		return bound;
	}
	
	public Stage getStage(){
		return stage;
	}

	public void render(){
		stage.getBatch().begin();
		
		stage.getBatch().draw(sprite.getTexture(), 0, 0, stage.getWidth(), stage.getHeight());
		
		stage.getBatch().end();
		
		camera.update();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public boolean contains(Vector2 currentTouchPos) {
		return bound.contains(currentTouchPos);
	}
	
	public boolean contains(Actor actor){
		return stage.getActors().contains(actor, true);
	}

	public void addNewActor(Actor newActor, float x, float y) {
		// set location for new Actor 
		setActorLocation(newActor, x, y);
		
		stage.addActor(newActor);
	}
	
	public void removeActor(Actor actor){
		Array<Actor> actors = stage.getActors();
		
		for (Actor object : actors) {
			if (actor == object){
				// delete actor
				actor.remove();
				return;
			}
		}
	}

	public void setActorLocation(Actor newActor, float x, float y) {
		newActor.setX(x - newActor.getWidth() / 2);
		newActor.setY(y - newActor.getHeight() / 2);
	}
	
	public Vector2 getStagePoint(float screenX, float screenY){
		Vector3 point = camera.unproject(new Vector3(screenX, Constant.SCREEN_SIZE.y - screenY, 0));
		
		return new Vector2(point.x, point.y);
	}
	
	protected void zoomin(){
		camera.zoom += Constant.CAMERA_ZOOM_STEP;
	}
	
	protected void zoomout(){
		camera.zoom -= Constant.CAMERA_ZOOM_STEP;
	}

	protected void drawBound(Rectangle bound) {
		shape.setAutoShapeType(true);
		shape.setProjectionMatrix(camera.combined);
		shape.begin();
		shape.setColor(Color.RED);
		shape.rect(bound.x, bound.y, bound.width, bound.height);
		shape.end();
	}
}