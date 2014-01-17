package com.example.demophisic;

/////////////////////////////////////////////////////////////////////////////////
////////////////////1112219 - cu ngoc phong/////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;


import com.example.demophisic.SceneManager.SceneType;

public class LoadingScene extends BaseScene {

	@Override
	public void createScene() {
		setBackground(new Background(Color.WHITE));
		
	    attachChild(new Text(240, 400, resourcesManager.font, "Loading...", vbom));
	}

	@Override
	public void onBackKeyPressed() {
		return;
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_LOADING;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

}
