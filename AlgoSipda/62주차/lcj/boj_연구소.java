import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int r, c;
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	
	static int N, M;
	static int[][] map, copyMap;
	static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	static int answer = Integer.MIN_VALUE;
	static int WALL = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(answer);
	}
	
	private static void dfs(int wallCnt) {
		if(wallCnt == WALL) {
			bfs();
			return;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(wallCnt+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==2) {
					q.add(new Point(i, j));
				}
			}
		}
		
		copy();
		while(!q.isEmpty()) {
			int r = q.peek().r;
			int c = q.poll().c;
			for(int i=0;i<dir.length;i++) {
				int nr = r+dir[i][0];
				int nc = c + dir[i][1];
				
				if(0<= nr && 0<=nc && nr<N && nc<M) {
					if(copyMap[nr][nc] == 0) {
						q.add(new Point(nr, nc));
						copyMap[nr][nc] = 2;
					}
				}
			}
		}
		checkSafe();
	}
	
	private static void copy() {
		copyMap = new int[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				copyMap[i][j] = map[i][j]; 
			}
		}
	}

	private static void checkSafe() {
		int tmp= 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(copyMap[i][j]==0) tmp++;
			}
		}
		answer = Math.max(answer, tmp);
	}
}