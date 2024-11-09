import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] nums, prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N];
        prefixSum = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        prefixSum[0] = nums[0];
        for(int i=1;i<N;i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            double answer = 0.0;
            if(start == 0) answer = prefixSum[end];
            else answer = prefixSum[end] - prefixSum[start-1];

            answer /= (end-start+1);
            sb.append(String.format("%.2f",answer)).append("\n");
        }
        System.out.print(sb);
    }
}
