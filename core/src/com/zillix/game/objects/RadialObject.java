package com.zillix.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.zillix.game.controllers.RadialObjectController;

public class RadialObject extends BasicObject implements Poolable {

	protected Sprite sprite;
	protected Sprite centerSprite;
	
	protected RadialObjectController movement;
	
	protected Boolean isDead;
	
	protected Pool<? extends RadialObject> pool;
	
	protected BasicObject nextState;
	
	private static final String DEFAULT_SPRITE_PATH = "objects/empty.png";
	private static final String CENTER_SPRITE_PATH = "objects/dot.png";
	
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
		centerSprite = new Sprite(new Texture(CENTER_SPRITE_PATH));
		centerSprite.setOriginCenter();
	

		reset();
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
		sprite = new Sprite(new Texture(getImagePath()));
		sprite.setOriginCenter();
		setBounds(-sprite.getWidth() / 2, -sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
	}
	
	public void debugUpdate(float delta)
	{
	}
	
	protected String getImagePath()
	{
		return DEFAULT_SPRITE_PATH;
	}
	
	public void draw(SpriteBatch batch)
	{
		Vector2 absPos = getAbsolutePosition();
		sprite.setPosition(absPos.x - getWidth() / 2, absPos.y - getHeight() / 2);
		
		//sprite.setPosition(position.x, position.y);
		sprite.setRotation(-90 + position.originAngle);
		//sprite.setRotation(originAngle);
		
		sprite.draw(batch);
		
		centerSprite.setPosition(absPos.x,  absPos.y);
		centerSprite.draw(batch);
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
	
	
	
	// TODO: Find out what this actually means
	/*
	public double getAccurateArcLength()
	{
		 return 2 * originDistance * Math.asin(sprite.getWidth() / 2 / originDistance);
	}
	*/
}
