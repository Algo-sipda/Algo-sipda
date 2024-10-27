import java.util.*;

class Solution {
    
    int[] parents;
    
    public int findParent(int n) {
        if(parents[n] == n)
           return n;
        return parents[n] = findParent(parents[n]);
    }

    public void union(int a, int b) {
        int rootA = findParent(a);
        int rootB = findParent(b);
        if(rootA < rootB)
            parents[rootB] = rootA;
        else
            parents[rootA] = rootB;
    }
    
    public int solution(int n, int[][] computers) {
        parents = new int[n];
        
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && i != j) {
                    union(i, j);
                }
            }
        }
                
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(findParent(i));
        }
        
        return set.size();
    }
}
