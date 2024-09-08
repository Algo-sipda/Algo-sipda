import java.io.*;
import java.util.*;

public class Main {

    static String[] cows = {"Beatrice", "Bessie", "Bella", "Blue", "Buttercup", "Betsy", "Belinda", "Sue"};
    static List<String[]> conditions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++) {
            String line = br.readLine();
            String[] parts = line.split(" must be milked beside ");
            conditions.add(new String[]{parts[0], parts[1]});
        }

        Arrays.sort(cows);

        while (nextPermutation(cows)) {
            if (checkCondition(cows)) {
                for (String cow : cows) {
                    System.out.println(cow);
                }
                break;
            }
        };
    }

    static boolean checkCondition(String[] order) {
        for (String[] condition : conditions) {
            String cow1 = condition[0];
            String cow2 = condition[1];
            int pos1 = -1, pos2 = -1;
            
            for (int i = 0; i < 8; i++) {
                if (order[i].equals(cow1)) pos1 = i;
                if (order[i].equals(cow2)) pos2 = i;
            }
            
            if (Math.abs(pos1 - pos2) != 1) return false;
        }
        return true;
    }

    static boolean nextPermutation(String[] array) {
        int i = array.length - 1;
        
        while (i > 0 && array[i - 1].compareTo(array[i]) >= 0) i--;
        if (i <= 0) return false;

        int j = array.length - 1;
        while (array[j].compareTo(array[i - 1]) <= 0) j--;

        String temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return true;
    }
}