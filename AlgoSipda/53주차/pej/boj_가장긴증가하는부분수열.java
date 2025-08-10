// [BOJ] 가장 긴 증가하는 부분 수열 5
// https://www.acmicpc.net/problem/14003

import java.io.*;
import java.util.*;
// 느린 방법 O(N^2) DP = dp[i] = i번째 원소를 마지막으로 하는 LIS 길이 dp[i] = max(dp[j] + 1) -> 매 i마다 j 비교
// 꼬리 배열(tails) + 이분탐색 : LIS 길이만 구하려면 실제 수열 전체를 기억할 필요가 없음
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> tails = new ArrayList<>();
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            int x = arr[i];
            int idx = Collections.binarySearch(tails, x);
            if (idx < 0) idx = -(idx + 1);

            if (idx == tails.size()) tails.add(x);
            else tails.set(idx, x);

            pos[i] = idx; // 위치 기록
        }

        // 역추적
        int len = tails.size();
        int targetPos = len - 1;
        int[] lis = new int[len];

        for (int i = n - 1; i >= 0; i--) {
            if (pos[i] == targetPos) {
                lis[targetPos] = arr[i];
                targetPos--;
                if (targetPos < 0) break;
            }
        }

        // 출력
        System.out.println(len);
        for (int x : lis) System.out.print(x + " ");
    }
}
