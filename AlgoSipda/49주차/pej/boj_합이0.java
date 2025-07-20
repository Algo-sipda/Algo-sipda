// [BOJ] 합이 0
// https://www.acmicpc.net/problem/3151

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = sc.nextInt();

		Arrays.sort(A);
		long count = 0;

		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				int sum = A[i] + A[left] + A[right];

				if (sum == 0) {
					if (A[left] == A[right]) {
						int n = right - left + 1;
						count += (long) n * (n - 1) / 2;
						break;
					} else { // left같은 수 * right 같은 수
						int cntLeft = 1;
						int cntRight = 1;
						while (left + 1 < right && A[left] == A[left + 1]) {
							cntLeft++;
							left++;
						}
						while (right - 1 > left && A[right] == A[right - 1]) {
							cntRight++;
							right--;
						}
						count += (long) cntLeft * cntRight;
						left++;
						right--;
					}
				} else if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}

		System.out.println(count);
	}
}
