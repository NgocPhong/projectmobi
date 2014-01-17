package com.example.demophisic;

/////////////////////////////////////////////////////////////////////////////////
////////////////////1112219 - cu ngoc phong/////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.align.HorizontalAlign;

import com.example.demophisic.SceneManager.SceneType;

public class OptionScene extends BaseScene {
	private static final String XOA = "Xóa Thành Công !";
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
	private Text soundtext;
	private Text resettext;

	private void createOptionChildScene() {
		soundtext = new Text(240, 500, resourcesManager.font, "Sound game :",
				new TextOptions(HorizontalAlign.CENTER), vbom);
		soundtext.setScaleX(0.8f);
		sound_on = new Sprite(240, 400, resourcesManager.sound_on_region, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()){
					sound_off.setScale(3.0f);
				}
				if (pSceneTouchEvent.isActionUp()) {
					sound_off.setScale(3.5f);
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
		sound_off = new Sprite(240, 400, resourcesManager.sound_off_region,
				vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if(pSceneTouchEvent.isActionDown()){
					sound_on.setScale(3.0f);
				}
				if (pSceneTouchEvent.isActionUp()) {
					sound_on.setScale(3.5f);
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
		resettext = new Text(240, 300, resourcesManager.font, "reset game :",
				new TextOptions(HorizontalAlign.CENTER), vbom);
		final Text ketqua = new Text(240, 50, resourcesManager.font, XOA,
				new TextOptions(HorizontalAlign.CENTER), vbom);
		ketqua.setVisible(false);
		
		ketqua.setColor(getColor().BLACK);
		ketqua.setScale(0.5f);
		resettext.setScale(0.8f);
		reset = new Sprite(240, 200, resourcesManager.reset_region, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()){
					this.setScale(0.3f);
				}
				if (pSceneTouchEvent.isActionUp()) {
					this.setScale(0.5f);
					infor.nhaplevel(1);
					infor.nhapquan(3);
					infor.nhapthoigian(30);
					infor.nhapTien(30);
					infor.nhapGiaItem(1, 3);
					infor.nhapGiaItem(2, 3);
					ketqua.setVisible(true);
					this.registerUpdateHandler(new TimerHandler(1.0f,
							new ITimerCallback() {
								public void onTimePassed(TimerHandler pTimerHandler) {
									ketqua.setVisible(false);

								}
							}));
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
		this.registerTouchArea(sound_on);
		this.registerTouchArea(reset);
		this.attachChild(sound_on);
		this.attachChild(reset);
		this.attachChild(resettext);
		this.attachChild(soundtext);
		this.attachChild(ketqua);
	}
}
