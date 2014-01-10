package com.example.demophisic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.text.TickerText;
import org.andengine.entity.text.TickerText.TickerTextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.SAXUtils;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.constants.LevelConstants;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.andengine.util.level.simple.SimpleLevelLoader;
import org.xml.sax.Attributes;

import android.opengl.GLES20;
import android.util.Log;

import com.example.demophisic.LevelCompleteWindow.StarsCount;
import com.example.demophisic.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnMenuItemClickListener {
	private static AcessHelper infor;
	private static final String TAG_ENTITY = "entity";
	private static final String TAG_ENTITY_ATTRIBUTE_X = "x";
	private static final String TAG_ENTITY_ATTRIBUTE_Y = "y";
	private static final String TAG_ENTITY_ATTRIBUTE_TYPE = "type";
	private static final String TAG_ENTITY_ATTRIBUTE_Time = "time";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_BALLOON = "nomalballoon";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_TRAP = "trap";

	private static final String TAG_LEVEL_ATTRIBUTE_Keyword = "Keyword";
	private static final String TAG_LEVEL_ATTRIBUTE_SPEED = "speed";
	private static final String TAG_ENTITY_ATTRIBUTE_Char = "char";
	private static String keyword = "lk";
	private static String levelscore = "";
	private LevelCompleteWindow levelCompleteWindow;
	private static int pos = 0;
	private List<AnimatedSprite> listballoon = new ArrayList<AnimatedSprite>();

	// thong tin nguoi choi
	private static int Mang;
	private static int thoigian;
	private static int speed;
	private static Text textkey;

	private void loadLevel(int levelID) {
		final SimpleLevelLoader levelLoader = new SimpleLevelLoader(vbom);

		levelLoader
				.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(
						LevelConstants.TAG_LEVEL) {
					public IEntity onLoadEntity(
							final String pEntityName,
							final IEntity pParent,
							final Attributes pAttributes,
							final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData)
							throws IOException {
						keyword = SAXUtils.getAttributeOrThrow(pAttributes,
								TAG_LEVEL_ATTRIBUTE_Keyword);
						speed = SAXUtils.getIntAttributeOrThrow(pAttributes,
								TAG_LEVEL_ATTRIBUTE_SPEED);
						textkey = new TickerText(240, 400,
								resourcesManager.font, keyword,
								new TickerTextOptions(HorizontalAlign.CENTER,
										5), vbom);
						textkey.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(new AlphaModifier(
										5, 0.0f, 1.0f))) {

							@Override
							protected void onModifierFinished(IEntity pItem) {
								// TODO Auto-generated method stub
								textkey.setVisible(false);
								super.onModifierFinished(pItem);
							}

						});
						textkey.setBlendFunction(GLES20.GL_SRC_ALPHA,
								GLES20.GL_ONE_MINUS_SRC_ALPHA);
						GameScene.this.attachChild(textkey);
					

						return GameScene.this;
					}

				});
		levelLoader
				.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(
						TAG_ENTITY) {
					public IEntity onLoadEntity(
							final String pEntityName,
							final IEntity pParent,
							final Attributes pAttributes,
							final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData)
							throws IOException {
						final int x = SAXUtils.getIntAttributeOrThrow(
								pAttributes, TAG_ENTITY_ATTRIBUTE_X);
						final int y = SAXUtils.getIntAttributeOrThrow(
								pAttributes, TAG_ENTITY_ATTRIBUTE_Y);
						final String type = SAXUtils.getAttributeOrThrow(
								pAttributes, TAG_ENTITY_ATTRIBUTE_TYPE);
						final float time = SAXUtils.getFloatAttributeOrThrow(
								pAttributes, TAG_ENTITY_ATTRIBUTE_Time);
						final String mchar = SAXUtils.getAttributeOrThrow(
								pAttributes, TAG_ENTITY_ATTRIBUTE_Char);

						GameScene.this.registerUpdateHandler(new TimerHandler(
								time, new ITimerCallback() {

									@Override
									public void onTimePassed(
											TimerHandler pTimerHandler) {
										// TODO Auto-generated method stub
										additem(x, y, type, mchar, keyword);
									}

								}));

						return null;
					}
				});

		levelLoader.loadLevelFromAsset(activity.getAssets(), "level/" + levelID
				+ ".lvl");
	}

	private static int curentlevel = 1;

	// man tiep theo
	public void nextlevel() {

		GameScene.this.clearUpdateHandlers();
		this.detachball();
		this.detachChild(levelCompleteWindow);
		this.detachChild(textkey);
		//this.setIgnoreUpdate(false);
		this.clearEntityModifiers();
		//this.clearTouchAreas();
		init();

		curentlevel += 1;
		infor.nhaplevel(curentlevel);
		loadLevel(curentlevel);
		// infor.nhaplevel(curentlevel);
	}

	// choi lai man
	public void restartlevel() {
		GameScene.this.clearUpdateHandlers();
		this.detachball();
		this.detachChild(levelCompleteWindow);
		this.detachChild(textkey);
		this.setIgnoreUpdate(false);
		this.clearEntityModifiers();
		
		init();
		loadLevel(curentlevel);
	}

	// them bong
	public void additem(int x, int y, String type, String mchar,
			final String key) {
		final AnimatedSprite face;

		if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_BALLOON)) {
			face = makeballoom(x, y, mchar);
			/*
			 * new balloon(x, y, resourcesManager.animate_balloon, vbom,
			 * character) {
			 * 
			 * @Override public boolean onAreaTouched(TouchEvent
			 * pSceneTouchEvent, float X, float Y) { if
			 * (pSceneTouchEvent.isActionDown()) { this.setScale(1.5f); }
			 * if(pSceneTouchEvent
			 * .isActionOutside()||pSceneTouchEvent.isActionUp()){ detachSelf();
			 * resourcesManager.music.play(); if
			 * (checkcorrect(this.getmText())==false){
			 * 
			 * }
			 * 
			 * 
			 * 
			 * } return true; } };
			 */

		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_TRAP)) {
			// vat the khac
			face = maketrap(x, y, mchar);
			/*
			 * new balloon(x, y, resourcesManager.animate_balloon, vbom,
			 * character) {
			 * 
			 * @Override public boolean onAreaTouched(TouchEvent
			 * pSceneTouchEvent, float X, float Y) { if
			 * (pSceneTouchEvent.isActionDown()) {
			 * 
			 * // decreadmang(); detachSelf();
			 * 
			 * } return true; } };
			 */

		
		} else {
			throw new IllegalArgumentException();
		}
		MoveYModifier move = new MoveYModifier(speed, 0, 800) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				pItem.setVisible(false);
				GameScene.this.unregisterTouchArea(pItem);

				// TODO Auto-generated method stub
				super.onModifierFinished(pItem);
			}
		};
		face.registerEntityModifier(move);
		/*
		 * MoveYModifier move = new MoveYModifier(10, 0, 600) {
		 * 
		 * @Override protected void onModifierFinished(IEntity pItem) {
		 * pItem.setVisible(false); // face.detachSelf(); // TODO Auto-generated
		 * method stub super.onModifierFinished(pItem); } }; if
		 * (move.isFinished()) { face.detachSelf(); }
		 * face.registerEntityModifier(move);
		 */
		// registerTouchArea(face);
		listballoon.add(face);
		face.setCullingEnabled(true);

		this.attachChild(face);
	}

	@Override
	public void createScene() {
	

		this.setBackground(new SpriteBackground(new Sprite(240, 400,
				resourcesManager.game_background_region, vbom)));
		infor = new AcessHelper();
		createHUD();
		init();
		createGame();
		levelCompleteWindow = new LevelCompleteWindow(vbom, this);
		this.registerTouchArea(levelCompleteWindow);
		// loadLevel(1);

	}

	Sprite rainbow;

	// balloon face;
	private void createGame() {

		// animate.animate(new long[]{100, 100, 100},3,5,true);
		loadLevel(curentlevel);
	}

	private HUD gameHUD;
	private Text keyString;
	private Text mangText;
	private Text timer;
	// menu
	private MenuScene gameChildScene;
	private static IMenuItem playGameItem;
	private static IMenuItem PauseGameItem;
	private final int Game_PLAY = 0;
	private final int Game_Pause = 1;
private Sprite clock;
	private void createHUD() {
		// create pause button
		gameChildScene = new MenuScene(camera);
		gameChildScene.setPosition(0, 100);
		playGameItem = new ScaleMenuItemDecorator(new SpriteMenuItem(Game_PLAY,
				resourcesManager.playgame_region, vbom), 1.1f, 1);
		PauseGameItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
				Game_Pause, resourcesManager.pausegame_region, vbom), 1.1f, 1);

		gameChildScene.addMenuItem(playGameItem);
		gameChildScene.addMenuItem(PauseGameItem);

		gameChildScene.buildAnimations();
		gameChildScene.setBackgroundEnabled(false);

		playGameItem.setPosition(50, 650);
		PauseGameItem.setPosition(50, 650);
		playGameItem.setVisible(false);
		gameChildScene.unregisterTouchArea(playGameItem);
		gameChildScene.setOnMenuItemClickListener(this);
		// create HUD
		gameHUD = new HUD();
		// create rain bow
		rainbow = new Sprite(240, 100, resourcesManager.rainwbow, vbom);
		
		keyString = new Text(300, 200, resourcesManager.font,
				"mang: 0123456789asdfghjklqwertyuiopzxcvbnm", new TextOptions(
						HorizontalAlign.CENTER), vbom);
		keyString.setText("");
		keyString.setPosition(240, 70);
		rainbow.attachChild(keyString);
		Sprite heart = new Sprite(300, 750, resourcesManager.heart_region, vbom);
		heart.setScale(0.3f);
		gameHUD.attachChild(rainbow);
		gameHUD.attachChild(heart);

		// CREATE mang
		mangText = new Text(400, 750, resourcesManager.font, "X 0123456789",
				new TextOptions(HorizontalAlign.LEFT), vbom);
		
		mangText.setText(String.valueOf(this.Mang) + " X");
		//CREATE timer
		clock = new Sprite(130, 750, resourcesManager.balloon_region, vbom);
		clock.setScale(0.3f);
		timer = new Text(200, 750, resourcesManager.font, "X 0123456789",
				new TextOptions(HorizontalAlign.LEFT), vbom);
		timer.setText(String.valueOf(timeRemaining));
		gameHUD.attachChild(mangText);
		gameHUD.attachChild(clock);
		gameHUD.attachChild(timer);
		setChildScene(gameChildScene);
		camera.setHUD(gameHUD);
	}

	public void detachball() {
		for (int i = 0; i < listballoon.size(); i++) {
			listballoon.get(i).detachSelf();
			
		}
		listballoon.clear();
	
	}

	public void unresigntouchballoon() {
		for (int i = 0; i < listballoon.size(); i++) {
			this.unregisterTouchArea(listballoon.get(i));
		}
	}

	public void resigntouchballoon() {
		for (int i = 0; i < listballoon.size(); i++) {
			this.registerTouchArea(listballoon.get(i));
		}
	}
	private static TimerHandler timerHandler; 
	private  static int timeRemaining = 20;
	//count time
	private void counttime(){
		
		// ...
		timerHandler = new TimerHandler(1.0f, new ITimerCallback() {
		        public void onTimePassed(TimerHandler pTimerHandler) {
		                timeRemaining--;
		                if (timeRemaining == 0) {
		                	cacula_score();
		                }
		                if (timeRemaining <= 10) {
		                     //   timerTickSound.play();
		                }
		                if (timeRemaining > 0){
		                	   timerHandler.reset();
		                	   Log.v("time",String.valueOf(timeRemaining));
		                	   timer.setText(String.valueOf(timeRemaining));
		                }
		                     
		        }
		});
		this.registerUpdateHandler(timerHandler);
	}
	// init value
	public void init() {
		this.keyString.setText("");
		this.keyword = "";
		timeRemaining = infor.Getthoigian();
		this.detachChildren();
		this.pos = 0;
		this.levelscore = "";
		counttime();
		curentlevel = infor.Getlevel();

		this.Mang = infor.Getquan();
		mangText.setText(String.valueOf(Mang) + " X");
		Log.v("quan", String.valueOf(Mang));
		this.thoigian = infor.Getthoigian();
		pause = new Sprite(240, 400, this.resourcesManager.game_pause_region,
				vbom);

		this.attachChild(pause);
		pause.setVisible(false);
	}

	// caculate score
	public void cacula_score() {
		detachball();
		if (levelscore.length() == 0) {
			levelCompleteWindow
					.display(StarsCount.ZERO, GameScene.this, camera);
			Log.v("score", "zero star");
		} else if (levelscore.length() < keyword.length() / 2) {
			// one start
			// detachball();
			levelCompleteWindow.display(StarsCount.ONE, GameScene.this, camera);
			Log.v("score", "one star");
		} else if (levelscore.length() < keyword.length()) {

			levelCompleteWindow.display(StarsCount.TWO, GameScene.this, camera);
			Log.v("score", "two star");
		} else if (levelscore.length() == keyword.length()) {

			levelCompleteWindow.display(StarsCount.THREE, GameScene.this,
					camera);
			Log.v("score", "three star");
		}
	}

	// check key is equals keyword
	public boolean checkkey() {
		
		if (keyString.getText().equals(keyword)){
			cacula_score();
			return true;
		}
			
		return false;
	}

	// check each character
	public boolean checkcorrect(char ch) {
		Log.v("position", String.valueOf(pos));
		if (pos < keyword.length()) {
			if (keyword.charAt(pos) == ch) {
				Log.v("checkcorrect", String.valueOf(keyword.charAt(pos)));
				if (keyword.length() > pos)
					pos += 1;
				levelscore = levelscore + String.valueOf(ch);
				keyString.setText(levelscore);
				return true;
			}
		}
		return false;
	}

	// make balloon
	public AnimatedSprite makeballoom(int x, int y, String character) {
		final char ch = character.charAt(0);

		AnimatedSprite animate = new balloon(x, y,
				resourcesManager.animate_balloon, vbom, ch) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (isIstouch() == false) {

					this.setIstouch(true);
					this.detachChildren();
					this.animate(new long[] { 100, 200, 300, 200, 200, 200 },
							0, 5, false);
					// GameScene.this.setIgnoreUpdate(true);
					// if (pSceneTouchEvent.isActionUp())
					if (checkcorrect(this.getmText()) == false)
						decreadmang();
					else 
						checkkey();
				}

				// TODO Auto-generated method stub
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

			@Override
			protected void onManagedUpdate(float pSecondsElapsed) {
				if (this.isAnimationRunning() == false
						&& this.getCurrentTileIndex() == 5) {
					this.setVisible(false);
					GameScene.this.unregisterTouchArea(this);
					// Log.v("<touch animate",String.valueOf(this.getCurrentTileIndex()));
				}
				// TODO Auto-generated method stub
				super.onManagedUpdate(pSecondsElapsed);
			}

		};
	/*	MoveYModifier move = new MoveYModifier(10, 0, 800) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				pItem.setVisible(false);
				GameScene.this.unregisterTouchArea(pItem);

				// TODO Auto-generated method stub
				super.onModifierFinished(pItem);
			}
		};
*/
		animate.animate(new long[] { 200, 200 }, 0, 1, true);
	//	animate.registerEntityModifier(move);
		// this.attachChild(animate);
		this.registerTouchArea(animate);
		return animate;

	}

	// make trap
	public AnimatedSprite maketrap(int x, int y, String character) {
		final char ch = character.charAt(0);
		AnimatedSprite animate = new balloon(x, y,
				resourcesManager.animate_trap, vbom, ch) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (isIstouch() == false) {
					this.setIstouch(true);
					this.detachChildren();
					this.animate(new long[] { 100, 200, 300, 200, 200, 200 },
							0, 5, false);
					// GameScene.this.setIgnoreUpdate(true);
					// if (pSceneTouchEvent.isActionUp())

					decreadmang();
				}
				// TODO Auto-generated method stub
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

			@Override
			protected void onManagedUpdate(float pSecondsElapsed) {
				if (this.isAnimationRunning() == false
						&& this.getCurrentTileIndex() == 5) {
					this.setVisible(false);
					GameScene.this.unregisterTouchArea(this);

				}
				// TODO Auto-generated method stub
				super.onManagedUpdate(pSecondsElapsed);
			}

		};
	/*	MoveYModifier move = new MoveYModifier(10, 0, 800) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				pItem.setVisible(false);
				GameScene.this.unregisterTouchArea(pItem);
				// face.detachSelf();
				// TODO Auto-generated method stub
				super.onModifierFinished(pItem);
			}
		};*/

		animate.animate(new long[] { 200, 200 }, 0, 1, true);
	//	animate.registerEntityModifier(move);
		// this.attachChild(animate);
		this.registerTouchArea(animate);
		return animate;

	}

	// gian mang khi chon nham

	private void decreadmang() {
		this.Mang -= 1;
		if (Mang > 0)
			mangText.setText(String.valueOf(Mang) + " X");
		else
			cacula_score();

	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene(engine);

	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		camera.setHUD(null);
		camera.setCenter(240, 400);
		this.detachChildren();
		this.detachSelf();
		this.dispose();
		// TODO code responsible for disposing scene
		// removing all game scene objects.
	}

	static Sprite pause;

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case Game_PLAY:
			this.setIgnoreUpdate(false);
			resigntouchballoon();
			pause.setVisible(false);
			// this.getLastChild().detachSelf();
			playGameItem.setVisible(false);
			PauseGameItem.setVisible(true);
			gameChildScene.unregisterTouchArea(playGameItem);
			gameChildScene.registerTouchArea(PauseGameItem);
			// this.unregisterTouchArea(this);
			return true;
		case Game_Pause:

			this.setIgnoreUpdate(true);

			gameChildScene.unregisterTouchArea(PauseGameItem);
			gameChildScene.registerTouchArea(playGameItem);
			playGameItem.setVisible(true);
			PauseGameItem.setVisible(false);
			pause.setVisible(true);
			unresigntouchballoon();

			return true;
		default:
			return false;
		}
	}

}
