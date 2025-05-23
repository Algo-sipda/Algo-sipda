import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static List<Integer> twoSum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                twoSum.add(arr[i] + arr[j]);
        Collections.sort(twoSum);
    }

    static void solve() {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int target = arr[i] - arr[j];
                if (Collections.binarySearch(twoSum, target) >= 0) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }
}
