import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            int idx = -1;
            for(int j = i-1; j >= 0; j--){
                if(arr[j] > arr[i]){
                    idx = j;
                    break;
                }
            }
            sb.append(idx+1).append(" ");
        }
        
        System.out.println(sb);
    }
}