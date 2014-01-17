package com.example.demophisic;

/////////////////////////////////////////////////////////////////////////////////
////////////////////1112219 - cu ngoc phong/////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.engine.camera.Camera;

import android.util.Log;

import com.example.demophisic.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener, OnClickListener {

	@Override
	public void createScene() {
		createBackground();
		createMenuChildScene();
		
	}
	private MenuScene menuChildScene;
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	private final int MENU_HELP = 2;

	private void createMenuChildScene()
	{
	    menuChildScene = new MenuScene(camera);
	    menuChildScene.setPosition(0, 50);
	    
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.play_region, vbom), 1.2f, 1);
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourcesManager.options_region, vbom), 1.2f, 1);
	    final IMenuItem helpMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_HELP, resourcesManager.help_region, vbom), 1.2f, 1);
	    
	    menuChildScene.addMenuItem(playMenuItem);
	    menuChildScene.addMenuItem(optionsMenuItem);
	    menuChildScene.addMenuItem(helpMenuItem);
	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);
	    
	    playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY()-100);
	    optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY()-150);
	    helpMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY()-150);
	    
	    menuChildScene.setOnMenuItemClickListener(this);
	   
	    setChildScene(menuChildScene);
	    ButtonSprite Shop = new ButtonSprite(240,200,resourcesManager.shop_region,vbom);
	    Shop.setScale(0.4f);
	    Shop.setPosition(50, 770);
	    Shop.setOnClickListener(this);
	  this.registerTouchArea(Shop);
	    this.attachChild(Shop);
	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}
	private void createBackground()
	{
		
	    attachChild(new Sprite(240, 400, resourcesManager.menu_background_region, vbom)
	    {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) 
	        {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		  switch(pMenuItem.getID())
	        {
	        case MENU_PLAY:
	        	 SceneManager.getInstance().loadGameScene(engine);
	             return true;
	        case MENU_OPTIONS:
	        	SceneManager.getInstance().createOptionScene();
	            return true;
	        case MENU_HELP:
	        	SceneManager.getInstance().createHelpScene();
	            return true;
	        default:
	            return false;
	    }
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		 SceneManager.getInstance().loadShopScene(engine);
		 Log.v("shop","");
	}

}
