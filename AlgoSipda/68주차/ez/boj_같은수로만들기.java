import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_같은수로만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Long> stack = new Stack<>();
        long max = 0;
        long answer = 0;

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            max = Math.max(max, num);

            if (stack.isEmpty()) {
                stack.push(num);
            } else {
                if (stack.peek() < num) {
                    answer += num - stack.pop();
                    stack.push(num);
                } else if (stack.peek() > num) {
                    stack.pop();
                    stack.push(num);
                }
            }
        }

        while (!stack.isEmpty()) {
            answer += max - stack.pop();
        }

        System.out.println(answer);
    }
}
