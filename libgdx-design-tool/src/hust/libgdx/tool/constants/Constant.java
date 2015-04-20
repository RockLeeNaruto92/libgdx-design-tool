package hust.libgdx.tool.constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Constant {
	public static Vector2 SCREEN_SIZE = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	public static Vector2 MENU_SIZE = new Vector2(1f, 0.03125f);
	public static Vector2 MENU_LOCATION = new Vector2(0f, 0.985f);
	public static Vector2 PALETTE_SIZE = new Vector2(0.2f, 0.96875f);
	public static Vector2 PALETTE_LOCATION = new Vector2(0f, 0f);
	public static Vector2 PROPERTY_SIZE = new Vector2(0.6f, 0.3f);
	public static Vector2 PROPERTY_LOCATION = new Vector2(0.2f, 0f);
	public static Vector2 PREVIEW_SIZE = new Vector2(0.2f, 0.3f);
	public static Vector2 PREVIEW_LOCATION = new Vector2(0.8f, 0f);
	public static Vector2 OUTLINE_SIZE = new Vector2(0.2f, 0.66875f);
	public static Vector2 OUTLINE_LOCATION = new Vector2(0.8f, 0.3f);
	public static Vector2 DESIGN_SIZE = new Vector2(0.6f, 0.66875f);
	public static Vector2 DESIGN_LOCATION = new Vector2(0.2f, 0.3f);
	
	public static Vector2 SUB_MENU_SIZE = new Vector2(0.3f, 0f);
	
	public static float GROUP_PAD_LEFT = 20f;
	public static float HORIZONTAL_GROUP_SPACE = 20f;
	public static float VERTICAL_GROUP_SPACE = 1f;
	public static float VERTICAL_GROUP_PAD_RIGHT = 10f;
	public static float VERTICAL_GROUP_IMAGE_WIDTH = 15f;
	public static float VERTICAL_GROUP_IMAGE_HEIGHT = 15f;
	public static float FONT_SCALE = 0.7f;
	
	// 18, 18
	public static Vector2 TREE_ICON_SIZE = new Vector2(0.01317716f, 0.0234375f);
	// 18, 18
	public static Vector2 TREE_NODE_ICON_SIZE = new Vector2(0.01317716f, 0.0234375f);
	
	
	public static String ICON_FOLDER_OPEN = "folder_open.png";
	public static String ICON_FOLDER_CLOSE = "folder_close.png";
	public static String ICON_FILE = "file.png";
	public static String FILE_UIELEMENT_LIST = "uielementList.txt";
	
}