package hust.libgdx.tool.models.customs;

import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class CAlign extends Align {
	public static String[] aligns = new String[] {
		"Top", "Top Left", "Top Right",
		"Bottom", "Bottom Left", "Bottom Right",
		"Left", "Right", "Center"
	};
	
	public static int getAlign(int index){
		switch (index) {
		case 0:
			return Align.top;
		case 1:
			return Align.topLeft;
		case 2: 
			return Align.topRight;
		case 3:
			return Align.bottom;
		case 4:
			return Align.bottomLeft;
		case 5:
			return Align.bottomRight;
		case 6:
			return Align.left;
		case 7:
			return Align.right;
		case 8:
			return Align.center;
		default:
			return -1;
		}
	}

	public static int getIndex(int align) {
		switch (align) {
		case Align.top:
			return 0;
		case Align.topLeft:
			return 1;
		case Align.topRight:
			return 2;
		case Align.bottom:
			return 3;
		case Align.bottomLeft:
			return 4;
		case Align.bottomRight:
			return 5;
		case Align.left:
			return 6;
		case Align.right:
			return 7;
		case Align.center:
			return 8;
		default:
			break;
		}
		return -1;
	}
}
