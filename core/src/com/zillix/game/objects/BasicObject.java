package com.zillix.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zillix.util.PolarUtil;

public class BasicObject {
	protected Vector2 acceleration;
	protected Vector2 velocity;
	protected Vector2 maxVelocity;
	protected Vector2 defaultVelocity;
	protected Vector2 deceleration;
	
	protected Rectangle bounds;
	
	protected RadialPosition position;
	
	public BasicObject()
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
		position = new RadialPosition(null,  0,  0);
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
	}
	
	protected void setBounds(float X, float Y, float W, float H)
	{
		bounds.set(X,  Y,  W,  H);
	}
	
	protected void setBounds(Rectangle bounds)
	{
		this.bounds.set(bounds.x, bounds.y, bounds.width, bounds.height);
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

	public RadialPosition getPosition() {
		return position;
	}

	public void setPosition(RadialPosition position) {
		this.position = position.copy();
	}
	
	
	
}
