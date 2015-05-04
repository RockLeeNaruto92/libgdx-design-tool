package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.constants.Word;
import hust.libgdx.tool.controllers.UIElementController;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Menu {
	public enum Action {
		FILE, WINDOWS, NONE
	};
	
	private TextButton fileMenu, windowsMenu;
	private Table mainMenu;
	private Table subFileMenu, subWindowsMenu;
	private UIElementController controller;
	private Rectangle bound;
	
	private Stage stage;
	
	public Menu(Stage stage, Skin skin, UIElementController controller){
		this.stage = stage;
		this.controller = controller;
		
		init(skin);
	}
	
	private void init(Skin skin){
		createMenuGroup(skin);
		createSubMenuGroup(skin);
	}
	
	private void createMenuGroup(Skin skin){
		bound = Utility.getActualBound(Constant.MENU_LOCATION, Constant.MENU_SIZE);
		
		mainMenu = new Table(skin);
		mainMenu.setBounds(bound.x, bound.y, bound.width, bound.height);
		mainMenu.left();
		mainMenu.setBackground("window-top");
		
		stage.addActor(mainMenu);
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
		
		float width = windowsMenu.getStyle().font.getBounds(windowsMenu.getText()).width * Constant.FONT_SCALE + Constant.GROUP_PAD_LEFT; 
		mainMenu.add(windowsMenu).left().width(width).height(mainMenu.getHeight()).padLeft(Constant.GROUP_PAD_LEFT);
		
		createSubWindowsMenu(skin);
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
		
		float width = fileMenu.getStyle().font.getBounds(fileMenu.getText()).width * Constant.FONT_SCALE + Constant.GROUP_PAD_LEFT; 
		mainMenu.add(fileMenu).left().width(width).height(mainMenu.getHeight()).padLeft(Constant.GROUP_PAD_LEFT);
		
		createSubFileMenu(skin);
	}
	
	private void createSubMenuGroup(Skin skin){
		createFileMenu(skin);
		createWindowsMenu(skin);
	}
	
	private void createSubFileMenu(Skin skin){
		subFileMenu = (new Table(skin)).align(Align.left);
		subFileMenu.setVisible(false);
		subFileMenu.setBackground("window-top");
		
		CheckBox btnNew = Utility.createSubMenuCheckbox(Word.NEW, skin, "no-has-image");
		float height = btnNew.getStyle().font.getBounds(btnNew.getText()).height * Constant.FONT_SCALE + Constant.GROUP_PAD_LEFT;
		subFileMenu.row();
		subFileMenu.add(btnNew).height(height).left();
		
		CheckBox btnOpen = Utility.createSubMenuCheckbox(Word.OPEN, skin, "no-has-image");
		subFileMenu.row();
		subFileMenu.add(btnOpen).height(height).left();
		
		CheckBox btnImport = Utility.createSubMenuCheckbox(Word.IMPORT, skin, "import");
		subFileMenu.row();
		subFileMenu.add(btnImport).height(height).left();
		
		CheckBox btnSave = Utility.createSubMenuCheckbox(Word.SAVE, skin, "save");
		subFileMenu.row();
		subFileMenu.add(btnSave).height(height).left();
		
		subFileMenu.setSize(subFileMenu.getPrefWidth(), subFileMenu.getPrefHeight());
		subFileMenu.setX(0);
		subFileMenu.setY(mainMenu.getY() - subFileMenu.getHeight());
		stage.addActor(subFileMenu);
	}
	
	private void createSubWindowsMenu(Skin skin){
		subWindowsMenu = (new Table(skin)).align(Align.left);
		subWindowsMenu.setVisible(false);
		subWindowsMenu.setBackground("window-top");
		
		CheckBox property = Utility.createSubMenuCheckbox(Word.PROPERTY, skin, "no-has-image");
		float height = property.getStyle().font.getBounds(property.getText()).height * Constant.FONT_SCALE + Constant.GROUP_PAD_LEFT;
		subWindowsMenu.row();
		subWindowsMenu.add(property).height(height).left();
		
		CheckBox packageExplore = Utility.createSubMenuCheckbox(Word.PACKAGE_EXPLORE, skin, "no-has-image");
		subWindowsMenu.row();
		subWindowsMenu.add(packageExplore).height(height).left();
		
		CheckBox palette = Utility.createSubMenuCheckbox(Word.PALETTE, skin, "no-has-image");
		subWindowsMenu.row();
		subWindowsMenu.add(palette).height(height).left();
		
		CheckBox outline = Utility.createSubMenuCheckbox(Word.OUTLINE, skin, "no-has-image");
		subWindowsMenu.row();
		subWindowsMenu.add(outline).height(height).left();
		
		subWindowsMenu.setSize(subWindowsMenu.getPrefWidth(), subWindowsMenu.getPrefHeight());
		subWindowsMenu.setY(mainMenu.getY() - subWindowsMenu.getHeight());
		subWindowsMenu.setX(mainMenu.getCell(fileMenu).getMinWidth() + 2 * mainMenu.getPadLeft());
		stage.addActor(subWindowsMenu);
	}
	
	public void displaySubMenuGroup(Action choose){
		switch (choose) {
		case FILE:
			subFileMenu.setVisible(true);
			subWindowsMenu.setVisible(false);
			break;
		case WINDOWS:
			subFileMenu.setVisible(false);
			subWindowsMenu.setVisible(true);
			break;
		case NONE:
			subFileMenu.setVisible(false);
			subWindowsMenu.setVisible(false);
			break;
		default:
			break;
		}
	}
	
	public boolean contains(float x, float y){
		if (bound.contains(x, y)) return true;
		
		Rectangle rect = new Rectangle(subFileMenu.getX(), subFileMenu.getY(), subFileMenu.getWidth(), subFileMenu.getHeight());
		if (subFileMenu.isVisible() && rect.contains(x, y)) return true;
		
		rect.set(subWindowsMenu.getX(), subWindowsMenu.getY(), subWindowsMenu.getWidth(), subWindowsMenu.getHeight());
		return subWindowsMenu.isVisible() && rect.contains(x, y);
	}
}
