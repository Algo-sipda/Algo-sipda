import java.util.*;

class Block{
    int cnt;
    int[][] shape = new int[6][2];
}

class Solution {
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        List<Block> puzzles = new LinkedList<>();
        findBlocks(table, puzzles, 1, 0);
        
        List<Block> blanks = new ArrayList<>();
        findBlocks(game_board, blanks, 0, 1);
        
        for(Block blank : blanks){
            for(Block puzzle : puzzles){
                if(canInsert(puzzle, blank)){
                    answer+=puzzle.cnt;
                    puzzles.remove(puzzle);
                    
                    if(puzzles.size() == 0){
                        return answer;
                    }
                    break;
                }
            }
        }
            
        return answer;
    }
    
    public void findBlocks(int[][] map, List<Block> list, int flag, int nflag){
        int n = map.length;
        int m = map[0].length;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == flag){
                    list.add(findOne(map, n, m, i, j, flag, nflag));
                }
            }
        }
    }
    
    public Block findOne(int[][] map, int n, int m, int x, int y, int flag, int blank){
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        Block block = new Block();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        map[x][y] = blank;
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            block.shape[block.cnt++] = now;
            for(int i=0; i<4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx<0 || nx>=n || ny<0 || ny>=m || map[nx][ny] == blank){
                    continue;
                }
                map[nx][ny] = blank;
                q.add(new int[]{nx, ny});
            }
        }
        
        setCenter(block);
        return block;
    }
    
    public boolean canInsert(Block puzzle, Block blank){
        if(puzzle.cnt != blank.cnt){return false;}
        
        int size = puzzle.cnt;
        
        for(int i=0; i<3; i++){
            if(isSameShape(size, puzzle.shape, blank.shape)){
                return true;
            }
            turn(puzzle);
        }
        if(isSameShape(size, puzzle.shape, blank.shape)){
            return true;
        }
        return false;
    }
    
    public boolean isSameShape(int size, int[][] shape1, int[][] shape2){
        for(int j=0; j<size; j++){
            int k = 0;
            for(; k<size; k++){
                if(shape1[j][0] == shape2[k][0]
                   && shape1[j][1] == shape2[k][1]){
                    break;
                }
            }
            if(k==size){
                return false;
            }
        }
        return true;
    }
    
    public void turn(Block block){
        for(int i=0; i<block.cnt; i++){
            int temp = -block.shape[i][0];
            block.shape[i][0] = block.shape[i][1];
            block.shape[i][1] = temp;
        }
        setCenter(block);
    }
    
    public void setCenter(Block block){
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for(int i=0; i<block.cnt; i++){
            if(block.shape[i][0] < minX){
                minX = block.shape[i][0];
                minY = block.shape[i][1];
            }
            else if(block.shape[i][0] == minX && block.shape[i][1] < minY){
                minY = block.shape[i][1];
            }
        }
        
        for(int i=0; i<block.cnt; i++){
            block.shape[i][0] -= minX;
            block.shape[i][1] -= minY;
        }
    }
    
}