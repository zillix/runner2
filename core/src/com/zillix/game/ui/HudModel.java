package com.zillix.game.ui;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.controllers.HudController;
import com.zillix.game.objects.Player;
import com.zillix.game.renderers.HudRenderer;

public class HudModel {
	
	protected Stage stage;

	private ArrayList<UIPopup> openPopups = new ArrayList<UIPopup>();
	
	public HudModel()
	{}
	
	public ArrayList<UIPopup> getOpenPopups() {
		return openPopups;
	}
	public void setOpenPopups(ArrayList<UIPopup> openPopups) {
		this.openPopups = openPopups;
	}
	
	public void openPopup(UIPopup popup)
	{
		if (!openPopups.contains(popup))
		{
			openPopups.add(popup);
			stage.addActor(popup);
		}
	}
	
	public void closePopup(UIPopup popup)
	{
		if (openPopups.contains(popup))
		{
			if (popup.remove())
			{
				openPopups.remove(popup);
			}
		}
	}
	
	public void setup(final HudController controller, HudRenderer renderer, ZAssetManager assetManager)
	{
		stage = renderer.getUIStage();
	}
	
	public void update(Player player)
	{}
}
