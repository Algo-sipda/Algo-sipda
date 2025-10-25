import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static ArrayList<Integer> list;
	static boolean[] visited;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		nums = new int[N+1];
		visited = new boolean[N+1];
		list = new ArrayList<>();
		
		for(int i=1;i<=N;i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		// 사이클이 존재하는지 dfs로 체크
		for(int i=1;i<=N;i++) {
			visited[i] = true;
			dfs(i, i);
			visited[i] = false;
		}
		
		Collections.sort(list);	
		sb.append(list.size()).append("\n");	
		for(int i=0;i<list.size();i++) {
			sb.append(list.get(i)).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void dfs(int start, int num) {
		if(visited[nums[start]]== false) {
			visited[nums[start]] = true;
			dfs(nums[start], num);
			visited[nums[start]] = false;
		}
		
		// 숫자가 같은 경우
		if(nums[start] == num)
			list.add(num);
	}
}
