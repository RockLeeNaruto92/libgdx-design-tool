package hust.libgdx.tool.utilities;

import java.util.ArrayList;

import hust.libgdx.tool.constants.Constant;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Utility {
	public static Rectangle getActualBound(Vector2 location, Vector2 size){
		Rectangle bound = new Rectangle();
		
		bound.width = Constant.SCREEN_SIZE.x * size.x;
		bound.height = Constant.SCREEN_SIZE.y * size.y;
		bound.x = Constant.SCREEN_SIZE.x * location.x;
		bound.y = Constant.SCREEN_SIZE.y * location.y;
		
		return bound;
	}
	
	public static CheckBox createSubMenuCheckbox(String text, Skin skin){
		CheckBox checkbox = new CheckBox(text, skin);
		checkbox.getLabel().setFontScale(Constant.FONT_SCALE);
		checkbox.getCells().get(0)
			.size(Constant.VERTICAL_GROUP_IMAGE_WIDTH, Constant.VERTICAL_GROUP_IMAGE_HEIGHT)
			.padRight(Constant.VERTICAL_GROUP_PAD_RIGHT);
		
		return checkbox;
	}
	
	public static ArrayList<Object> getListFile(String path, Object obj){
		FileHandle dir;
		ArrayList<Object> list = new ArrayList<Object>();
		
		if (obj != null) list.add(obj);
		
		if (Gdx.app.getType() == ApplicationType.Android){
			dir = Gdx.files.internal(path);
		}else{
			dir = Gdx.files.absolute(path);
		}
		
		for (FileHandle file : dir.list()) {
			if (!file.isDirectory())
				list.add(file.name());
			else 
				list.add(getListFile(path + "/" + file.name(), file.name()));
		}
		
		return list;
	}
}
