// [BOJ] 외판원 순회 2
// https://www.acmicpc.net/problem/10971
/*
 백트래킹(DFS) + 가지치기
dfs(curr, depth, cost)
depth == N이면 curr→start 가능 여부 확인 후 갱신.
매 단계에서 W[curr][next] != 0이고 미방문이면 진행.
cost >= best면 컷.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N; // 도시의 수
    static int[][] W;
    static int start;
    static boolean[] visited; // 방문 여부
    static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        W = new int[N + 1][N + 1]; // 비용 행렬
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N + 1]; // 1 ~ N번 도시 방문 여부 체크
        for (start = 1; start <= N; start++) { // 어느 한 도시에서 출발
            visited[start] = true;
            back(start, 0, 0);
            visited[start] = false;
        }
        System.out.println(minCost);
    }

    // 상태 변화함 , 변화한 상태 기억해야 함
    private static void back(int now, int depth, int sum) {

        if (depth == N - 1) { // 기저조건
            if (W[now][start] != 0) { // 끝 -> 출발지 갈 수 있다면,
                minCost = Math.min(minCost, sum + W[now][start]);
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (W[now][i] == 0 || visited[i]) continue;
            visited[i] = true;
            back(i, depth + 1, sum + W[now][i]);
            visited[i] = false;
        }
    }
}
