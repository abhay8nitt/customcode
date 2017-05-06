Determine the total number of stops an elevator has taken to serve X number of people.

There is a elevator in a building with M floors. This elevator can take a max of X people at a time or max of total weight Y. Given that a set of people has arrived and their weight and the floor they need to stop given how many stops has the elevator taken to serve all the people. Consider elevator serves in the first come first serve basis.

A[] : represents the weight of each person in the queue
B[] : represents the floor where A[i] wants to alight
M   : represents the total number of floors
X   : represents the number of people who can accommodate into the lift at any given time
Y   : represents the weight limit


Example 1:

Let Array A be the weight of people to be considered A[] = {60, 80, 40, 20 }.
Let Array B be the floors where person needs to be dropped respectively B[] = {2, 3, 5,5}.
Total building floors be 5,max allowed person in elevator be 2 at a time with max weight capacity being 200. 
For this example, the elevator would take total of 5 stops

Example 2:

Let Array A be the weight of people to be considered A[] = {40, 40, 100, 80, 20}.
Let Array B be the floors where person needs to be dropped respectively B[] = {3, 3, 2, 2, 3}.
Total building floors be 3,max allowed person in elevator be 5 at a time with max weight capacity being 200. 
For this example, the elevator would take total of 6 stops


