import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N]; // 주어지는 숫자를 담을 배열
        HashSet<Integer> set = new HashSet<>(); // 제외할 숫자를 담을 set

        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
            set.add(arr[i]);
        }

        int answer = 0;
        int size = set.size();
        for(Integer num: set){
            int temp = arr[0];
            int cnt = 1;

            for(int j=1;j<arr.length;j++) {
                if(arr[j] == num) {
                    continue;
                }

                if(temp == arr[j]) {
                    cnt++;
                }
                else {
                    answer = Math.max(answer, cnt);
                    temp = arr[j];
                    cnt = 1;
                }
            }

            answer = Math.max(answer, cnt);

        }
        System.out.println(answer);
    }
}