import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int answer = 0;
        for(int i=0;i<N-2;i++) {
            for(int j=i+1; j<N-1;j++) {
                for(int k=j+1; k<N;k++) {
                    if(isPossible(arr[i], arr[j], arr[k])) {
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static boolean isPossible(int x, int y, int z) {
        return ((y-x) <= (z-y)) && ((z-y) <= 2*(y-x));
        
    }
}