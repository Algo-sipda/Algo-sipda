import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2048 {
    static int n;
    static int[][] graph;
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(result);
    }
    static void dfs(int cnt) {
        if(cnt==10) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result = Math.max(result, graph[i][j]);
                }
            }
            return;
        }
        int[][] temp = new int[n][n];
        temp= deepCopy(temp,graph);

        left();
        dfs(cnt+1);
        graph= deepCopy(graph,temp);

        right();
        dfs(cnt+1);
        graph= deepCopy(graph,temp);

        top();
        dfs(cnt+1);
        graph= deepCopy(graph,temp);

        down();
        dfs(cnt+1);
        graph= deepCopy(graph,temp);


    }

    static void left() {

        for (int i = 0; i < n; i++) {
            int curPoint=0;
            int tempCount = 0;
            for (int j = 0; j < n; j++) {
                if(graph[i][j] ==0) continue;
                if(graph[i][j]==tempCount) {
                    graph[i][curPoint-1]= tempCount*2;
                    tempCount = 0;
                    graph[i][j] = 0;
                }
                else{
                    tempCount = graph[i][j];
                    graph[i][j] = 0;
                    graph[i][curPoint++]= tempCount;
                }
            }

        }

    }

    static void right() {

        for (int i = 0; i < n; i++) {
            int curPoint=n-1;
            int tempCount = 0;
            for (int j = n-1; j > -1; j--) {
                if(graph[i][j] ==0) continue;
                if(graph[i][j]==tempCount) {
                    graph[i][curPoint+1]= tempCount*2;
                    graph[i][j] = 0;
                    tempCount = 0;
                }
                else {
                    tempCount = graph[i][j];
                    graph[i][j] = 0;
                    graph[i][curPoint--]= tempCount;
                }
            }
        }
    }

    static void down() {

        for (int j = 0; j < n; j++) {
            int curPoint=n-1;
            int tempCount =0;
            for (int i = n-1; i > -1; i--) {
                if(graph[i][j] ==0) continue;
                if(graph[i][j]==tempCount) {
                    graph[curPoint+1][j]= tempCount*2;
                    graph[i][j] = 0;
                    tempCount = 0;
                }
                else{
                    tempCount = graph[i][j];
                    graph[i][j] = 0;
                    graph[curPoint--][j]= tempCount;
                }
            }
        }
    }

    static void top() {

        for (int j = 0; j < n; j++) {
            int curPoint=0;
            int tempCount = 0;
            for (int i = 0; i < n; i++) {
                if(graph[i][j] ==0) continue;
                if(graph[i][j]==tempCount) {
                    graph[curPoint-1][j]= tempCount*2;
                    graph[i][j] = 0;
                    tempCount = 0;
                }
                else{
                    tempCount = graph[i][j];
                    graph[i][j] = 0;
                    graph[curPoint++][j]= tempCount;
                }
            }
        }

    }


    static int[][] deepCopy(int[][] temp, int[][] graph){
        for (int i = 0; i < temp.length; i++) {
            temp[i] = graph[i].clone();
        }
        return temp;
    }
    static void print(int[][] graph) {
        for (int[] is : graph) {
            System.out.println(Arrays.toString(is));
        }
        System.out.println();
    }
}