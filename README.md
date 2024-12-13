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

 2. **Game Settings**
* settings screen for the game, allowing player to configure sound effects and background music.
* Providing controls for adjusting volume and toggling sound on/off
  - Details :
  * Using sliders to adjust sound effects and background music volume.
  * Using toggle buttons to toggle sound effects and background music on/off.
  * Preserved music settings using preferences for future sessions.
 
3. **In game pause button & Pause Screen**
* player can toggle the pause button using cursor or 'p' button.
* pause screen provides functionality to display and hide the pause menu when the game is paused. Pause Screen has a set of buttons which are Resume, Settings, Restart, and Main Menu buttons.
  - Details :
  The pause menu is initially hidden and can be shown by calling the showPauseMenu() method.

4. **Transition Scene**
*  visually appealing transitions between different game states, such as level changes or game-over screens, with animated effects.
  - Details :
Transition scene plays two animations in parallel which is a fade-in effect for the transition message and a horizontal translation for the plane image. It also accepts a callback (onTransitionEnd) that executes once the animations are complete, signaling the end of the transition.

5. **Boss Health Bar**
* The boss health bar provides visual feedback to the player about how much damage they have dealt to the boss. It allows the player to track their progress throughout the fight, which enhances the feeling of achievement and motivates them to keep going.
 - Details :
updateHealth(double healthPercentage) adjusts the width of the health bar based on the boss's current health percentage.

6. **Boss Shielded Glowing Effect**
* When the shield is active, it applies a glowing effect to the boss plane, making it visually distinct and signaling to the player that the boss is shielded. The shield icon is also displayed next to the boss's health bar to further indicate its active status. If the shield is inactive, the glow effect is removed, and the shield icon is hidden, giving a clear visual cue that the boss is vulnerable.
_ Details :
The shield effect is applied visually to the boss plane using the applyShieldEffect method.

7. **UserPlane dual shooting styles**
* provides two distinct shooting styles: continuous firing and rapid firing.
- Details :
The fireContinuous method allows the user plane to fire continuously at a consistent rate, with a delay defined by FIRE_RATE_DELAY.
Then, the fireRapid method enables rapid firing, where the user plane can fire projectiles at a faster rate, governed by the RAPID_FIRE_DELAY. This allows for a faster-paced attack, providing a more aggressive shooting style.

8. **In-game instruction**
* Display on-screen instructions to guide the player.
- Details : 
It shows instructions like "Press UP and DOWN arrow keys to move" and "Press SPACE to fire" with a fade-in and fade-out effect to ensure they appear and disappear smoothly during gameplay.

9. **Kill Count Display**
* The kill count is dynamically updated to show the remaining kills required to advance to the next level.
- Details :
The count is displayed on the screen with a StringProperty binding that updates the UI text based on the current number of kills and target kills.

# Implemented but Not Working Properly
1. **Kill Count Display**
* the kill count may return a negative value if multiple planes are taken down simultaneously.
**Possible Solution:**
using the Math.max() function, which will ensure that the kill count remains at zero when the player exceeds the required number of kills.
# Features Not Implemented

# New Java Classes
# Package : exports com.example.demo.Actors.Components;
1. **BossHealthBar class** 
- represents the bossplane health bar and related UI components
 * Including the health bar, health bar background, bossplane's name text label, 
 * and shield icon indicating the bossplane's shielding status.
   
# Package : exports com.example.demo.Actors.Planes;
2. **Warplane class**
- represents war plane in game.
# Package : exports com.example.demo.Actors.Projectiles;
3. **WarPlaneProjectile class**
- represents projectile fired by warplane in game.

# Package : exports com.example.demo.Assets;
4. **AssetsLoader**
- responsible for loading assets, such as images and audio files.

5. **AudioAssetLoader class**
- responsible for loading audio assets in game.

6. **ImgAssetLoader**

7. CacheSoundEffect class
8. ImgAssetLoaderImpl
# Package : exports com.example.demo.Factories;
  *  ActorImplement class
  *  AssetsImplement class
  *  ComponentsImplement class
  * ImgViewFactoryImpl
  *  ProjectilesImplement class
  *  UIControlFactoryImpl class
# Package : exports com.example.demo.Factories.Interfaces;
  *  ActorFactory interface
  * 17. AssetFactory interface
18. ComponentsFactory
19. ImgViewFactory
20. ProjectilesFactory
21. UIControlFactory
# Package : exports com.example.demo.Levels;
23. LevelThree class
# Package : exports com.example.demo.Managers;
25. AudioManager class
26. CollisionManager
27. ImageManager
28. KeyAction
29. KeyBindings class
30. KeyEventHandlers
31. PauseManager
# Package : exports com.example.demo.Screens;
33. GameSettings
34. LoseScreen
35. MainMenu
36. PauseScreen
37. TransitionScene
38. WinScreen
# Package : exports com.example.demo.Strategy;  
39. BossFiringStrategy
40. BossMovementStrategy
41. BossShielding
42. BossShieldStrategy
43. EnemyFiringStrategy
44. EnemyMovementStrategy
45. FiringStrategy
46. MovementStrategy
47. UserFiringStrategy
48. UserMovementStrategy
49. WarPlaneFiringStrategy
50. WarPlaneMovementStrategy
# Package : exports com.example.demo.utils;
51. ImageProperties
## Modified Java Classes
# Package : exports com.example.demo;
1. Controller
# Package : exports com.example.demo.Actors.Components;
1.HeartDisplay
2.Shield
# Package : exports com.example.demo.Actors.Planes;
1. Boss
2. EnemyPlane
3. UserPlane
# Package : exports com.example.demo.Actors.Projectiles;
1.BossProjectile
2.EnemyProjectile
3.UserProjectile
4.Projectile
# Package : exports com.example.demo.Actors;
5.ActiveActor
6. ActiveActorDestructible
7.Destructible
# Package : exports com.example.demo.Levels;
1. LevelParent
2. LevelOne
3. LevelTwo
# Package : exports com.example.demo.utils;
LevelScreen (LevelView)
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
Root Cause: The glow effect was applied inside the takeDamage() method when the shield was active, but it was never removed once the shield was deactivated
**Solution:**
Explicitly remove the glow effect when the shield is not active or when the boss takes damage.
