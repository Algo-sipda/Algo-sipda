// [BOJ] 소문난 칠공주
// https://www.acmicpc.net/problem/1941

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int answer;
	static char[][] box;
	static int[] selected;
	static int[][] candidate;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		box = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			for (int j = 0; j < 5; j++) {
				box[i][j] = line.charAt(j);
			}
		}
		selected = new int[7];
		dfs(0, 0);
		System.out.println(answer);
	}

	static void dfs(int cnt, int idx) { // 7명 check
		if (cnt == 7) {
			int someCnt = 0;
			candidate = new int[5][5];
			for (int i = 0; i < 7; i++) {
				int value = selected[i];
				int x = value / 5;
				int y = value % 5;
				if (box[x][y] == 'S') {
					someCnt += 1;
				}
				candidate[x][y] = 1;
			}

			if (someCnt >= 4 && isValid(selected[0] / 5, selected[0] % 5)) {
				answer += 1;
			}
			return;
		}
		for (int i = idx; i < 25; i++) {
			selected[cnt] = i;
			dfs(cnt + 1, i + 1);
		}
	}

	static int[][] way = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static boolean isValid(int x, int y) {
		int near = 1;
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[5][5];
		queue.add(new int[] { x, y });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int w = 0; w < 4; w++) {
				int nx = now[0] + way[w][0];
				int ny = now[1] + way[w][1];
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
					continue;
				if (visited[nx][ny])
					continue;
				if (candidate[nx][ny] == 1) {
					visited[nx][ny] = true;
					queue.add(new int[] { nx, ny });
					near += 1;

				}
			}
		}
		return near == 7;
	}

}