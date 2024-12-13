package com.example.demo.utils;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class ImagePropertiesTest {

	private ImageView imageView;
	private Image image;

	@BeforeEach
	void setUp() {
		imageView = Mockito.mock(ImageView.class);
		image = Mockito.mock(Image.class);
	}

	@Test
	void testApplyProperties_withImage() {
		Double xPosition = 10.0;
		Double yPosition = 20.0;
		Integer height = 30;
		Double width = 40.0;
		Boolean preserveRatio = true;

		ImageProperties.applyProperties(imageView, image, xPosition, yPosition, height, width, preserveRatio);

		verify(imageView).setImage(image);
		verify(imageView).setLayoutX(xPosition);
		verify(imageView).setLayoutY(yPosition);
		verify(imageView).setFitHeight(height);
		verify(imageView).setFitWidth(width);
		verify(imageView).setPreserveRatio(preserveRatio);
	}

	@Test
	void testApplyProperties_withNullValues() {
		ImageProperties.applyProperties(imageView, image, null, null, null, null, null);

		verify(imageView).setImage(image);
		verify(imageView, never()).setLayoutX(anyDouble());
		verify(imageView, never()).setLayoutY(anyDouble());
		verify(imageView, never()).setFitHeight(anyInt());
		verify(imageView, never()).setFitWidth(anyDouble());
		verify(imageView, never()).setPreserveRatio(anyBoolean());
	}

	@Test
	void testSetImage() {
		ImageProperties.setImage(imageView, image);

		verify(imageView).setImage(image);
	}

	@Test
	void testSetLayoutX() {
		Double xPosition = 10.0;
		ImageProperties.setLayoutX(imageView, xPosition);

		verify(imageView).setLayoutX(xPosition);
	}

	@Test
	void testSetLayoutY() {
		Double yPosition = 20.0;
		ImageProperties.setLayoutY(imageView, yPosition);

		verify(imageView).setLayoutY(yPosition);
	}

	@Test
	void testSetFitHeight() {
		Integer height = 30;
		ImageProperties.setFitHeight(imageView, height);

		verify(imageView).setFitHeight(height);
	}

	@Test
	void testSetFitWidth() {
		Double width = 40.0;
		ImageProperties.setFitWidth(imageView, width);

		verify(imageView).setFitWidth(width);
	}

	@Test
	void testSetPreserveRatio() {
		Boolean preserveRatio = true;
		ImageProperties.setPreserveRatio(imageView, preserveRatio);

		verify(imageView).setPreserveRatio(preserveRatio);
	}
}
