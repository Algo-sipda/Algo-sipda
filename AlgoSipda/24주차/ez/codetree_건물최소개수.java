
import java.util.*;
import java.io.*;

public class codetree_건물최소개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        int res = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int height = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() > height) {
                if (stack.peek() != 0) {
                    res++;
                }
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek() == height) {
                continue;
            }

            stack.push(height);
        }

        while (!stack.isEmpty()) {
            if (stack.peek() != 0) {
                res++;
            }
            stack.pop();
        }

        System.out.println(res);
    }
}
