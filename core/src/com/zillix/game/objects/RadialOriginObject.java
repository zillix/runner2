package com.zillix.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RadialOriginObject {

	protected Vector2 position;
	
	protected float radius;
	protected Sprite sprite;
	protected float increaseRate;
	protected float minRadius;
	
	public RadialOriginObject()
	{
		position = new Vector2();
	}
	
	public void update(float delta)
	{	
	}
	
	public void draw(SpriteBatch batch)
	{
		sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getWidth() / 2);
		sprite.draw(batch);
	}
	
	public void rotate(float rotateAngle)
	{}
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getIncreaseRate() {
		return increaseRate;
	}

	public void setIncreaseRate(float increaseRate) {
		this.increaseRate = increaseRate;
	}

	public float getMinRadius() {
		return minRadius;
	}

	public void setMinRadius(float minRadius) {
		this.minRadius = minRadius;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
