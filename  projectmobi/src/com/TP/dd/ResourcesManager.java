package com.TP.dd;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;
import android.util.Log;

public class ResourcesManager {
	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------

	private static final ResourcesManager INSTANCE = new ResourcesManager();

	public Engine engine;
	public Main activity;

	public Camera camera;
	public VertexBufferObjectManager vbom;

	// ---------------------------------------------
	// TEXTURES & TEXTURE REGIONS
	// --------------------------------------------
	// splash screen-
	public ITextureRegion splash_region;
	private BitmapTextureAtlas splashTextureAtlas;
	// menu screen
	public ITextureRegion menu_background_region;
	public ITextureRegion play_region;
	public ITextureRegion options_region;

	private BuildableBitmapTextureAtlas menuTextureAtlas;
	// Game resouse
	public ITextureRegion game_background_region;
	private BuildableBitmapTextureAtlas TextureAtlas;
	public ITextureRegion balloon_region;
	private BuildableBitmapTextureAtlas balloonTextureAtlas;
	// Game Texture
	public BuildableBitmapTextureAtlas gameTextureAtlas;

	// Game Texture Regions
	public ITextureRegion platform1_region;
	public ITextureRegion platform2_region;
	public ITextureRegion platform3_region;
	public ITextureRegion coin_region;
	// ---------------------------------------------
	// Music
	// ---------------------------------------------
	// music menu scence
	public Music backgruond;
	public Music music;

	// ---------------------------------------------
	// CLASS LOGIC
	// ---------------------------------------------

	public void loadMenuResources() {
		loadMenuGraphics();
		loadMenuAudio();
		loadMenuFonts();
	}

	public void loadGameResources() {
		loadGameGraphics();
		loadGameFonts();
		loadGameAudio();
	}

	private void loadMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		menuTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR);
		menu_background_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity,
						"menu_background.jpg");
		play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "play.png");
		options_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity, "options.png");

		try {
			this.menuTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.menuTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}

	private void loadMenuAudio() {
		try {
			backgruond = MusicFactory.createMusicFromAsset(
					engine.getMusicManager(), activity, "mfx/ogg/town.ogg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadGameGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		balloonTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 255, 255,
				TextureOptions.BILINEAR);
		game_background_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(balloonTextureAtlas, activity,
						"game_background.png");
		platform1_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(balloonTextureAtlas, activity, "face_box2.png");
		platform2_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(balloonTextureAtlas, activity, "face_box3.png");
		balloon_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(balloonTextureAtlas, activity, "face_box.png");
		try {
			this.balloonTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.balloonTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		/*
		 * TextureAtlas = new
		 * BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024,
		 * TextureOptions.BILINEAR); game_background_region =
		 * BitmapTextureAtlasTextureRegionFactory.createFromAsset(TextureAtlas,
		 * activity, "game_background.png");
		 * 
		 * try { this.TextureAtlas.build(new
		 * BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
		 * BitmapTextureAtlas>(0, 1, 0)); this.TextureAtlas.load(); } catch
		 * (final TextureAtlasBuilderException e) { Debug.e(e);
		 * Log.v("<game scence>",game_background_region.toString()); }
		 */
	}

	public void unloadGameTextures() {
		// TODO (Since we did not create any textures for game scene yet)
		balloonTextureAtlas.unload();
	}

	public Font font;

	private void loadMenuFonts() {
		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		font = FontFactory.createStrokeFromAsset(activity.getFontManager(),
				mainFontTexture, activity.getAssets(), "font.ttf", 50, true,
				Color.WHITE, 2, Color.BLACK);
		font.load();
	}

	public Font font_sprite;

	private void loadGameFonts() {
		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		font_sprite = FontFactory.createStrokeFromAsset(
				activity.getFontManager(), mainFontTexture,
				activity.getAssets(), "font_sprite.ttf", 50, true, Color.BLACK,
				2, Color.BLACK);
		font_sprite.load();
	}

	private void loadGameAudio() {
		try {
			music = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
					activity, "mfx/ogg/balloon_hit_01.ogg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadSplashScreen() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 800, 480,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				splashTextureAtlas, activity, "splash.png", 0, 0);
		splashTextureAtlas.load();
	}

	public void unloadSplashScreen() {
		splashTextureAtlas.unload();
		splash_region = null;
	}

	public void unloadMenuTextures() {
		menuTextureAtlas.unload();
	}

	public void loadMenuTextures() {
		menuTextureAtlas.load();
	}

	/**
	 * @param engine
	 * @param activity
	 * @param camera
	 * @param vbom
	 * <br>
	 * <br>
	 *            We use this method at beginning of game loading, to prepare
	 *            Resources Manager properly, setting all needed parameters, so
	 *            we can latter access them from different classes (eg. scenes)
	 */
	public static void prepareManager(Engine engine, Main activity,
			Camera camera, VertexBufferObjectManager vbom) {
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}

	// ---------------------------------------------
	// GETTERS AND SETTERS
	// ---------------------------------------------

	public static ResourcesManager getInstance() {
		return INSTANCE;
	}
}
