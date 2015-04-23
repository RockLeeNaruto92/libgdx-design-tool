package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.utilities.Utility.NodeElement;
import hust.libgdx.tool.utilities.Utility.NodeType;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Palette extends CustomTree {
	private static String[][] UI_ELEMENT_LIST = {
			{ "Basic elements", "Checkbox", "Slider", "Button", "Label" },
			{ "Structured elements", "Window", "Scroll Pane" },
			{ "Static images", "Sprite", "Image" },
			{ "Animation", "Animation" } };
	
	private UIElementController controller;
	
	public Palette(Stage stage, Skin skin, Vector2 location, Vector2 size) {
		super(stage, skin, location, size);
	}
	
	public Palette(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller) {
		super(stage, skin, location, size);
		this.controller = controller;
	}

	@Override
	public void onTouchDown(UIElementType type) {
		System.out.println("Touch down on " + type);
	}

	@Override
	public void onTouchUp(UIElementType type) {

	}

	@Override
	public void onTouchDrag() {
		
	}

	@Override
	public ArrayList<NodeElement> readTreeInfo() {
		ArrayList<NodeElement> roots = new ArrayList<NodeElement>();

		for (int i = 0; i < UI_ELEMENT_LIST.length; i++) {
			String[] group = UI_ELEMENT_LIST[i];

			NodeElement root = new NodeElement(NodeType.UIELEMENT_GROUP, group[0], new ArrayList<NodeElement>());
			roots.add(root);

			for (int j = 1; j < group.length; j++) {
				NodeElement childNode = new NodeElement(NodeType.UIELEMENT, group[j], getUIElementType(group[j]), null);
				root.getChilds().add(childNode);
			}
		}
		
		for (NodeElement node : roots) {
			node.retrive(0);
		}
		
		return roots;
	}

	@Override
	public Label createNodeLabel(NodeElement node) {
		Label label = new Label(node.getName(), getSkin());
		label.setFontScale(Constant.FONT_SCALE);
		
		if (node.getObject() == null) return label;
		
		final UIElementType type = (UIElementType)node.getObject();
		
		label.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println(type);
				controller.onTouchDown(type, event.getStageX(), event.getStageY());
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("Touch up " + event.getStageX() + "-" + event.getStageY());
				
				super.touchUp(event, x, y, pointer, button);
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				System.out.println("Touch drag " + event.getStageX() + "-" + event.getStageY());
				super.touchDragged(event, x, y, pointer);
			}
			
		});
		
		return label;
	}
	
	private UIElementType getUIElementType(String name){
		switch (name) {
		case "Checkbox":
			return UIElementType.CHECKBOX;
		case "Slider":
			return UIElementType.SLIDER;
		case "Button":
			return UIElementType.BUTTON;
		case "Label":
			return UIElementType.LABEL;
		case "Window":
			return UIElementType.WINDOW;
		case "Scroll Pane":
			return UIElementType.SCROLL_PANE;
		case "Sprite":
			return UIElementType.SPRITE;
		case "Image":
			return UIElementType.IMAGE;
		case "Animation":
			return UIElementType.ANIMATION;
		default:
			break;
		}
		
		return null;
	}
}
