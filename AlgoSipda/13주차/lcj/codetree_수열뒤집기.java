import java.io.*;
import java.util.*;

/*
    N개의 원소로 이루어져 있음
    [A1, A2], [B1, B2] 구간 안의 원소들을 순서대로 뒤집어 순서를 바꿔주는 것을 K번 반복한 이후 수열 출력
    7 2 / 2 5 / 3 7
    [2, 5] -> 1 5 4 3 2 6 7
    [3, 7] -> 1 5 7 6 2 3 4
*/

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N+1];
        for(int i=1;i<=N;i++) {
            nums[i] = i;
        }
        
        st = new StringTokenizer(br.readLine());
        int A1 = Integer.parseInt(st.nextToken());
        int A2 = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int B1 = Integer.parseInt(st.nextToken());
        int B2 = Integer.parseInt(st.nextToken());

        // 먼저 같은 주기 찾기
        int cycleCnt = 0;
        boolean cycleCheck = false;
        int[] copy = Arrays.copyOf(nums, N+1);
        for(int i=0;i<K;i++) {
            cycleCnt++;
            copy = solution(N, A1, A2, copy);
            copy = solution(N, B1, B2, copy);

            if (Arrays.equals(copy, nums)) {
                cycleCheck = true;
                break;
            }
        }

        nums = Arrays.copyOf(copy, N+1);

        if(cycleCheck) {
            K %= cycleCnt;
            for(int i=0;i<K;i++) {
                nums = solution(N, A1, A2, nums);
                nums = solution(N, B1, B2, nums);
            }
        }

        for(int i=1; i<=N;i++) {
            sb.append(nums[i]).append("\n");
        }
        System.out.print(sb);
    }

    public static int[] solution(int N, int start, int end, int[] nums) {
        int cnt = (end-start)/2 + 1;
        for(int i=0; i<cnt;i++) {
            int tmp = nums[i+start];
            nums[i+start] = nums[end-i];
            nums[end-i] = tmp;
        }
        return nums;
    }
}