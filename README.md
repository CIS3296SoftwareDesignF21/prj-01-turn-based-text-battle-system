# Project 

For this project, a text-based game will be created. There won't be any visuals outside of text on a command line, unless 
somehow time is permitted, but very likely not. What this game will be is a simple program demonstrating a battle between 
an enemy and player. The user will be controlling the player's actions. These actions will be a list of commands that can 
be used to alter the battle. After selecting a command, it will commence and afterwards the enemy will perform an action. 
Then repeat. Details such as character statistics, multiple playable characters, and multiple various enemies may be included if time is permitted.
The current status of the battle will be shown in the text, along with player options. This will be completed completely by scratch.


# How to Run
To run this program, navigate to the latest release on our github page.  Once there, you can download the jarfile attached,
and place it in the same folder as battleBatch.bat, in out/artifacts/prj_01_turn_based_text_battle_system_jar.  Once there, 
for some you may be able to click the jarfile and launch it.  If not, click on the batchfile and it will run the following command: \
java -jar prj-01-turn-based-text-battle-system.jar, the command line function to run the jarfile.  If you are still running 
into issues with running this way, make sure that the version of java that is used to compile the jar file is the same as the
version on your computer.  If it isn't, it's best to remake the jarfile manually as detailed in the section below.

# How to Install/Build

To install this program, you simply need to download it to your local computer from our github repo.  If you would like to 
have a local version, you can download git to your computer, and clone the repo to your local computer using the url of the
project with the following command: \
git clone https://github.com/CIS3296SoftwareDesignF21/prj-01-turn-based-text-battle-system.git

To build the files from here, we use IntelliJ, which provides another method to directly get from version control when opening
a project, and also can build from inside the IDE.  To build the jarFile manually instead of downloading from the version, under 
the file tab you go to project structure.  Then, go to the Artifacts section, and add a new jar file, choosing "From modules with dependencies".
From here, choose the main class to be our main.java file under src/main/java, and chose your manifest directory to be where our
manifest is, under src/main/resources. 

From here, to build the jar, you go to the build tab, select build artifacts, then build the jar you just created.  


[comment]: <> (employ to create our jarFile is to use our IDE of choice to add it as an artifact, but we are working on creating an executable)

[comment]: <> (version of the project soon.  At that stage, you will simply need to download and run it by clicking on it in your file explorer.)



# Weekly Markdown Pages

[Week 1 Markdown](Week1.md)

[Week 2 Markdown](Week2.md)

[Week 3 Markdown](Week3.md)

[Week 4 Markdown](Week4.md)
