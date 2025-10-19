import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int lowerBound(int[] a, int l, int r, int x) {
        int lo = l;
        int hi = r;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] < x) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] tails = new int[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            if (size == 0 || tails[size - 1] < x) {
                tails[size] = x;
                size++;
            } else {
                int idx = lowerBound(tails, 0, size, x);
                tails[idx] = x;
            }
        }

        System.out.println(size);
    }
}
