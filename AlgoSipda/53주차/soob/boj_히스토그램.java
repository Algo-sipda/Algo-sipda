import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n + 1];
        for (int i = 0; i < n; i++) h[i] = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        long answer = 0;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && h[stack.peek()] > h[i]) {
                int top = stack.pop();
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                int width = i - left;
                long area = (long) h[top] * width;
                if (area > answer) answer = area;
            }
            stack.push(i);
        }
        System.out.println(answer);
    }
}