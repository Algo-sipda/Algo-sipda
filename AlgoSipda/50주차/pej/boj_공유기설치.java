import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, C; // 도현이의 집 N개 , 공유기 C개
    static int[] box;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        box = new int[N];
        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(br.readLine());
            box[i] = x;
        }

        Arrays.sort(box);

        // 가장 인접한 두 공유기 사이의 거리 1 ~ box[N-1] - box[0]
        long maxDist = 0;
        long left = 1, right = box[N-1] - box[0];
        while(left <= right) {
            long minDist = (right - left) / 2 + left;
            if(isPossible(minDist)) {
                maxDist = Math.max(maxDist, minDist);
                left = minDist + 1;
            } else {
                right = minDist - 1;
            }
        }
        System.out.println(maxDist);
    }
    static boolean isPossible(long dist){
        int used = 1;
        int prev = box[0];
        for(int i = 1; i < N; i++){
            if(box[i] - prev >= dist) {
                used += 1;
                prev = box[i];
            }

        }
        return used >= C;
    }

}