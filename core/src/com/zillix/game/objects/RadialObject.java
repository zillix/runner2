package com.zillix.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.zillix.game.controllers.RadialObjectController;

public class RadialObject extends BasicObject implements Poolable {

	protected Sprite sprite;
	
	protected RadialObjectController movement;
	
	protected Boolean isDead;
	
	protected Pool<? extends RadialObject> pool;
	
	protected BasicObject nextState;
	
	private static final String DEFAULT_SPRITE_PATH = "objects/empty.png";
	
	public RadialObject(RadialOriginObject pOrigin)
	{
		this();

		setup(pOrigin);
	}
	
	protected RadialObject()
	{
		initialize();
	}
	
	@Override
	protected void initialize()
	{
		super.initialize();
		sprite = new Sprite();
		position = new RadialPosition(null, 0, 0);
		nextState = new BasicObject();
		reset();
	}
	
	public void setTexture(Texture texture)
	{
		setTexture(texture, false);
	}
	
	public void setTexture(Texture texture, boolean force)
	{
		if (sprite != null || force)
		{
			sprite = new Sprite(texture);
			sprite.setOriginCenter();
			setBounds(-sprite.getWidth() / 2, -sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
		}
	}
	
	@Override
	public void reset()
	{
		super.reset();
		
		if (nextState != null)
		{
			nextState.reset();
		}
		isDead = false;
	}
	
	public void setup(RadialOriginObject pOrigin)
	{
		position.setOrigin(pOrigin);
	}
	
	public void debugUpdate(float delta)
	{
	}
	
	public String getImagePath()
	{
		return DEFAULT_SPRITE_PATH;
	}
	
	public Vector2 getRelativePosition()
	{
		return position.getRelativeCoordinates();
	}
	
	public Vector2 getAbsolutePosition()
	{
		return position.getAbsoluteCoordinates();
	}
	
	public BasicObject getObjectAtNextFrame(float deltaTime)
	{
		double rotateAngle = 360 * (velocity.x /*+ acceleration.x * deltaTime*/) * deltaTime / (position.originDistance * 2 * Math.PI);
		double nextRadius = position.originDistance - (velocity.y /*+ acceleration.y * deltaTime*/) * deltaTime;
		
		nextState.reset();
		nextState.setOriginAngle((float)(position.originAngle + rotateAngle));
		nextState.setOriginDistance((float)nextRadius);
		nextState.setBounds(bounds);
		return nextState;
	}

	public Boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(Boolean isDead) {
		this.isDead = isDead;
	}
	

	public Pool<? extends RadialObject> getPool() {
		return pool;
	}

	public void setPool(Pool<? extends RadialObject> pool) {
		this.pool = pool;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	
	
	// TODO: Find out what this actually means
	/*
	public double getAccurateArcLength()
	{
		 return 2 * originDistance * Math.asin(sprite.getWidth() / 2 / originDistance);
	}
	*/
}
