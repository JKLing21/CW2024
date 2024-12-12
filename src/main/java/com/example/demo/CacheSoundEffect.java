package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Manages cache of MediaPlayer instances for sound effects
 * in order to optimise game performance
 * by reusing the existing MediaPlayer objects
 * instead of creating new ones for every single playback.
 */
public class CacheSoundEffect {

	private final HashMap<String, Queue<MediaPlayer>> soundPools = new HashMap<>();
	private final AudioAssetLoader audioAssetLoader;
	private final int MAX_POOL_SIZE = 10;
	/**
     * Constructs CacheSoundEffect instance with AudioAssetLoader.
     *
     * @param audioAssetLoader: AudioAssetLoader used to load audio assets.
     */
	public CacheSoundEffect(AudioAssetLoader audioAssetLoader) {
		this.audioAssetLoader = audioAssetLoader;
	}
	/**
     * Retrieves MediaPlayer instance for specified sound effect.
     * If MediaPlayer is available in cache, it's then reused.
     * Otherwise, a new MediaPlayer is created and then configured.
     *
     * @param audioName: Name of sound effect to retrieve the MediaPlayer for.
     * @return A MediaPlayer instance for sound effect or null if audio asset fails to load.
     */
	public MediaPlayer getMediaPlayer(String audioName) {
		soundPools.putIfAbsent(audioName, new LinkedList<>());
		Queue<MediaPlayer> pool = soundPools.get(audioName);

		if (!pool.isEmpty()) {
			return pool.poll();
		}

		Media media = audioAssetLoader.loadAsset(audioName);
		if (media != null) {
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setOnEndOfMedia(() -> {
				mediaPlayer.stop();
				if (pool.size() < MAX_POOL_SIZE) {
					pool.offer(mediaPlayer);
				} else {
					mediaPlayer.dispose();
				}
			});
			return mediaPlayer;
		} else {
			System.err.println("Failed to load sound effect: " + audioName);
			return null;
		}
	}
	/**
     * Plays specified sound effects at given volume.
     * If a MediaPlayer is available in cache, it's then reused.
     * Otherwise, a new MediaPlayer is created and then played.
     *
     * @param audioName: Name of sound effects to be played.
     * @param volume: Volume at which to play the sound effect.
     */
	public void playSoundEffect(String audioName, double volume) {
		MediaPlayer mediaPlayer = getMediaPlayer(audioName);
		if (mediaPlayer != null) {
			mediaPlayer.setVolume(volume);
			mediaPlayer.play();
		}
	}
}
