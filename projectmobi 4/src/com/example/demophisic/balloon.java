package com.example.demophisic;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.IEntityModifier;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import android.util.Log;

public class balloon extends AnimatedSprite {
	private  ResourcesManager resourcesManager;
	private Text Textchar;
	private  boolean istouch = false;
	private char mText;
	public balloon(float pX, float pY, ITiledTextureRegion pTextureRegion,
			VertexBufferObjectManager vbom,char text) {
		
		super(pX, pY, pTextureRegion, vbom);
		setmText(text);
		resourcesManager = ResourcesManager.getInstance();
		Textchar = new Text(0, 0, resourcesManager.font_sprite,"qwertyuiopasdfghjkzxcvbnm123456780", new TextOptions(HorizontalAlign.LEFT),vbom);
		Textchar.setText(String.valueOf(getmText()));
		this.attachChild(Textchar);
		Textchar.setPosition(43,40);
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean detachSelf() {
		
		// TODO Auto-generated method stub
		return super.detachSelf();
	}

	public balloon(int x, int y, ITiledTextureRegion complete_stars_region,
			VertexBufferObjectManager vbom) {
		super(x, y, complete_stars_region, vbom);
		// TODO Auto-generated constructor stub
	}

	/*@Override
	public void onAttached() {
	this.registerUpdateHandler(new TimerHandler(
											30f, new ITimerCallback() {

												@Override
												public void onTimePassed(
														TimerHandler pTimerHandler) {
													// TODO Auto-generated
													// method stub
													pTimerHandler.reset();
												}

											}));
		super.onAttached();
	}*/

	/*@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			
			
			this.setScale(1.5f);
		}				
		if (pSceneTouchEvent.isActionUp()||pSceneTouchEvent.isActionOutside()||pSceneTouchEvent.isActionMove()) {
			
		
			this.detachSelf();
			resourcesManager.music.play();
			Log.v("<xoa>","xong !");
		}
		
		return super
				.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	}*/

	public char getmText() {
		return mText;
	}

	public void setmText(char text) {
		this.mText = text;
	}

	public boolean isIstouch() {
		return istouch;
	}

	public void setIstouch(boolean istouch) {
		this.istouch = istouch;
	}




	

	

	

}
