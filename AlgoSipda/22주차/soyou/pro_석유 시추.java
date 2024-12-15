import java.util.*;

class Solution {
    public int solution(int[][] land) {

        int y = land.length;
        int x = land[0].length;

        int [] dy = {1,-1,0,0};
        int [] dx = {0,0,1,-1};
        int [] oil = new int[x];

        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(land[j][i] == 1){

                    Queue<int[]> q = new LinkedList<>();
                    int amount = 1;
                    land[j][i] = 0;

                    q.offer(new int[]{j,i});
                    Set<Integer> set = new HashSet<>();

                    while (!q.isEmpty()){
                        int size = q.size();

                        for(int s = 0; s< size; s++){
                            int [] c = q.poll();
                            set.add(c[1]);

                            for(int d = 0; d < 4; d++){
                                int ny = c[0] + dy[d];
                                int nx = c[1] + dx[d];

                                if(ny > -1 && nx > -1 && ny < y && nx < x && land[ny][nx] == 1){
                                    amount++;
                                    land[ny][nx] = 0;

                                    q.offer(new int[]{ ny, nx });
                                }
                            }
                        }
                    }

                    for(int st : set) oil[st] += amount;
                }
            }
        }

        int answer = Integer.MIN_VALUE;
        for(int o : oil) answer = Math.max(answer,o);

        return answer;
    }
}