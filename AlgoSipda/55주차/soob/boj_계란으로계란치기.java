import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Egg {
        int durability;
        int weight;
        Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    static int answer = 0;
    static int N;    // 계란의 수
    static Egg[] eggs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[n] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int now) {
        if (now >= N) {
            int broken = (int) Arrays.stream(eggs).filter(e -> e.durability <= 0).count();
            if (broken > answer) answer = broken;
            return;
        }

        if (eggs[now].durability <= 0) {
            dfs(now + 1);
            return;
        }

        boolean hit = false;
        int wNow = eggs[now].weight;
        for (int i = 0; i < N; i++) {
            if (i == now) continue;
            if (eggs[i].durability <= 0) continue;

            hit = true;
            eggs[now].durability -= eggs[i].weight;
            eggs[i].durability -= wNow;

            dfs(now + 1);

            eggs[i].durability += wNow;
            eggs[now].durability += eggs[i].weight;
        }

        if (!hit) dfs(now + 1);
    }
}
