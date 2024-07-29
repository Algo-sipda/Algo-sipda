import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            arr[s]++;
            if(e != N-1)
                arr[e+1]--;
        }

        int sum = 0;
        for(int i = 0; i < N; i++){
            arr[i] += sum;
            sum = arr[i];
        }

        Arrays.sort(arr);

        System.out.println(arr[N/2]);
    }
}