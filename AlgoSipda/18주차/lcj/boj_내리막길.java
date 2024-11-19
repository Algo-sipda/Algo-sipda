import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] map, dp;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	public static int dfs(int r, int c) {
		if(r == N-1 && c == M-1) {
			return 1;
		}
		
		if(dp[r][c] != -1) return dp[r][c];
		
		dp[r][c] = 0;
		for(int d = 0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!isRange(nr, nc)) continue;
			if(map[r][c] > map[nr][nc])
				dp[r][c] += dfs(nr, nc);
		}
		
		return dp[r][c];
	}
	
	public static boolean isRange(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
