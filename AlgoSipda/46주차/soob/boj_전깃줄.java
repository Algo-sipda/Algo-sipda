import java.io.*;
import java.util.*;

public class Main {
    static class Line implements Comparable<Line> {
        int a, b;
        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }
        public int compareTo(Line o) {
            return this.a - o.a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Line[] lines = new Line[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new Line(a, b);
        }

        Arrays.sort(lines);

        int[] dp = new int[N];
        int len = 0;
        for (Line line : lines) {
            int idx = Arrays.binarySearch(dp, 0, len, line.b);
            if (idx < 0) idx = -idx - 1;
            dp[idx] = line.b;
            if (idx == len) len++;
        }

        System.out.println(N - len);
    }
}
