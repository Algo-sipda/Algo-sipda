import java.util.*;

// 정렬

class pro_인사고과 {
    public int solution(int[][] scores) {
        int rank = 1;
        int[] won = scores[0];
        int max = 0;

        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        for (int[] score : scores) {
            if (max <= score[1]) {
                max = score[1];
                if (score[0] + score[1] > won[0] + won[1]) {
                    rank++;
                }
            } else {
                if (score[0] == won[0] && score[1] == won[1]) {
                    return -1;
                }
            }
        }

        return rank;
    }
}