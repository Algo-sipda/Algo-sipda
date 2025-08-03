// [BOJ] 괄호의 값
// https://www.acmicpc.net/problem/2504
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Stack<Character> stack = new Stack<>();
        int temp = 1;
        int answer = 0;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '(') {
                stack.push(c);
                temp *= 2;
            } else if (c == '[') {
                stack.push(c);
                temp *= 3;
            } else if (c == ')') {
                // 유효성 체크
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                }
                // 바로 이전이 '('라면 값 더하기
                if (line.charAt(i - 1) == '(') {
                    answer += temp;
                }
                stack.pop();
                temp /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                }
                if (line.charAt(i - 1) == '[') {
                    answer += temp;
                }
                stack.pop();
                temp /= 3;
            }
        }

        // 스택이 남아있으면 잘못된 괄호열
        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
