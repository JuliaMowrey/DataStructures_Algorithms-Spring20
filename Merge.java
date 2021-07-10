import java.util.*;
import java.io.*;

class Merge {

      public static void main(String[] args) throws IOException
      {
        for(int value : merge(new File(args[0]),new File(args[1])))
        {
            System.out.print(value+" ");
        }
      }

    //MERGE METHOD

    static int[] merge(File file, File file2) throws IOException
    {
      //TRANSFER FILES TO ARRAYS
      int[] A1 = fileToArray(file);
      int[] A2 = fileToArray(file2);
     //SORT FILE 1 IN ASCENDING ORDER
      A1 = insertionSort(A1);
      //SORT FILE 2 IN DESCENDING ORDER
      A2 = reverseInsertionSort(A2);
      //CREATE MERGED ARRAY
      int[] mergedArray = mergeNSort(A1,A2,new int[A1.length+A2.length], A1.length, A2.length);

      return mergedArray;
    }

    //INSERTION SORT
    static int[] insertionSort(int[] array)
    {
      int key;
      for (int j = 1; j < array.length; j++) {
          key = array[j];
          int i = j - 1;
          while (i >= 0 && array[i] > key) {
              array[i + 1] = array[i];
              i--;
          }
          array[i + 1] = key;
      }
      for(int value:array)
      {
         System.out.print(value+" ");
      }
      System.out.println();
      return array;
    }


    //REVERSE INSERTION SORT
    static int[] reverseInsertionSort(int[] array)
    {
      int key;
      for (int j = 1; j < array.length; j++) {
          key = array[j];
          int i = j - 1;
          while (i >= 0 && array[i] < key) {
              array[i + 1] = array[i];
              i--;
          }
          array[i + 1] = key;
      }

      for(int value:array)
      {
         System.out.print(value+" ");
      }
      System.out.println();
      return array;
    }



    //COMPARE AND MERGE VALUE IN ARRAY
    static int[] mergeNSort(int[] array1, int[] array2, int[] R, int p, int r)
    {

      int i = p-1, j = 0, k = 0;
      //COMPARES VALUES AND PUTS THEM INTO NEW ARRAY
      while (i>= 0&& j < r) {
          if (array1[i] >= array2[j]) {
            R[k] = array1[i];
            i--;
            k++;
          } else {
            R[k] = array2[j];
            j++;
            k++;
          }
      }
      //ADDS REMAINING VALUES IF ANY
      while (i >=0) {
        R[k] = array1[i];
        i--;
        k++;
      }
      while (j < r) {
        R[k] = array2[j];
        j++;
        k++;
      }
      return R;
    }


    //FILE TO ARRAY
   static int[] fileToArray(File file) throws IOException
   {

      int k=0;
      Scanner in = new Scanner(file);
      ArrayList<Integer> array = new ArrayList<Integer>();
      while(in.hasNext())
      {
        array.add(in.nextInt());
        k++;
      }
      int[] ARRAY = new int[k];

      for(int i=0;i<ARRAY.length;i++)
      {
         ARRAY[i]= array.get(i).intValue();
      }
      return ARRAY;
   }
}
