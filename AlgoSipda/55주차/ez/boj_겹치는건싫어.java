import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_겹치는건싫어 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int len = 0;
        while (left < N) {
            while (right < N) {
                if (map.getOrDefault(arr[right], 0) + 1 > K) {
                    break;
                }
                map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
                right++;
            }
            len = Math.max(len, right - left);
            map.put(arr[left], map.get(arr[left]) - 1);
            left++;
        }
        System.out.println(len);
    }
}
