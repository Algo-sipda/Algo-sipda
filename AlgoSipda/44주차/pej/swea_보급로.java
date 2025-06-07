// 1249. [S/W 문제해결 응용] 4일차 - 보급로
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
	static int T;
	static int N;
	static int[][] box;
	static int[][] way = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			box = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					box[i][j] = line.charAt(j) - '0';
				}
			}
			// 출발지 0,0 -> 도착지 N-1, N-1 : 가장 짧은 경로에 대한 총 복구 시간
			// 상하좌우 움직일 수 있음 -> dp만으로는 불가능
			// 다익스트라
			int answer = -1;
			boolean[][] visited = new boolean[N][N];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, 0, 0));

			while (!pq.isEmpty()) {
				Node now = pq.poll();
				if (now.x == N - 1 && now.y == N - 1) {
					answer = now.cost;
					break;
				}
				for (int w = 0; w < 4; w++) {
					int nx = now.x + way[w][0];
					int ny = now.y + way[w][1];
					if (inRange(nx, ny) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						pq.add(new Node(nx, ny, now.cost + box[nx][ny]));
					}
				}
			}
			System.out.println("#" + t + " " + answer);
		}

	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static class Node implements Comparable<Node> {
		int x, y, cost;

		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

}
