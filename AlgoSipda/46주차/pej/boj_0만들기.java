// [BOJ] 0 만들기 https://www.acmicpc.net/problem/7490
// 이전 처리중인 숫자 : 공백 고르면 누적, + 나 - 고르면 해당 값이 더해지고 빠진 것

// [BOJ] 0 만들기 https://www.acmicpc.net/problem/7490
// 이전 처리중인 숫자 : 공백 고르면 누적, + 나 - 고르면 해당 값이 더해지고 빠진 것

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int T, N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			search("1", 1, 0, 1, 1);
			sb.append('\n');
		}
		System.out.println(sb);
	}

	// str : 현재까지 만들어진 수식 문자열
	// depth : 현재까지 사용한 숫자
	// sum : 이전까지 계산된 결과값
	// num : 현재 처리 중인 숫자
	// op : 현재 num 앞의 부호
	static void search(String str, int depth, int sum, int num, int op) {
		if (depth == N) {
			if (sum + (num * op) == 0) {
				sb.append(str + '\n');
			}
			return;
		}
		search(str + " " + (depth + 1), depth + 1, sum, (num * 10) + depth + 1, op);
		search(str + "+" + (depth + 1), depth + 1, sum + (num * op), depth + 1, 1);
		search(str + "-" + (depth + 1), depth + 1, sum + (num * op), depth + 1, -1);
	}

}
