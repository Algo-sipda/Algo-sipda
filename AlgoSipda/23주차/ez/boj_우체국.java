import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_우체국 {
    static Town[] towns;

    static class Town implements Comparable<Town> {
        int idx, num;

        public Town(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Town o) {
            return this.idx - o.idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        towns = new Town[N];
        long people = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            towns[i] = new Town(idx, num);
            people += num;
        }

        Arrays.sort(towns);

        long sum = 0;
        int res = 0;
        for (int i = 0; i < N; i++) {
            sum += towns[i].num;
            if (sum >= Math.ceil((double)people / 2)) {
                res = towns[i].idx;
                break;
            }
        }
        System.out.println(res);
    }
}