import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] C = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		int answer = 0, value = 1;

		char pre = ' ';
		for (Character c : C) {
			switch (c) {
			case '(':
				stack.push('(');
				value *= 2;
				pre=c;
				break;
			case '[':
				stack.push('[');
				value *= 3;
				pre=c;
				break;
			case ')':
				if (stack.isEmpty() || stack.pop() != '(') {
					System.out.println(0);
					return;
				}
				if(pre=='(')
					answer += value;
				value /= 2;
				pre=c;
				break;
			case ']':
				if (stack.isEmpty() || stack.pop() != '[') {
					System.out.println(0);
					return;
				}
				if(pre == '[')
					answer += value;
				value /= 3;
				pre=c;
				break;
			}
		}
		if(stack.isEmpty())
			System.out.println(answer);
		else
			System.out.println(0);
	}

}