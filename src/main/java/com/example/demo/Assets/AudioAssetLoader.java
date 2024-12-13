package com.example.demo.Assets;

import javafx.scene.media.Media;
import java.util.Map;

import static java.util.Map.entry;
/**
 * AudioAssetLoader class responsible for loading audio assets in game.
 * AudioAssetLoader class implements AssetsLoader interface to provide a method for loading audio files.
 */
public class AudioAssetLoader implements AssetsLoader<Media> {
	/**
	 * Map containing paths to audio assets.
	 * Keys are the names of the audio assets, and the values are the corresponding file paths.
	 */
	protected static final Map<String, String> audioPaths = Map.ofEntries(
			entry("MainMenuBGM", "/com/example/demo/audio/MainMenuBGM.mp3"),
			entry("LevelOneBGM", "/com/example/demo/audio/LevelOneBGM.mp3"),
			entry("LevelTwoBGM", "/com/example/demo/audio/LevelTwoBGM.mp3"),
			entry("BossBGM", "/com/example/demo/audio/BossLevelBGM.mp3")
			);
	/**
	 * Loads audio asset by its name.
	 *
	 * @param assetName: name of audio asset to load.
	 * @return loaded Media object, or null if asset could not be loaded.
	 */
	public Media loadAsset(String assetName) {
		String path = audioPaths.get(assetName);
		if (path != null) {
			try {
				Media media = new Media(getClass().getResource(path).toExternalForm());
				return media;
			} catch (Exception e) {
				System.err.println("Error loading audio: " + assetName + " from path: " + path);
				e.printStackTrace();
			}
		} else {
			System.err.println("Audio path not found for: " + assetName);
		}
		return null;
	}
}