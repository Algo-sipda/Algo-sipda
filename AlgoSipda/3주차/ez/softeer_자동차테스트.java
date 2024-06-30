import java.io.*;
import java.util.*;

/*
    풀이방식: 이분 탐색
 */

public class Main {

    static int n, q, sum;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        for (int i = 0; i < q; i++) {
            int m = Integer.parseInt(br.readLine());
            int idx = binarySearch(m, 0, n-1);
            if (idx < 1 || idx == n-1) {
                sum = 0;
            } else {
                sum = idx * (n - idx - 1);
            }
            sb.append(sum + "\n");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int m, int left, int right) {
        if (left > right) return -1;

        int mid = (left + right) / 2;

        if (num[mid] < m) {
            return binarySearch(m, mid + 1, right);
        } else if (num[mid] > m) {
            return binarySearch(m, left, mid - 1);
        }
        return mid;
    }
}