import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static int[][][] dp;
	private static int[] closetOrders;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine()," ");
		
		int left = Integer.parseInt(st.nextToken());
		int right = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		closetOrders = new int[M];
		dp = new int[M][N+1][N+1];
		
		for(int i=0;i<M;i++) {
			closetOrders[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<=N;j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.println(dfs(0, left, right));
	}

	private static int dfs(int idx, int left, int right) {
		if(idx == M) return 0;
		
		if(dp[idx][left][right] == -1) {
			dp[idx][left][right] = Math.min(Math.abs(left-closetOrders[idx]) + dfs(idx+1, closetOrders[idx], right), Math.abs(right-closetOrders[idx]) + dfs(idx+1, left, closetOrders[idx]));
		}
		
		return dp[idx][left][right];
	}
}