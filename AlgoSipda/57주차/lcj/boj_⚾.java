import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static boolean[] selected;
	static boolean[] visited;
	static int[] battingOrder;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][10];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		selected = new boolean[10];
		battingOrder = new int[10];

		selected[4] = true;// 1번 타수는 이미 코치가 지정했으니까
		battingOrder[4] = 1;// 타순 4번 자리는 1번으로 지정

		permutation(2);

		System.out.println(answer);
	}

	private static void permutation(int cnt) {
		if (cnt == 10) {
			playBaseBall();
			return;
		}

		for (int i = 1; i < selected.length; i++) {
			if (selected[i])
				continue;

			selected[i] = true;
			battingOrder[i] = cnt;
			permutation(cnt + 1);
			selected[i] = false;
		}
	}

	private static void playBaseBall() {
		int sum = 0;

		int playerIdx = 1;// 플레이어 인덱스 수

		for (int r = 0; r < N; r++) {
			int out = 0;// 아웃된 플레이어 수
			visited = new boolean[3];

			while (out < 3) {
				int a = map[r][battingOrder[playerIdx]];
				switch (a) {
				case 1://안타
					for(int i=visited.length-1;i>=0;i--) {
						if(visited[i]) {
							int x = i+1;
							visited[i] = false;
							if(x>2) {
								sum++;
							}else {
								visited[x] = true; 
							}
						}
					}
					visited[0] = true;
					break;
				case 2://2루타
					for(int i=visited.length-1;i>=0;i--) {
						if(visited[i]) {
							int x = i+2;
							visited[i] = false;
							if(x>2) {
								sum++;
							}else {
								visited[x]=true; 
							}
						}
					}
					visited[1] = true;
					break;
				case 3://3루타
					for(int i=0;i<visited.length;i++) {
						if(visited[i]) sum++;
						visited[i]= false; 
					}
					
					visited[2] = true;
					break;
				case 4://홈런
					for(int i=0;i<visited.length;i++) {
						if(visited[i]) sum++;
						visited[i]= false; 
					}
					sum++;
					break;
				case 0://아웃
					out++;
					break;
				}
				playerIdx++;// 다음 타자
				if (playerIdx >= 10)
					playerIdx = 1;// 10번 타자 끝나면 1번 타자로 이동
			}

		}

		answer = Math.max(answer, sum);
	}

}
