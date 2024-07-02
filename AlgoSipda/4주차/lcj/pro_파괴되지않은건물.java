class Solution {
    public int[][] sum;
    public int N, M;
    
    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        
        sum = new int[N+1][M+1];
        
        for(int i=0;i<skill.length;i++) {
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            
            int degree = skill[i][0] == 1? -skill[i][5]:skill[i][5];
            
            sum[r1][c1] += degree;
            sum[r2+1][c1] -= degree;
            sum[r1][c2+1] -= degree;
            sum[r2+1][c2+1] += degree;
        }
        
        // 세로 합 구하기
        for(int c = 0; c<=board[0].length;c++) {
            for(int r = 0; r<board.length;r++) {
                sum[r+1][c] += sum[r][c];
            }
        }
        
        // 가로 합 구하기
        for(int r = 0; r<=board.length;r++) {
            for(int c = 0; c<board[0].length;c++) {
                sum[r][c+1] += sum[r][c];
            }
        }
        
        int answer = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++) {
                if((board[i][j] + sum[i][j]) > 0){
                    answer++;
                }
            }
        }
        
        return answer;
    }
}