// [BOJ] 다이어트 https://www.acmicpc.net/problem/19942
// backtracking 
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, K;
	static int[][] items;
	static int mp, mf, ms, mv;

	static boolean[] selected;
	static int minPrice;

	static String ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());

		items = new int[N][5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken()); // 단
			items[i][1] = Integer.parseInt(st.nextToken()); // 지
			items[i][2] = Integer.parseInt(st.nextToken()); // 탄
			items[i][3] = Integer.parseInt(st.nextToken()); // 비
			items[i][4] = Integer.parseInt(st.nextToken()); // 가격

		}

		minPrice = Integer.MAX_VALUE; // 최소금액
		selected = new boolean[N];
		ans = "";
		back(0, new int[] { 0, 0, 0, 0 }, 0, ""); // 영양분, 총합

		if (minPrice == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(minPrice);

		System.out.print(ans);
	}

	static void back(int id, int[] gradient, int price, String str) {
		if (id == N) { // 종료 조건 주의

			if (gradient[0] >= mp && gradient[1] >= mf && gradient[2] >= ms && gradient[3] >= mv && minPrice >= price) {
				// 1) 지금까지 찾은 최소 비용 minPrice보다 더 적은 비용 발견 >> 무조건 갱신
				// 2) 최소 비용이 기존과 같은 경우 사전순으로 더 빠른 조합인지 판단
				if (price < minPrice || price == minPrice && str.compareTo(ans) < 0) {// 사전순으로 가장 빠른 경우 찾아야 함
					minPrice = price;
					ans = str;

				}
			}
			return;
		}

		int[] newGradient = new int[] { gradient[0] + items[id][0], gradient[1] + items[id][1],
				gradient[2] + items[id][2], gradient[3] + items[id][3] };
		// id : 선택할지 말지 결정해야 하는 상태
		back(id + 1, newGradient, price + items[id][4], str + (id + 1) + " "); // 선택하는 경우

		back(id + 1, gradient, price, str); // 선택하지 않는 경우

	}

}
