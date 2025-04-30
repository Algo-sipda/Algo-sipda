import java.io.*;
import java.util.*;

public class Main_14712_넴모넴모 {

	static int N, M, answer = 0;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		dfs(0);
		
		System.out.println(answer);
	}

	public static void dfs(int depth) {
		if(depth == N*M) {
			if(check()) answer++;
			return;
		}
		
		int r = depth/M;
		int c = depth%M;
		
		dfs(depth+1);
		visited[r][c] = true;
		dfs(depth+1);
		visited[r][c] = false;
	}
	
	public static boolean check() {
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M-1;j++) {
				if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1])
					return false;
			}
		}
		return true;
	}
}
