package com.TP.dd;

import org.andengine.entity.modifier.IEntityModifier;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;

import android.util.Log;

public class balloon extends Sprite {
	private  ResourcesManager resourcesManager;
	private Text Textchar;
	private char mText;
	public balloon(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager vbom,char text) {
		
		super(pX, pY, pTextureRegion, vbom);
		setmText(text);
		resourcesManager = ResourcesManager.getInstance();
		Textchar = new Text(0, 0, resourcesManager.font_sprite,"qwertyuiopasdfghjkzxcvbnm123456780", new TextOptions(HorizontalAlign.LEFT),vbom);
		Textchar.setText(String.valueOf(getmText()));
		this.attachChild(Textchar);
		Textchar.setPosition(50,90);
	
		// TODO Auto-generated constructor stub
	}

	public balloon(int x, int y, ITiledTextureRegion complete_stars_region,
			VertexBufferObjectManager vbom) {
		// TODO Auto-generated constructor stub
		super(x, y, complete_stars_region, vbom);
	}

	@Override
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
	}

	public char getmText() {
		return mText;
	}

	public void setmText(char text) {
		this.mText = text;
	}




	

	

	

}
