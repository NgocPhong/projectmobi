package com.example.demophisic;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

import com.example.demophisic.SceneManager.SceneType;

public class OptionScene extends BaseScene {
	private static AcessHelper infor = new AcessHelper();

	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		createOptionChildScene();
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		SceneManager.getInstance().backmenuScene();
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_OPTION;
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

	private Sprite sound_on;
	private Sprite sound_off;
	private Sprite reset;

	private void createOptionChildScene() {
		sound_on = new Sprite(240, 500, resourcesManager.sound_on_region, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					OptionScene.this.registerTouchArea(sound_off);
					OptionScene.this.unregisterTouchArea(sound_on);
					this.setVisible(true);
					sound_off.setVisible(false);
					engine.getMusicManager().setMasterVolume(1.0f);
				}
				// TODO Auto-generated method stub
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};
		sound_off = new Sprite(240, 500, resourcesManager.sound_off_region,
				vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (pSceneTouchEvent.isActionUp()) {
					OptionScene.this.registerTouchArea(sound_on);
					OptionScene.this.unregisterTouchArea(sound_off);
					this.setVisible(true);
					sound_on.setVisible(false);
					engine.getMusicManager().setMasterVolume(0.0f);
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};
		reset = new Sprite(240, 200, resourcesManager.reset_region, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					infor.nhaplevel(1);
					infor.nhapquan(3);
					infor.nhapthoigian(20);
					infor.nhapdiemcao(0);
				}
				// TODO Auto-generated method stub
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};
		sound_off.setScale(3.5f);
		sound_on.setScale(3.5f);
		sound_off.setVisible(false);
		this.attachChild(sound_off);
		reset.setScale(0.5f);
		this.registerTouchArea(sound_off);
		this.registerTouchArea(reset);
		this.attachChild(sound_on);
		this.attachChild(reset);
	}
}
