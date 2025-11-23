import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        String keyword = br.readLine();
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));

            if (keyword.length() <= stack.size()) {
                boolean isExist = true;
                for (int j = 0; j < keyword.length(); j++) {
                    if (stack.get(stack.size() - keyword.length() + j) != keyword.charAt(j)) {
                        isExist = false;
                        break;
                    }
                }
                if (isExist) {
                    for (int j = 0; j < keyword.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.size() == 0) {
            System.out.println("FRULA");
        } else {
            for (char c : stack) {
                sb.append(c);
            }
            System.out.println(sb.toString());
        }

    }
}