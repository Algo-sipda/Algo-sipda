import java.util.ArrayList;

class pro_주차요금계산 {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        int[] car = new int[10000];
        boolean[] chk = new boolean[10000];
        ArrayList<Integer> carList = new ArrayList<>();
        int max = 0;

        for(int i=0;i<records.length;i++) {
            String[] info = records[i].split(" ");
            String[] time = info[0].split(":");
            int minute = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            int carNum = Integer.parseInt(info[1]);
            max = Math.max(max, carNum);

            if(info[2].equals("IN")) {
                car[carNum] += minute;
                chk[carNum] = true;
            } else {
                car[carNum] -= minute;
                chk[carNum] = false;
            }
        }

        //23:59 처리
        for(int i=0;i<=max;i++) {
            if(car[i] != 0 || chk[i] == true) {
                carList.add(i);
                if(chk[i] == true)
                    car[i] = 1439 - car[i];
            }
        }

        answer = new int[carList.size()];

        //요금계산
        for(int i=0;i<carList.size();i++) {
            int num = carList.get(i);
            int totalTime = Math.abs(car[num]);

            if(totalTime <= fees[0]) {
                answer[i] = fees[1];
            } else {
                int tmp = totalTime - fees[0];
                answer[i] = fees[1] + ((int)Math.ceil((double)tmp / fees[2]) * fees[3]);
            }
        }

        return answer;
    }
}
