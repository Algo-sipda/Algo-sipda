import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	Stack<Integer> data_stack = new Stack<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		Stack<Point> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int temp;

		for (int i = 1; i <= N; i++) {
			temp = Integer.parseInt(st.nextToken());
			if (stack.isEmpty()) {
				sb.append(0).append(" ");
				stack.add(new Point(temp, i));
			} else {
				while (!stack.empty() && stack.peek().x < temp) {
					stack.pop();
				}

				if (stack.empty()) {
					sb.append(0).append(" ");
					stack.add(new Point(temp, i));
				} else {
					sb.append(stack.peek().y).append(" ");
					stack.add(new Point(temp, i));
				}

			}
		}

		System.out.println(sb);
	}

}