import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static long answer = 1;
    static List<Integer> developerList = new ArrayList<>();
    static List<Integer> companyList = new ArrayList<>();
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            developerList.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            companyList.add(Integer.parseInt(st.nextToken()));
        }

        developerList.sort(Collections.reverseOrder());
        companyList.sort(Collections.reverseOrder());

        used = new boolean[N];

        if (!calculate()) System.out.println(0);
        else System.out.println(answer);
    }

    public static boolean calculate() {
        for (int i = 0; i < N; i++) {
            int devSkill = developerList.get(i);
            int validCompanies = 0;

            for (int j = 0; j < N; j++) {
                if (!used[j] && companyList.get(j) >= devSkill) validCompanies++;
            }

            if (validCompanies == 0) return false;

            answer *= validCompanies;

            for (int j = 0; j < N; j++) {
                if (!used[j] && companyList.get(j) >= devSkill) {
                    used[j] = true;
                    break;
                }
            }
        }

        return true;
    }
}
