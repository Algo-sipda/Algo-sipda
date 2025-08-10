import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static double[] percent;
	static boolean[][] visited;
	static int[][] way = {{0,1},{0,-1},{1,0},{-1,0}};
	static double res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // N번의 행동
		
		percent = new double[4];
		percent[0] = Integer.parseInt(st.nextToken())*0.01;
		percent[1] = Integer.parseInt(st.nextToken())*0.01;
		percent[2] = Integer.parseInt(st.nextToken())*0.01;
		percent[3] = Integer.parseInt(st.nextToken())*0.01;
		// 이동 경로가 단순하다  == 같은 곳을 한 번보다 많이 이동하지 않는다 == 방문한 곳은 다시 방문하지 않는다.
		// EENE ENW -> 단순 O; ENWS WWWSNE -> 단순 X 
		// 로봇의 이동 경로가 단순할 확률 ? 
		
		visited = new boolean[30][30]; // (0,0) ~ (15,15)
		res = 0;
		dfs(15, 15, 0, 1); // 시작 위치 ? -> 한 방향으로 최대 15까지 움직일 수 있으므로 시작점:(15,15); (0,0) ~ (29,29)
		System.out.println(res);
	}

	private static void dfs(int x, int y, int cnt, double total) {
		if(cnt == N) {
			res += total;
			return ;
		}
		visited[x][y] = true;
		for(int w = 0; w < 4; w++) { // 동(0) 서(1) 북(2) 남(3)
			int nx = x + way[w][0];
			int ny = y + way[w][1];
			if(nx < 0 || nx >= 30 || ny < 0 || ny >= 30 || visited[nx][ny]) {continue;}
			visited[nx][ny] = true;
			dfs(nx,ny, cnt+1, total * percent[w]);
			visited[nx][ny] = false;
		}
	}

}
