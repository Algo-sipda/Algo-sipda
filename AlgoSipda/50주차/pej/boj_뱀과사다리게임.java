// [BOJ] 뱀과 사다리 게임
// https://www.acmicpc.net/problem/16928

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 뱀과 사다리 게임
    static int N, M; // 사다리의 수, 뱀의 수
    static Map<Integer, Integer> items;
    static int minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사다리의 수
        M = Integer.parseInt(st.nextToken()); // 뱀의 수
        items = new HashMap<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 사다리 정보 : x번 칸 -> y번 칸
            int y = Integer.parseInt(st.nextToken());
            items.put(x, y);
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 뱀의 정보 : u -> v
            int v = Integer.parseInt(st.nextToken());
            items.put(u, v);
        }
        bfs(1); // 1 -> 100
        System.out.println(minCnt);
    }


    private static void bfs(int start) {
        boolean[] visited = new boolean[101]; // 1 ~ 100
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == 100) {
                minCnt = Math.min(minCnt, now[1]);
                return;
            }
            for (int w = 1; w <= 6; w++) {
                int next = now[0] + w;
                if (next >= 101 || visited[next]) continue;

                if (items.containsKey(next)) { // 사다리(O) 또는 뱀(x)
                    visited[next] = true;
                    next = items.get(next);
                }

                visited[next] = true;
                queue.add(new int[]{next, now[1] + 1});
            }
        }
    }



}
