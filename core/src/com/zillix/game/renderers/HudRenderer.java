package com.zillix.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.zillix.game.Level;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.collectables.Collectable.CollectableType;

public class HudRenderer implements IRenderer {
	private Level level;
	private BitmapFont font;
	private Player player;
	
	private Stage stage;
	private Table table;
	private ShapeRenderer shapeRenderer;
	
	private Label iceBallLabel;
	private Label coinLabel;
	private Label fpsLabel;
	
	public HudRenderer(Level level)
	{
		this.level = level;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		player = level.getPlayer();
		
		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		shapeRenderer = new ShapeRenderer();
		
		setup();
	}
	
	private void setup()
	{
		LabelStyle textStyle;
		BitmapFont font = new BitmapFont();
		
		textStyle = new LabelStyle();
		textStyle.font = font;
		
		
		coinLabel = initLabel("Coins", textStyle, 20, Gdx.graphics.getHeight() - 20);
		stage.addActor(coinLabel);
		
		iceBallLabel = initLabel("Ice Balls", textStyle, 20, Gdx.graphics.getHeight() - 40);
		stage.addActor(iceBallLabel);
		
		fpsLabel = initLabel("FPS", textStyle, 20, Gdx.graphics.getHeight() - 60);
		stage.addActor(fpsLabel);
	}
	
	private Label initLabel(String text, LabelStyle style, int X, int Y)
	{
		Label label = new Label(text, style);
		label.setBounds(0, .2f, Gdx.graphics.getWidth(), 2);
		label.setFontScale(1f, 1f);
		label.setPosition(X,  Y);
		
		return label;
	}
	
	public void render(SpriteBatch batch, OrthographicCamera camera, float delta)
	{
		updateText();
		
		//batch.begin();
		stage.draw();
			/*font.drawMultiLine(batch,
					"Ice Balls: " + Integer.toString(player.getCollectableCount(CollectableType.ICEBALL)),
					20,
					Gdx.graphics.getHeight() - 20);
			font.drawMultiLine(batch,
					"Coins: " + Integer.toString(player.getCollectableCount(CollectableType.COIN)),
					20,
					Gdx.graphics.getHeight() - 40);
			font.drawMultiLine(batch,
					"FPS: " + 1 / Gdx.graphics.getRawDeltaTime(),
					20,
					Gdx.graphics.getHeight() - 60);
					*/
		//batch.end();
	}
	
	private void updateText()
	{
		iceBallLabel.setText("Ice Balls: " + player.getCollectableCount(CollectableType.ICEBALL));
		coinLabel.setText("Coins: " + player.getCollectableCount(CollectableType.COIN));
		fpsLabel.setText("FPS: " + 1 / Gdx.graphics.getRawDeltaTime());
	}
	
	public void dispose()
	{
		font.dispose();
	}
	
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height, true);
	}
}
