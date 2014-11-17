package com.zillix.game.assets;

import java.util.HashSet;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;

public class ZAssetManager extends AssetManager {
	private HashSet<String> requestedAssets = new HashSet<String>();

	@Override
	public synchronized <T> void load (String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
		if (requestedAssets.contains(fileName))
		{
			return;
		}
		
		requestedAssets.add(fileName);
		super.load(fileName, type, parameter);
	}
	
	public boolean hasRequestedAsset(String fileName)
	{
		return requestedAssets.contains(fileName);
	}
	
	@Override
	public synchronized void unload (String fileName)
	{
		super.unload(fileName);
		requestedAssets.remove(fileName);
	}
}
