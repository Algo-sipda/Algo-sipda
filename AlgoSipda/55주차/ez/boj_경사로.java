import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_경사로 {

    static int N, L, ans;
    static int[][] mapWidth, mapHeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        mapWidth = new int[N][N];
        mapHeight = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mapWidth[i][j] = Integer.parseInt(st.nextToken());
                mapHeight[j][i] = mapWidth[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            if (canGo(mapWidth[i])) {
                ans++;
            }
            if (canGo(mapHeight[i])) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean canGo(int[] arr) {
        boolean[] visited = new boolean[N];
        int before = arr[0];
        int idx = 0;
        for (int i = 1; i < N; i++) {
            // 만약 높이 차가 날 경우
            if (before != arr[i]) {
                if (Math.abs(before - arr[i]) > 1) {
                    return false;
                }

                if (before < arr[i]) {
                    // 오르막
                    for (int j = idx; j > idx - L; j--) {
                        if (j < 0) return false;
                        if (arr[j] != before) return false;
                        if (visited[j]) return false;
                        visited[j] = true;
                    }
                } else {
                    // 내리막
                    for (int j = i; j < i + L; j++) {
                        if (j >= N) return false;
                        if (arr[j] != arr[i]) return false;
                        if (visited[j]) return false;
                        visited[j] = true;
                    }
                }
            }
            before = arr[i];
            idx = i;
        }

        return true;
    }
}