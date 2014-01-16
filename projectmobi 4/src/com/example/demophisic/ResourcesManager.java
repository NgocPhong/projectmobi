package com.example.demophisic;

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
import org.andengine.opengl.texture.region.ITiledTextureRegion;
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
	public MainActivity activity;

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
	public ITextureRegion menu_help_background_region;
	public ITextureRegion play_region;
	public ITextureRegion options_region;
	public ITextureRegion help_region;
	public ITextureRegion sound_on_region;
	public ITextureRegion sound_off_region;
	public ITextureRegion reset_region;
	
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	private BuildableBitmapTextureAtlas OptionTextureAtlas;
	// Game resouse
	public ITextureRegion game_background_region;
	private BuildableBitmapTextureAtlas TextureAtlas;
	public ITextureRegion balloon_region;
	public ITextureRegion game_pause_region;

	// Game Texture
	public BuildableBitmapTextureAtlas gameTextureAtlas;
	public BuildableBitmapTextureAtlas gamebackgroundTextureAtlas;
	public BuildableBitmapTextureAtlas gamepauseTextureAtlas;
	public BuildableBitmapTextureAtlas levelcompleteTextureAtlas;
	// Game Texture Regions
	public ITextureRegion playgame_region;
	
	public ITextureRegion pausegame_region;
	public ITextureRegion heart_region;
	public ITextureRegion rainwbow;
	// ---------------------------------------------
	// Music
	// ---------------------------------------------
	// music menu scence
	public Music backgruond;
	public Music music;
	// Level Complete Window
	public ITextureRegion complete_window_region;
	public ITiledTextureRegion complete_stars_region;
	public ITextureRegion complete_next_region;
	public ITextureRegion complete_home_region;
	public ITextureRegion complete_restart_region;
	public ITiledTextureRegion animate_balloon;
	public ITiledTextureRegion animate_trap;

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
				activity.getTextureManager(), 2048, 1024,
				TextureOptions.BILINEAR);
		menu_background_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity,
						"menu_background.png");
		menu_help_background_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity,
						"menu_help_background.png");
		play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "play.png");
		options_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity, "options.png");
		help_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity, "help.png");
		OptionTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 2048, 1024,
				TextureOptions.BILINEAR);
		sound_on_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				OptionTextureAtlas, activity, "sound_on.png");
		sound_off_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				OptionTextureAtlas, activity, "sound_off.png");
		reset_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				OptionTextureAtlas, activity, "reset.png");
		try {
			this.menuTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.menuTextureAtlas.load();
			this.OptionTextureAtlas
			.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
					0, 1, 0));
	this.OptionTextureAtlas.load();
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
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
		// game region
		gameTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		// pause region
		gamepauseTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		// background region
		gamebackgroundTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 2024, 2024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		// levelcomplete region
		levelcompleteTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		// background
		game_background_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gamebackgroundTextureAtlas, activity,
						"game_background.png");

		rainwbow = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				gameTextureAtlas, activity, "rainbow.png");
		balloon_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gameTextureAtlas, activity, "clock.png");
		// sprite level complete
		complete_window_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(levelcompleteTextureAtlas, activity,
						"levelCompleteWindow.png");
		complete_stars_region = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(levelcompleteTextureAtlas, activity,
						"star.png", 2, 1);
		complete_next_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gamepauseTextureAtlas, activity,
						"next.png");
		complete_home_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gamepauseTextureAtlas, activity,
						"home.png");
		complete_restart_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gamepauseTextureAtlas, activity,
						"restart.png");
		// sprite game
		animate_balloon = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(gameTextureAtlas, activity,
						"balloon.png", 6, 1);
		animate_trap = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(gameTextureAtlas, activity, "trap.png",
						6, 1);
		heart_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gamebackgroundTextureAtlas, activity,
						"heart.png");
		// pause
		game_pause_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gamepauseTextureAtlas, activity,
						"pausebackground.png");
		// button
		playgame_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gamebackgroundTextureAtlas, activity,
						"play.png");
		pausegame_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gamebackgroundTextureAtlas, activity,
						"pause.png");

		try {
			this.gameTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.gameTextureAtlas.load();
			this.gamebackgroundTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.gamebackgroundTextureAtlas.load();
			this.gamepauseTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.gamepauseTextureAtlas.load();
			this.levelcompleteTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.levelcompleteTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
	}

	public void unloadGameTextures() {
		// TODO (Since we did not create any textures for game scene yet)
		gameTextureAtlas.unload();
		gamebackgroundTextureAtlas.unload();
		gamepauseTextureAtlas.unload();
		levelcompleteTextureAtlas.unload();
	}

	public Font font;
	public Font gamefont;

	private void loadMenuFonts() {
		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		font = FontFactory.createStrokeFromAsset(activity.getFontManager(),
				mainFontTexture, activity.getAssets(), "font.ttf", 50, true,
				Color.WHITE, 2, Color.BLACK);

		font.load();

	}

	public Font font_sprite;

	private void loadGameFonts() {
		FontFactory.setAssetBasePath("font/");
		final ITexture gameFontTexture = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		font_sprite = FontFactory.createStrokeFromAsset(
				activity.getFontManager(), gameFontTexture,
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
		OptionTextureAtlas.unload();
	}

	public void loadMenuTextures() {
		menuTextureAtlas.load();
		OptionTextureAtlas.load();
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
	public static void prepareManager(Engine engine, MainActivity activity,
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
