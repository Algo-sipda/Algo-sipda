import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	private static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, min;
	static int[][] map;
	static ArrayList<Point> homes, chickens;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());// 크기
		M = Integer.parseInt(st.nextToken());// 최대 치킨집의 개수
		
		map = new int[N][N];
		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
				//집 1, 치킨집 2
				if(map[i][j]==1) homes.add(new Point(i, j));
				else if(map[i][j] == 2) chickens.add(new Point(i, j));
			}
		}
		visited = new boolean[chickens.size()];
		min = Integer.MAX_VALUE;
		selectedChicken(0, 0);
		System.out.println(min);
	}

	private static void selectedChicken(int start, int cnt) {
		if(cnt == M) {
			int total = 0;
			for(int i=0;i<homes.size();i++) {
				int sd = Integer.MAX_VALUE;
				for(int j=0;j<chickens.size();j++) {
					if(visited[j]) {
						int distance = Math.abs(homes.get(i).x - chickens.get(j).x)+Math.abs(homes.get(i).y - chickens.get(j).y);
						sd = Math.min(sd, distance);
					}
				}
				total += sd;
			}
			min = Math.min(total, min);
			return;
		}
		
		for(int i=start;i<chickens.size();i++) {
			if(!visited[i]) {
				visited[i]= true;
				selectedChicken(i+1, cnt+1);
				visited[i]= false;
			}
		}
	}
}
