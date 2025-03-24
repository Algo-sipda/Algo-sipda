import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static int[][] dp;
	static int answer;
	
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		dp = new int[n][n];
		//대나무 숲 정보 입력
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		for(int r = 0;r<n;r++) {
			for(int c = 0; c<n;c++) {
				int move = dfs(r, c);
				answer = Math.max(answer, move);
			}
		}
		System.out.println(answer);
	}

	private static int dfs(int r, int c) {
		if(dp[r][c] != 0) return dp[r][c];
		
		dp[r][c] = 1;
		
		for(int d=0;d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!isRange(nr, nc)) continue;
			
			if(map[nr][nc]>map[r][c]) {
				dp[r][c] = Math.max(dp[r][c], dfs(nr, nc)+1);
			}
		}
		return dp[r][c];
	}
	
	private static boolean isRange(int r, int c) {
		return r>=0 && c>=0 && r<n && c<n;
	}
}
