import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] friends;
    static boolean[] visited;
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        friends = new ArrayList[N];
        visited = new boolean[N];
        
        for(int i=0;i<N;i++) {
            friends[i]= new ArrayList<>(); 
        }
        
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            
            //무방향이기 때문에
            friends[n1].add(n2);
            friends[n2].add(n1);
        }
        
        for(int i=0;i<N;i++) {
            visited[i]= true;
            dfs(0, i);
            visited[i] = false;
            if(answer == 1) break;
        }
        System.out.println(answer);
    }

    private static void dfs(int cnt, int num) {
        if(cnt == 4) {
            answer = 1;
            return;
        }
        
        for(int i:friends[num]) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(cnt+1, i);
                visited[i] = false; 
            }
        }
    }
}
