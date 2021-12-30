# PoopPlugin
Minecraft server plugin for dropping a load on SHIFT key press

## Platform Information
- Tested on PaperMC (https://papermc.io/) version 1.18.1
- Java version: OpenJDK 17
- Dev Platform: IntelliJ Community Edition

## Installation
1. Using IntelliJ open the pom.xml file
2. Click the icon to import Maven dependencies (this may take a few moments)
3. Expand the Maven tab on the top right to access Maven commands
4. Expand "PoopPlugin" > "Lifecycle" and double click on "Package"
5. Check the console for errors. If there are no errors look in your local project folder for a new folder called "target" This is where you will find the compiled jar file.
6. Place compiled jar (PoopPlugin-1.0.jar) in the server "plugins" folder and restart:

    
    Example:
    /opt/minecraft/paper/plugins/PoopPlugin-1.0.jar

## Usage:
Note: This plugin is only available to Operators

    Enable plugin: 
    /poop on

    Disable plugin:
    /poop off

    Get plugin status:
    /poop status

