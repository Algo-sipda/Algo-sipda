// [PRO] 사라지는 발판 https://school.programmers.co.kr/learn/courses/30/lessons/92345
// 두 플레이어가 최선을 다해서 이기는 게임 : 미니맥스 게임 이론 

class Solution {
    static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static int N, M;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;

        Result result = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]);
        return result.count;
    }

    // 현재 턴 플레이어가 a라면 a가 움직임 -> 결과는 a의 승패와 턴 수
    private Result dfs(int[][] board, int ax, int ay, int bx, int by) {
        if (board[ax][ay] == 0) return new Result(false, 0); // 현재 위치 사라졌다면 패배

        boolean canWin = false;
        int minTurn = Integer.MAX_VALUE;
        int maxTurn = 0;

        for (int[] d : dir) {
            int nax = ax + d[0];
            int nay = ay + d[1];

            if (nax < 0 || nay < 0 || nax >= N || nay >= M || board[nax][nay] == 0) continue;

            board[ax][ay] = 0; // 현재 위치 제거
            Result result = dfs(board, bx, by, nax, nay); // 다음 턴은 상대(b)가 주인공
            board[ax][ay] = 1; // 백트래킹: 위치 복원

            // 현재 플레이어가 이기는 상황
            if (!result.win) {
                canWin = true;
                minTurn = Math.min(minTurn, result.count + 1);
            } else {
                maxTurn = Math.max(maxTurn, result.count + 1);
            }
        }

        if (canWin) return new Result(true, minTurn);
        else return new Result(false, maxTurn);
    }

    // 승패와 턴 수를 저장하는 내부 클래스
    static class Result {
        boolean win;
        int count;

        Result(boolean win, int count) {
            this.win = win;
            this.count = count;
        }
    }
}
