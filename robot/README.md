### We have a robot that can pick up blocks from a stash, move them horizontally, and lower them in place. There are 10 positions available to lower blocks, numbered from 0 to 9. Each position can hold up to 15 blocks.
 

The robot understands the commands 'P', 'M' and 'L':

P: Pickup from the stash and move to position 0
M: Move to the next position
L: Lower the block

The robot is safe to operate and very forgiving:


- There are always blocks in the stash (Pickup will always get a block).
- If the robot already holds a block, Pickup will reset the robot to position 0.
- The robot will not go beyond position 9. Trying to Move it further does nothing.
- Lowering a block on a pile of 15 blocks does nothing (and the robot will keep any block it holds).
- Lowering without a block does nothing.
- The robot ignores any command that is not 'P', 'M' or 'L'.
 

Implement a function that takes a String of commands for the robot. The function should output a String representing the number of blocks (in hexadecimal) at each position after running all the commands.

Examples

SNo|Sample Input|Sample Output
---|--------- | -------------
1   |""		|		       		        0000000000
2  	|QWERTYUI|        			      0000000000
3  	|PPPPPP  |      				      0000000000
4  	|LLLLLL   |     				      0000000000
5  	|MMMMMMMMMMMMMMM|             0000000000
6   |PMLPMMLPMLPMMML |            0211000000
7  	|PLPLPLPLPLPLPLPLPLPL|        A000000000
8  	|PPPPPPLL       | 			      1000000000
9  	|PMMMMMLMMMMMML  |            0000010000
10  |LLLLLLM        	|			      0000000000
11  |MMMMMMMMMMMMMMML  |          0000000000
12  |MMMMMMMMLMMMMMMML  |         0000000000
13  |MLMLMLMLMLMLMLMLMLMLMLML|    0000000000
