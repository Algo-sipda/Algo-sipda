import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};//동 서 남 북
	static double[] percent;
	static boolean[][] visited;
	static double answer=0;

	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine()," ");
		 n = Integer.parseInt(st.nextToken());
		 
		 percent = new double[4];
		 for(int i=0;i<4;i++) {//동 서 남 북
			 percent[i] = Integer.parseInt(st.nextToken())*0.01;
		 }
		 
		 visited = new boolean[30][30];
		 dfs(15, 15, 0, 1);
		 System.out.println(answer);
	}

	private static void dfs(int x, int y, int idx, double total) {
		if(idx == n) {
			answer += total;
			return;
		}
		
		visited[x][y] = true;
		for(int i=0;i<4;i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if(nx>=0 && ny>=0 && nx<30 && ny<30) {
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny, idx+1, total*percent[i]);
					visited[nx][ny] = false;
				}
			}
		}
	}
}