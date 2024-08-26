import java.io.*;

public class Main {

    static int N;
    static int[] nums;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        backtracking(0, 0, 0);
        System.out.println(answer);
    }

    public static void backtracking(int start, int sum, int cnt) {
        answer = Math.max(answer, cnt);

        for(int i=start; i<N;i++) {
            if(isNotCarry(nums[i], sum)) {
                backtracking(i+1, sum + nums[i], cnt+1);
            }
        }
    }

    public static boolean isNotCarry(int num1, int num2) {
        while(num1 != 0 || num2 != 0) {
            int digitNum1 = num1%10;
            int digitNum2 = num2%10;

            if(digitNum1 + digitNum2 >= 10) return false;
            num1 /= 10;
            num2 /= 10;
        }
        return true;
    }
}