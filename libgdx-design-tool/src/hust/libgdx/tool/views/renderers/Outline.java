package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.models.UIElementType;
import hust.libgdx.tool.models.customs.CustomTree;
import hust.libgdx.tool.utilities.Utility.NodeElement;
import hust.libgdx.tool.utilities.Utility.NodeType;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class Outline extends CustomTree {
	public Outline(Stage stage, Skin skin, Vector2 location, Vector2 size) {
		super(stage, skin, location, size);
	}
	
	public Outline(Stage stage, Skin skin, Vector2 location, Vector2 size, UIElementController controller) {
		super(stage, skin, location, size, controller);
	}

	@Override
	public void onTouchDown(UIElementType type) {
	}

	@Override
	public void onTouchUp(UIElementType type) {
	}

	@Override
	public void onTouchDrag() {
	}

	@Override
	public ArrayList<NodeElement> readTreeInfo() {
		if (getController() == null){
			System.out.println("Cotnroller null");
		} else {
			System.out.println("Not null");
		}
		
		ArrayList<Actor> actors = getController().getActors();
		ArrayList<NodeElement> roots = new ArrayList<>();
		
		for (Actor actor : actors) {
			ArrayList<NodeElement> childs = (actor instanceof Group) ? new ArrayList<NodeElement>() : null;
			NodeElement root = new NodeElement(NodeType.UIELEMENT, actor.getName(), actor, childs);
			if (actor instanceof Group)
				readReverseNode((Group)actor, root);
			roots.add(root);
		}
		
		return roots;
	}
	
	private void readReverseNode(Group group, NodeElement parent) {
		Array<Actor> childs = group.getChildren();
		
		for (Actor actor : childs) {
			if (actor instanceof Group){
				NodeElement node = new NodeElement(NodeType.UIELEMENT_GROUP, actor.getName(), actor, new ArrayList<NodeElement>());
				parent.addChild(node);
				readReverseNode((Group)actor, node);
			}else 
				parent.addChild(new NodeElement(NodeType.UIELEMENT, actor.getName(), actor, null));
		}
	}

	@Override
	public Label createNodeLabel(final NodeElement node) {
		Label label = new Label(node.getName(), getSkin());
		label.setFontScale(Constant.FONT_SCALE);
		
		label.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				getController().setSelected((Actor)node.getObject());
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		return label;
	}
	
	public void update(){
		removeAllNode();
		setRoots(readTreeInfo());
		createTreeNodes();
	}
}
