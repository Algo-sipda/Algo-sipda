import java.util.Scanner;

public class boj_flyMeToTheAlphaCentauri {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt();

        for (int i = 0; i < test; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            int distance = y - x;
            int max = (int) Math.sqrt(distance);
            int result = 0;

            if (Math.pow(max, 2) == distance) {
                result = 2 * max - 1;
            } else if (Math.pow(max, 2) < distance && distance <= max * max + max) {
                result = 2 * max;
            } else if (distance > max * max + max) {
                result = 2 * max + 1;
            }

            System.out.println(result);
        }
    }

}

