## Programmers 즐겨찾기가 가장 많은 식당 정보 출력하기
### 🛠️ 문제 🛠️
```
REST_INFO 테이블에서 음식종류별로 즐겨찾기수가 가장 많은 식당의 음식 종류, ID, 식당 이름, 즐겨찾기수를 조회하는 SQL문을 작성해주세요. 이때 결과는 음식 종류를 기준으로 내림차순 정렬해주세요.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/131123)

<br/>

### 💡 코드 💡
```sql
SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
FROM REST_INFO
WHERE (FOOD_TYPE, FAVORITES) IN (SELECT FOOD_TYPE, MAX(FAVORITES)
                                 FROM REST_INFO
                                GROUP BY FOOD_TYPE)
ORDER BY FOOD_TYPE DESC;
```

<br/>

### 📙 Mysql 개념 📙
[없음]