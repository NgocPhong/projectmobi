package com.example.demophisic;

/////////////////////////////////////////////////////////////////////////////////
////////////////////1112219 - cu ngoc phong/////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import com.example.demophisic.SceneManager.SceneType;

public class ShopScene extends BaseScene implements OnClickListener {
	private static AcessHelper infor = new AcessHelper();

	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createbackground();
		createShop();
	}

	private void createbackground() {
		attachChild(new Sprite(240, 400,
				resourcesManager.background_Shop_region, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
	}

	private Text cur_coin;
	private Text buy;
	private static final int Time = 1;
	private static final int heart = 2;
	private static final String detail_time = "Tăng Thời Gian\n Màn Chơi Thêm 10s\n\n Giá :";
	private static final String detail_heart = "Tăng Mạng\n  Thêm 1\n\n Giá :";
	private static final String ThanhCong = "Chúc Mừng Bạn \nĐã Mua Thành Công ^_^";
	private static final String Thatbai = "Bạn Cần Thêm Tiền Nha ^_^";
	private Text text_time;
	private Text text_heart;

	private void createShop() {
		// time item
		Sprite time = new Sprite(100, 250, resourcesManager.clock_Shop_region,
				vbom);
		Sprite time_detail = new Sprite(250, 250,
				resourcesManager.mssg_Shop_region, vbom);
		time_detail.setScale(0.6f);
		this.attachChild(time_detail);
		text_time = new Text(140, 100, resourcesManager.shop_font, detail_time
				+ String.valueOf(infor.GetItemsCost(Time)), new TextOptions(
				HorizontalAlign.CENTER), vbom);
		text_time.setScale(0.5f);
		text_time.setColor(Color.WHITE);
		time_detail.attachChild(text_time);
		ButtonSprite buy_time = new ButtonSprite(400, 250,
				resourcesManager.buy_Shop_region, vbom);
		time.setScale(0.5f);
		buy_time.setTag(Time);

		buy_time.setOnClickListener(this);
		this.registerTouchArea(buy_time);
		this.attachChild(time);
		this.attachChild(buy_time);
		// heart intem

		Sprite spriteheart = new Sprite(100, 400,
				resourcesManager.heart_Shop_region, vbom);
		Sprite heart_detail = new Sprite(250, 400,
				resourcesManager.mssg_Shop_region, vbom);
		heart_detail.setScale(0.6f);
		this.attachChild(heart_detail);
		text_heart = new Text(140, 100, resourcesManager.shop_font, detail_heart
				+ String.valueOf(infor.GetItemsCost(heart)), new TextOptions(
				HorizontalAlign.CENTER), vbom);
		text_heart.setScale(0.5f);
		text_heart.setColor(Color.WHITE);
		heart_detail.attachChild(text_heart);
		ButtonSprite buy_heart = new ButtonSprite(400, 400,
				resourcesManager.buy_Shop_region, vbom);
		spriteheart.setScale(0.5f);
		buy_heart.setTag(heart);

		buy_heart.setOnClickListener(this);
		this.registerTouchArea(buy_heart);
		this.attachChild(spriteheart);
		this.attachChild(buy_heart);
		// current money
		Sprite coin = new Sprite(200, 680, resourcesManager.coin_Shop_region,
				vbom);
		cur_coin = new Text(300, 680, resourcesManager.shop_font, "1234567890",
				new TextOptions(HorizontalAlign.CENTER), vbom);
		cur_coin.setText(String.valueOf(infor.GetTien()));
		cur_coin.setScale(1.5f);
		coin.setScale(0.4f);
		this.attachChild(coin);
		this.attachChild(cur_coin);
		// info buy
		buy = new Text(240, 100, resourcesManager.shop_font, ThanhCong
				+ Thatbai, new TextOptions(HorizontalAlign.CENTER), vbom);
		buy.setText("");
		buy.setScale(0.6f);
		this.attachChild(buy);
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		SceneManager.getInstance().loadMenu_ShopScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_SHOP;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		int tag = pButtonSprite.getTag();
		int tien = infor.GetTien();
		int cost = infor.GetItemsCost(tag);
		switch (tag) {
		case Time:

			if (tien >= cost) {

				int time = infor.Getthoigian();
				time = time + 10;
				int money = tien - cost;
				infor.nhapthoigian(time);
				infor.nhapGiaItem(Time, cost + 4);
				infor.nhapTien(money);
				cur_coin.setText(String.valueOf(money));
				buy.setText(ThanhCong);
				text_time.setText(detail_time
						+ String.valueOf(cost + 4));
			} else
				buy.setText(Thatbai);
			this.registerUpdateHandler(new TimerHandler(1.0f,
					new ITimerCallback() {
						public void onTimePassed(TimerHandler pTimerHandler) {
							buy.setText("");

						}
					}));
			break;

		case heart:

		{

			if (tien >= cost) {

				int quan = infor.Getquan();
				quan = quan + 1;
				int money = tien - cost;
				infor.nhapquan(quan);
				infor.nhapGiaItem(heart, cost + 4);
				infor.nhapTien(money);
				cur_coin.setText(String.valueOf(money));
				buy.setText(ThanhCong);
				text_heart.setText(detail_heart+ String.valueOf(cost + 4));
			} else
				buy.setText(Thatbai);
			this.registerUpdateHandler(new TimerHandler(1.0f,
					new ITimerCallback() {
						public void onTimePassed(TimerHandler pTimerHandler) {
							buy.setText("");

						}
					}));
			break;
		}

		// TODO Auto-generated method stub

		}
	}
}
