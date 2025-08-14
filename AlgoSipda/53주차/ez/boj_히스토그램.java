import java.io.*;
import java.util.Stack;

public class boj_히스토그램 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N =  Integer.parseInt(br.readLine());
        arr = new int[N + 2];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ans = 0;
        for (int i = 1; i < N + 2; i++) {
            while (!stack.isEmpty()) {
                int top = stack.peek();
                if (arr[top] <= arr[i]) break;
                stack.pop();
                ans = Math.max(ans, arr[top] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        System.out.println(ans);
    }
}