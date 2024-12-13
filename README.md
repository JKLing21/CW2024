# COMP2042 Coursework
LING JUN KIT 20509915

# GitHub Repository
[Project Repository](https://github.com/JKLing21/CW2024)

---

# Compilation Instructions

Follow these steps to set up, compile, and run the application in Eclipse:

## Requirements

Ensure you have the following installed on your system:

Java Development Kit (JDK): Version 21 or later.
* Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK.

JavaFX SDK: Required for JavaFX applications.
* Download from [JavaFX Downloads](https://openjfx.io).

## Steps to Set Up in Eclipse

1. **Install Eclipse IDE:**

* Download and install [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/).
* Ensure you have a version compatible with your JDK.

2. **Set Up the JavaFX SDK in Eclipse:**

* Download and extract the JavaFX SDK to a directory on your system.
* Open Eclipse and go to Window > Preferences.
* Navigate to Java > Build Path > User Libraries and click New.
* Name the library (e.g., JavaFX) and click OK.
* Click Add External JARs, navigate to the lib folder of your JavaFX SDK, and add all .jar files.
* Click OK to save the library.

3. **Import the Project:**

- Open Eclipse and create a new JavaFX project:
  - Go to File > New > Project > JavaFX > JavaFX Project.
  - If the option for JavaFX Project is not available, use a regular Java Project and add JavaFX support manually.
- Place your Main.java and other files in the appropriate src folder, ensuring the package structure (com.example.demo.controller) is maintained.

4. **Configure the Module Path:**

* Right-click the project in the Package Explorer and select Build Path > Configure Build Path.
* Go to the Libraries tab, click Modulepath, and click Add Library.
* Select User Library, choose your JavaFX library, and click Finish.
  
5. **Set VM Arguments:**

* Right-click the project and select Run As > Run Configurations.

* Select your application, go to the Arguments tab, and under VM Arguments, add:
```bash
--module-path <path-to-javafx-lib> --add-modules javafx.controls,javafx.fxml
```
* Replace "path-to-javafx-lib" with the actual path to the lib folder of your JavaFX SDK.

Notes:
- Ensure all required files (e.g., Controller class) are in the appropriate package structure and compiled successfully.
If you encounter issues, verify the JavaFX library configuration and VM arguments.

# Implemented and Working Properly
1. **Main Menu**
* Main menu screen of a game, where players can choose to start the game, access settings, or quit the game
  - Details :
  * Start Game: Launches the game by calling controller.launchGame().
  * Settings: Opens the settings screen through controller.showSettings().
  * Quit: Prompts the user with a confirmation dialog to quit the game.
    
 **Reason of Implementation**
- Initially the game does not have a main menu, players may directly jump into the gameplay without an option to access a main menu

 2. **Game Settings**
* settings screen for the game, allowing player to configure sound effects and background music.
* Providing controls for adjusting volume and toggling sound on/off
  - Details :
  * Using sliders to adjust sound effects and background music volume.
  * Using toggle buttons to toggle sound effects and background music on/off.
  * Preserved music settings using preferences for future sessions.
    
 **Reason of Implementation**
* enables players to customize their gaming environment to suit their personal preferences,
 
3. **In game pause button & Pause Screen**
* player can toggle the pause button using cursor or 'p' button.
* pause screen provides functionality to display and hide the pause menu when the game is paused. Pause Screen has a set of buttons which are Resume, Settings, Restart, and Main Menu buttons.
  - Details :
  The pause menu is initially hidden and can be shown by calling the showPauseMenu() method.

**Reason of Implementation**
improves the accessibility of the game, accommodating players who may need to pause and resume for various reasons

4. **Transition Scene**
*  visually appealing fade in and fade out transitions between different game states, such as level changes or game-over screens, with animated effects.
  - Details :
Transition scene plays two animations in parallel which is a fade-in effect for the transition message and a horizontal translation for the plane image. It also accepts a callback (onTransitionEnd) that executes once the animations are complete, signaling the end of the transition.

**Reason of Implementation**
* Transitions between game states (such as between levels or after a game-over screen) can feel abrupt without a smooth transition.

5. **Boss Health Bar**
* The boss health bar provides visual feedback to the player about how much damage they have dealt to the boss. It allows the player to track their progress throughout the fight, which enhances the feeling of achievement and motivates them to keep going.
 - Details :
updateHealth(double healthPercentage) adjusts the width of the health bar based on the boss's current health percentage.

**Reason of Implementation**
* boss health bar offers immediate and clear visual feedback, allowing the player to track how much damage they’ve dealt during the fight.

6. **Boss Shielded Glowing Effect**
* When the shield is active, it applies a glowing effect to the boss plane, making it visually distinct and signaling to the player that the boss is shielded. The shield icon is also displayed next to the boss's health bar to further indicate its active status. If the shield is inactive, the glow effect is removed, and the shield icon is hidden, giving a clear visual cue that the boss is vulnerable.
_ Details :
The shield effect is applied visually to the boss plane using the applyShieldEffect method.

**Reason of Implementation**
* The glowing effect makes it immediately clear to the player when the boss is shielded.
  
7. **UserPlane dual shooting styles**
* provides two distinct shooting styles: continuous firing and rapid firing.
- Details :
The fireContinuous method allows the user plane to fire continuously at a consistent rate, with a delay defined by FIRE_RATE_DELAY.
Then, the fireRapid method enables rapid firing, where the user plane can fire projectiles at a faster rate, governed by the RAPID_FIRE_DELAY. This allows for a faster-paced attack, providing a more aggressive shooting style.

**Reason of Implementation**
* Giving players the ability to choose between shooting styles offers more control over how they approach the game.

8. **In-game instruction**
* Display on-screen instructions to guide the player.
- Details : 
It shows instructions like "Press UP and DOWN arrow keys to move" and "Press SPACE to fire" with a fade-in and fade-out effect to ensure they appear and disappear smoothly during gameplay.

**Reason of Implementation**
  * Ensuring that players know how to interact with the game

9. **Background Music & Sound Effect**
* Background music and sound effects enhances the player's experience by creating an engaging and immersive environment.
- Details :
Background music can be paused, resumed, or stopped using pauseBackgroundMusic, resumeBackgroundMusic, or stopBackgroundMusic.

**Reason of Implementation**
- Background music and sound effects help to create a more immersive environment

# Implemented but Not Working Properly
1. **Kill Count Display**
* the kill count may return a negative value if multiple planes are taken down simultaneously.
  
**Possible Solution:**
* using the Math.max() function, which will ensure that the kill count remains at zero when the player exceeds the required number of kills.

2. **Actors Firing Sound Effect**
* Implemented at first but removed. Sound effects feature are technically functional in game, but can introduce slight performance overhead especially with frequent firing actions and other actors spawn even though i have implemented to CacheSoundEffect to preload it. Lag issues caused by it occurred only rarely, but i've decided to remove it to avoid any potential issues that could arise.
**Possible Solution:**
* Handle audio playback in a separate thread to prevent it from interfering with the main game loop.
# Features Not Implemented
1. **Explosion Effect**
* While the effects would enhance the visual appeal, they are not core to the gameplay mechanics.

# New Java Classes
# Package : com.example.demo.Actors.Components;
1. **BossHealthBar** 
- represents the bossplane health bar and related UI components
 * Including the health bar, health bar background, bossplane's name text label, 
 * and shield icon indicating the bossplane's shielding status.
   
# Package : com.example.demo.Actors.Planes;
1. **Warplane**
- represents war plane in game.
# Package : com.example.demo.Actors.Projectiles;
2. **WarPlaneProjectile**
- represents projectile fired by warplane in game.

# Package : com.example.demo.Assets;
4. **AssetsLoader**
- responsible for loading assets, such as images and audio files.

5. **AudioAssetLoader**
- responsible for loading audio assets in game.

6. **ImgAssetLoader**
- ImgAssetLoader class is an implementation of the AssetsLoader interface that manages image asset loading
7. **CacheSoundEffect**
- CacheSoundEffect class optimizes game performance by managing a cache of MediaPlayer instances for sound effects. 
8. ImgAssetLoaderImpl
- load image assets by delegating the asset loading process to the parent ImgAssetLoader class
# Package : com.example.demo.Factories;
1. ActorImplement 
- concrete implementation of the ActorFactory interface, responsible for creating various game actors, such as UserPlane, EnemyPlane, WarPlane, and Boss.
2. AssetsImplement 
- concrete implementation of the AssetFactory interface, responsible for creating and managing assets like images.
3. ComponentsImplement
- implements the ComponentsFactory interface, which defines methods for creating different types of UI components and game elements
4. ImgViewFactoryImpl
- implements the ImgViewFactory interface, which defines methods for generating different types of ImageView elements.
5. ProjectilesImplement 
- The ProjectilesImplement class is responsible for creating various types of projectiles by implementing the ProjectilesFactory interface.
6. UIControlFactoryImpl 
- The UIControlFactoryImpl class is responsible for creating various user interface controls by implementing the UIControlFactory interface.
# Package : com.example.demo.Factories.Interfaces;
1. ActorFactory
- The ActorFactory interface defines methods for creating different game actors.
3. AssetFactory
- The AssetFactory interface defines a method for creating various game assets.
4. ComponentsFactory
- The ComponentsFactory interface defines a set of methods for creating various UI components and game elements.
5. ImgViewFactory
- ImgViewFactory defines methods for creating various ImageView components.
6. ProjectilesFactory
- ProjectilesFactory defines methods for creating various types of projectiles.
7. UIControlFactory
- UIControlFactory defines methods for creating various user interface controls.
# Package : com.example.demo.Levels;
1. LevelThree class (previously LevelTwo)
- LevelThree class defines specific behaviors and logic for the third level of the game, which features a boss battle
# Package : com.example.demo.Managers;
1. AudioManager class
- responsible for managing background music and sound effects in the game
2. CollisionManager
- responsible for managing all collision-related logic in the game, including detecting collisions between actors, applying damage, and managing actor destruction.
3. ImageManager
- singleton that manages the loading and retrieval of image assets in the game.
4. KeyAction
- enum represents different actions that can be triggered by key events in the game.
5. KeyBindings class
- manages the mapping between key codes and corresponding actions in the game.
6. KeyEventHandlers
-  responsible for handling keyboard input events in the game.
7. PauseManager
- responsible for managing the pause functionality in the game.
# Package : com.example.demo.Screens;
1. GameSettings
- provides a settings screen for managing the game's sound effects and background music settings.
2. LoseScreen
- manages the display of the game over screen when the player loses.
3. MainMenu
- responsible for displaying the main menu of the game.
4. PauseScreen
- manages the pause menu UI in the game.
5. TransitionScene
- manages the transition scene between game levels and states.
6. WinScreen
- displays a "You Win" screen when the player defeats the game.
# Package : com.example.demo.Strategy;  
1. BossFiringStrategy
- implements the FiringStrategy interface and defines the firing behavior for the boss plane in the game.
3. BossMovementStrategy
- implements the MovementStrategy interface and defines the movement behavior for the boss plane in the game.
4. BossShielding
- defines the contract for managing and applying shielding effects for the boss plane
5. BossShieldStrategy
- manages the shielding mechanics for the boss plane.
6. EnemyFiringStrategy
- handling the firing behavior of enemy planes
7. EnemyMovementStrategy
- implement the movement logic for the enemy plane.
8. FiringStrategy
- defines a contract for different firing strategies in the game.
9. MovementStrategy
- defines the movement behavior for any type of FighterPlane.
10. UserFiringStrategy
- implements the firing behavior for the userplane.
11. UserMovementStrategy
- implements the MovementStrategy interface and defines the movement behavior for the userplane 
12. WarPlaneFiringStrategy
-  implements the FiringStrategy interface and defines the firing behavior for the WarPlane
13. WarPlaneMovementStrategy
-  implements the MovementStrategy interface and defines the movement behavior for the WarPlane
# Package : com.example.demo.utils;
1. ImageProperties
- utility class designed to simplify the management and manipulation of ImageView objects
# Modified Java Classes
# Package : com.example.demo;
1. Controller
- manage the game's flow, including navigation between scenes, handling of audio, and managing the dynamic loading of levels.
  
**Reason of modification**
* the central hub for managing the flow of the application. It acts as an intermediary between various components 
# Package : com.example.demo.Actors.Components;
1.HeartDisplay
- managing and displaying heart images in game
  
**Reason of modification**
* the initializeHearts method calls the componentsFactory.createHeartImage() to create each heart image.
2.Shield
- managing shield icon in the game
  
**Reason of modification**
* uses the ImgAssetLoader to load the shield image, which acts as a form of asset management
# Package : com.example.demo.Actors.Planes;
1. Boss
   
**Reason of modification**
- delegates the logic of movement, firing, and shielding to specific strategies, encapsulating each behavior
3. EnemyPlane
  
**Reason of modification**
- delegates the logic of movement and firing to the EnemyMovementStrategy and EnemyFiringStrategy classes
4. UserPlane
  
  **Reason of modification**
- movement logic is delegated to the UserMovementStrategy instance. Then, it delegates the firing of projectiles to the UserFiringStrategy instance.
# Package : com.example.demo.Actors.Projectiles;
1.BossProjectile

**Reason of modification**
- delegation of responsibilities for handling the projectile's behavior
- loaded using the ImgAssetLoader
2.EnemyProjectile
  
  **Reason of modification**
- loaded using the ImgAssetLoader
3.UserProjectile
  
  **Reason of modification**
- - loaded using the ImgAssetLoader

# Package : com.example.demo.Actors;
1. ActiveActor
   
  **Reason of modification**
- provides core functionality common to all active actors, such as movement, hitbox management, and destruction

3. ActiveActorDestructible
   
 **Reason of modification**
- adding and removing of the hitbox from the scene, and ensuring that the hitbox remains in sync with the actor's movement and destruction status.

# Package : com.example.demo.Levels;
1. LevelParent
- makes use of factories and use of KeyEventHandlers to manages various key events
- ensures a consistency without requiring each level to replicate the setup.
3. LevelOne
-  uses the BACKGROUND_IMAGE constant for setting up the background, ensuring consistency in the level's appearance.
4. LevelTwo
-  uses the BACKGROUND_IMAGE constant for setting up the background, ensuring consistency in the level's appearance.
# Package : com.example.demo.utils;
1. LevelScreen (LevelView)
- manages the UI elements and interactions within the game levels. It handles the display of hearts, kill count, and instructions, as well as managing the hitboxes of actors during gameplay. 
## Unexpected Problems
1. **Game Glitching/Lag During Level Transition**
**Description of the problem:**
* When transitioning to the next level in the game, a glitch or lag occurred, affecting the gameplay experience. The game would freeze or experience noticeable delays, which was disruptive to the flow of the game.
  
**Solution:**
To resolve the problem, the Observable pattern was removed from the Controller class. This change simplified the level transition logic and eliminated the performance bottleneck caused by the pattern.
* Removed the Observable and Observer pattern from the Controller class.
* Simplified the level transition logic by directly invoking the next level without relying on Observable updates.

2. **volume slider change not saved**
**Description of the problem:**
* The issue arises when the Slider control's value is not saved and the value reverts to its default setting each time the application is restarted. This is problematic as the application cannot remember the user's preferences.
  
**Solution:**
- initialize the Preferences object. This object will be used to store and retrieve the slider's value.
- Added listener to save slider value when it changes.
- the saved slider value is loaded from the Preferences when the application starts.

3. **Pause Button Not Functioning as Expected**
**Description of the problem:**
pause button and 'p' key were not functioning correctly to pause and resume the game. The root cause was identified as incorrect focus management and improper handling of the pause state.

**Solution:**
* Focus Management:
   - Removed unnecessary focus requests on the background node.
   - Ensured the scene retains focus to receive key events by requesting focus on the root node.
* Pause State Management:
   - Centralized the pause state management in the `LevelParent` class.
   - Updated the `togglePause()` method to correctly pause and resume the game timeline.
* KeyEventHandlers Integration:
   - Modified the `KeyEventHandlers` class to notify `LevelParent` when 'p' is pressed.
   - Ensured that both the pause button and 'p' key trigger the same `togglePause()` method in `LevelParent`.

4. **Glow effect persisting after shield deactivation**
**Description of the problem:**
when the shield is activated, the boss plane receives a glow effect to indicate its shielded state. However, after the shield deactivates, the glow effect continues to be applied, even though the shield is no longer active. This behavior causes the boss plane to appear as if it is still glowing, even though it has taken damage or is no longer shielded.
The glow effect remained on the boss plane even after the shield was deactivated, which caused visual inconsistencies in the game.
Root Cause: The glow effect was applied inside the takeDamage() method when the shield was active, but it was never removed once the shield was deactivated.

**Solution:**
Explicitly remove the glow effect when the shield is not active or when the boss takes damage.
