import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static String msg, key;
    static char[][] map = new char[5][5];
    static List<String> sepMsg = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        msg = br.readLine();
        key = br.readLine();

        // 표 만들기
        createTable();

        // 문자 분리하기(X, Q)
        splitMsg();

        // 문자 암호화
        System.out.println(encrypMsg());
    }

    private static void createTable() {
        // 1. 알파벳 순서대로 넣어주기 (겹치는 알파벳은 중복으로 넣을 수 없음)
        // 2. 들어가지 않은 알파벳 넣어주기(j는 들어가지 않음)
        HashSet<Character> hs = new HashSet<>();

        int idx = 0;
        for(int i = 0; i<key.length();i++) {
            int r = idx/5;
            int c = idx%5;
            char m = key.charAt(i);
            
            if(!hs.contains(m)) {
                hs.add(m);
                idx++;
                map[r][c] = m;
            }
        }

        for(char c = 'A'; c<='Z';c++) {
            if(c == 'J') continue;
            if(!hs.contains(c)) {
                map[idx/5][idx%5] = c;
                idx++;
            }
        }
    }

    private static void splitMsg() {
        // 1. 둘이 같은 문자로 들어가면 X 추가하기
        // 2. 그 해당 문자가 X이면 Q로 넣기
        int idx = 0;
        while(idx != msg.length()) {
            if(idx == msg.length()-1) {
                sepMsg.add(msg.charAt(idx)+"X");
                break;
            }

            if(msg.charAt(idx) == msg.charAt(idx+1)) {
                // 문자열이 같은 경우 X 넣기(시작 문자열이 X일 경우 -> Q가 들어가야함)
                if(msg.charAt(idx) == 'X') {
                    sepMsg.add(msg.charAt(idx)+"Q");
                }
                else {
                    sepMsg.add(msg.charAt(idx)+"X");
                }
                idx++;
            }
            else {
                sepMsg.add(msg.substring(idx, idx+2));
                idx += 2;
            }
        }
    }

    private static String encrypMsg() {
        StringBuilder sb = new StringBuilder();

        for(int e=0;e<sepMsg.size();e++) {
            char[] encrypWord = sepMsg.get(e).toCharArray();

            Point first = new Point(0, 0);
            Point second = new Point(0, 0);

            for(int i=0;i<map.length;i++) {
                for(int j=0;j<map[i].length;j++) {
                    if(map[i][j] == encrypWord[0]) {
                        first.x = i;
                        first.y = j;
                    }

                    if(map[i][j] == encrypWord[1]) {
                        second.x = i;
                        second.y = j;
                    }
                }
            }

            // 같은 행에 있는 경우
            if(first.x == second.x) {
                first.y = (first.y+1)%5;
                second.y = (second.y +1)%5;
            }
            // 같은 열에 있는 경우
            else if(first.y == second.y) {
                first.x = (first.x+1)%5;
                second.x = (second.x+1)%5;
            }
            // 둘 다 아닌 경우
            else  {
                int tmp = first.y;
                first.y = second.y;
                second.y = tmp;
            }
            sb.append(map[first.x][first.y]).append(map[second.x][second.y]);
        }

        return sb.toString();
    }
}
