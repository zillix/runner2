package com.zillix.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.RadialObject;

public class RadialObjectRenderer {
	
	private Sprite sprite;
	private Sprite centerSprite;
	private ZAssetManager assets;
	
	private static final String CENTER_SPRITE_PATH = "objects/dot.png";
	private static final int OFFSCREEN_RENDER = 10;
	
	public RadialObjectRenderer(ZAssetManager assets)
	{
		this.assets = assets;
		assets.load(CENTER_SPRITE_PATH, Texture.class);
	}

	public void draw(RadialObject object, SpriteBatch batch)
	{
		if (centerSprite == null && assets.isLoaded(CENTER_SPRITE_PATH))
		{
			centerSprite = new Sprite(assets.get(CENTER_SPRITE_PATH, Texture.class));
		}
		
		String imagePath = object.getImagePath();
		if (assets.isLoaded(imagePath))
		{
			Vector2 absPos = object.getAbsolutePosition();
			Texture texture = assets.get(imagePath, Texture.class);
			object.setTexture(texture);
			sprite = object.getSprite();
			if (sprite != null)
			{
				sprite.setPosition(absPos.x - object.getWidth() / 2, absPos.y - object.getHeight() / 2);
				sprite.setRotation(-90 + object.getPosition().getOriginAngle());
				sprite.draw(batch);
			}
			
			if (centerSprite != null)
			{
				centerSprite.setPosition(absPos.x,  absPos.y);
				centerSprite.draw(batch);
			}
		}
		else if (!assets.hasRequestedAsset(imagePath))
		{
			assets.load(imagePath, Texture.class);
		}
	}
}
