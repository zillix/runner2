package com.zillix.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.zillix.game.controllers.RadialObjectController;
import com.zillix.util.PolarUtil;

public class RadialObject implements Poolable {

	protected Sprite sprite;
	protected Sprite centerSprite;
	
	Vector2 acceleration;
	Vector2 velocity;
	Vector2 maxVelocity;
	Vector2 defaultVelocity;
	Vector2 deceleration;
	
	Rectangle bounds;
	
	protected RadialObjectController movement;
	
	protected RadialPosition position;
	
	protected Boolean isDead;
	
	protected Pool<? extends RadialObject> pool;
	
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
	
	protected void initialize()
	{
		acceleration = new Vector2();
		velocity = new Vector2();
		maxVelocity = new Vector2();
		defaultVelocity = new Vector2();
		deceleration = new Vector2();
		bounds = new Rectangle();
		sprite = new Sprite();

		reset();
	}
	
	public void reset()
	{
		acceleration.set(0, 0);
		velocity.set(0, 0);
		maxVelocity.set(100 * 30, 100 * 30);
		defaultVelocity.set(0,0);
		deceleration.set(0, 0);
		bounds.set(0, 0, 0, 0);
		
		if (position != null)
		{
			position.reset();
		}
		
		/*if (sprite != null && sprite.getTexture() != null)
		{
			sprite.getTexture().dispose();
		}*/
		isDead = false;
	}
	
	public void setup(RadialOriginObject pOrigin)
	{
		// TODO: Don't allocate these!
		position = new RadialPosition(pOrigin, 0, 0);
		sprite = new Sprite(new Texture(getImagePath()));
		sprite.setOriginCenter();
		setBounds(-sprite.getWidth() / 2, -sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
		
		centerSprite = new Sprite(new Texture(CENTER_SPRITE_PATH));
		centerSprite.setOriginCenter();
	}
	
	public void debugUpdate(float delta)
	{
	}
	
	protected void setBounds(float X, float Y, float W, float H)
	{
		bounds.set(X,  Y,  W,  H);
	}
	
	protected void setBounds(Rectangle bounds)
	{
		this.bounds.set(bounds.x, bounds.y, bounds.width, bounds.height);
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
	
	public RadialObject getObjectAtNextFrame(float deltaTime)
	{
		double rotateAngle = 360 * (velocity.x /*+ acceleration.x * deltaTime*/) * deltaTime / (position.originDistance * 2 * Math.PI);
		double nextRadius = position.originDistance - (velocity.y /*+ acceleration.y * deltaTime*/) * deltaTime;
		
		// TODO: Don't allocate a new radialobject!
		RadialObject next = new RadialObject(position.origin);
		next.setOriginAngle((float)(position.originAngle + rotateAngle));
		next.setOriginDistance((float)nextRadius);
		next.setBounds(bounds);
		return next;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Vector2 getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(Vector2 maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public Vector2 getDefaultVelocity() {
		return defaultVelocity;
	}

	public void setDefaultVelocity(Vector2 defaultVelocity) {
		this.defaultVelocity = defaultVelocity;
	}

	public Vector2 getDeceleration() {
		return deceleration;
	}

	public void setDeceleration(Vector2 deceleration) {
		this.deceleration = deceleration;
	}

	public float getOriginDistance() {
		return position.originDistance;
	}

	public void setOriginDistance(float originDistance) {
		position.setOriginDistance(originDistance);
	}

	public float getOriginAngle() {
		return position.originAngle;
	}
	
	public float getOriginAngleRadians() {
		return (float)PolarUtil.toRadians(position.originAngle);
	}

	public void setOriginAngle(float originAngle) {
		position.setOriginAngle(originAngle);
	}
	
	public float getWidth() {
		return bounds.width;
	}
	
	public float getHeight() {
		return bounds.height;
	}
	
	public float getBottomDistance() {
		return getOriginDistance() - (bounds.y + bounds.height);
	}
	
	public float getTopDistance() {
		return getOriginDistance() - bounds.y;
	}
	
	public void setBottomDistance(float distance)	{
		setOriginDistance(distance - bounds.y);
	}
	
	public void setTopDistance(float distance) {
		setOriginDistance(distance - (bounds.y + bounds.height));
	}

	public Boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(Boolean isDead) {
		this.isDead = isDead;
	}

	public RadialPosition getPosition() {
		return position;
	}

	public void setPosition(RadialPosition position) {
		this.position = position.copy();
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
