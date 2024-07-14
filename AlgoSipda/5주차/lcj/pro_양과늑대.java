import java.util.*;

class Solution {
    int max = 0;
    int[] animal;
    ArrayList<Integer>[] childs;
    
    public int solution(int[] info, int[][] edges) {
        animal = info;
        childs = new ArrayList[info.length];
        
        for(int[] edge : edges) {
            if(childs[edge[0]] == null) {
                childs[edge[0]] = new ArrayList<>();
            }    
            childs[edge[0]].add(edge[1]);
        }
        
        List<Integer> check = new ArrayList<>();
        
        check.add(0);
        dfs(0, 0, 0, check);
        
        return max;
    }
    
    
    public void dfs(int idx, int sheep, int wolf, List<Integer> check) {
        if(animal[idx] == 0) {
            sheep++;
        }else {
            wolf++;
        }
        
        if(sheep <= wolf) return;
        
        max = Math.max(sheep, max);
        
        // 다음 갈 수 있는 곳 갱신해줌
        List<Integer> next = new ArrayList<>();
        next.addAll(check);
        next.remove(Integer.valueOf(idx));
        if(childs[idx] != null) {
            for(int child: childs[idx]) {
                next.add(child);
            }
        }
        
        for(int node : next) {
            dfs(node, sheep, wolf, next);
        }
        
    }
}
