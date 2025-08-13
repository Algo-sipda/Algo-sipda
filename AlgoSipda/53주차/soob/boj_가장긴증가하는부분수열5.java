import java.io.*;
import java.util.*;

public class Main {
    static int lowerBound(int[] a, int l, int r, int key) {
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= key)
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] tail = new int[n];
        int[] pos = new int[n];
        int[] prev = new int[n];
        int[] last = new int[n];
        Arrays.fill(prev, -1);
        int len = 0;

        for (int i = 0; i < n; i++) {
            int p = lowerBound(tail, 0, len, arr[i]);
            tail[p] = arr[i];
            pos[i] = p;
            if (p > 0) 
                prev[i] = last[p - 1];
            last[p] = i;
            if (p == len) 
                len++;
        }

        int idx = last[len - 1];
        int[] seq = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            seq[i] = arr[idx];
            idx = prev[idx];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len).append('\n');
        for (int i = 0; i < len; i++) {
            if (i > 0) 
                sb.append(' ');
            sb.append(seq[i]);
        }
        System.out.println(sb.toString());
    }
}