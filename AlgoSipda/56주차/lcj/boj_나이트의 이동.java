import java.io.*;
import java.util.*;

public class Main_7562_나이트의이동 {
	
	static int I, r1, c1, r2, c2;
	static int[][] arr;
	static boolean[][] visited;
	
	static int[] dr = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] dc = {2, 1, -1, -2, -2, -1, 1, 2};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0;tc<T;tc++) {
			I = Integer.parseInt(br.readLine());
			
			arr = new int[I][I];
			visited = new boolean[I][I];
			
			st = new StringTokenizer(br.readLine());
			r1 = Integer.parseInt(st.nextToken());
			c1 = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			r2 = Integer.parseInt(st.nextToken());
			c2 = Integer.parseInt(st.nextToken());
			
			bfs();
			
			sb.append(arr[r2][c2]).append("\n");
		}
		System.out.print(sb);

	}
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {r1, c1});
		visited[r1][c1] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d = 0; d<dr.length;d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(!isRange(nr, nc) || visited[nr][nc]) continue;
				
				q.add(new int[] {nr, nc});
				arr[nr][nc] = arr[cur[0]][cur[1]]+1;
				visited[nr][nc] = true;
				
				if (nr == r2 && nc == c2) return;
				
			}
		}
	}
	
	private static boolean isRange(int r, int c) {
		return 0<=r && r<I && 0<=c && c<I;
	}

}