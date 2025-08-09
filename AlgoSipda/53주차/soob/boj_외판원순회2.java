import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long answer = Long.MAX_VALUE;
	static int[][] map;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(map[i], 0);
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0, 0, 0);
		
		System.out.println(answer);
	}

	public static void DFS(int start, int cost, int visitCount) {
		if (start == 0 && visitCount == N) {
			answer = Math.min(answer, cost);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (map[start][i] != 0 && !visited[i]) {
				visited[i] = true;
				DFS(i, cost + map[start][i], visitCount + 1);
				visited[i] = false;
			}
		}
	}

}