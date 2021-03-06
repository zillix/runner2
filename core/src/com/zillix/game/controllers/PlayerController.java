package com.zillix.game.controllers;

import java.util.HashMap;
import java.util.Map;

import com.zillix.game.Level;
import com.zillix.game.objects.IceBall;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.collectables.Collectable.CollectableType;

public class PlayerController extends RadialObjectController {
	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	};
	
	private Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
	
	private static final long LONG_JUMP_PRESS = 150l;
	
	private long jumpPressedTime;
	private boolean jumpingPressed;
	
	protected Player player;
	protected boolean doubleJumped = false;
	
	public PlayerController(Player player, Level pLevel)
	{
		super(player, pLevel);
		this.player = player;
		this.usesSurfaceCollision = true;
		this.usesPlatformCollision = true;
		this.allowMovement = true;
		
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	}
	
	@Override
	public void update(float delta)
	{
		processInput();
		super.update(delta);
	}
	
	private float getJumpVelocityMultiplier(int playerState)
	{
		switch (playerState)
		{
		case Player.State.JUMPING:
			return 1.0f;
		case Player.State.DOUBLE_JUMPING:
			return player.getDoubleJumpPercent();
		default:
			return 0.0f;
		}
	}
	
	private void processInput()
	{
		if (keys.get(Keys.JUMP))
		{
			jump(1.0f, true);
		}
		else
		{
			jumpingPressed = false;
		}
		
		if (keys.get(Keys.LEFT))
		{
			player.getAcceleration().x = -player.getRunAcceleration();
		}
		else if (keys.get(Keys.RIGHT))
		{
			player.getAcceleration().x = player.getRunAcceleration();
		}
		else
		{
			player.getAcceleration().x = 0;
		}
	}
	
	private void jump(float multiplier, boolean allowHeldJumps)
	{
		boolean allowJump = false;
		boolean startedJump = false;
		if (!jumpingPressed && player.getState() == Player.State.JUMPING)
		{
			player.setState(Player.State.DOUBLE_JUMPING);
			allowJump = true;
			startedJump = true;
		}
		
		if (!Player.isJumpingState(player.getState()))
		{
			player.setState(Player.State.JUMPING);
			allowJump = true;
			startedJump = true;
		}
		else if (System.currentTimeMillis() - jumpPressedTime < LONG_JUMP_PRESS)
		{
			allowJump = true;
		}
		
		if (startedJump)
		{
			jumpingPressed = true;
			jumpPressedTime = System.currentTimeMillis();
		}
		
		if (allowJump)
		{
			player.getVelocity().y =  -player.getJumpVelocity() * getJumpVelocityMultiplier(player.getState());
		}
 	}
	
	private void fireIceBall()
	{
		if (player.getCollectableCount(CollectableType.ICEBALL) > 0)
		{
			player.consumeCollectable(CollectableType.ICEBALL, 1);
			player.getVelocity().y = Math.min(-player.getIceBallVelocity(), player.getVelocity().y - player.getIceBallVelocity());
			IceBall droppedIce = new IceBall(level.getPlanet());
			droppedIce.setPosition(player.getPosition());
			level.getGameObjects().add(droppedIce);
		}
	}
	
	public void swipeUp(float velocityY)
	{
		System.out.println("Swipe up: " + velocityY);
		jump(1.0f, false);
	}
	
	public void swipeDown(float velocityY)
	{
		fireIceBall();
	}
	
	public void leftPressed(int pointer) {
		keys.put(Keys.LEFT, true);
	}

	public void rightPressed(int pointer) {
		keys.put(Keys.RIGHT, true);
	}

	public void jumpPressed(int pointer) {
		keys.put(Keys.JUMP, true);
	}

	public void firePressed(int pointer) {
		keys.put(Keys.FIRE, true);
		fireIceBall();
	}

	public void leftReleased(int pointer) {
		keys.put(Keys.LEFT, false);
	}

	public void rightReleased(int pointer) {
		keys.put(Keys.RIGHT, false);
	}

	public void jumpReleased(int pointer) {
		keys.put(Keys.JUMP, false);
	}

	public void fireReleased(int pointer) {
		keys.put(Keys.FIRE, false);
	}
	
	@Override
	public void onObjectLanded()
	{
		super.onObjectLanded();
		player.setState(Player.State.IDLE);
		doubleJumped = false;
	}
	
	
}
