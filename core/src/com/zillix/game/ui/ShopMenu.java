package com.zillix.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.controllers.HudController;

public class ShopMenu extends UIPopup {
	
	private static final String MENU_BACKGROUND_PATH = "ui/boxes/box1/9patch/Box.pack";
	
	private BasicButton closeButton;
	
	public ShopMenu(int width, int height, BitmapFont font, ZAssetManager assetManager, HudController controller)
	{
		super(width, height, font, assetManager, controller, MENU_BACKGROUND_PATH);
	}
	
	@Override
	protected void initialize(int width, int height, BitmapFont font, ZAssetManager assetManager, HudController controller, String backgroundImage)
	{
		super.initialize(width, height, font, assetManager, controller, backgroundImage);
		
		closeButton = new BasicButton("close", width / 2 - 80, height / 2 - 60, font, assetManager);
		this.addActor(closeButton);
		closeButton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				close();
			}
		});
	}
}
