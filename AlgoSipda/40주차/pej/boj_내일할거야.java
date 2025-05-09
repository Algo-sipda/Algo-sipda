// [BOJ] 내일 할거야 https://www.acmicpc.net/problem/7983
// 정렬 + 가장 미루기(이것을 코드로 구현하는 방법)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int N; // 과제의 개수
	static int[][] homeworks;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		homeworks = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			homeworks[i][0] = Integer.parseInt(st.nextToken()); // d일
			homeworks[i][1] = Integer.parseInt(st.nextToken()); // t일 내에 끝내야 함
			// 연속으로 최대 며칠동안 놀 수 있는가
		}

		Arrays.sort(homeworks, Comparator.comparing((int[] x) -> -x[1]));

		int lastRest = homeworks[0][1] + 1;
		for (int i = 0; i < N; i++) {
			if (lastRest >= homeworks[i][1]) { // lastRest >= 마감일 : 마감일 기준 작업
				lastRest = homeworks[i][1] - homeworks[i][0];
			} else { // lastRest < 마감일 : 현재 가능한 마지막 날 기준으로 작업
				lastRest = lastRest - homeworks[i][0];
			}
		}
		// 마지막 과제를 모두 끝내고 쉴 수 있는 시작 날짜
		System.out.println(lastRest);
	}

}
