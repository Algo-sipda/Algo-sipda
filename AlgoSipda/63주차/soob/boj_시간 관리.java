import java.io.*;
import java.util.*;

public class Main {
    static class Job {
        int t;
        int s;
        Job(int t, int s) {
            this.t = t;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Job[] a = new Job[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            a[i] = new Job(t, s);
        }

        Arrays.sort(a, new Comparator<Job>() {
            @Override
            public int compare(Job p, Job q) {
                if (p.s == q.s)
                    return q.t - p.t;
                return q.s - p.s;
            }
        });

        int cur = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (cur > a[i].s)
                cur = a[i].s;
            cur -= a[i].t;
        }

        if (cur < 0)
            System.out.println(-1);
        else
            System.out.println(cur);
    }
}
