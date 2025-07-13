// [BOJ] 감소하는 수
// https://www.acmicpc.net/problem/1038


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main { // 감소하는 수
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //back("", 0);
        System.out.println(bfs());
    }

    static String bfs() {
        int cnt = 0;
        Queue<String> queue = new ArrayDeque<>();
        for (int i = 0; i <= 9; i++) {
            queue.add("" + i);
        }

        while (!queue.isEmpty()) {
            String now = queue.poll();

            if (cnt == N) {
                return now;
            }
            cnt += 1;
            for (int nextItem = 0; nextItem <= 9; nextItem++) {
                if (now.charAt(now.length() - 1) - '0' > nextItem) { // 감소하는 수
                    queue.add(now + nextItem);
                }
            }
        }
        return ""+-1;
    }

}
