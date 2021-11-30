
# Uml Sequence Diagram 1

[Sequence Diagram 1](UML%20Sequence%20Diagram%201.png)

- battleBatch.bat used to compile and run program
- main receives user input for name and instantiates a Player object based off user input
- user allowed to choose strength of Player, main calls setDifficulty
- Player overrides the Battler class
- addSpecialAttacks adds new Special Attack object to set in Battler
-	main receives user input and instantiates an Enemy object based off user input
- user allowed to choose strength of Enemy, main calls setDifficulty
- Enemy overrides Battler class
- addSpecialAttacks adds new Special Attack object to a set in Battler
-	main battle loop begins, more user input taken
- user can decide to attack or guard
- processAttack is called which calculates damage between user and target and supplies print messages describing so
- if player decides to guard, guard in Battler class is set to true and any damage done to the player is halved
- endTurn called at end of loop to reset guard in Battler to false
- when either the hp of Player or Enemy reaches 0, loop returns to beginning and user asked if they’d like to play another game
- user would like to play again so loop restarts



# Uml Sequence Diagram 2

[Sequence Diagram 2](UML%20sequence%20diagram%202.png)

- battleBatch.bat used to compile and run program
- main receives user input for name and instantiates a Player object based off user input
- user allowed to choose strength of Player, main calls setDifficulty
- Player overrides the Battler class
- addSpecialAttacks adds new Special Attack object to set in Battler
-	main receives user input and instantiates an Enemy object based off user input
- user allowed to choose strength of Enemy, main calls setDifficulty
- Enemy overrides Battler class
- addSpecialAttacks adds new Special Attack object to a set in Battler
-	main battle loop begins, more user input taken
- user can decide to attack or guard
- processAttack is called which calculates damage between user and target and supplies print messages describing so
- if player decides to guard, guard in Battler class is set to true and any damage done to the player is halved
- endTurn called at end of loop to reset guard in Battler to false
- when either the hp of Player or Enemy reaches 0, loop returns to beginning and user asked if they’d like to play another game
- user would not like to play again so game over


