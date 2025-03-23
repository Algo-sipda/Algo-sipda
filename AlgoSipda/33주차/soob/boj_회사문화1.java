import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 회사 직원 수
        int m = Integer.parseInt(st.nextToken());   // 최초의 칭찬 회수
        int[] praise = new int[n + 1];              // 각 직원이 받은 칭찬 수치
        int[] answer = new int[n + 1];              // 정답 저장 배열

        Map<Integer, List<Integer>> bossMap = new HashMap<>();  // 상사 → 부하 리스트

        st = new StringTokenizer(br.readLine());
        for (int x = 1; x <= n; x++) {
            int y = Integer.parseInt(st.nextToken());
            bossMap.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        // 칭찬 입력
        for (int x = 0; x < m; x++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());   // 칭찬 받은 직원 번호
            int w = Integer.parseInt(st.nextToken());   // 칭찬 수치
            praise[i] += w;
        }

        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(1, 0));

        while (!queue.isEmpty()) {
            Status now = queue.poll();
            int totalPraise = now.praiseSum + praise[now.n];
            answer[now.n] = totalPraise;

            if (!bossMap.containsKey(now.n)) continue;

            for (int x : bossMap.get(now.n)) {
                queue.add(new Status(x, totalPraise));
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public static class Status {
        int n;              // 직원 번호
        int praiseSum;      // 지금까지 누적된 칭찬 수치

        public Status(int n, int praiseSum) {
            this.n = n;
            this.praiseSum = praiseSum;
        }
    }
}
