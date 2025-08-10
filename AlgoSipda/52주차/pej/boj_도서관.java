import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<Integer> left = new ArrayList<>();
    static List<Integer> right = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            int value = Integer.parseInt(st.nextToken());
            if(value < 0){
                left.add(-value);
            } else {
                right.add(value);
            }
        }
        Collections.sort(left, Collections.reverseOrder());
        Collections.sort(right, Collections.reverseOrder());
        List<Integer> dist = new ArrayList<>();
        for(int i = 0; i < left.size(); i += M) {
            int x = 2 * left.get(i);
            dist.add(x);
        }
        for(int i = 0; i < right.size(); i += M){
            int y = 2 * right.get(i);
            dist.add(y);
        }
        // 0으로부터 가장 멀리 있는 책에 접근한다
        // 가장먼 ... M개의 책 은 2 * 가장 먼
        Collections.sort(dist);
        int res = 0;
        for(int i = 0; i < dist.size() - 1; i++){
            res += dist.get(i);
        }
        System.out.println(res + dist.get(dist.size()-1)/2);
    }


}