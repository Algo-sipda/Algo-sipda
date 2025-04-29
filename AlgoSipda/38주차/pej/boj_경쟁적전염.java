// [BOJ] 경쟁적 전염 https://www.acmicpc.net/problem/18405

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] box;
	static int N, K;
	static List<int[]> virus = new ArrayList<>();
	static int[][] way = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N개의 원소
		K = Integer.parseInt(st.nextToken()); // 모든 바이러스 번호는 K이하의 자연수
		box = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] != 0) {
					virus.add(new int[] { box[i][j], 0, i, j }); // virusId, time, 위치
				}
			}
		}
		Collections.sort(virus, Comparator.comparing((int[] x) -> x[0])); // virusId가 낮은 종류의 바이러스부터 먼저 증식한다고 문제에 나와있음 

		// S 초 뒤에 X, Y의 바이러스 종류
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;
		int Y = Integer.parseInt(st.nextToken()) - 1;

		Queue<int[]> queue = new ArrayDeque<>(virus);
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[1] == S) {
				break;
			}

			for (int w = 0; w < 4; w++) {
				int nx = now[2] + way[w][0];
				int ny = now[3] + way[w][1];
				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if (box[nx][ny] == 0) {
						box[nx][ny] = now[0]; // 바이러스 기록 
						queue.add(new int[] { now[0], now[1] + 1, nx, ny });
					}
				}
			}
		}
		System.out.println(box[X][Y]);
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
