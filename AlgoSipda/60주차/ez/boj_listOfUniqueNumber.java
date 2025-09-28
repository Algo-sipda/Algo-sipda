import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_listOfUniqueNumber {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        boolean[] check = new boolean[100001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = 0;
        long ans = 0;
        Set<Integer> set = new HashSet<>();
        while (e < N) {
            while (set.contains(arr[e])) {
                set.remove(arr[s]);
                s++;
            }
            set.add(arr[e]);
            ans = ans + e - s + 1;
            e++;
        }
        System.out.println(ans);
    }
}
