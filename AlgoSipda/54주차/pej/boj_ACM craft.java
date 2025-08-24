// [BOJ] ACM Craft
// https://www.acmicpc.net/problem/1005

//  위상 정렬로 선행 건물 완료시간을 누적하여 목표 W의 최소 완공시간 계산

import java.io.*;
import java.util.*;

public class Main {
    // buildTime : 각 건물 자체 시공 시간
    // indegree  : 선행(진입차수) 개수
    // finish    : 각 건물의 (선행 고려한) 최종 완공 시간
    static int[] buildTime, indegree, finish;
    static ArrayList<Integer>[] graph; // 건물 간 의존 관계 (u -> v: u 끝나야 v 가능)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물 수
            int K = Integer.parseInt(st.nextToken()); // 규칙 수

            buildTime = new int[N + 1];
            indegree  = new int[N + 1];
            finish    = new int[N + 1];

            // 그래프 초기화
            graph = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

            // 각 건물 시공 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) buildTime[i] = Integer.parseInt(st.nextToken());

            // 규칙 입력: A -> B (A 먼저 완성 후 B)
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                graph[A].add(B);
                indegree[B]++;
            }

            int W = Integer.parseInt(br.readLine()); // 목표 건물

            // 위상 정렬 (Kahn)
            ArrayDeque<Integer> q = new ArrayDeque<>();

            // 선행 없는 건물부터 시작: finish = 자기 자신 건설 시간
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    finish[i] = buildTime[i];
                    q.offer(i);
                }
            }

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int nxt : graph[cur]) {
                    // nxt의 완공 시간은 선행(cur) 완공 후 + 자신의 건설시간
                    // 여러 선행 중 최대 경로를 선택
                    finish[nxt] = Math.max(finish[nxt], finish[cur] + buildTime[nxt]);

                    if (--indegree[nxt] == 0) q.offer(nxt);
                }
            }

            out.append(finish[W]).append('\n');
        }

        System.out.print(out);
    }
}
