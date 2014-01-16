package com.example.demophisic;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

public class LevelCompleteWindow extends Sprite {
	GameScene game;
	private TiledSprite star1;
	private TiledSprite star2;
	private TiledSprite star3;

	public enum StarsCount {
		ZERO, ONE, TWO, THREE
	}

	public LevelCompleteWindow(
			VertexBufferObjectManager pSpriteVertexBufferObject, GameScene scene) {

		super(0, 0, 460, 400,
				ResourcesManager.getInstance().complete_window_region,
				pSpriteVertexBufferObject);
		game = scene;

		attachStars(pSpriteVertexBufferObject);
		attachmenu(pSpriteVertexBufferObject);
	}

	private void attachStars(VertexBufferObjectManager pSpriteVertexBufferObject) {
		star1 = new TiledSprite(130, 190,
				ResourcesManager.getInstance().complete_stars_region,
				pSpriteVertexBufferObject);
		star2 = new TiledSprite(230, 230,
				ResourcesManager.getInstance().complete_stars_region,
				pSpriteVertexBufferObject);
		star3 = new TiledSprite(330, 190,
				ResourcesManager.getInstance().complete_stars_region,
				pSpriteVertexBufferObject);

		attachChild(star1);
		attachChild(star2);
		attachChild(star3);
	}

	private Sprite next;
	private Sprite home;
	private Sprite restart;

	private void attachmenu(VertexBufferObjectManager pSpriteVertexBufferObject) {
		next = new Sprite(350, 80,
				ResourcesManager.getInstance().complete_next_region,
				pSpriteVertexBufferObject) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp())
					game.nextlevel();
				Log.v("hehe", "next");
				// TODO Auto-generated method stub
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};

		home = new Sprite(230, 80,
				ResourcesManager.getInstance().complete_home_region,
				pSpriteVertexBufferObject) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					SceneManager.getInstance().loadMenuScene(game.engine);
				}
				// TODO Auto-generated method stub
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		restart = new Sprite(100, 80,
				ResourcesManager.getInstance().complete_restart_region,
				pSpriteVertexBufferObject) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp())
					game.restartlevel();
				Log.v("restart", "restart");
				// TODO Auto-generated method stub
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		restart.setScale(0.5f);
		home.setScale(0.5f);
		next.setScale(0.5f);
		attachChild(next);
		attachChild(home);
		attachChild(restart);
	}

	/**
	 * Change star`s tile index, depends on stars count.
	 * 
	 * @param starsCount
	 */
	public void display(StarsCount starsCount, Scene scene, Camera camera) {
		// Change stars tile index, based on stars count (1-3)
		switch (starsCount) {
		case ZERO:
			star1.setCurrentTileIndex(1);
			star2.setCurrentTileIndex(1);
			star3.setCurrentTileIndex(1);
			break;
		case ONE:
			star1.setCurrentTileIndex(0);
			star2.setCurrentTileIndex(1);
			star3.setCurrentTileIndex(1);
			break;
		case TWO:
			star1.setCurrentTileIndex(0);
			star2.setCurrentTileIndex(0);
			star3.setCurrentTileIndex(1);
			break;
		case THREE:
			star1.setCurrentTileIndex(0);
			star2.setCurrentTileIndex(0);
			star3.setCurrentTileIndex(0);
			break;
		}

		// Hide HUD
		camera.getHUD().setVisible(true);

		// Disable camera chase entity
		camera.setChaseEntity(null);

		// Attach our level complete panel in the middle of camera
		setPosition(camera.getCenterX(), camera.getCenterY() + 100);
		scene.registerTouchArea(next);
		scene.registerTouchArea(home);
		scene.registerTouchArea(restart);
		scene.attachChild(this);
		scene.clearUpdateHandlers();

	}
}
