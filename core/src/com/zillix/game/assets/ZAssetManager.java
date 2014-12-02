package com.zillix.game.assets;

import java.util.HashSet;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;

public class ZAssetManager extends AssetManager {
	private HashSet<String> requestedAssets = new HashSet<String>();

	@Override
	public synchronized <T> void load (String fileName, Class<T> type) {
		if (requestedAssets.contains(fileName))
		{
			return;
		}
		
		requestedAssets.add(fileName);
		super.load(fileName, type);
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
	
	public synchronized <T> void forceLoad (String fileName, Class<T> type) {
		load(fileName, type);
		finishLoading();
	}
	
	public synchronized <T> void forceLoad (String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
		load(fileName, type, parameter);
		finishLoading();
	}
}
