import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Map<Integer, Integer>> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            
            Map<Integer, Integer> map = new HashMap<>();
            
            map.put(A, F);
            map.put(B, D);
            map.put(C, E);
            map.put(F, A);
            map.put(D, B);
            map.put(E, C);
            
            list.add(map);
        }

        int answer = 0;
        for (int i = 1; i <= 6; i++) {
            int bottom = i;
            int sum = 0;
            for(Map<Integer, Integer> map : list){
                int top = map.get(bottom);
                for (int j = 6; j > 1; j--) {
                    if(j != top && j != bottom) {
                        sum += j;
                        break;
                    }
                }
                bottom = top;
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
