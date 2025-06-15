
// [BOJ] 점수따먹기
// https://www.acmicpc.net/problem/1749
// 2차원 압축해서 1차원으로 변경하기 
// kadane 알고리즘 : 음수를 포함하는 연속하는 부분 배열의 합의 최댓값 구하기 -> 현재까지의 누적합 vs 현재값

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] box;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				box[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		// 행 범위 고정(높이 고정) -> 열로 압축하여 1차원 문제로 바꿔 풀기
		int answer = Integer.MIN_VALUE;
		for (int top = 0; top < N; top++) {
			int[] temp = new int[M];
			for (int bottom = top; bottom < N; bottom++) {
				// 압축 진행 -> 1차원 배열로 변경
				for (int col = 0; col < M; col++) {
					temp[col] += box[bottom][col];
				}
				// temp는 (top ~ bottom) 행을 합친 1차원 배열이 됨
				int max = kadane(temp);
				answer = Math.max(answer, max);
			}
		}
		System.out.println(answer);
	}

	// 음수가 포함된 연속하는 부분 수열
	// 지금까지의 합을 저장하면서,, 지금까지의 누적 vs 새로 시작 
	static int kadane(int[] temp) {
		int maxSum = temp[0]; // 지금까지 나온 최대 연속합
		int current = temp[0]; // 현재 확인 중인 연속합
		for (int i = 1; i < temp.length; i++) {
			// current를 끊고 새로 시작 (자기 자신부터 시작)
			current = Math.max(temp[i], current + temp[i]);
			maxSum = Math.max(maxSum, current);
		}
		return maxSum;
	}

}