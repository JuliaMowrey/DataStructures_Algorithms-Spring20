/*FEBRUARY 21 2020
*JULIA MOWREY
*JM890455@WCUPA.EDU
*CSC241_01
*PROFESSOR HUNG
*
*THIS IS A PROGRAM THAT TAKES AN ARRAY OF UNSORTED INTEGERS
* AND IMPLEMENTS THE DISTRIBUTION COUNTING SORT ALGORITHM.
*
*THE TIME/SPACE COMPLEXITY WILL BE THETA(N)
*/

import java.util.*;
import java.io.*;

public class DistributionCountingSort {
      static int[] DistributionCountingSort(int num, File file, int min, int max) throws IOException {
        //INITIAL VARIABLES
        Scanner in = new Scanner(file);
        int[] COUNT = new int[max-min+1];
        int[] UNSORTED = new int[num];
        int k=0; //WILL BE USED AS A TEMPORARY VARIABLE THROUGHOUT CODE

        //TRANSLATES TEST-FILE INTO AN UNSORTED ARRAY
        while(in.hasNext()){
            UNSORTED[k]= in.nextInt();
            k++;
        }

        //STEP D1: SETS COUNT == 0
        for (int i = 0; i < COUNT.length-1; i++) {//Step D1
          COUNT[i] = 0;
        }

        //STEPS D2-D3 COUNTS EACH INDEX VALUE IN ARRAY
        for (int j = 0; j<=UNSORTED.length-1; j++)
        {
            if(UNSORTED[j]==max)
            {
                COUNT[COUNT.length-1]++;
            }
            else if(UNSORTED[j]==min)
            {
                COUNT[0]++;
            }
            else{
              COUNT[UNSORTED[j]-min]++;
            }
        }

        //SET TEMPORARY VARIABLE
        k=1;

        //STEP D4: ADD 1 TO EACH COUNT
        for(int i=0;i<=COUNT.length-1;i++){ //Step D4

          if(COUNT[i]==0)
          {
              COUNT[i]+=COUNT[i-1];
          }
          else
          {
              COUNT[i]=k;
              k++;
          }

        }

        //INITIALIZE SORTED ARRAY: WILL HOLD SORTED ARRAY
        int[] SORTED = new int[UNSORTED.length];

        //SETS ALL S[UNSORTED[i]]==0
        for(int i = 0; i < SORTED.length; i++){
            SORTED[i] = 0;
        }

        //STEP D5-D6: SORT ARRAY
        for(int j = UNSORTED.length-1; j >= 0; j--) {

            k = COUNT[UNSORTED[j]-min];

            SORTED[k-1] = UNSORTED[j];
            COUNT[UNSORTED[j]-min]--;
        }
    //END
        return SORTED;
    }



    public static void main(String[] args) throws IOException {

        //INITIAL VARIABLES
        Scanner in = new Scanner(System.in);
        PrintWriter FW = new PrintWriter("array.txt");
        int min=0;
        int max=0;

        //GET INPUT

        System.out.println("How many values will your array contain?");
        int num = in.nextInt();

        in.nextLine();

        int Array[]= new int[num];

        //INPUTS VALUES INTO A TEST-FILE
        for(int i=0;i<=Array.length-1;i++){
            System.out.println("Enter a value into the array.");
            Array[i]=in.nextInt();
            FW.println(Array[i]);
        }
        FW.close();
        //GET MAX AND MIN VALUES
        for(int i=0;i<Array.length-1;i++){
          if(Array[i]>max){
            max=Array[i];
          }
        }

        for(int i=0;i<Array.length-1;i++){
         min=Array[0];
          if(Array[i]<min)
          {
            min=Array[i];
          }
        }
         
        //INPUT VARIABLES INTO DISTRIBUTION COUNTING SORT METHOD
        int[] sortedIndex = DistributionCountingSort(num, new File("array.txt"),min,max);

        //OUTPUT SORTED ARRAY
        System.out.println(Arrays.toString(sortedIndex));
    }
}
