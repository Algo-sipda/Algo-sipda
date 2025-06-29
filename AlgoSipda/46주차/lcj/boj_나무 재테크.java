import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tree {
	int r, c;
	int age;

	public Tree(int r, int c, int age) {
		super();
		this.r = r;
		this.c = c;
		this.age = age;
	}
}

public class Main {

	static int N, M, K;	
	static int[][] A;
	static int[][] food;
	static Deque<Tree> list = new LinkedList<>();

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][N + 1];
		food = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				food[i][j] = 5;
			}
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			list.add(new Tree(r, c, age));
		}

		while (K > 0) {
			treeInvestment();
			K--;
		}
		System.out.println(list.size());
	}

	private static void treeInvestment() {
		Queue<Tree> dieList = new LinkedList<>();

		// 봄
		for (int i = 0; i < list.size();) {
			Tree current = list.poll();
			if (food[current.r][current.c] >= current.age) {
				food[current.r][current.c] -= current.age;
				current.age++;
				i++;
				list.add(current);
			} else {
				dieList.add(current);
			}
		}

		// 여름
		for (Tree t : dieList) {
			food[t.r][t.c] += t.age / 2;
		}

		// 가을
		Queue<Tree> tmpList = new LinkedList<>();
		for (Tree t : list) {
			if (t.age % 5 == 0) {
				tmpList.add(t);
			}
		}

		while (!tmpList.isEmpty()) {
			Tree t = tmpList.poll();
			for (int i = 0; i < 8; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];
				if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
					list.addFirst(new Tree(nr, nc, 1));
				}
			}
		}

		// 겨울
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				food[i][j] += A[i][j];
			}
		}
	}
}
