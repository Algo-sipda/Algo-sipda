import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N + 1];
		int[] dp = new int[N + 1];
		
		List<Integer> list = new ArrayList<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		
		list.add(Integer.MIN_VALUE);
		for (int i = 1; i <= N; i++) {
			int num = a[i];
			int left = 1;
			int right = list.size()-1;
			
			if(num>list.get(list.size()-1)) {
				list.add(num);
				dp[i] = list.size()-1;
			}
			else {
				while(left<right) {
					int mid = (left+right)/2;
					if(list.get(mid)>=num)
						right = mid;
					else
						left = mid+1;
				}
				list.set(right, num);
				dp[i] = right;
			}
		}
		
		sb.append(list.size()-1).append("\n");

		int idx = list.size()-1;
		Stack<Integer> answer = new Stack<>();
		for (int i = N; i >= 1; i--) {
			if (idx==dp[i]) {
				answer.push(a[i]);
				idx--;
			}
		}

		while (!answer.isEmpty()) {
			sb.append(answer.pop() + " ");
		}
		System.out.println(sb);
	}
}