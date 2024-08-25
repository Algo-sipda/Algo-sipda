import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    static int n = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        DFS(arr, 0, 0, 0);
        System.out.println(answer);
    }

    public static void DFS(int[] arr, int idx, int sum, int count) {
        if (idx == n) {
            answer = Math.max(answer, count);
            return;
        }
        if (!hasCarry(sum, arr[idx]))
            DFS(arr, idx + 1, sum + arr[idx], count + 1);
        DFS(arr, idx + 1, sum, count);
    }

    public static boolean hasCarry(int a, int b) {
        while (a > 0 || b > 0) {
            int digitA = a % 10;
            int digitB = b % 10;
            if (digitA + digitB >= 10)
                return true;
            a /= 10;
            b /= 10;
        }
        return false;
    }
}