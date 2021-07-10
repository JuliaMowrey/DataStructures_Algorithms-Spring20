import java.util.*;
import java.io.*;
public class InsertionSort
{
  public static String[] insertionSort(int num, File file) throws IOException
  {
    Scanner in =new Scanner(file);
    int k=0;
    String Array[] = new String[num];
    while(in.hasNext())
    {
      Array[k]= in.nextLine();
      k++;
    }
    for (int j = 1; j < Array.length; j++) {
            String key = Array[j];
            int i = j-1;
            while (i>=0&&Array[i].compareTo(key)>0) {
                Array[i + 1] = Array[i];
                i--;
            }
            Array[i + 1] = key;
        }
        for(String value: Array)
        {
            System.out.print(value+", ");
        }
        return Array;
  }
  public static void main(String[] args) throws IOException
  {
    Scanner in = new Scanner(System.in);
    System.out.println("What is the name of the file?(add .txt if necessary)");
    String fileName= in.nextLine();
    System.out.println("How many strings are in this file to be sorted?");
    insertionSort(in.nextInt(), new File(fileName));

  }
}
