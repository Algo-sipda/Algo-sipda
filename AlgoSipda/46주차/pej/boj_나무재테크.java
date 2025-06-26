// [BOJ] 나무 재테크 https://www.acmicpc.net/problem/16235
// 시간초과 -> 봄, 여름 계산 합치기 
// age만 관리하고 있으면 된다 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] A; // 겨울에 추가될 양분
	static int[][] ground; // 현재 양분 상태
	static PriorityQueue<Integer>[][] trees; // 나무 나이만 저장

	static int[][] way = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		ground = new int[N][N];
		trees = new PriorityQueue[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				ground[i][j] = 5;
				trees[i][j] = new PriorityQueue<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			trees[x][y].add(age);
		}

		for (int year = 0; year < K; year++) {
			springAndSummer();
			fall();
			winter();
		}

		// 결과 출력: 남아있는 나무 수 세기
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += trees[i][j].size();
			}
		}
		System.out.println(result);
	}

	static void springAndSummer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (trees[i][j].isEmpty())
					continue;

				PriorityQueue<Integer> newTrees = new PriorityQueue<>();
				int nutrients = ground[i][j]; // 현재 땅의 영양분 
				int deadSum = 0; // 죽은 나무의 나이/2 의 합 (추가될 영양분 )

				// 나무 순회
				while (!trees[i][j].isEmpty()) {
					int age = trees[i][j].poll();
					if (nutrients >= age) {
						nutrients -= age;
						newTrees.add(age + 1);
					} else {
						deadSum += age / 2;
					}
				}

				ground[i][j] = nutrients + deadSum;
				trees[i][j] = newTrees;
			}
		}
	}

	static void fall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int age : trees[i][j]) {
					if (age % 5 == 0) {
						for (int[] dir : way) {
							int nx = i + dir[0];
							int ny = j + dir[1];
							if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
								trees[nx][ny].add(1);
							}
						}
					}
				}
			}
		}
	}

	static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ground[i][j] += A[i][j];
			}
		}
	}
}
