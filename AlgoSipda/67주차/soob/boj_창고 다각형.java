import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Pillar implements Comparable<Pillar> {
        int x;
        int h;
        Pillar(int x, int h) {
            this.x = x;
            this.h = h;
        }
        public int compareTo(Pillar o) {
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pillar[] a = new Pillar[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            a[i] = new Pillar(x, h);
        }

        Arrays.sort(a);

        int maxH = 0;
        int idxMax = 0;
        for (int i = 0; i < n; i++) {
            if (a[i].h > maxH) {
                maxH = a[i].h;
                idxMax = i;
            }
        }

        long area = 0;

        int curH = a[0].h;
        int curX = a[0].x;
        for (int i = 1; i <= idxMax; i++) {
            if (a[i].h >= curH) {
                area += (long) (a[i].x - curX) * curH;
                curH = a[i].h;
                curX = a[i].x;
            }
        }

        curH = a[n - 1].h;
        curX = a[n - 1].x;
        for (int i = n - 2; i >= idxMax; i--) {
            if (a[i].h >= curH) {
                area += (long) (curX - a[i].x) * curH;
                curH = a[i].h;
                curX = a[i].x;
            }
        }

        area += maxH;

        System.out.println(area);
    }
}
