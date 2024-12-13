package com.example.demo.Assets;
/**
 * AssetsLoader defines the generic interface for loading assets of specific type.
 * This interface is responsible for loading assets, such as images and audio files.
 *
 * @param <main>: Type of asset that the loader will return upon loading.
 */
public interface AssetsLoader<main> {
    main loadAsset(String assetName);
}