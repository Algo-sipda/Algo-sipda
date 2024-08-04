import java.util.*;

class Solution {
	public void union(int[] parent, int x, int y) {
		x = find(parent, x);
		y = find(parent, y);
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}

    public int find(int[] parent, int x) {
		if(parent[x] == x) return x;
		else return find(parent, parent[x]);
	}
	
	public int kruskal(int[][] graph, int[] parent) {
		int cost = 0;
		for(int i = 0; i < graph.length;i++) {
			if (find(parent, graph[i][0]) != find(parent, graph[i][1])) {
				cost += graph[i][2];
				union(parent, graph[i][0], graph[i][1]);
			}
		}
        
		return cost;
	}
    
    public int solution(int n, int[][] costs) {
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        int[] parent = new int[n + 1];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
        
        return kruskal(costs, parent);
    }
}