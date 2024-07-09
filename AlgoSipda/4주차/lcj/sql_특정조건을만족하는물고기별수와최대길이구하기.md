## Programmers 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기
### 🛠️ 문제 🛠️
```
FISH_INFO에서 평균 길이가 33cm 이상인 물고기들을 종류별로 분류하여 잡은 수, 최대 길이, 물고기의 종류를 출력하는 SQL문을 작성해주세요. 결과는 물고기 종류에 대해 오름차순으로 정렬해주시고, 10cm이하의 물고기들은 10cm로 취급하여 평균 길이를 구해주세요.

컬럼명은 물고기의 종류 'FISH_TYPE', 잡은 수 'FISH_COUNT', 최대 길이 'MAX_LENGTH'로 해주세요.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/298519)

<br/>

### 💡 코드 💡
```sql
SELECT COUNT(FISH_TYPE) AS FISH_COUNT, MAX(LENGTH) AS MAX_LENGTH, FISH_TYPE
FROM FISH_INFO
GROUP BY FISH_TYPE
HAVING AVG(IFNULL(LENGTH, 10)) >= 33
ORDER BY FISH_TYPE;
```

<br/>

### 📙 Mysql 개념 📙
[없음]