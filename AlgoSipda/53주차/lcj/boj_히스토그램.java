import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Integer> stack = new Stack<>();
		stack.add(0);
		
		int answer = 0;
		for(int i=1;i<N;i++) {
			while(!stack.isEmpty() && nums[stack.peek()]>=nums[i]) {
				int height = nums[stack.pop()];
				int weight = stack.isEmpty()?i:i-stack.peek()-1;
				answer = Math.max(answer, weight*height);
			}
			stack.add(i);
		}
		
		while(!stack.isEmpty()) {
			int height = nums[stack.pop()];
			int weight = stack.isEmpty()?N:N-stack.peek()-1;
			answer = Math.max(answer, weight*height); 
		}
		System.out.println(answer);
	}
}