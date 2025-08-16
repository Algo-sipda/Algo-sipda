import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N; // 도시의 수
	static int[][] W; // 드는 비용
	static int[][] dp; // dist[node][visit] : 현재 node 번에 있고 visit를 방문하고 왔을 때 0번(시작점)노드로 가는 최소 거리
	static int INF = (1000000*17+1);
	static int INFMAX = INF*2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		W = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[N][1 << N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INFMAX);
		}

		System.out.println(tsp(0, 1));
		br.close();
	}

	private static int tsp(int current, int visit) {
		visit = visit|(1<<current);
		// 이미 방문한 적이 있는 경우
		if (dp[current][visit] != INFMAX)
			return dp[current][visit];
		
		// 모든 마을을 방문하였을 경우 시작점으로 돌아가자
		if(visit == (1<<N)-1) {
			//마지막 노드에서 처음으로 갈 수 없는 경우
			if(W[current][0] == 0) return INF;
			//갈 수 있는 경우 : 마지막 노드(current)에서 0으로 가는 비용 return
			return W[current][0];
		}
			
		for (int next = 0; next < N; next++) {
			//current에서 next로 못 가는 경우, 방문한 경우
			if(W[current][next]==0 || (visit & (1 <<next))!= 0) continue;
			
			int tmp = tsp(next, visit)+W[current][next];
			
			if(dp[current][visit]>tmp) {
				dp[current][visit]=tmp;
			}
		}
		return dp[current][visit];
	}
}