import java.util.*;
import java.io.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] maxPreviousLayer = new int[3];
        int[] minPreviousLayer = new int[3];
        int[] curMaxLayer = new int[3];
        int[] curMinLayer = new int[3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] input = new int[3];
            input[0] = Integer.parseInt(st.nextToken());
            input[1] = Integer.parseInt(st.nextToken());
            input[2] = Integer.parseInt(st.nextToken());

            if (i == 0) {
                maxPreviousLayer = input.clone();
                minPreviousLayer = input.clone();
                curMaxLayer = input.clone();
                curMinLayer = input.clone();
            } else {
                curMaxLayer[0] = Math.max(maxPreviousLayer[0], maxPreviousLayer[1]) + input[0];
                curMaxLayer[2] = Math.max(maxPreviousLayer[1], maxPreviousLayer[2]) + input[2];
                int curMax = Math.max(maxPreviousLayer[0], maxPreviousLayer[1]);
                curMax = Math.max(curMax, maxPreviousLayer[2]);
                curMaxLayer[1] = curMax + input[1];
                maxPreviousLayer = curMaxLayer.clone();

                curMinLayer[0] = Math.min(minPreviousLayer[0], minPreviousLayer[1]) + input[0];
                curMinLayer[2] = Math.min(minPreviousLayer[1], minPreviousLayer[2]) + input[2];
                int curMin = Math.min(minPreviousLayer[0], minPreviousLayer[1]);
                curMin = Math.min(curMin, minPreviousLayer[2]);
                curMinLayer[1] = curMin + input[1];
                minPreviousLayer = curMinLayer.clone();
            }
        }
        int max = Math.max(maxPreviousLayer[0], maxPreviousLayer[1]);
        max = Math.max(max, maxPreviousLayer[2]);
        int min = Math.min(minPreviousLayer[0], minPreviousLayer[1]);
        min = Math.min(min, minPreviousLayer[2]);
        System.out.println(max + " " + min);
    }
}