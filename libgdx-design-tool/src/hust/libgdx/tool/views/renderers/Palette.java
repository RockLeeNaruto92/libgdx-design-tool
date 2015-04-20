package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.utilities.Utility.NodeElement;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Palette {
	private TextButton paletteTab;
	
	private Stage stage;
	private Table table;

	public Palette(Stage stage, Skin skin) {
		this.stage = stage;
		
		init(skin);
	}
	
	private void init(Skin skin){
		
	}
}
