import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Puyo{
    int r, c;
    char color;
    public Puyo(int r, int c, char color) {
        super();
        this.r = r;
        this.c = c;
        this.color = color;
    }
}

public class Main {
    
    static char[][] map = new char[12][6];
    
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    
    static int cnt = 0;//연쇄로 터진 개수
    static boolean isPop = false;//연쇄가 발생했는지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //입력 start
        for(int i=0;i<map.length;i++) {
            String str = br.readLine();
            for(int j=0;j<map[i].length;j++) {
                map[i][j] = str.charAt(j);
            }
        }
        //입력 end
        
        //게임 start
        while(true) {
            isPop = false;
            //연쇄가 가능한 뿌요들이 있는지 있으면 터지는 것
            bfs();
            //모두 바닥으로 내리기
            moveFloor();
            
            //더 이상 연쇄가 발생할 것이 없다면 탈출
            if(isPop == false) {
                break;
            }
            cnt++;
        }
        //게임 end
        
        System.out.println(cnt);
    }

    private static void bfs() {
        Queue<Puyo> q = new LinkedList<Puyo>();
        boolean[][] visited = new boolean[12][6];
        
        for(int i=0;i<12;i++) {
            for(int j=0;j<6;j++) {
                if(map[i][j] != '.' && !visited[i][j]) {
                    //같은 색의 뿌요 존재 -> list 삽입
                    ArrayList<int[]> list = new ArrayList<>();
                    q.add(new Puyo(i, j, map[i][j]));
                    list.add(new int[] {i, j});
                    visited[i][j] = true;
                    
                    while(!q.isEmpty()) {
                        Puyo puyo = q.poll();
                        int cr = puyo.r;
                        int cc = puyo.c;
                        int cColor = puyo.color;
                        
                        for(int d=0;d<4;d++) {
                            int nr = cr + dr[d];
                            int nc = cc + dc[d];
                            
                            if(!isRange(nr, nc)) continue;
                            
                            if(!visited[nr][nc] && map[nr][nc] == cColor) {
                                q.add(new Puyo(nr, nc, map[nr][nc]));
                                list.add(new int[] {nr, nc});
                                visited[nr][nc] = true;
                            }
                        }
                    }
                    
                    //리스트에 들어잇는 수가 4개 이상이면 터트림
                    if(list.size()>=4) {
                        for(int k=0;k<list.size();k++) {
                            map[list.get(k)[0]][list.get(k)[1]] = '.';
                            isPop = true;
                        }
                    }
                }
            }
        }
    }
    
    private static boolean isRange(int r, int c) {
        return 0<=r && r<12 && 0<=c && c<6;
    }
    
    private static void moveFloor() {
        for(int j=0;j<6;j++) {
            Queue<Puyo> q = new LinkedList<>();
            int idx = 11;
            
            for(int i=idx;i>=0;i--) {
                if(map[i][j]!='.') {
                    q.add(new Puyo(i, j, map[i][j]));
                    map[i][j] = '.';
                }
            }
            
            while(!q.isEmpty()) {
                Puyo puyo = q.poll();
                map[idx][j] = puyo.color;
                idx--;
            }
        }
    }
}