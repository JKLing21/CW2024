package com.example.demo;

import javafx.scene.image.Image;
import java.util.Map;
import static java.util.Map.entry;

public abstract class ImgAssetLoader implements AssetsLoader {
	
	protected static final Map<String, String> imagePaths = Map.ofEntries (
			entry("background1", "/com/example/demo/images/background1.jpg"),
			entry("background2", "/com/example/demo/images/background2.jpg"),
			entry("bossplane", "/com/example/demo/images/bossplane.png"),
			entry("enemyFire", "/com/example/demo/images/enemyFire.png"),
			entry("enemyplane", "/com/example/demo/images/enemyplane.png"),
			entry("fireball", "/com/example/demo/images/fireball.png"),
			entry("gameover", "/com/example/demo/images/gameover.png"),
			entry("heart", "/com/example/demo/images/heart.png"),
			entry("MainMenuIcon", "/com/example/demo/images/main_menu.png"),
			entry("MainMenu", "/com/example/demo/images/MainMenu.jpeg"),
			entry("PauseIcon", "/com/example/demo/images/pause.png"),
			entry("rectangle_container", "/com/example/demo/images/rectangle_container.png"),
			entry("ResumeIcon", "/com/example/demo/images/resume.png"),
			entry("SettingsIcon", "/com/example/demo/images/settings.png"),
			entry("Shield", "/com/example/demo/images/shield.png"),
			entry("stormy_sky", "/com/example/demo/images/stormy_sky.jpg"),
			entry("userfire", "/com/example/demo/images/userfire.png"),
			entry("userplane", "/com/example/demo/images/userplane.png"),
			entry("youwin", "/com/example/demo/images/youwin.png")
			
	);
	
	@Override
	public Image loadImage(String assetName) {
	    String path = imagePaths.get(assetName);
	    if (path != null) {
	        try {
	            Image image = new Image(getClass().getResource(path).toExternalForm());
	            if (image.isError()) {
	                System.err.println("Error loading image: " + assetName + " from path: " + path);
	            }
	            return image;
	        } catch (Exception e) {
	            System.err.println("Error loading image: " + assetName + " from path: " + path);
	            e.printStackTrace();
	        }
	    } else {
	        System.err.println("Image path not found for: " + assetName);
	    }
	    return null;
	}

}
