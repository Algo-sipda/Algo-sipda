import java.io.*;
import java.util.*;

public class Main {
    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static boolean isSame(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int A1 = Integer.parseInt(st.nextToken()) - 1;
        int A2 = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int B1 = Integer.parseInt(st.nextToken()) - 1;
        int B2 = Integer.parseInt(st.nextToken()) - 1;

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        Map<String, Long> seen = new HashMap<>();
        long cycleLength = 0;
        boolean cycleFound = false;

        for (long i = 0; i < K; i++) {
            String currentState = Arrays.toString(arr);

            if (seen.containsKey(currentState)) {
                cycleLength = i - seen.get(currentState);
                K = (K - i) % cycleLength;
                cycleFound = true;
                break;
            }

            seen.put(currentState, i);

            reverse(arr, A1, A2);
            reverse(arr, B1, B2);
        }

        if (cycleFound) {
            for (long i = 0; i < K; i++) {
                reverse(arr, A1, A2);
                reverse(arr, B1, B2);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append("\n");
        }
        System.out.print(sb.toString());
    }
}