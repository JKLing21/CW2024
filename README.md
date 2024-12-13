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
*
# Features Not Implemented

# New Java Classes
|        |        |       |
|-----------------|---------------|----------------|
|  |  |  |
|  |  |  |
## Modified Java Classes

## Unexpected Problems
Game Glitching/Lag During Level Transition
