package com.example.demo.Managers;

import java.util.prefs.Preferences;

import com.example.demo.Assets.AudioAssetLoader;
import com.example.demo.Assets.CacheSoundEffect;
import com.example.demo.controller.GameSettings;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/**
 * Manages background music and sound effects, including music volume control, 
 * fading effects and toggling audio on/off.
 */
public class AudioManager {

	private MediaPlayer mediaPlayer;
	private MediaPlayer soundEffectsPlayer;
	private final AudioAssetLoader audioAssetLoader;
	private Preferences prefs;
	private final Object lock = new Object();
	private static AudioManager instance;
	private double volume;
	private double previousSoundEffectsVolume;
	private double previousBackgroundMusicVolume;
	private final CacheSoundEffect cacheSoundEffect;
	/**
     * Constructs AudioManager instance with AudioAssetLoader.
     * Initialises preferences, music volume settings and sound effects cache.
     *
     * @param audioAssetLoader: AudioAssetLoader used to load audio assets.
     */
	public AudioManager(AudioAssetLoader audioAssetLoader) {
		prefs = Preferences.userNodeForPackage(GameSettings.class);
		this.audioAssetLoader = audioAssetLoader;
		this.cacheSoundEffect = new CacheSoundEffect(audioAssetLoader);
		this.volume = prefs.getDouble("backgroundMusicVolume", 0.5);
		this.previousSoundEffectsVolume = prefs.getDouble("soundEffectsVolume", 0.5);
		this.previousBackgroundMusicVolume = prefs.getDouble("backgroundMusicVolume", 0.5);
		this.previousSoundEffectsVolume = 0.5;
		this.previousBackgroundMusicVolume = 0.5;
	}
	/**
     * Returns singleton instance of AudioManager.
     * It creates one if instance does not exist.
     *
     * @param audioAssetLoader: AudioAssetLoader used to load audio assets.
     * @return Singleton instance of AudioManager.
     */
	public static AudioManager getInstance(AudioAssetLoader audioAssetLoader) {
		if (instance == null) {
			synchronized (AudioManager.class) {
				if (instance == null) {
					instance = new AudioManager(audioAssetLoader);
				}
			}
		}
		return instance;
	}
	/**
     * Plays specified background music. It stops any currently playing background music,
     * loads new background music and applies fades in effect.
     *
     * @param audioName: Name of audio file to play as the background music.
     */
	public void playBackgroundMusic(String audioName) {
		stopBackgroundMusic();

		Media media = audioAssetLoader.loadAsset(audioName);
		if (media != null) {
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			fadeIn(mediaPlayer);
		} else {
			System.err.println("Failed to load media for: " + audioName);
			mediaPlayer = null;
		}
	}
	/**
     * Stops currently playing background music and releases the resources.
     */
	public void stopBackgroundMusic() {
		synchronized (lock) {
			if (mediaPlayer != null) {
				mediaPlayer.stop();
				mediaPlayer.dispose();
				mediaPlayer = null;
			}
		}
	}
	/**
     * Sets volume for background music.
     *
     * @param volume: Music volume level to set.
     */
	public void setBackgroundMusicVolume(double volume) {
		this.volume = volume;
		if (mediaPlayer != null) {
			mediaPlayer.setVolume(volume);
			prefs.putDouble("backgroundMusicVolume", volume);
		}
	}
	/**
     * Gets current volume of background music.
     *
     * @return Current volume level of background music.
     */
	public double getBackgroundMusicVolume() {
		return prefs.getDouble("backgroundMusicVolume", 0.5);
	}
	/**
     * Toggles background music on/off.
     * If it's toggled on, it resumes playback with previous volume set.
     * If it's toggled off, it pauses playback and mutes the volume.
     *
     * @param isOn: True to turn on background music, false to turn it off.
     */
	public void toggleBackgroundMusic(boolean isOn) {
		System.out.println("Toggling background music: " + isOn);
		if (isOn) {
			setBackgroundMusicVolume(previousBackgroundMusicVolume);
			resumeBackgroundMusic();
		} else {
			previousBackgroundMusicVolume = getBackgroundMusicVolume();
			setBackgroundMusicVolume(0.0);
			pauseBackgroundMusic();
		}
		prefs.putBoolean("backgroundMusicOn", isOn);
	}
	/**
     * Fades in effect for specified MediaPlayer 
     * by gradually increasing the volume when player enters the scene.
     *
     * @param player: MediaPlayer to fade in.
     */
	private void fadeIn(MediaPlayer player) {
		player.setVolume(0.0);
		player.play();
		Timeline fadeInTimeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(player.volumeProperty(), 0.0)),
				new KeyFrame(Duration.seconds(2), new KeyValue(player.volumeProperty(), volume)));
		fadeInTimeline.play();
	}
	/**
     * Fades out effect for currently playing background music over a specified duration.
     *
     * @param fadeDuration: The duration over which to fade out background music.
     */
	public void fadeOut(Duration fadeDuration) {
		if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			Timeline fadeOutTimeline = new Timeline(
					new KeyFrame(fadeDuration, new KeyValue(mediaPlayer.volumeProperty(), 0.0)));
			fadeOutTimeline.setOnFinished(event -> stopBackgroundMusic());
			fadeOutTimeline.play();
		}
	}
	/**
     * Pauses currently playing background music.
     */
	public void pauseBackgroundMusic() {
		if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			mediaPlayer.pause();
		}
	}
	/**
     * Resumes currently paused background music.
     */
	public void resumeBackgroundMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
		}
	}
	/**
     * Plays sound effect with specified audioName.
     * If sound effects settings are toggled off, this method does nothing.
     *
     * @param audioName: Name of audio file to play as sound effect.
     */
	public void playSoundEffect(String audioName) {
		boolean soundEffectsOn = prefs.getBoolean("soundEffectsOn", true);
		if (!soundEffectsOn) {
			return;
		}
		cacheSoundEffect.playSoundEffect(audioName, getSoundEffectsVolume());
	}
	 /**
     * Sets volume levels for sound effects.
     *
     * @param volume: Volume level to set.
     */
	public void setSoundEffectsVolume(double volume) {
		prefs.putDouble("soundEffectsVolume", volume);
		if (soundEffectsPlayer != null) {
			soundEffectsPlayer.setVolume(volume);
		}
	}
	/**
     * Preloads sound effect into cache in order to reduce latency when it's played later.
     *
     * @param audioName: Name of audio file to preload.
     */
	public void preloadSoundEffect(String audioName) {
		cacheSoundEffect.getMediaPlayer(audioName);
	}
	/**
     * Gets current volume of sound effects.
     *
     * @return Current volume level of sound effects.
     */
	public double getSoundEffectsVolume() {
		return prefs.getDouble("soundEffectsVolume", 0.5);
	}
	/**
     * Toggles sound effects on/off.
     * If toggled on, restores previous volume set.
     * If toggled off, it mutes the volume.
     *
     * @param isOn: True to turn on sound effects, false to turn it off.
     */
	public void toggleSoundEffects(boolean isOn) {
		System.out.println("Toggling sound effects: " + isOn);
		if (isOn) {
			setSoundEffectsVolume(previousSoundEffectsVolume);
		} else {
			previousSoundEffectsVolume = getSoundEffectsVolume();
			setSoundEffectsVolume(0.0);
		}
		prefs.putBoolean("soundEffectsOn", isOn);
	}
}