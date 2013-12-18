package com.example.demophisic;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.SAXUtils;

import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.constants.LevelConstants;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.andengine.util.level.simple.SimpleLevelLoader;
import org.xml.sax.Attributes;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.example.demophisic.SceneManager.SceneType;

public class GameScene extends BaseScene {
	private static final String TAG_ENTITY = "entity";
	private static final String TAG_ENTITY_ATTRIBUTE_X = "x";
	private static final String TAG_ENTITY_ATTRIBUTE_Y = "y";
	private static final String TAG_ENTITY_ATTRIBUTE_TYPE = "type";
	    
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM1 = "platform1";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM2 = "platform2";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM3 = "platform3";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_COIN = "coin";
	
	public String keyword ="ah";
	private int pos =0;
	private void loadLevel(int levelID)
	{
	    final SimpleLevelLoader levelLoader = new SimpleLevelLoader(vbom);
	    
	    final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(0, 0.01f, 0.5f);
	    
	    levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(LevelConstants.TAG_LEVEL)
	    {
	        public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData) throws IOException 
	        {
	            final int width = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH);
	            final int height = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT);
	            
	            // TODO later we will specify camera BOUNDS and create invisible walls
	            // on the beginning and on the end of the level.

	            return GameScene.this;
	        }

			
	    });
	    
	    levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(TAG_ENTITY)
	    {
	        public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData) throws IOException
	        {
	            final int x = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_X);
	            final int y = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_Y);
	            final String type = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_TYPE);
	            
	            final balloon face;
	            
	            if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM1))
	            {
	            	 face = new balloon(x, y, resourcesManager.balloon_region,
	         				vbom,'r'){
	            		 
	            	 @Override
	         			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
	        					float Y) {
	        				if (pSceneTouchEvent.isActionDown()) {
	        					detachSelf();
	        					
	        					Log.v("<xoa>","xong !");
	        				}
	        				return true;
	         				}};
	            	 Log.v("<position 1>",String.valueOf(x)+"   "+ String.valueOf(y));
	             //   levelObject = new Sprite(x, y, resourcesManager.platform1_region, vbom);
	             //   PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyType.StaticBody, FIXTURE_DEF).setUserData("platform1");
	            } 
	            else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM2))
	            {
	            	 face = new  balloon(x, y, resourcesManager.balloon_region,
		         				vbom,'a'){
	            		 
	            	 @Override
	         			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
	        					float Y) {
	        				if (pSceneTouchEvent.isActionDown()) {
	        					detachSelf();
	        				
	        					if(keyword.charAt(pos) == this.getmText()){
	        						scoreText.setText("Score: " + 1);
	        						Log.v("<scoreText>","xong a !");
	        					}
	        					Log.v("<xoa>","xon a !");
	        				}
	        				return true;
	         				}};
	            	 
	             /*  levelObject = new Sprite(x, y, resourcesManager.platform2_region, vbom);
	                final Body body = PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyType.StaticBody, FIXTURE_DEF);
	                body.setUserData("platform2");
	                physicsWorld.registerPhysicsConnector(new PhysicsConnector(levelObject, body, true, false));*/
	            	 Log.v("<position 2>",String.valueOf(x)+"   "+ String.valueOf(y));
	            }
	            else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM3))
	            {
	            	 face = new balloon(x, y, resourcesManager.balloon_region,
		         				vbom,'v'){
	            		 
	            	 @Override
	         			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
	        					float Y) {
	        				if (pSceneTouchEvent.isActionDown()) {
	        					detachSelf();
	        					
	        					Log.v("<xoa>","xong !");
	        				}
	        				return true;
	         				}};
	            	 Log.v("<position 3>",String.valueOf(x)+"   "+ String.valueOf(y));
	               /* levelObject = new Sprite(x, y, resourcesManager.platform3_region, vbom);
	                final Body body = PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyType.StaticBody, FIXTURE_DEF);
	                body.setUserData("platform3");
	                physicsWorld.registerPhysicsConnector(new PhysicsConnector(levelObject, body, true, false));*/
	            }
	            else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_COIN))
	            {
	               // vat the khac
	            	 face = new balloon(x, y, resourcesManager.balloon_region,
		         				vbom,'c'){
	            		 
	            	 @Override
	         			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
	        					float Y) {
	        				if (pSceneTouchEvent.isActionDown()) {
	        					detachSelf();
	        					
	        					Log.v("<xoa>","xong !");
	        				}
	        				return true;
	         				}};
	            	 Log.v("<position c>",String.valueOf(x)+"   "+ String.valueOf(y));
	            	// face.setPosition(400, 0);
	            }            
	            else
	            {
	                throw new IllegalArgumentException();
	            }
	           
	            face.registerEntityModifier(new MoveYModifier(10, y, 600));
	            registerTouchArea(face);
	            face.setCullingEnabled(true);

	            return face;
	        }
	    });

	    levelLoader.loadLevelFromAsset(activity.getAssets(), "level/" + levelID + ".lvl");
	}
	@Override
	public void createScene() {
		this.setBackground( new SpriteBackground(new Sprite(400, 240, resourcesManager.game_background_region, vbom)));
		// SpriteBackground bg = new SpriteBackground(new Sprite(400, 240, resourcesManager.menu_background_region,));
		createHUD();
		createPhysics();
		createGame();
		loadLevel(1);
	}
//	balloon face;
	private void createGame() {
	//	resourcesManager.loadGameResources();
		// face = new balloon(0, 0, resourcesManager.balloon_region,
		//		vbom,"s");
		/* {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionDown()) {
					face.detachSelf();
					
					Log.v("<xoa>","xong !");
				}
				return true;
			}*/
		//};
	//	face.setTag(1);
		//scoreText.setText("score: 10");
	//	face.setPosition(400, 0);
		
	//	face.attachChild(new Text(0, 0, resourcesManager.font_sprite,"S", new TextOptions(HorizontalAlign.LEFT),vbom));
		// attachChild(face);
	//	face.registerEntityModifier(new MoveYModifier(10, 0, 480));
		/* {

			@Override
			protected void onModifierFinished(IEntity pItem) {
				// TODO Auto-generated method stub
				super.onModifierFinished(pItem);
				detachChild(face);
			}
		});*/
	//	attachChild(face);
		//this.registerTouchArea(face);
	
	}

	private HUD gameHUD;

	private Text scoreText;

	private void createHUD() {
		gameHUD = new HUD();

		// CREATE SCORE TEXT
		scoreText = new Text(20, 420, resourcesManager.font,
				"Score: 0123456789", new TextOptions(HorizontalAlign.LEFT),
				vbom);
		scoreText.setPosition(150, 400);
		scoreText.setText("Score: 0");
		gameHUD.attachChild(scoreText);

		camera.setHUD(gameHUD);
	}

	private int score = 0;

	private void addToScore(int i) {
		score += i;
		scoreText.setText("Score: " + score);
	}

	private PhysicsWorld physicsWorld;

	private void createPhysics() {
		physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, -17), false);
		registerUpdateHandler(physicsWorld);
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
		camera.setCenter(400, 240);

		// TODO code responsible for disposing scene
		// removing all game scene objects.
	}

}
