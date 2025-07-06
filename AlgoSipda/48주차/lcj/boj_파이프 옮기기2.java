import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] map;
	static long[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new long[N+1][N+1][3];
		dp[1][2][0] = 1;
		
		for(int i=1;i<=N;i++) {
			for(int j=3;j<=N;j++) {
				if(map[i][j] == 1) continue;
				
				// 가로
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				// 세로
				dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
				// 대각선
				if(map[i][j-1] == 0 && map[i-1][j] == 0) {
					dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		System.out.println(dp[N][N][0]+dp[N][N][1]+dp[N][N][2]);
	}

}
