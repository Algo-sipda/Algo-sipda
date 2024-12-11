import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		int r, c, dir, point;

		public Point(int r, int c, int dir, int point) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.point = point;
		}
	}

	private static int N, M;
	private static int[][] map;
	private static boolean[][][] visited;
	private static Point start;
	private static Point end;
	private static int answer = Integer.MAX_VALUE;

	// 동(1), 서(2), 남(3), 북(4)
	private static int[] dr = { 0, 0, 1, -1 };
	private static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][4];

		// 맵 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 출발지점 입력
		st = new StringTokenizer(br.readLine(), " ");
		int startR = Integer.parseInt(st.nextToken()) - 1;
		int startC = Integer.parseInt(st.nextToken()) - 1;
		int startDir = Integer.parseInt(st.nextToken()) - 1;
		start = new Point(startR, startC, startDir, 0);

		// 끝지점 입력
		st = new StringTokenizer(br.readLine(), " ");
		int endR = Integer.parseInt(st.nextToken()) - 1;
		int endC = Integer.parseInt(st.nextToken()) - 1;
		int endDir = Integer.parseInt(st.nextToken()) - 1;
		end = new Point(endR, endC, endDir, 0);

		robot();
		System.out.println(answer);
	}

	private static void robot() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(start.r, start.c, start.dir, start.point));
		visited[start.r][start.c][start.dir] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.r == end.r && cur.c == end.c && cur.dir == end.dir) {
				answer = cur.point;
				return;
			}

			// 1. 지금 방향대로 쭉 가기
			// r, c, dir, point 그대로 queue에 추가
			// 범위 넘어갈 수 있는 부분 고려
			// 최대 1~3칸까지..
			for (int i = 1; i <= 3; i++) {
				int nr = cur.r + dr[cur.dir] * i;
				int nc = cur.c + dc[cur.dir] * i;
				if (!isRange(nr, nc) || map[nr][nc] == 1)
					break;
				if (!visited[nr][nc][cur.dir]) {
					visited[nr][nc][cur.dir] = true;
					q.add(new Point(nr, nc, cur.dir, cur.point + 1));
				}

			}

			// 2. 방향 바꾸기
			// dir: 1~2 => 3~4만 가능 / 3~4 => 1~2로만 방향 전환 가능
			// 여기서는 범위가 넘어갈 일이 없음.
			if (cur.dir == 0 || cur.dir == 1) {
				if(!visited[cur.r][cur.c][2]) {
					q.add(new Point(cur.r, cur.c, 2, cur.point + 1));
					visited[cur.r][cur.c][2] = true;
				}
				
				if(!visited[cur.r][cur.c][3]) {
					q.add(new Point(cur.r, cur.c, 3, cur.point + 1));
					visited[cur.r][cur.c][3] = true;
				}
			} else {
				if(!visited[cur.r][cur.c][0]) {
					q.add(new Point(cur.r, cur.c, 0, cur.point + 1));
					visited[cur.r][cur.c][0] = true;
				}
				
				if(!visited[cur.r][cur.c][1]) {
					q.add(new Point(cur.r, cur.c, 1, cur.point + 1));
					visited[cur.r][cur.c][1] = true;
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}