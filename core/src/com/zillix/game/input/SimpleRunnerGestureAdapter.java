package com.zillix.game.input;

import com.zillix.game.controllers.LevelController;

public class SimpleRunnerGestureAdapter extends RunnerGestureAdapter {
	public SimpleRunnerGestureAdapter(LevelController controller) {
		super(controller);
	}

	@Override
	 public boolean fling(float velocityX, float velocityY, int button) {
			if(Math.abs(velocityX)>Math.abs(velocityY)){
				if(velocityX>0){
						// TODO swipe right
				}else{
						// TODO swipe left
				}
			}else{
				if(velocityY>0){
						controller.firePressed(button);
				}else{                                  
						controller.jumpPressed(button);
				}
			}
			return super.fling(velocityX, velocityY, button);
   }

}
