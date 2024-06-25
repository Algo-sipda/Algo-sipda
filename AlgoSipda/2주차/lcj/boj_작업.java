import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    문제풀이: 위상정렬
            (선행되어야 할 작업이 끝나면 자신의 작업도 수행할 수 잇으며, 동시수행도 상관없는 조건)

    1. list 내에 각 노드들의 연결을 저장, 선행되어야 할 작업들의 카운트를 쌓아둠
    2. 진행에 따라 자신을 선행작업으로 두고 있는 작업들의 카운트를 줄여나가면서,
        그 카운트가 0이 되었을 경우에는 해당 작업도 큐에 담아 진행
    3. 시간은 res 배열에서 항상 해당 작업 시점의 최댓값으로 최신화시켜주어 마지막 정담을 도출
 */

public class Main {
	static ArrayList<Integer>[] list;
	static int n;
	static int[] cost;
	static int[] indegree;
	static int[] times;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		indegree = new int[n+1];
		times = new int[n+1];
		
		for(int i=1;i<=n;i++) {
			list[i]= new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			indegree[i] = Integer.parseInt(st.nextToken());
			
			for(int j=0;j<indegree[i];j++) {
				list[Integer.parseInt(st.nextToken())].add(i);
			}
		}
		
		cost = new int[n+1];
		topologySort();
		
		int result = 0;
		for(int i=1;i<=n;i++) {
			result = Math.max(result, cost[i]);
		}
        System.out.println(result);
	}

	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1;i<=n;i++) {
			if(indegree[i] == 0) {
				q.offer(i);
				cost[i] = times[i];
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i=0;i<list[cur].size();i++) {
				int next = list[cur].get(i);
				cost[next] = Math.max(cost[cur] + times[next], cost[next]);
				indegree[next]--;
				if(indegree[next] == 0) q.offer(next);
			}
		}
	}
}
