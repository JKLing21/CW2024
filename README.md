# Project Title

# GitHub Repository
[Project Repository](https://github.com/your-username/your-repository)

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
|        |        |       |
|-----------------|---------------|----------------|
|  |  |  |
|  |  |  |

# Implemented but Not Working Properly

# Features Not Implemented

# New Java Classes

## Modified Java Classes

## Unexpected Problems
Game Glitching/Lag During Level Transition
