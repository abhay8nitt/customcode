class ElevatorStops {
    public static void main(String[] args) {
        System.out.println("Stops:"+ stops(new int[]{60,80,40,20},new int[]{2,3,5,5}, 5, 2, 200));
        System.out.println("Stops:"+ stops(new int[]{40, 40, 100, 80, 20},new int[]{3, 3, 2, 2, 3}, 3, 5, 200));
    }

    /**
     * 
       A[]  : represents the weight of each person in the queue
       B[]  : represents the floor where A[i] wants to alight
       M    : represents the total number of floors
       X    : represents the number of people who can accommodate into the lift at any given time
       Y    : represents the weight limit
     * @param A
     * @param B
     * @param M
     * @param X
     * @param Y
     * @return
     */
    public static int stops(int[] A, int[] B, int M, int X, int Y) {        
        int stops = 0;
        long weightPerTrip = 0;
        int maxPeople = 0;
        java.util.Set<Integer> distinctFloors = new java.util.HashSet();
        int current = 0;
        boolean elevate = false;
        while(current < A.length){
            if ((weightPerTrip + A[current]) <= Y && (maxPeople + 1) <= X){
                weightPerTrip += A[current];
                maxPeople++;
                distinctFloors.add(B[current]);
                if (current == A.length - 1) {
                    elevate = true;
                }
                current++;
            }
            else{
                elevate = true;
            }
            if (elevate){
                stops += distinctFloors.size() + 1;
                distinctFloors.clear();
                //reset for the next trip
                maxPeople = 0;
                weightPerTrip = 0;
                elevate = false;
            }
        }
        return stops;
    }

}
