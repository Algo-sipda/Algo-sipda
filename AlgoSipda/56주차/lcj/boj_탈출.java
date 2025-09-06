import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class PointW {
	int r, c;

	public PointW(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

class PointH{
	int r, c, cost;

	public PointH(int r, int c, int cost) {
		super();
		this.r = r;
		this.c = c;
		this.cost = cost;
	}
}

public class Main {

	static int R, C;
	static String[][] map;
	static Queue<PointH> q = new LinkedList<>();
	static Queue<PointW> water = new LinkedList<>();
	static int answer = Integer.MAX_VALUE;
	static boolean escape = false;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new String[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				if (map[i][j].equals("S")) { // 고슴도치의 위치
					q.add(new PointH(i, j, 0));
				} else if (map[i][j].equals("*")) { // 물이 차 있는 위치
					water.add(new PointW(i, j));
				}
			}

		}

		bfs();

		if (answer == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(answer);
		}
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			// 1. 물 먼저 퍼뜨리기
			spreadWater();
			// 2. 고슴도치 이동
			moveHedgehog();
			
			if(escape)
				return;
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}

	private static void spreadWater() {
		int len = water.size();
		for(int i=0;i<len;i++) {
			PointW current = water.poll();
			
			int nr = current.r;
			int nc = current.c;
			
			for (int j = 0; j < 4; j++) {
				int wr = nr + dr[j];
				int wc = nc + dc[j];
				
				if (isRange(wr, wc) && map[wr][wc].equals(".")) {
					map[wr][wc] = "*";
					water.add(new PointW(wr, wc));
				}
			}
		}
	}
	
	private static void moveHedgehog() {
		int len = q.size();
		for(int i=0;i<len;i++) {
			PointH current = q.poll();
			
			int nr = current.r;
			int nc = current.c;
			int cost = current.cost;

			for(int j=0;j<4;j++) {
				int hr = nr + dr[j];
				int hc = nc + dc[j];
				
				if(isRange(hr, hc)) {
					if(map[hr][hc].equals("D")) {
						//목적지 도착
						escape = true;
						answer = Math.min(answer, cost+1);
						return;
					}
					else if(map[hr][hc].equals(".")) {
						map[hr][hc] = "S";
						q.add(new PointH(hr, hc, cost+1));
					}
				}
			}
		}
	}
}