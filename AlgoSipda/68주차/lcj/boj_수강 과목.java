import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[K+1][N+1];
		int[] I = new int[K+1];
		int[] T = new int[K+1];
		
		for(int i=1;i<=K;i++) {
			st = new StringTokenizer(br.readLine());
			I[i] = Integer.parseInt(st.nextToken());
			T[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=K;i++) {
			for(int j=1;j<=N;j++) {
				if(j-T[i] >= 0) {
					// 시간 - 현재 과목의 공부시간이 양수 => 해결 가능
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-T[i]] + I[i]);
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[K][N]);
	}

}