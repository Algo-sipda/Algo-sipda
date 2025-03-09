import java.io.*;
import java.util.*;

public class Main_9084_동전 {
	
	static int N, M;
	static int[] arr, dp;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc<T;tc++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N+1];
			st = new StringTokenizer(br.readLine());
			
			for(int n = 1;n<=N;n++) {
				arr[n] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(br.readLine());
			
			dp = new int[M+1];
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					if(j-arr[i] > 0) dp[j] += dp[j-arr[i]];
					else if(j-arr[i] == 0) dp[j]++;
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		
		System.out.print(sb);
	}

}
