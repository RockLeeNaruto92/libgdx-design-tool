package hust.libgdx.tool.views.renderers.dialogs;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MessageDialog extends Dialog{
	public MessageDialog(String title, Skin skin) {
		super(title, skin);
	}

	public MessageDialog(String title, Skin skin, String windowStyleName) {
		super(title, skin, windowStyleName);
	}

	public MessageDialog(String title, WindowStyle windowStyle) {
		super(title, windowStyle);
	}
	
	{
		text("Do you ?");
		button("Ok");
	}

	public void show(boolean show){
		
	}
	
}
