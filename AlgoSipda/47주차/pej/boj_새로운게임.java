// [boj] 새로운 게임 https://www.acmicpc.net/problem/17837
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static final int WHITE = 0, RED = 1, BLUE = 2;
	static int[][] box;
	static List<Integer>[][] players;
	static Map<Integer, int[]> idToPos;
	static int[] playerDirs;
	static int[][] way = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		box = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		players = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				players[i][j] = new ArrayList<>();
			}
		}

		idToPos = new HashMap<>();
		playerDirs = new int[K];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			idToPos.put(k, new int[] { r, c });
			playerDirs[k] = d;
			players[r][c].add(k);
		}

		int turn = 0;
		while (true) {
			turn++;
			if (turn > 1000) {
				System.out.println(-1);
				return;
			}

			for (int k = 0; k < K; k++) {
				int[] pos = idToPos.get(k);
				int dir = playerDirs[k];

				int index = players[pos[0]][pos[1]].indexOf(k);
				if (index == -1)
					continue;

				List<Integer> move = new ArrayList<>(
						players[pos[0]][pos[1]].subList(index, players[pos[0]][pos[1]].size()));
				players[pos[0]][pos[1]] = new ArrayList<>(players[pos[0]][pos[1]].subList(0, index));

				int nx = pos[0] + way[dir][0];
				int ny = pos[1] + way[dir][1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || box[nx][ny] == BLUE) {
					dir = reverse(dir);
					playerDirs[k] = dir;
					nx = pos[0] + way[dir][0];
					ny = pos[1] + way[dir][1];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N || box[nx][ny] == BLUE) {
						players[pos[0]][pos[1]].addAll(move);
						continue;
					}
				}

				if (box[nx][ny] == WHITE) {
					players[nx][ny].addAll(move);
				} else if (box[nx][ny] == RED) {
					Collections.reverse(move);
					players[nx][ny].addAll(move);
				}

				for (int m : move) {
					idToPos.put(m, new int[] { nx, ny });
				}

				if (players[nx][ny].size() >= 4) {
					System.out.println(turn);
					return;
				}
			}
		}
	}

	static int reverse(int d) {
		if (d == 0)
			return 1;
		if (d == 1)
			return 0;
		if (d == 2)
			return 3;
		return 2;
	}
}
