class Solution {
    
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;
        
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(n, computers, visited, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    private static void dfs(int n, int[][] computers, boolean[] visited, int i){
        visited[i] = true;
        
        for(int j=0;j<n;j++){
            if(computers[i][j] == 1 && !visited[j]){
                dfs(n, computers, visited, j);
            }
        }
    }
}