package com.example.demophisic;

/////////////////////////////////////////////////////////////////////////////////
////////////////////1112219 - cu ngoc phong/////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.BaseGameActivity;
import android.view.KeyEvent;


public class MainActivity extends BaseGameActivity implements IOnSceneTouchListener, IAccelerationListener {

	private final int CAMERA_WIDTH = 480;
	private final int CAMERA_HEIGHT = 800;
	private ResourcesManager resourcesManager;
	private Camera camera;
	
	
  
    
/*	private enum SceneType
	{
		SPLASH,
		MAIN,
		OPTIONS,
		WORLD_SELECTION,
		LEVEL_SELECTION,
		CONTROLLER
	}
*/	
	//private SceneType currentScene = SceneType.SPLASH;
	
	@Override
	protected synchronized void onResume() {
		// TODO Auto-generated method stub
		mEngine.getMusicManager().setMasterVolume(1.0f);
		super.onResume();
	}

	@Override
	public synchronized void onResumeGame() {
		// TODO Auto-generated method stub
		super.onResumeGame();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mEngine.getMusicManager().setMasterVolume(0.0f);
		super.onPause();
	}

	@Override
	public synchronized void onPauseGame() {
		// TODO Auto-generated method stub
		super.onPauseGame();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
        System.exit(0);	
	}
	
	@Override
	public EngineOptions onCreateEngineOptions()
	{
		
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR, new RatioResolutionPolicy(480, 800), this.camera);
	    engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
	    engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
	    return engineOptions;
	}
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) 
	{
	    return new LimitedFPSEngine(pEngineOptions, 60);
	}
	
	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException
	{
	    ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
	    resourcesManager = ResourcesManager.getInstance();
	    pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
	{
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
		
	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback)
	{
		mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() 
	    {
	            public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	                mEngine.unregisterUpdateHandler(pTimerHandler);
	                // load menu resources, create menu scene
	                // set menu scene using scene manager
	                // disposeSplashScene();
	                // READ NEXT ARTICLE FOR THIS PART.
	               
	                SceneManager.getInstance().createMenuScene();
	            }
	    }));
	    pOnPopulateSceneCallback.onPopulateSceneFinished();
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{  
		 if (keyCode == KeyEvent.KEYCODE_BACK)
		    {
		        SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
		    }
		    return false; 
	}
	
	
	
	// ===========================================================
	// INITIALIZIE  
	// ===========================================================
	
	

	@Override
	public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccelerationChanged(AccelerationData pAccelerationData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
