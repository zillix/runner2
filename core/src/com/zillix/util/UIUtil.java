package com.zillix.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class UIUtil {

	
	public static Label initLabel(String text, LabelStyle style, int X, int Y)
	{
		Label label = new Label(text, style);
		label.setBounds(0, .2f, Gdx.graphics.getWidth(), 2);
		label.setFontScale(1f, 1f);
		label.setPosition(X,  Y);
		
		return label;
	}
}
