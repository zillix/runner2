package com.zillix.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zillix.game.objects.collectables.Collectable;
import com.zillix.game.objects.collectables.Collection;
import com.zillix.game.objects.collectables.ICollector;

public class Player extends RadialObject implements ICollector {

	private double health;
	private double maxHealth;
	private float runAcceleration;
	private float jumpVelocity;
	private int state;
	private float iceBallVelocity;
	
	private float doubleJumpPercent;
	
	private static final String PLAYER_IMAGE_PATH = "objects/coin.png";
	
	private Collection _collection;
	
	public static class State
	{
		public static final int IDLE = 0;
		public static final int JUMPING = 1;
		public static final int FALLING = 2;
		public static final int DOUBLE_JUMPING = 3;
	}
	
	public Player(RadialOriginObject pOrigin, float pRadius, float pAngle)
	{
		super(pOrigin);
		
		setOriginDistance(pRadius);
		setOriginAngle(pAngle);
		
		_collection = new Collection();
		
		// Temp code for configuring movement
		maxVelocity.set(60 * 20, 50 * 30);
		acceleration.set(0f, 1.5f * 50 * 30); // should use planet.gravity
		deceleration.set(1.3f * 30 * 30, 0f);
		runAcceleration = 2 * 30 * 30;
		jumpVelocity = 15 * 30;
		doubleJumpPercent = 1.5f;
		iceBallVelocity = 15 * 30;
	}
	
	public static boolean isJumpingState(int state)
	{
		return state == State.JUMPING || state == State.DOUBLE_JUMPING;
	}
	
	@Override
	protected String getImagePath()
	{
		return PLAYER_IMAGE_PATH;
	}
	
	public void collect(Collectable collectable)
	{
		_collection.addCollectable(collectable);
	}
	
	public boolean isTouching(Collectable collectable)
	{
		return collectable.isTouching(this);
	}
	
	public int getCollectableCount(Collectable.CollectableType type)
	{
		return _collection.getCount(type);
	}
	
	public int getIceBallCount()
	{
		return getCollectableCount(Collectable.CollectableType.ICEBALL);
	}
	
	public int getMoneyCount()
	{
		return getCollectableCount(Collectable.CollectableType.COIN);
	}
	
	public void consumeCollectable(Collectable.CollectableType type, int count)
	{
		_collection.decrementCollectable(type, count);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public float getRunAcceleration() {
		return runAcceleration;
	}

	public void setRunAcceleration(float runAcceleration) {
		this.runAcceleration = runAcceleration;
	}

	public float getJumpVelocity() {
		return jumpVelocity;
	}

	public void setJumpVelocity(float jumpVelocity) {
		this.jumpVelocity = jumpVelocity;
	}
	
	public float getIceBallVelocity() {
		return iceBallVelocity;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state)
	{
		this.state = state;
	}

	public float getDoubleJumpPercent() {
		return doubleJumpPercent;
	}

	public void setDoubleJumpPercent(float doubleJumpPercent) {
		this.doubleJumpPercent = doubleJumpPercent;
	}
	
}
