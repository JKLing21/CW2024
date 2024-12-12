package com.example.demo;

import javafx.scene.media.Media;
import java.util.Map;
import static java.util.Map.entry;

public class AudioAssetLoader implements AssetsLoader<Media> {

	protected static final Map<String, String> audioPaths = Map.ofEntries(
			entry("MainMenuBGM", "/com/example/demo/audio/MainMenuBGM.mp3"),
			entry("LevelOneBGM", "/com/example/demo/audio/LevelOneBGM.mp3"),
			entry("LevelTwoBGM", "/com/example/demo/audio/LevelTwoBGM.mp3"),
			entry("BossBGM", "/com/example/demo/audio/BossLevelBGM.mp3"),
			entry("planefire", "/com/example/demo/audio/planefire.wav")
			);

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