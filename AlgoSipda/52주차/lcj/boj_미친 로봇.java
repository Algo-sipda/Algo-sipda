import java.io.*;
import java.util.*;

public class Main_1405_미친로봇 {
	
	static int N;
	static double[] percent;
	static boolean[][] visited;
	static double answer=0;
	
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		percent = new double[4];
		for(int i=0;i<4;i++) {//동 서 남 북
			 percent[i] = Integer.parseInt(st.nextToken())*0.01;
		}
		
		visited = new boolean[30][30];
		dfs(15, 15, 0, 1);
		System.out.println(answer);
	}
	
	private static void dfs(int r, int c, int idx, double total) {
		if(idx == N) {
			answer += total;
			return;
		}
		
		visited[r][c] = true;
		for(int i=0;i<4;i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr>=0 && nc>=0 && nr<30 && nc<30) {
				if(!visited[nr][nc]) {
					visited[nr][nc] = true;
					dfs(nr, nc, idx+1, total*percent[i]);
					visited[nr][nc] = false;
				}
			}
		}
	}
}