package hust.libgdx.tool.models;

public enum UIElementType {
	EMPTY, CHECKBOX, SLIDER, BUTTON, LABEL,
	WINDOW, SCROLL_PANE,
	SPRITE, IMAGE,
	ANIMATION;
	
	public static UIElementType[] getTypes(){
		UIElementType[] types = new UIElementType[]{
				CHECKBOX, SLIDER, BUTTON, LABEL,
				WINDOW, SCROLL_PANE,
				SPRITE, IMAGE,
				ANIMATION
		};
		return types;
	}
}
