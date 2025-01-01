import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, List<String>> carRecords = new HashMap<>();
        Set<String> carNumbersSet = new HashSet<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNumber = parts[1];
            carNumbersSet.add(carNumber);
            carRecords.putIfAbsent(carNumber, new ArrayList<>());
            carRecords.get(carNumber).add(time);
        }

        List<String> carNumbers = new ArrayList<>(carNumbersSet);
        Collections.sort(carNumbers);

        List<Integer> carTimes = new ArrayList<>();
        for (String carNumber : carNumbers) {
            List<String> times = carRecords.get(carNumber);
            int totalTime = 0;

            if (times.size() % 2 == 1) {
                times.add("23:59");
            }

            for (int i = 0; i < times.size(); i += 2) {
                int inTime = hToM(times.get(i));
                int outTime = hToM(times.get(i + 1));
                totalTime += outTime - inTime;
            }

            carTimes.add(totalTime);
        }

        int[] answer = new int[carNumbers.size()];
        for (int i = 0; i < carNumbers.size(); i++) {
            int totalTime = carTimes.get(i);
            if (totalTime <= fees[0]) {
                answer[i] = fees[1];
            } else {
                int extraTime = totalTime - fees[0];
                int extraFee = (int) Math.ceil((double) extraTime / fees[2]) * fees[3];
                answer[i] = fees[1] + extraFee;
            }
        }

        return answer;
    }

    private int hToM(String time) {
        String[] parts = time.split(":");
        int hh = Integer.parseInt(parts[0]);
        int mm = Integer.parseInt(parts[1]);
        return hh * 60 + mm;
    }
}
