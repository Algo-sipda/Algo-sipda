import java.util.*;

class Solution {
    static class Record {
        int time;
        String type;

        Record(int time, String type) {
            this.time = time;
            this.type = type;
        }
    }

    public List<Integer> solution(int[] fees, String[] records) {
        HashMap<String, List<Record>> cars = new HashMap<>();

        for (String r : records) {
            String[] record = r.split(" ");
            int[] time = Arrays.stream(record[0].split(":")).mapToInt(Integer::parseInt).toArray();
            String num = record[1];
            if (!cars.containsKey(num)) {
                cars.put(num, new ArrayList<>());
            }
            cars.get(num).add(new Record(time[0] * 60 + time[1], record[2]));
        }

        List<String> keys = new ArrayList<>(cars.keySet());
        Collections.sort(keys);
        List<Integer> answer = new ArrayList<>();

        for (String carNum : keys) {
            if (cars.get(carNum).size() % 2 == 1) {
                cars.get(carNum).add(new Record(23 * 60 + 59, "OUT"));
            }

            int time = 0;
            int fee = 0;

            for (Record record : cars.get(carNum)) {
                if (record.type.equals("IN")) {
                    time -= record.time;
                } else {
                    time += record.time;
                }
            }

            fee += fees[1];
            if (time > fees[0]) {
                fee += Math.ceil((time - fees[0]) / (float) fees[2]) * fees[3];
            }
            answer.add(fee);
        }

        return answer;
    }
}
