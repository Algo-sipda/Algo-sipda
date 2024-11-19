import java.io.*;
import java.util.*;

public class Main {

    static int N, K, answer;
    static int[][] rules;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N명의 학생수
        K = Integer.parseInt(st.nextToken()); // 조건 수
        
        rules = new int[K][3];
        arr = new char[N];
        
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            rules[i][0] = st.nextToken().equals("S")? 0 : 1;
            rules[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rules[i][2] = Integer.parseInt(st.nextToken()) - 1;
        }

        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int idx) {
        if(idx == N) {
            for(int i=0;i<rules.length;i++) {
                if(rules[i][0] == 0) {
                    // 동일해야 하는데 다를 경우
                    if(arr[rules[i][1]] != arr[rules[i][2]]) return;
                }
                else {
                    // 달라야 하는데 같을 경우
                    if(arr[rules[i][1]] == arr[rules[i][2]]) return;
                }
            }
            answer++;
            return;
        }

        for(int i=0;i<3;i++) {
            arr[idx] = (char)(i + 'A');
            dfs(idx+1);
        }
    }
}