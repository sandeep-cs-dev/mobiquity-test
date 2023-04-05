
# Packing problem

## Problem analysis

Packing problem could be solved using DP, Greedy or recursion.

1. Greedy approach is not suitable for requirement even though  item weight are fractional, Hoever we are  not allowed to break items so we can use it.


2. Dynamic Programing would have been good choice if item weight were integer values. In oreder to use DP we will have to adjust decimal by multiplying by 100 assuiming item weight would be up to two decimal precesion.

Time complexity analysis for input within given constraint in the problem for DP based solution:

 * Max allowed Items = 15;
 * Item max weight = 100.00
 
Now in order to use DP technique accurately we will have to adjust weight for each item by multiplying by 100;

Hence our DP time and space complexity becomes 100 time wider.


## Solution Used

Since input set is small I have used recursion which is still efficient for input for the given constraint in the problem statement.

### Time complexity 
 2^15 < 15*10000 (32768 < 150000)









## Roadmap

- We can further improve solution design by  implementing various strategies (Strategy Pattern) to choose right algorithms based on input size, type etc.


- Better test cases and coverage 
- Provide Configuration Factory for to configure algorithm and  other input parameters such as allowed total items, weigth etc.

