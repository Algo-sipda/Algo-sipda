import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int A1, A2, B1, B2;
    static int[] arr, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        copy = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            copy[i] = i;
            arr[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        A1 = Integer.parseInt(st.nextToken());
        A2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        B1 = Integer.parseInt(st.nextToken());
        B2 = Integer.parseInt(st.nextToken());

        boolean isCycle = false;
        int num = 0;
        for (int i = 0; i < K; i++) {
            swap(A1, A2);
            swap(B1, B2);
            if (checkSame(copy, arr)) {
                isCycle = true;
                num = K % (i + 1);
                break;
            }
        }
        if (isCycle) {
            for (int i = 0; i < num; i++) {
                swap(A1, A2);
                swap(B1, B2);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            System.out.println(arr[i]);
        }
    }

    private static boolean checkSame(int[] copy, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != copy[i]) {
                return false;
            }
        }
        return true;
    }

    private static void swap(int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}