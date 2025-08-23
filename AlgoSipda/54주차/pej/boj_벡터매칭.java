// [BOJ] 벡터 매칭
// https://www.acmicpc.net/problem/1007

// 선택 합(sx, sy)만 유지 → (2*sx - totalX, 2*sy - totalY)로 즉시 계산
//      첫 점 고정 선택으로 대칭 가지 절반 제거, 기본 프루닝 적용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Main {
    static int N;
    static int[][] P;
    static long totalX, totalY;
    static double result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            P = new int[N][2];
            totalX = totalY = 0L;

            for (int i = 0; i < N; i++) {
                String[] s = br.readLine().split(" ");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                P[i][0] = x;
                P[i][1] = y;
                totalX += x;
                totalY += y;
            }

            result = Double.MAX_VALUE;

            // 대칭 가지 제거: 0번 점을 반드시 양수 그룹에 포함시키고 시작
            // -> 나머지에서 (N/2 - 1)개만 더 고르면 됨
            dfs(1, N / 2 - 1, P[0][0], P[0][1]);

            sb.append(String.format(Locale.US, "%.12f%n", result));
        }

        System.out.print(sb);
    }

    // idx: 현재 검사할 인덱스
    // pick: 앞으로 더 골라야 할 양수 그룹 개수
    // sx, sy: 지금까지 양수 그룹에 포함한 점들의 합
    static void dfs(int idx, int pick, long sx, long sy) {
        // 남은 원소보다 더 많이 골라야 하면 불가능
        if (pick > N - idx) return;

        // 더 고를 필요 없으면 결과 갱신
        if (pick == 0) {
            long dx = (sx << 1) - totalX; // 2*sx - totalX
            long dy = (sy << 1) - totalY; // 2*sy - totalY
            double len = Math.hypot((double) dx, (double) dy);
            if (len < result) result = len;
            return;
        }

        // 모든 원소를 다 본 경우
        if (idx == N) return;

        // 1) 현재 점을 선택(양수 그룹에 포함)
        dfs(idx + 1, pick - 1, sx + P[idx][0], sy + P[idx][1]);

        // 2) 현재 점을 선택하지 않음(음수 그룹으로 간주)
        dfs(idx + 1, pick, sx, sy);
    }
}
