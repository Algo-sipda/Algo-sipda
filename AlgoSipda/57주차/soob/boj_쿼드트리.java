import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int[] dx = { 0, 0, 1, 1 };
	static int[] dy = { 0, 1, 0, 1 };
	static int[][] video;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		video = new int[N][N];
		char[] arr = new char[N];

		for (int i = 0; i < N; i++) {
			arr = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (arr[j] == '1')
					video[i][j] = 1;
			}
		}

		if (N == 1) {
			System.out.println(video[0][0]);
		} else {
			if (canZip(0, 0, N) == 1) {
				System.out.println("1");
				return;
			} else if (canZip(0, 0, N) == 0) {
				System.out.println("0");
				return;
			} else {
				zip(0, 0, N);
			}
		}

		System.out.println(sb);
	}

	public static void zip(int r, int c, int n) {

		if (n == 1) {
			sb.append('(');
			for (int i = 0; i < 4; i++) {
				sb.append(video[r + dx[i]][c + dy[i]]);
			}
			sb.append(')');
			return;
		}

		sb.append('(');

		int x, y, check;
		for (int i = 0; i < 4; i++) {
			x = r + n / 2 * dx[i];
			y = c + n / 2 * dy[i];
			check = canZip(x, y, n / 2);
			if (check == 0) {
				sb.append(0);
			} else if (check == 1) {
				sb.append(1);
			} else {
				zip(x, y, n / 2);
			}
		}
		sb.append(')');
	}

	public static int canZip(int x, int y, int n) {

		int sum = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += video[i + x][j + y];
			}
		}

		if (sum == 0)
			return 0;
		else if (sum == Math.pow(n, 2))
			return 1;
		else
			return -1;
	}

}