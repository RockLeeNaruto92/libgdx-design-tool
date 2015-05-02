package hust.libgdx.tool.views.renderers.properties;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.customs.CAlign;
import hust.libgdx.tool.models.customs.CScaling;
import hust.libgdx.tool.models.uielements.LImage;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ImageProperty extends ActorProperty {
	private static ImageProperty _instance;
	
	private SelectBox<Object> align, scaling;
	private Image image;
	

	public ImageProperty(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}
	
	public static ImageProperty getInstance(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller){
		if (_instance == null){
			_instance = new ImageProperty(stage, skin, location, size, controller);
		}
		
		return _instance;
	}

	@Override
	public void createOtherField(Skin skin, Vector2 parentSize) {
		createSelectBoxAlign(skin, parentSize);
		createSelectBoxScaling(skin, parentSize);
		createImageImage(skin, parentSize);
	}
	
	private void createImageImage(Skin skin, Vector2 parentSize) {
		String[] labels = {Word.IMAGE};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3, Constant.PROPERTY_COLUMN_4};
		
		image = Utility.createImageField(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.IMAGE);
	}

	private void createSelectBoxAlign(Skin skin, Vector2 parentSize){
		String[] labels = {Word.ALIGN};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		
		align = Utility.createSelectBox(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.ALIGN, CAlign.aligns);
	}
	
	private void createSelectBoxScaling(Skin skin, Vector2 parentSize){
		String[] labels = {Word.SCALING};
		float[] widths = {Constant.PROPERTY_COLUMN_1, Constant.PROPERTY_COLUMN_2 + Constant.PROPERTY_COLUMN_3 + Constant.PROPERTY_COLUMN_4};
		
		scaling = Utility.createSelectBox(getParent(), parentSize, labels, widths, skin, this, getController(), ActorPropertyType.SCALING, CScaling.scalings);
	}
	
	@Override
	public void setActorProperty(Actor actor) {
		super.setActorProperty(actor);
		LImage obj = (LImage)actor;
		
		align.setSelectedIndex(CAlign.getIndex(obj.getAlign()));
		scaling.setSelectedIndex(CScaling.getIndex(obj.getScaling()));
		image.setDrawable(obj.getDrawable());
	}
}
