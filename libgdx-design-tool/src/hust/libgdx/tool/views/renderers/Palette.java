package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.controllers.Controller;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.utilities.Utility;
import hust.libgdx.tool.utilities.Utility.NodeElement;
import hust.libgdx.tool.utilities.Utility.NodeType;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class Palette extends CustomTree {
	public Palette(Stage stage, Skin skin, Vector2 location, Vector2 size) {
		super(stage, skin, location, size);
	}

	private static String[][] UI_ELEMENT_LIST = {
			{ "Basic elements", "Checkbox", "Slider", "Button", "Label" },
			{ "Structured elements", "Window", "Scroll Pane" },
			{ "Static images", "Sprite", "Image" },
			{ "Animation", "Animation" } };

	@Override
	public void onTouchDown(UIElementType type) {
		System.out.println("Touch down on " + type);
	}

	@Override
	public void onTouchUp(UIElementType type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchDrag() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<NodeElement> readTreeInfo() {
		ArrayList<NodeElement> roots = new ArrayList<NodeElement>();

		for (int i = 0; i < UI_ELEMENT_LIST.length; i++) {
			String[] group = UI_ELEMENT_LIST[i];

			NodeElement root = new NodeElement(NodeType.UIELEMENT_GROUP,
					group[0], new ArrayList<NodeElement>());
			roots.add(root);

			for (int j = 1; j < group.length; j++) {
				NodeElement childNode = new NodeElement(NodeType.UIELEMENT,
						group[j], null);
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
		
		
		
		return label;
	}
}
