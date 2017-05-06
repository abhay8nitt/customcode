Let's define a function "LookAndSay" as follow: read off the digits of the input, counting the number of digits in groups of same digit. Here are some examples of this function

LookAndSay(1) = 11                        because 1 is read off as "one 1" or 11. 
LookAndSay(11) = 21                      because 11 is read off as "two 1s" or 21. 
LookAndSay(21) =  1211               because 21 is read off as "one 2, then one 1" or 1211.
LookAndSay(1211) =  111221      because 1211 is read off as "one 1, then one 2, then two 1s" or 111221.
LookAndSay(111221) =  312211 because 111221 is read off as "three 1s, then two 2s, then one 1" or 312211.
 

We then define a "Look and Say" sequence as repeatedly called the "Look and Say" function on its output.

Given a  number start and a number of iteration n, calculate the nth number in a "Look and Say" sequence starting with start. 

Reusing the previous example with start = 11 and n = 2, LookAndSay(11, 2) = 1211 because LookAndSay(LookAndSay(11)) = 1211
