// 5648. [모의 SW 역량테스트] 원자 소멸 시뮬레이션
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRFInKex8DFAUo
// 원자의 최초 위치 (x, y) 
// 상 : +y
// 하 : -y
// 좌 : -x
// 우 : +x
// 처음에 Map<String, List<Integer>> 로 두었었는데 -> 시간 초과 남 key: x좌표,y좌표 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static final int MAX_ARR_LEN = 4001;
	static int T, N, answer;
	static int[][] arrmap = new int[MAX_ARR_LEN][MAX_ARR_LEN];

	static class Atom {
		int x, y, dir, energy;
		boolean isLive;

		Atom(int x, int y, int dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
			this.isLive = true;
		}
	}

	static List<Atom> atoms;
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			input(br);
			simulate();
			System.out.println("#" + tc + " " + answer);
		}
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		answer = 0;
		atoms = new ArrayList<>();
		for (int[] row : arrmap)
			Arrays.fill(row, 0);

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
			int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
			int dir = Integer.parseInt(st.nextToken());
			int energy = Integer.parseInt(st.nextToken());
			atoms.add(new Atom(x, y, dir, energy));
			arrmap[x][y] = 1;
		}
	}

	static boolean allVanish() {
		for (Atom a : atoms)
			if (a.isLive)
				return false;
		return true;
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < MAX_ARR_LEN && y >= 0 && y < MAX_ARR_LEN;
	}

	static void simulate() {
		while (!allVanish()) {
			// 1. Move all atoms
			for (Atom atom : atoms) {
				if (!atom.isLive)
					continue;

				int cx = atom.x;
				int cy = atom.y;
				int nx = cx + dx[atom.dir];
				int ny = cy + dy[atom.dir];

				if (inRange(nx, ny)) {
					arrmap[cx][cy] = 0;
					arrmap[nx][ny] += 1;
					atom.x = nx;
					atom.y = ny;
				} else {
					arrmap[cx][cy] = 0;
					atom.isLive = false;
				}
			}

			// 2. Handle collisions
			for (int i = 0; i < atoms.size(); i++) {
				Atom atomI = atoms.get(i);
				if (!atomI.isLive)
					continue;

				int cx = atomI.x;
				int cy = atomI.y;

				if (arrmap[cx][cy] >= 2) { // 체크해 둔 게 2개보다 크다 -> 이곳이 충돌 지점 
					for (int j = 0; j < atoms.size(); j++) { // 모든 원자들에 대해서
						if (i == j) // 지금 원자 x 
							continue;
						Atom atomJ = atoms.get(j);
						if (!atomJ.isLive)
							continue;
						if (atomJ.x == cx && atomJ.y == cy) { // 충돌한 다른 원자들인 경우
							answer += atomJ.energy;
							atomJ.isLive = false; // 원자들 소멸 처리 
						}
					}
					answer += atomI.energy;
					atomI.isLive = false;
					arrmap[cx][cy] = 0;
				}
			}
		}
	}
}
