package com.zillix.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.zillix.game.Level;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.controllers.HudController;
import com.zillix.game.controllers.LevelController;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.collectables.Collectable.CollectableType;
import com.zillix.game.renderers.HudRenderer;
import com.zillix.util.UIUtil;

public class GameHudModel extends HudModel {
	private Label iceBallLabel;
	private Label coinLabel;
	private Label fpsLabel;
	
	private LevelController levelController;
	

	private TextButton shopButton;
	
	private ShopMenu shopMenu;
	
	public GameHudModel(LevelController levelController)
	{
		this.levelController = levelController;
	}
	
	@Override
	public void setup(final HudController controller, HudRenderer renderer, ZAssetManager assetManager)
	{
		super.setup(controller, renderer, assetManager);
		
		BitmapFont font = renderer.getFont();
		
		LabelStyle textStyle = new LabelStyle();
		textStyle.font = font;
		
		shopMenu = new ShopMenu(600, 480, font, assetManager, controller) {
			public void onClose()
			{
				levelController.togglePause(false);
			}
		};
		
		
		coinLabel = UIUtil.initLabel("Coins", textStyle, 20, Gdx.graphics.getHeight() - 20);
		stage.addActor(coinLabel);
		
		iceBallLabel = UIUtil.initLabel("Ice Balls", textStyle, 20, Gdx.graphics.getHeight() - 40);
		stage.addActor(iceBallLabel);
		
		fpsLabel = UIUtil.initLabel("FPS", textStyle, 20, Gdx.graphics.getHeight() - 60);
		stage.addActor(fpsLabel);
		
		shopButton = new ShopButton((int)(Gdx.graphics.getWidth() / 2),
				Gdx.graphics.getHeight() - 100,
				font,
				assetManager);
		
		shopButton.addListener(new InputListener() {
	            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	                // Do nothing
	            	return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	               controller.open(shopMenu);
	               levelController.togglePause(true);
	        }
	    });
		
		stage.addActor(shopButton);
	}
	
	@Override
	public void update(Player player)
	{
		iceBallLabel.setText("Ice Balls: " + player.getCollectableCount(CollectableType.ICEBALL));
		coinLabel.setText("Coins: " + player.getCollectableCount(CollectableType.COIN));
		fpsLabel.setText("FPS: " + 1 / Gdx.graphics.getRawDeltaTime());
	}
}
