import java.io.*;
import java.util.*;

public class Main {
    static class Interval {
        int s, e;

        Interval(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 웅덩이의 개수
        int L = Integer.parseInt(st.nextToken()); // 널빤지의 길이
        List<Interval> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 웅덩이 시작 좌표
            int e = Integer.parseInt(st.nextToken()); // 웅덩이 끝 좌표
            a.add(new Interval(s, e));
        }

        a.sort(Comparator.comparingInt(o -> o.s));

        long cur = 0;
        long ans = 0;

        for (Interval it : a) {
            int s = it.s;
            int e = it.e;

            if (cur < s)
                cur = s;
            if (cur >= e)
                continue;

            long need = (e - cur + L - 1) / L;
            ans += need;
            cur += need * L;
        }

        System.out.println(ans);
    }
}
