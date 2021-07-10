import java.util.*;
import java.io.*;

public class SelectionSort{
  public static void main(String[] args) throws IOException
  {
      for(int value: selectionSort(new File(args[0]))){
      System.out.println(value+" ");
    }
  }

  static int[] selectionSort(File file) throws IOException
  {
    int[] array = fileToArray(file);
    int key=0;
    int k;
    for(int j=array.length-1;j>1;j--){
     k = j;
     for(int i= j-1;i>=0;i--){
       if(array[k]<array[i]){
         k = i;
       }

     }
     key = array[j];
     array[j] = array[k];
     array[k] = key;

    }

    return array;
  }
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
