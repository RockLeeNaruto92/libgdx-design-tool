package hust.libgdx.tool.models.customs;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;


public class CPlayMode {
	public static String[] playModes = {
		PlayMode.LOOP.toString(),
		PlayMode.LOOP_PINGPONG.toString(),
		PlayMode.LOOP_RANDOM.toString(),
		PlayMode.LOOP_REVERSED.toString(),
		PlayMode.NORMAL.toString(),
		PlayMode.REVERSED.toString()
	};
	
	public static PlayMode getPlayMode(int index){
		switch (index) {
		case 0:
			return PlayMode.LOOP;
		case 1:
			return PlayMode.LOOP_PINGPONG;
		case 2:
			return PlayMode.LOOP_RANDOM;
		case 3:
			return PlayMode.LOOP_REVERSED;
		case 4:
			return PlayMode.NORMAL;
		case 5:
			return PlayMode.REVERSED;
		default:
			break;
		}
		
		return PlayMode.NORMAL;
	}
	
	public static int getIndex(PlayMode playMode){
		for (int i = 0; i < playModes.length; i++)
			if (playModes[i] == playMode.toString())
				return i;
		return -1;
	}
}
