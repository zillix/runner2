package com.zillix.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zillix.game.assets.ZAssetManager;

public class BasicImageButton extends ImageButton {
	
	private static final String DEFAULT_BUTTON_ATLAS_PATH = "ui/buttons/button1/9patch/Buttons.pack";
	private static final String DEFAULT_UP_DRAWABLE = "button1up";
	private static final String DEFAULT_DOWN_DRAWABLE = "button1down";
	private static final int DEFAULT_WIDTH = 100;
	private static final int DEFAULT_HEIGHT = 50;
	
	public BasicImageButton(String imagePath,
			int X, 
			int Y,
			ZAssetManager assetManager)
	{
		super(initializeButtonStyle(
				DEFAULT_BUTTON_ATLAS_PATH, 
				DEFAULT_UP_DRAWABLE, 
				DEFAULT_DOWN_DRAWABLE, 
				imagePath,
				assetManager));
		initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT, X, Y);
	}
	
	public BasicImageButton(String imagePath,
			int X, 
			int Y, 
			ZAssetManager assetManager,
			String buttonAtlasPath, 
			String upDrawable,
			String downDrawable, 
			int width,
			int height)
	{
		super(initializeButtonStyle(
				buttonAtlasPath,
				upDrawable,
				downDrawable,
				imagePath,
				assetManager));
		initialize(width, height, X, Y);
	}
	
	protected void initialize(int width, int height, int x, int y)
	{
		setWidth(width);
		setHeight(height);
		setX(x - width / 2);
		setY(y - height / 2);
	}
	
	protected static ImageButtonStyle initializeButtonStyle(String backgroundImagePath, 
			String upDrawable, 
			String downDrawable, 
			String imagePath,
			ZAssetManager assetManager)
	{
		TextureAtlas buttonAtlas = assetManager.forceLoad(backgroundImagePath, TextureAtlas.class);
		Skin buttonSkin = new Skin();
		buttonSkin.addRegions(buttonAtlas);
		
		Texture imageTexture = assetManager.forceLoad(imagePath, Texture.class);
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(imageTexture));
		
		ImageButtonStyle buttonStyle = new ImageButtonStyle();
		buttonStyle.up = buttonSkin.getDrawable(upDrawable);
		buttonStyle.down = buttonSkin.getDrawable(downDrawable);
		buttonStyle.imageUp = drawable;
		// TODO(alex): Support this
		/*if (imageDownDrawable != null)
		{
			buttonStyle.imageDown = imageSkin.getDrawable(imageDownDrawable);
		}
		*/
		buttonSkin.dispose();
		
		return buttonStyle;
	}

}
