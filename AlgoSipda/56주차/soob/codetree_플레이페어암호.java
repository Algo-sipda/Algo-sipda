import java.io.*;
import java.util.*;

public class Main {
    static char[][] board = new char[5][5];
    static int[] pos = new int[26];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String plaintext = br.readLine().trim().replace(" ", "").toUpperCase().replace('J', 'I');
        String key = br.readLine().trim().replace(" ", "").toUpperCase().replace('J', 'I');
        buildBoard(key);
        List<char[]> pairs = makePairs(plaintext);
        StringBuilder sb = new StringBuilder();
        for (char[] p : pairs) {
            int a = p[0] - 'A';
            int b = p[1] - 'A';
            int ay = pos[a] / 5, ax = pos[a] % 5;
            int by = pos[b] / 5, bx = pos[b] % 5;
            if (ay == by) {
                sb.append(board[ay][(ax + 1) % 5]);
                sb.append(board[by][(bx + 1) % 5]);
            } else if (ax == bx) {
                sb.append(board[(ay + 1) % 5][ax]);
                sb.append(board[(by + 1) % 5][bx]);
            } else {
                sb.append(board[ay][bx]);
                sb.append(board[by][ax]);
            }
        }
        System.out.println(sb.toString());
    }

    static void buildBoard(String key) {
        boolean[] used = new boolean[26];
        used['J' - 'A'] = true;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!used[c - 'A']) {
                used[c - 'A'] = true;
                s.append(c);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!used[c - 'A']) {
                used[c - 'A'] = true;
                s.append(c);
            }
        }
        int k = 0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                char c = s.charAt(k++);
                board[y][x] = c;
                pos[c - 'A'] = y * 5 + x;
            }
        }
    }

    static List<char[]> makePairs(String text) {
        List<char[]> list = new ArrayList<char[]>();
        int i = 0;
        while (i < text.length()) {
            char a = text.charAt(i++);
            char b = (i < text.length() ? text.charAt(i) : 0);
            if (b == 0) {
                list.add(new char[]{a, 'X'});
                break;
            }
            if (a == b) {
                char filler = (a == 'X' ? 'Q' : 'X');
                list.add(new char[]{a, filler});
            } else {
                list.add(new char[]{a, b});
                i++;
            }
        }
        return list;
    }
}

