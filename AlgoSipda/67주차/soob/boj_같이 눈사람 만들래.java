import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (!st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int target = a[i] + a[j];
                int l = 0;
                int r = N - 1;

                while (l < r) {
                    if (l == i || l == j) {
                        l++;
                        continue;
                    }
                    if (r == i || r == j) {
                        r--;
                        continue;
                    }

                    int sum = a[l] + a[r];
                    int diff = target - sum;
                    if (diff < 0)
                        diff = -diff;
                    if (diff < ans)
                        ans = diff;

                    if (sum < target)
                        l++;
                    else
                        r--;
                }
            }
        }

        System.out.print(ans);
    }
}
