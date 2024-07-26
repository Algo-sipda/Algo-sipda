import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+2];

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[A]++;
            arr[B+1]--;
        }

        for(int i=1;i<=N;i++) {
            arr[i] += arr[i-1];
        }

        Arrays.sort(arr);

        System.out.println(arr[arr.length/2+1]);
    }
}