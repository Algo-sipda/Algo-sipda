import java.io.*;
import java.util.*;

public class Main {
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> cheese = new ArrayList<>();
	static int cheeseCnt = 0;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheese.add(new Point(i, j));
					cheeseCnt++;
				}
			}
		}
		int time = 0;
		while(cheeseCnt != 0) {
			time++;
			visited = new boolean[N][M];
			// 외부 치즈 구하기
			checkCheeseOutline(0, 0);
			// 치즈 녹이기
			meltingCheese();
		}
		System.out.println(time);
	}
	
	// 외부 치즈 구하기
	public static void checkCheeseOutline(int r, int c) {
		visited[r][c] = true;
		map[r][c] = 2;
		
		for(int d = 0;d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nc<0 || nr>=N || nc >=M) continue;
			if(visited[nr][nc] || map[nr][nc] == 1) continue;
			
			checkCheeseOutline(nr, nc);
		}
	}
	
	// 치즈 녹이기
	public static void meltingCheese() {
		int size = cheese.size();
		for(int i=0;i<cheese.size();i++) {
			Point cur = cheese.get(i);
			
			int cnt = 0;
			for(int d = 0; d<4;d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
				if(map[nr][nc] >= 2) cnt++;
			}
			
			if(cnt>=2) {
				map[cur.r][cur.c] = 0;
				cheese.remove(i);
				cheeseCnt--;
				i--;
			}
		}
		
	}
}