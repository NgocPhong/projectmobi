package com.TP.dd;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.TP.dd.SceneManager.SceneType;

public class SplashScene extends BaseScene {

	private Sprite splash;
	@Override
	public void createScene() {
		splash = new Sprite(0,0, resourcesManager.splash_region, vbom);
		
		        
		//splash.setScale(1.5f);
		splash.setPosition(400, 240);
		attachChild(splash);
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub

	}

	@Override
	public SceneType getSceneType() {
		 return SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene() {
		splash.detachSelf();
	    splash.dispose();
	    this.detachSelf();
	    this.dispose();

	}

}
