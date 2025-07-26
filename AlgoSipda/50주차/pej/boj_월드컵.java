//[BOJ] 월드컵 - https://www.acmicpc.net/problem/6987

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
    static final int MAX_TEAM_COUNT = 6;
    static int[][] matches;
    static boolean isEndGame = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = 4;
        // 최대 경기 가능한 경우의 수 구하기
        int size = 0;
        for(int i = 1; i < MAX_TEAM_COUNT; i++) {
            size += i;
        }

        // 경기 매치 별 팀 별도 저장
        matches = new int[size][2];
        int index = 0;
        for(int i = 0; i < MAX_TEAM_COUNT - 1; i++) {
            for(int j = i + 1; j < MAX_TEAM_COUNT; j++) {
                matches[index][0] = i;
                matches[index][1] = j;
                index++;
            }
        }

        while(tc --> 0) {
            st = new StringTokenizer(br.readLine());
            int[][] worldCup = new int[3][MAX_TEAM_COUNT]; // 인덱스를 뒤집는게 나은가? 열 : 승/무/패, 행 : A,B,C,D,E,F팀
            boolean isPossible = true;

            // 모든 경기 결과 입력받기
            for(int i = 0; i < MAX_TEAM_COUNT; i++) {
                int win = Integer.valueOf(st.nextToken());
                int draw = Integer.valueOf(st.nextToken());
                int lose = Integer.valueOf(st.nextToken());

                worldCup[0][i] = win;
                worldCup[1][i] = draw;
                worldCup[2][i] = lose;

                // 한 팀당 5번을 경기해야 한다.
                if(win + draw + lose != 5) {
                    isPossible = false;
                    break;
                }
            }

            // 모든 팀의 경기 수가 조건에 일치하는 경우 경기 결과 비교 진행
            if(isPossible) {
                backTracking(worldCup, 0, size);
                if(isEndGame) {
                    sb.append(1);
                }
                else {
                    sb.append(0);
                }
            }
            else {
                sb.append(0);
            }

            sb.append(" ");
            isEndGame = false;
        }

        System.out.print(sb.toString());
    }

    // 백트래킹 함수
    static void backTracking(int[][] worldCup, int matchCount, int size) {
        if(isEndGame) {
            return;
        }

        // 모든 게임을 수행할 수 있다면 이 월드컵은 가능하다.
        if(matchCount == size) {
            isEndGame = true;
            return;
        }

        int myTeam = matches[matchCount][0];
        int enemyTeam = matches[matchCount][1];

        // 승 -> 패
        if(worldCup[0][myTeam] > 0 && worldCup[2][enemyTeam] > 0) {
            worldCup[0][myTeam]--;
            worldCup[2][enemyTeam]--;
            backTracking(worldCup, matchCount + 1, size);
            worldCup[0][myTeam]++;
            worldCup[2][enemyTeam]++;
        }
        // 무 -> 무
        if(worldCup[1][myTeam] > 0 && worldCup[1][enemyTeam] > 0) {
            worldCup[1][myTeam]--;
            worldCup[1][enemyTeam]--;
            backTracking(worldCup, matchCount + 1, size);
            worldCup[1][myTeam]++;
            worldCup[1][enemyTeam]++;
        }
        // 패 -> 승
        if(worldCup[2][myTeam] > 0 && worldCup[0][enemyTeam] > 0) {
            worldCup[2][myTeam]--;
            worldCup[0][enemyTeam]--;
            backTracking(worldCup, matchCount + 1, size);
            worldCup[2][myTeam]++;
            worldCup[0][enemyTeam]++;
        }
    }
}