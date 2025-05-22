import java.util.Arrays;

class leetcode_boats {
    public int numRescueBoats(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            if (people[left] + people[right] > limit) {
                answer++;
                right--;
            } else {
                answer++;
                right--;
                left++;
            }
        }
        return answer;
    }
}