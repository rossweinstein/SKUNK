# Skunk (SEIS635 Homework 4)

## About

This app allows a user to play skunk, an old dice game, against up to 7 other opponents (both human and CPU).

## Rules of Skunk

*These rules are copied vertabim from the inside of the game box*
 
### DIRECTIONS FOR PLAYING
 
The object of the game is to accumulate a score of 100 points or more. A
score is made by rolling the dice and combining the points on the two
dice. For example: A 4 and 5 would be 9 points -if the player decides to take
another roll of the dice and turns up a 3 and 5 (8 points), he would then
have an accumulated total of 17 points for the two rolls. The player has the
privilege of continuing to shake to increase his score or of passing the dice
to wait for the next series, thus preventing the possibility of rolling
a Skunk and losing his score.
 
### PENALTIES:

* A skunk in any series voids the score for that series only and draws a penalty of 1 chip placed in the "kitty," and loss of dice.
 
* A skunk and a deuce voids the score for that series only and draws a penalty of 2 chips placed in the "kitty," and loss of dice.
 
* TWO skunks void the ENTIRE accumulated score and draws a penalty of 4 chips placed in the "kitty," and loss of dice. Player must again start to score from scratch.
 
### PLAYERS

Any number can play. The suggested number of chips to start is 50. There are sufficient chips in the box to allow 8 players to start with 50 chips by placing a par value of "one" on white chips, 5 for 1 on red chips and 10 for 1 on the blue chips.

### WINNING THE GAME

The first player to accumulate a total of 100 or more points can continue to
score as many points over 100 as he believes is needed to win. When he
decides to stop, his total score is the “goal.” Each succeeding player
receives one more chance to better the goal and end the game.
 
The winner of each game collects all chips in "kitty" and in addition five
chips from each losing player or 10 chips from any player without a score.

## Installation

```
$ git clone https://github.com/rossweinstein/SKUNK
```

## Running the code

Once you have the repository cloned, navigate to the package *skunkApp* within the *src* folder to find SkunkApp.java.  Run this file and the game will start. 


## A few notes on this implementaiton of skunk

* When it is a player's turn, they automatically roll the dice. It is only if they want to take additional rolls that they are prompted with a choice.
* During the final found, when one player has a score of equal or greater than 100 and every other player has one final chance to beat them, the player will automatically roll until they have a score greater than the player with the high score or a skunk. If they do reach a score greater than the player with the high score, they will be prompted to see if they want to continue rolling.
* Every player starts with 50 chips. Since this is a command line game, there is no need for chip denomination (i.e. red is worth 5).  All chips have a value of one.
* If a player incurs a penalty and do not have sufficient chips to pay, they will pay their remaining chips and then be removed from the game
* If at the end of the round a player has zer0 chips, they will not be included in the next round
