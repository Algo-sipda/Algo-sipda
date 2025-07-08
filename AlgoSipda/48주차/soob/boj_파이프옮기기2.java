import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


	public static class MapAnswer {
		// 가로 상태의 가지수, 세로 상태 가지수, 대각선 상태 가지수
		long[] answer = new long[3];

		public MapAnswer() {
			this.answer[0] = 0;
			this.answer[1] = 0;
			this.answer[2] = 0;
		}

		public MapAnswer(int answer0, int answer1, int answer2) {
			this.answer[0] = answer0;
			this.answer[1] = answer1;
			this.answer[2] = answer2;
		}

	}

	static int[] dx = { 1, 0, 1 };
	static int[] dy = { 0, 1, 1 };

	static MapAnswer[][] mapAnswer;
	static int map[][];
	static int N, answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		mapAnswer = new MapAnswer[N + 2][N + 2];
		map = new int[N + 2][N + 2];

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				mapAnswer[i][j] = new MapAnswer();
				map[i][j] = -1;
			}
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		mapAnswer[1][2] = new MapAnswer(1, 0, 0);

		for (int i = 1; i < N + 1; i++) {
			for (int j = 2; j < N + 1; j++) {
				if (i == 1 && j == 2)
					continue;
				if (map[i][j] == 0) {
					mapAnswer[i][j].answer[0] += mapAnswer[i][j - 1].answer[0] + mapAnswer[i][j - 1].answer[2];
					mapAnswer[i][j].answer[1] += mapAnswer[i - 1][j].answer[1] + mapAnswer[i - 1][j].answer[2];
					if (map[i][j - 1] == 0 && map[i - 1][j] == 0)
						mapAnswer[i][j].answer[2] += mapAnswer[i - 1][j - 1].answer[0]
								+ mapAnswer[i - 1][j - 1].answer[1] + mapAnswer[i - 1][j - 1].answer[2];
				}
			}
		}

		long answer = 0;
		for (long a : mapAnswer[N][N].answer) {
			answer += a;
		}

		System.out.println(answer);

	}

}