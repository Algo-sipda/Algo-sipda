import java.io.*;
import java.util.*;

public class Main_17484_진우의달여행_SMALL {
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	
	static int[] dc = {-1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<M;i++) {
			dfs(0, i, -1, map[0][i]);
		}
		
		
		System.out.println(answer);
	}
	
	public static void dfs(int r, int c, int dir, int sum) {
		if(r == N) {
			answer = Math.min(answer, sum);
			return;
		}

		int nr = r+1;
		for(int d = 0; d<3;d++) {
			if(dir == d) continue;
			
			int nc = c + dc[d];
			
			if(!isRange(nr, nc)) continue;
			
			dfs(nr, nc, d, sum + map[nr][nc]);
		}
	}
	
	public static boolean isRange(int r, int c) {
		return 0<= r && r<=N && 0<=c && c<M;
	}

}