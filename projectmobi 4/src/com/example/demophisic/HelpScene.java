package com.example.demophisic;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.example.demophisic.SceneManager.SceneType;

public class HelpScene extends BaseScene {
	private static AcessHelper infor = new AcessHelper();

	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		SceneManager.getInstance().backmenuScene();
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_HELP;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

	private void createBackground() {
		attachChild(new Sprite(240, 400,
				resourcesManager.menu_background_region, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
	}

	
}
