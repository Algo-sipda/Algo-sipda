import java.util.*;
import java.io.*;

public class Main{
   static int[] arr;
   static int N;
   static int M;
   static int min = Integer.MAX_VALUE;
    
   public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      arr = new int[N];

      for (int i = 0; i < N; i++) {
         arr[i] = Integer.parseInt(br.readLine());
      }
      Arrays.sort(arr);

      int start = 0;
      int end = 0;

      while(end<N){
         if(arr[end] - arr[start] < M){
            end++;
         }
         else if(arr[end] - arr[start] > M){
            min = Math.min(arr[end] - arr[start], min);
            start++;
         }
         else {
            min = M;
            break;
         }
      }

      System.out.println(min);
   }
}