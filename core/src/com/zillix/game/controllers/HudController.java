package com.zillix.game.controllers;

import com.zillix.game.ui.HudModel;
import com.zillix.game.ui.UIPopup;

public class HudController {
	
	private HudModel hud;
	
	public HudController(HudModel hud)
	{
		this.hud = hud;
	}
	
	public void close(UIPopup popup)
	{
		hud.closePopup(popup);
	}
	
	public void open(UIPopup popup)
	{
		hud.openPopup(popup);
	}
}
