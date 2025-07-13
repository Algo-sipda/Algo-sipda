import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime(i))
                primeList.add(i);
        }

        List<Integer> sumList = new ArrayList<>();
        sumList.add(0);
        int sum = 0;
        for (int prime : primeList) {
            sum += prime;
            sumList.add(sum);
        }

        int answer = 0;
        for (int i = sumList.size() - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int x = sumList.get(i) - sumList.get(j);
                if (x > N)
                    break;
                if (x == N){
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    public static boolean isPrime(int n) {
        if (n == 1)
            return false;
        if (n == 2 || n == 3)
            return true;
        int x = (int) Math.sqrt(n);
        for (int i = 2; i <= x; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
