import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        List<String> words = new ArrayList<>();
        List<String> fore = new ArrayList<>();
        List<String> back = new ArrayList<>();

        for(int i=0;i<N;i++) {
            String word = br.readLine().trim();
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            String reverseWord = new StringBuilder(sortedWord).reverse().toString();

            words.add(sortedWord);
            fore.add(sortedWord);
            back.add(reverseWord);
        }

        Collections.sort(fore);
        Collections.sort(back);

        for (String word : words) {
            int leftIndex = bisectLeft(back, word) + 1;
            int rightIndex = bisectRight(fore, new StringBuilder(word).reverse().toString());
            sb.append(leftIndex + " " + rightIndex).append("\n");
        }
        System.out.print(sb);
    }

    public static int bisectLeft(List<String> list, String key) {
        int index = Collections.binarySearch(list, key);
        
        if (index < 0) {
            return -index - 1;
        } else {
            // 리스트에 중복 요소 있는지 확인 차
            while (index > 0 && list.get(index - 1).equals(key)) {
                index--;
            }
            return index;
        }
    }

    public static int bisectRight(List<String> list, String key) {
        int index = Collections.binarySearch(list, key);

        if (index < 0) {
            return -index - 1;
        } else {
            while (index < list.size() && list.get(index).equals(key)) {
                index++;
            }
            return index;
        }
    }
}