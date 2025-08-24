import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_벡터매칭 {

    static int[][] map;
    static boolean[] visited;
    static double min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            map = new int[N][2];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[i][0] = r;
                map[i][1] = c;
            }
            visited = new boolean[N];
            min = Double.MAX_VALUE;
            combi(0, N / 2);
            System.out.println(min);
        }
    }

    private static void combi(int start, int cnt) {
        if (start == map.length) {
            return;
        }
        if (cnt == 0) {
            min = Math.min(min, getVector());
        }
        for (int i = start; i < map.length; i++) {
            visited[i] = true;
            combi(i + 1, cnt - 1);
            visited[i] = false;
        }
    }

    private static double getVector() {
        double res = 0;
        int sumR = 0;
        int sumC = 0;
        for (int i = 0; i < map.length; i++) {
            if (visited[i]) {
                sumR += map[i][0];
                sumC += map[i][1];
            } else {
                sumR -= map[i][0];
                sumC -= map[i][1];
            }
        }
        res = Math.sqrt(Math.pow(sumR, 2) + Math.pow(sumC, 2));
        return res;
    }
}
