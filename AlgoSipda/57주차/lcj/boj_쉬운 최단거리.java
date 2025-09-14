import java.io.*;
import java.util.*;

public class Main_14940_쉬운최단거리 {

	static int n, m;
	static int[][] map, result;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m];
		result = new int[n][m];
		
		int r = 0;
		int c = 0;
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					r = i;
					c = j;
				}
				else if(map[i][j] == 0) {
					visited[i][j] = true;
				}
			}
		}
		
		bfs(r, c);

		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(!visited[i][j]) {
					result[i][j] = -1;
				}
				System.out.print(result[i][j] +" ");
			}
			System.out.println();
		}
	}
	
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d = 0; d<4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(0<=nr && nr < n && 0<=nc && nc < m) {
					if(!visited[nr][nc] && map[nr][nc] == 1) {
						visited[nr][nc] = true;
						result[nr][nc] = result[cur[0]][cur[1]] + 1;
						q.add(new int[] {nr, nc});
					}
				}
			}
		}
	}

}