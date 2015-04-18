package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.utilities.Utility;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Menu {
	public enum Action {
		FILE, WINDOWS, NONE
	};
	
	private float subMenuX, subMenuY;
	
	private TextButton fileMenu, windowsMenu;
	private HorizontalGroup menuGroup;
	private VerticalGroup subMenuGroup;
	private ArrayList<Actor> subFileMenu, subWindowsMenu;
	
	private Stage stage;
	
	public Menu(Stage stage, Skin skin){
		this.stage = stage;
		
		init(skin);
	}
	
	private void init(Skin skin){
		createMenuGroup(skin);
		createSubMenuGroup(skin);
	}
	
	private void createMenuGroup(Skin skin){
		Rectangle bound = Utility.getActualBound(Constant.MENU_LOCATION, Constant.MENU_SIZE);
		
		menuGroup = new HorizontalGroup();
		menuGroup.padLeft(Constant.GROUP_PAD_LEFT);
		menuGroup.space(Constant.HORIZONTAL_GROUP_SPACE);
		menuGroup.setDebug(true);
		menuGroup.setX(bound.x);
		menuGroup.setY(bound.y);
		menuGroup.setDebug(true, true);
		
		createFileMenu(skin);
		createWindowsMenu(skin);
		
		stage.addActor(menuGroup);
	}
	
	private void createWindowsMenu(Skin skin){
		windowsMenu = new TextButton(Word.WINDOWS, skin);
		windowsMenu.getLabel().setFontScale(Constant.FONT_SCALE);
		windowsMenu.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				displaySubMenuGroup(Action.WINDOWS);
			}
			
		});
		
		menuGroup.addActor(windowsMenu);
	}
	
	private void createFileMenu(Skin skin){
		fileMenu = new TextButton(Word.FILE, skin);
		fileMenu.getLabel().setFontScale(Constant.FONT_SCALE);
		fileMenu.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				displaySubMenuGroup(Action.FILE);
			}
		});
		
		menuGroup.addActor(fileMenu);
	}
	
	private void createSubMenuGroup(Skin skin){
		subMenuGroup = new VerticalGroup();
		subMenuGroup.left();
		subMenuGroup.space(Constant.VERTICAL_GROUP_SPACE);
		subFileMenu = new ArrayList<Actor>();
		subWindowsMenu = new ArrayList<Actor>();
		subMenuGroup.setDebug(true, true);
		subMenuGroup.setColor(24, 243, 12, 255);
		
		
		createSubFileMenu(skin);
		createSubWindowsMenu(skin);
		stage.addActor(subMenuGroup);
	}
	
	private void createSubFileMenu(Skin skin){
		CheckBox btnNew = Utility.createSubMenuCheckbox(Word.NEW, skin);
		subFileMenu.add(btnNew);
		
		CheckBox btnOpen = Utility.createSubMenuCheckbox(Word.OPEN, skin);
		subFileMenu.add(btnOpen);
	}
	
	private void createSubWindowsMenu(Skin skin){
		CheckBox property = Utility.createSubMenuCheckbox(Word.PROPERTY, skin);
		subWindowsMenu.add(property);
		
		CheckBox outline = Utility.createSubMenuCheckbox(Word.OUTLINE, skin);
		subWindowsMenu.add(outline);
		
		CheckBox palette = Utility.createSubMenuCheckbox(Word.PALETTE, skin);
		subWindowsMenu.add(palette);
		
		CheckBox preview = Utility.createSubMenuCheckbox(Word.PALETTE, skin);
		subWindowsMenu.add(preview);
	}
	
	private void displaySubMenuGroup(Action choose){
		switch (choose) {
		case FILE:
			removeActorFromSubMenu(subWindowsMenu);
			addActorToSubMenu(subFileMenu);
			break;
		case WINDOWS:
			removeActorFromSubMenu(subFileMenu);
			addActorToSubMenu(subWindowsMenu);
			break;
		case NONE:
			removeActorFromSubMenu(subFileMenu);
			removeActorFromSubMenu(subWindowsMenu);
			break;
		default:
			break;
		}
		setSubMenuLocation(choose);
		subMenuGroup.layout();
	}
	
	private void addActorToSubMenu(ArrayList<Actor> list){
		for (Actor actor : list) {
			subMenuGroup.addActor(actor);
		}
	}
	
	private void removeActorFromSubMenu(ArrayList<Actor> list){
		for (Actor actor : list) {
			subMenuGroup.removeActor(actor);
		}
	}
	
	private void setSubMenuLocation(Action choose){
		switch (choose) {
		case FILE:
			subMenuX = menuGroup.getX() + menuGroup.getPadLeft();
			break;
		case WINDOWS:
			subMenuX = menuGroup.getX() + menuGroup.getPadLeft() + fileMenu.getPrefWidth() + menuGroup.getSpace();
			break;

		default:
			break;
		}
		subMenuY = menuGroup.getY() - menuGroup.getPrefHeight();
		subMenuGroup.setX(subMenuX);
		subMenuGroup.setY(subMenuY);
	}
}
