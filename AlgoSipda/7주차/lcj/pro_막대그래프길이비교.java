import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Stack<int[]> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<n;i++) {
            int height = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek()[1] <= height) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append("0 ");
            } else {
                sb.append(stack.peek()[0]).append(" ");
            }
            
            stack.push(new int[]{i + 1, height});
        }
        System.out.println(sb);
    }
}