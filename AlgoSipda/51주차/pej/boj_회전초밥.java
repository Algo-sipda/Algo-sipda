// [BOJ] 회전 초밥
// https://www.acmicpc.net/problem/2531

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, d, k, c;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] susi = new int[N];
        for(int i = 0; i < N; i++){
            susi[i] = Integer.parseInt(br.readLine());
        }
        int maxDiff = 0;
        int left = 0, right = k - 1;
        Map<Integer, Integer> diff = new HashMap<>();
        for(int i = 0; i <= k-1; i++){
            diff.put(susi[i], diff.getOrDefault(susi[i], 0) + 1);

        }
        if(!diff.keySet().contains(c)){
            maxDiff = Math.max(maxDiff, 1 + diff.size());
        }

        while(true){
            // 예전 left가 가리키는 값 먼저 삭제 후 
            if(diff.getOrDefault(susi[left], 0) <= 1){
                diff.remove(susi[left]);
            }else {
                diff.put(susi[left],diff.get(susi[left]) - 1);
            }
            // 새로운 left 다시 만들어야 함 
            left += 1;
            if(left >= N) break;
            
            right = (right + 1) % N;
            diff.put(susi[right], diff.getOrDefault(susi[right], 0)+ 1);
            int curDiff = diff.size();
            if(!diff.containsKey(c)) {
                curDiff += 1;
            }
            maxDiff = Math.max(curDiff, maxDiff);
        }

        System.out.println(maxDiff);

    }

}
