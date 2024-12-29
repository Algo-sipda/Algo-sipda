import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int answer = -1;

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
            }

            if(stack.isEmpty() || stack.peek() != y) {
                stack.push(y);
                answer++;
            }
        }

        System.out.println(answer);
    }
}