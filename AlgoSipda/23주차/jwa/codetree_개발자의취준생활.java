import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] devs, coms;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        devs = new int[N];
        coms = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            devs[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coms[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(devs);
        Arrays.sort(coms);
        
        long answer = 1;
        for (int i = 0; i < N; i++) {
            int find = binarySearch(devs[N - 1 - i]);
            answer *= N - find - i;
        }

        System.out.println(answer);
    }

    static int binarySearch(int target) {
        int start = 0, end = N - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (coms[mid] == target) {
                return mid;
            }
            if (coms[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
