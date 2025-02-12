import java.io.*;
import java.util.*;

public class Main_16973_직사각형탈출 {
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int h, w, sr, sc, fr, fc;
	static int[][] map;
	static List<Point> list;
	static int answer = -1;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		map = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					map[i][j] = -1;
					list.add(new Point(i, j));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		fr = Integer.parseInt(st.nextToken());
		fc = Integer.parseInt(st.nextToken());
		
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N+1][M+1];
		
		q.add(new Point(sr, sc));
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.r == fr && cur.c == fc) {
				answer = map[cur.r][cur.c];
				return;
			}
			
			for(int d = 0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(!isRange(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				
				if(!isPossible(nr, nc)) continue;
				
				if(map[nr][nc] == 0) {
					q.add(new Point(nr, nc));
					visited[nr][nc] = true;
					map[nr][nc] = map[cur.r][cur.c] + 1;
				}
			}
		}
	}
	
	public static boolean isPossible(int r, int c) {
		if(r+h-1 > N || c+w-1 > M) return false;
		
		for(int i=0;i<list.size();i++) {
			Point p = list.get(i);
			
			if(p.r >= r && p.r<=r+h-1 && p.c >= c && p.c<= c+w-1) return false;
		}
		return true;
	}
	
	public static boolean isRange(int r, int c) {
		return 0<r && r<=N && 0<c && c<=M;
	}
}
