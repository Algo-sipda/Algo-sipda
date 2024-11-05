import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] radius;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        radius = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            radius[i] = Integer.parseInt(st.nextToken());
        }

        int result = 1;

        for(int i = 2; i <= 100; i++) {
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(radius[j] % i == 0) count++;
            }
            result = Math.max(count, result);
        }

        System.out.println(result);
    }
}
