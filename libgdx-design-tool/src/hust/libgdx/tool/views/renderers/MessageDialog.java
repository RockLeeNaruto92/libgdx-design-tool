package hust.libgdx.tool.views.renderers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MessageDialog {
	
	private Dialog dialog;
	private String message;
	
	public MessageDialog(Stage stage, Skin skin){
		dialog = new Dialog("", skin);
//		dialog.setModal(true);
		dialog.setMovable(true);
		dialog.setPosition(200, 200);
		
		stage.addActor(dialog);
	}
	
	public void show(boolean show){
		
	}
	
}
