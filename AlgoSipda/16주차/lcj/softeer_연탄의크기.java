import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for(int i=0;i<n;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int answer = 0;
        for(int i=2;i<=nums[n-1];i++) {
            int cnt = 0;
            for(int j=0;j<n;j++) {
                if(nums[j] % i == 0) cnt++;
            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }
}
