import java.util.HashSet;


/**
 * TopCoder Question
 * 
 You work for an electric company, and the power goes out in a rather large
 apartment complex with a lot of irate tenants. You isolate the problem to a network
 of sewers underneath the complex with a step-up transformer at every junction in
 the maze of ducts. Before the power can be restored, every transformer must be
 checked for proper operation and fixed if necessary. To make things worse, the
 sewer ducts are arranged as a tree with the root of the tree at the entrance to the
 network of sewers. This means that in order to get from one transformer to the
 next, there will be a lot of backtracking through the long and claustrophobic ducts
 because there are no shortcuts between junctions. Furthermore, it's a Sunday; you
 only have one available technician on duty to search the sewer network for the bad
 transformers. Your supervisor wants to know how quickly you can get the power back
 on; he's so impatient that he wants the power back on the moment the technician
 okays the last transformer, without even waiting for the technician to exit the
 sewers first.
 You will be given three int[]'s: fromJunction, toJunction, and ductLength that
 represents each sewer duct. Duct i starts at junction (fromJunction[i]) and leads
 to junction (toJunction[i]). ductlength[i] represents the amount of minutes it
 takes for the technician to traverse the duct connecting fromJunction[i] and
 toJunction[i]. Consider the amount of time it takes for your technician to
 check/repair the transformer to be instantaneous. Your technician will start at
 junction 0 which is the root of the sewer system. Your goal is to calculate the
 minimum number of minutes it will take for your technician to check all of the
 transformers. You will return an int that represents this minimum number of
 minutes.

 Constraints
 -fromJunction will contain between 1 and 50 elements, inclusive.
 -toJunction will contain between 1 and 50 elements, inclusive.
 -ductLength will contain between 1 and 50 elements, inclusive.
 -toJunction, fromJunction, and ductLength must all contain the same number of
 elements.
 -Every element of fromJunction will be between 0 and 49 inclusive.
 -Every element of toJunction will be between 1 and 49 inclusive.
 -fromJunction[i] will be less than toJunction[i] for all valid values of i.
 -Every (fromJunction[i],toJunction[i]) pair will be unique for all valid values of
 i.
 -Every element of ductlength will be between 1 and 2000000 inclusive.
 -The graph represented by the set of edges (fromJunction[i],toJunction[i]) will
 never contain a loop, and all junctions can be reached from junction 0.

 Examples
 0)
 {0}
 {1}
 {10}
 Returns: 10
 The simplest sewer system possible. Your technician would first check transformer
 0, travel to junction 1 and check transformer 1, completing his check. This will
 take 10 minutes.

 1)
 {0,1,0}
 {1,2,3}
 {10,10,10}
 Returns: 40
 Starting at junction 0, if the technician travels to junction 3 first, then
 backtracks to 0 and travels to junction 1 and then junction 2, all four

 transformers can be checked in 40 minutes, which is the minimum.

 2)
 {0,0,0,1,4}
 {1,3,4,2,5}
 {10,10,100,10,5}
 Returns: 165
 Traveling in the order 0-1-2-1-0-3-0-4-5 results in a time of 165 minutes which is
 the minimum.

 3)
 {0,0,0,1,4,4,6,7,7,7,20}
 {1,3,4,2,5,6,7,20,9,10,31}
 {10,10,100,10,5,1,1,100,1,1,5}
 Returns: 281
 Visiting junctions in the order 0-3-0-1-2-1-0-4-5-4-6-7-9-7-10-7-8-11 is optimal,
 which takes (10+10+10+10+10+10+100+5+5+1+1+1+1+1+1+100+5) or 281 minutes.

 4)
 {0,0,0,0,0}
 {1,2,3,4,5}
 {100,200,300,400,500}
 Returns: 2500
 */
public class PowerOutage {     


    /**
     * The main function which calls the
     * @param args
     */
    public static void main(String[] args){
        int len = sampleSpace.length;
        for(int i=0;i<len;i++) {
            //Prints the from, to and the ductLength
            print(i+1,sampleSpace[i][0],sampleSpace[i][1],sampleSpace[i][2]);
            if(!isValidInput(sampleSpace[i][0],sampleSpace[i][1],sampleSpace[i][2])){
                System.out.println("Invalid Input");
            } else{
                System.out.print("Minimum number of minutes for the technician to check all of the transformers::");
                //Find the minimum time taken
                int timeTaken = new PowerOutageImpl(sampleSpace[i][0],sampleSpace[i][1],sampleSpace[i][2]).minimumTimeTaken();
                System.out.println(timeTaken);
                assert timeTaken == sampleNumberToTime.get(i+1);
                System.out.println("-----------------------------------------------------------------------------------");
            }

        }
    }

    /**
     * This method prints the input taken to find the minimum time taken by the
     * technician to check all the transformers
     * @param sampleNo
     * @param fromJunction
     * @param toJunction
     * @param ductLength
     */
    private static void print(int sampleNo,int fromJunction[],int toJunction[],int ductLength[]){
        System.out.println("");
        System.out.println("");
        System.out.println("Sample Number::"+sampleNo);
        System.out.println("----------------");
        System.out.println("From Junction data-");
        System.out.println(java.util.Arrays.toString(fromJunction));

        System.out.println("To Junction data-");
        System.out.println(java.util.Arrays.toString(toJunction));

        System.out.println("The duct length-");
        System.out.println(java.util.Arrays.toString(ductLength));

        System.out.println("");

    }

    private static boolean isValidInput(int[] fromJunction, int[] toJunction, int[] ductLength){
        boolean isDataValid;
        /* -fromJunction will contain between 1 and 50 elements, inclusive.
           -toJunction   will contain between 1 and 50 elements, inclusive.
           -ductLength   will contain between 1 and 50 elements, inclusive.
           -toJunction, fromJunction, and ductLength must all contain the same number of elements.
        */
        isDataValid = checkRange(fromJunction.length,1,50)      &&
                      checkRange(toJunction.length,1,50)        &&
                      checkRange(ductLength.length,1,50)        &&
                      haveEqualElements(fromJunction,toJunction,ductLength);

        if(!isDataValid) {
            return false;
        }
            /*-Every element of fromJunction will be between 0 and 49 inclusive.
              -Every element of toJunction will be between 1 and 49 inclusive.
              -fromJunction[i] will be less than toJunction[i] for all valid values of i.
              -Every (fromJunction[i],toJunction[i]) pair will be unique for all valid values of i.
              -Every element of ductlength will be between 1 and 2000000 inclusive.
              -The graph represented by the set of edges (fromJunction[i],toJunction[i]) will
               never contain a loop, and all junctions can be reached from junction 0.
            */

        //Check fromJunction elements, return if invalid
        if(!valuesInRange(fromJunction,0,49)){
            return false;
        }

        //Check toJunction elements, return if invalid
        if(!valuesInRange(toJunction,1,49)){
            return false;
        }

        //Check ductLength elements, return if invalid
        if(!valuesInRange(ductLength,1,2000000)){
            return false;
        }

        //Check if fromJunction[i] < toJunction[i] for all i
        isDataValid = true;
        int len = fromJunction.length;
        for(int i=0;i < len;i++){
             if(!(fromJunction[i] < toJunction[i])){
                 isDataValid =false;
                 break;
             }
        }
        if(!isDataValid){
            return false;
        }

        //Check if fromJunction[i], toJunction[i] pair is Unique
        if(!areFromToPairsUnique(fromJunction,toJunction)){
            return false;
        }

        // The input is valid
        return isDataValid;
    }

    private static boolean areFromToPairsUnique(int from[],int to[]){
        int size =from.length;
        java.util.Set<FromToPair> temp = new HashSet<FromToPair>();
          for(int i =0;i<size;i++){
               boolean isUnique = temp.add(new FromToPair(from[i],to[i]));
              if(!isUnique){
                  return false;
              }
          }
        return true;
    }

    private static boolean valuesInRange(int data[],int min,int max){
        for(int aData: data){
            if(!checkRange(aData,min,max)){
                return false;
            }
        }
        return true;
    }

    private static boolean checkRange(int val,int min,int max) {
        return val >= min && val <= max;
    }

    private static boolean haveEqualElements(int[] fromJunction, int[] toJunction, int[] ductLength ){
        int fromLen = fromJunction.length;
        int toLen   = toJunction.length;
        int ductLen = ductLength.length;
        return (fromLen == toLen) && (toLen == ductLen);
    }



    /**
     * The sample space given in the problem statement
     */
    private final static int[][][] sampleSpace= {
            {{0},{1},{10}},
            {{0},{1},{10,11}},//Invalid
            {{0,1},{1},{10}}, //Invalid
            {{0,1,0},{1,2,3},{10,10,10}},
            {{0,1,0},{1,2,1},{10,10,10}},//Invalid
            {{0,1,0},{3,2,3},{10,10,10}},//Invalid
            {{0,0,0,1,4},{1,3,4,2,5},{15,15,100,15,5}},//{10,10,100,10,5}
            {{0,0,0,1,4,4,6,7,7,7,20},{1,3,4,2,5,6,7,20,9,10,31},{10,10,100,10,5,1,1,100,1,1,5}},
            {{0,0,0,0,0},{1,2,3,4,5},{100,200,300,400,500}},
            {{0,0,0,0,5},{0,2,3,4,5},{100,200,300,400,500,700}},//Invalid
            {{0,0,1,0,3},{1,2,3,4,5},{100,200,300,400,500}}
    };
    /**
     * A map to hold the results for each sample.
     */
    private final static java.util.Map<Integer,Integer> sampleNumberToTime = new java.util.HashMap<Integer,Integer>();
    static{
        sampleNumberToTime.put(1,10);
        sampleNumberToTime.put(2,-1);
        sampleNumberToTime.put(3,-1);
        sampleNumberToTime.put(4,40);
        sampleNumberToTime.put(5,-1);
        sampleNumberToTime.put(6,-1);
        sampleNumberToTime.put(7,165);
        sampleNumberToTime.put(8,281);
        sampleNumberToTime.put(9,2501);
        sampleNumberToTime.put(10,-1);
        sampleNumberToTime.put(11,2100);
    }

}

/**
 * PowerOutageImpl class to calculate the minimum time taken by the technician to check all of the transformers
 */
class PowerOutageImpl {
    int[] from;
    int[] to;
    int[] ductLength;

    PowerOutageImpl(int[] from, int[] to, int[] ductLength) {
        this.from = from;
        this.to = to;
        this.ductLength = ductLength;
    }

    /**
     * Accepts three parameters as under and returns the minimumTimeTaken
     * The key to this problem is to realize that the minimum time is
     * (2 * the sum of the times for all of the paths)  (the time for the longest path)
     * and that is faster and easier to compute this value then to evaluate
     * all of the possible routes through ducts.
     * @return
     */
    public int minimumTimeTaken() {
        int maxRoute=0;
        int min=0;
        for (int aTo : to) {
            if (isLeafElement(aTo)) {
                if (distanceToRoot(aTo) > maxRoute) {
                    maxRoute = distanceToRoot(aTo);
                }
            }
        }
        for (int aDuctLength : this.ductLength) {
            min = min + aDuctLength;
        }
        return 2 * min - maxRoute;
    }

    private boolean isLeafElement(int elem){
        for (int aFrom : from) {
            if (elem == aFrom) {
                return false;
            }
        }
        return true;
    }
    /**
     *
     * @param elem
     * @return The distance to root from elem
     */
    private int distanceToRoot(int elem){
        int n;
        n= getIndex(elem, to);
        int distance = ductLength[n];
        while(from[n]!=0){
            n= getIndex(from[n], to);
            distance=distance+ ductLength[n];
        }
        return distance;
    }

    /**
     *
     * @param elem
     * @param arr
     * @return
     */
    private int getIndex(int elem,int[] arr){
        for(int i=0;i<arr.length;i++){
            if(elem==arr[i]){
                return i;
            }
        }
        return -1;
    }


}

class FromToPair{
    private int from;
    private int to;

    public FromToPair(int from,int to){
        this.from = from;
        this.to =to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FromToPair)) return false;

        FromToPair that = (FromToPair) o;

        if (from != that.from) return false;
        return to == that.to;

        }

    @Override
    public int hashCode() {
        int result = from;
        result = 31 * result + to;
        return result;
    }
}

