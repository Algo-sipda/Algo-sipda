import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        Set<Integer> set = new HashSet<>();
        int max = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            set.add(arr[i]);
        }

        for (Integer k : set) {
            int cnt = 0;
            int before = 0;
            for (int i = 0; i < N; i++) {
                if (k == arr[i]) continue;
                if (arr[i] == before) {
                    cnt++;
                } else {
                    cnt = 1;
                    before = arr[i];
                }
                max = Math.max(max, cnt);
            }
        }

        System.out.println(max);
    }
}