package factories.interfaces;

import javafx.scene.image.ImageView;

public interface AssetFactory {
    ImageView createBackgroundImage(String imageName, double width, double height);
}