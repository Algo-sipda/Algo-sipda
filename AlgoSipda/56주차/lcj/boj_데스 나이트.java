import java.io.*;
import java.util.*;

public class Main_16948_데스나이트 {
	
	static class Point {
		int r, c, cnt;
		
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int N;
	static int sr, sc, er, ec;
	
	static int[] dr = {-2, -2, 0, 0, 2, 2};
	static int[] dc = {-1, 1, -2, 2, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.add(new Point(sr, sc, 0));
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.r == er && cur.c == ec) {
				return cur.cnt;
			}
			
			for(int d = 0; d<dr.length;d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(!isRange(nr, nc) || visited[nr][nc]) continue;
				q.add(new Point(nr, nc, cur.cnt+1));
				visited[nr][nc] = true;
			}
		}
		
		return -1;
	}
	
	private static boolean isRange(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}