import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point{
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static int N, K;
	private static int[][] map;
	private static int S, X, Y;
	
	private static int[] dr = {1, 0, -1, 0};
	private static int[] dc = {0, 1, 0, -1};	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;
		Y = Integer.parseInt(st.nextToken())-1;
		
		exit:
		for(int i=0;i<S;i++) {
			for(int j=1;j<=K;j++) {
				bfs(j);
				if(map[X][Y]!= 0) break exit;
			}
		}
		System.out.println(map[X][Y]);
	}
	
	private static void bfs(int time) {
		Queue<Point> q = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == time) q.add(new Point(i, j));
			}
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(map[nr][nc] != 0) continue;
				
				map[nr][nc] = time;
			}
		}
	}

}
