package opgave3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Laurens on 16-2-2016.
 */
public class LineairSearch {

    public static final int ARRAY_LENGTH = Integer.MAX_VALUE/30;
    private Random random = new Random();

    class ZitErInA implements SearchFunction {
        public  boolean search(int getal, int[] a){
            boolean found = false;
            for(int getalInA: a){
                if(getal==getalInA)found=true;
            }
            return found;
        }
    }

    class ZitErInB implements SearchFunction {
        public boolean search(int getal, int[] a){
            for(int getalInA: a){
                if(getal==getalInA)return true;
            }
            return false;
        }
    }

    /**
     * An implementation of the binary search algorithm. This algorithm does not have a very proper stopping condition.
     */
    class ZitErInC implements SearchFunction {
        public boolean search(int getal, int[] a){
            if(getal<a[0]||getal>a[a.length-1])return false;
            int index = a.length/2;
            long iterations = 2;
            while(true) {
                iterations = iterations << 1;
                if(iterations==0)return false;//The 1 bit has reached the end and fell of the long
                int surplus = (int)Math.round((double)a.length / iterations);
                if (a[index] > getal) {
                    index -= surplus;
                } else if (a[index] < getal) {
                    index += surplus;
                } else {
                    return true;
                }

            }
        }
    }

    /**
     * An implementation of the binary search algorithm.
     */
    class ZitErInD implements SearchFunction {
        public boolean search(int getal, int[] a){
            if(getal<a[0]||getal>a[a.length-1])return false;
            int indexMin = 0, indexMax = a.length;
            while(indexMin<indexMax) {
                int indexMid = midPoint(indexMin,indexMax);
                if(getal<a[indexMid])
                    indexMax = indexMid;
                else
                    indexMin = indexMid+1;
                if(a[indexMid]==getal)return true;
            }
            if ((indexMin == indexMax) && (a[indexMin] == getal))
                return true;
            else
                return false;
        }

        int midPoint(int min, int max) {
            return (min+max)/2;
        }
    }

    public static void main(String[] args) {
        LineairSearch lineairSearch = new LineairSearch();
        lineairSearch.test();
    }


    private void test(){
        int[] allZero = new int[ARRAY_LENGTH];
        for (int i = 0 ; i < 1 ; i++) {
            printTimes(1, allZero,new ZitErInA(),"ZitErInA niet te vinden");
            printTimes(1, allZero,new ZitErInB(),"ZitErInB niet te vinden");
            printTimes(1, allZero, new ZitErInC(), "ZitErInC niet te vinden");
            printTimes(1, allZero, new ZitErInD(), "ZitErInD niet te vinden");
            int[] rand = rand(new int[ARRAY_LENGTH]);
            long beforeSort = System.currentTimeMillis();
            Arrays.sort(rand);
            for(int n : rand){
                System.out.println(n);
            }
            System.out.println("Sorting took " + (System.currentTimeMillis() - beforeSort));
            int toFind = random.nextInt();
            printTimes(toFind, rand,new ZitErInA(),"ZitErInA misschien te vinden");
            printTimes(toFind, rand,new ZitErInB(),"ZitErInB miscchien te vinden");
            printTimes(toFind, rand,new ZitErInC(),"ZitErInC miscchien te vinden");
            printTimes(toFind, rand,new ZitErInD(),"ZitErInD miscchien te vinden");
            toFind = rand[random.nextInt(rand.length)];
            printTimes(toFind, rand,new ZitErInA(),"ZitErInA zeker te vinden");
            printTimes(toFind, rand,new ZitErInB(),"ZitErInB zeker te vinden");
            printTimes(toFind, rand,new ZitErInC(),"ZitErInC zeker te vinden");
            printTimes(toFind, rand,new ZitErInD(),"ZitErInD zeker te vinden");
        }
    }

    private int[] rand(int[] input){
        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt();
        }
        return input;
    }


    public void printTimes(int getal, int[] a, SearchFunction function,String description){
        long time = System.currentTimeMillis();
        boolean found = function.search(getal, a);
        String foundDescription = found? "found":"not found";
        System.out.println(description + " took " + (System.currentTimeMillis()-time) + " to complete and the result was "+foundDescription);
    }

    interface SearchFunction {
        boolean search(int searchFor, int[] toSearch);
    }
}
