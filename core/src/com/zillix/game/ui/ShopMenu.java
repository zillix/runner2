package com.zillix.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.controllers.HudController;

public class ShopMenu extends UIPopup {
	
	private static final String MENU_BACKGROUND_PATH = "ui/boxes/box1/9patch/Box.pack";
	
	private BasicTextButton closeButton;
	
	public ShopMenu(int width, int height, BitmapFont font, ZAssetManager assetManager, HudController controller)
	{
		super(width, height, font, assetManager, controller, MENU_BACKGROUND_PATH);
	}
	
	@Override
	protected void initialize(int width, int height, BitmapFont font, ZAssetManager assetManager, HudController controller, String backgroundImage)
	{
		super.initialize(width, height, font, assetManager, controller, backgroundImage);
		
		closeButton = new BasicTextButton("close", width / 2 - 80, height / 2 - 60, font, assetManager);
		this.addActor(closeButton);
		closeButton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				close();
			}
		});
		
		LabelStyle textStyle = new LabelStyle();
		textStyle.font = font;
		
		InputListener onButtonPressed = new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                // Do nothing
            	return true;
			}
		};
		
		
		ShopElement element;
		element = new ShopElement("Speed", 10, -100, textStyle, assetManager, onButtonPressed);
		addActor(element);
		element = new ShopElement("Jump", 10, 0, textStyle, assetManager, onButtonPressed);
		addActor(element);
		element = new ShopElement("DblJump", 10, 110, textStyle, assetManager, onButtonPressed);
		addActor(element);
	}
}
