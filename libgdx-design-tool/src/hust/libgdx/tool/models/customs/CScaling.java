package hust.libgdx.tool.models.customs;

import com.badlogic.gdx.utils.Scaling;

public class CScaling {
	public static String[] scalings = {
		"Fit", "Fill", "Fill X", "Fill Y", "Stretch", "Stretch X", "Stretch Y", "None"
	};
	
	public static Scaling getScaling(int index){
		switch (index) {
		case 0:
			return Scaling.fit;
		case 1:
			return Scaling.fill;
		case 2:
			return Scaling.fillX;
		case 3:
			return Scaling.fillY;
		case 4:
			return Scaling.stretch;
		case 5:
			return Scaling.stretchX;
		case 6:
			return Scaling.stretchY;
		default:
			return Scaling.none;
		}
	}
	
	public static int getIndex(Scaling scaling){
		switch (scaling) {
		case fit:
			return 0;
		case fill:
			return 1;
		case fillX:
			return 2;
		case fillY:
			return 3;
		case stretch:
			return 4;
		case stretchX:
			return 5;
		case stretchY:
			return 6;
		default:
			return 7;
		}
	}
}
