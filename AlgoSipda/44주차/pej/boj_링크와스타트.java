// [BOJ] 링크와 스타트 https://www.acmicpc.net/problem/15661

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] box;
	static boolean[] isVisited;
	static int ans = Integer.MAX_VALUE;
    static int TEAM_LEN;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		box = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (TEAM_LEN = 1; TEAM_LEN <= N/2 + 1; TEAM_LEN++) { // 팀원이 정확히 몇명으로 나눠야 한다는 이야기가 없음 -> 1명인 팀부터 N/2 + 1 명인 팀까지 존재할 수 있음 (나머지 절반은 반복됨 )
			isVisited = new boolean[N];
			back(0, 0);
		}

		System.out.println(ans);
	}

	static void back(int cnt, int k) {
		if (cnt == TEAM_LEN) {
	
			int sum = 0, sumelse = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (isVisited[i] && isVisited[j]) {
						sum += box[i][j];
					} else if (!isVisited[i] && !isVisited[j]) {
						sumelse += box[i][j];
					}
				}
			}

			ans = Math.min(Math.abs(sumelse - sum), ans);
			return;
		}

		for (int i = k; i < N; i++) {
			if (isVisited[i]) {
				continue;
			}
			isVisited[i] = true;
			back(cnt + 1, i + 1);
			isVisited[i] = false;
		}
	}

}



/***
 * 더 느림 -> 모든 가능한 부분집합을 탐색하면서 중복 계산을 제거하지 않음 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] box;
	static boolean[] isVisited;
	static int ans = Integer.MAX_VALUE;
	static int TEAM_LEN;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		box = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		isVisited = new boolean[N];
		back(0, 0);

		System.out.println(ans);
	}

	static void back(int cnt, int k) {

		if (cnt >= 1 && cnt <= N - 1) { // 1명팀 ~ N-1명 팀
			int sum = 0, sumelse = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (isVisited[i] && isVisited[j]) {
						sum += box[i][j];
					} else if (!isVisited[i] && !isVisited[j]) {
						sumelse += box[i][j];
					}
				}
			}

			ans = Math.min(Math.abs(sumelse - sum), ans);
		}

		if (k == N)
			return; // 종료 조건 (모든 index 탐색 완료)

		// idx를 포함하는 경우
		isVisited[k] = true;
		back(cnt + 1, k + 1);
		isVisited[k] = false;

		// idx를 포함하지 않는 경우
		back(cnt, k + 1);

	}

}
 * 
 * 
 */