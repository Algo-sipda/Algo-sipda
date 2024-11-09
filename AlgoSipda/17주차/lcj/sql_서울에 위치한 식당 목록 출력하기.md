## Programmers 서울에 위치한 식당 목록 출력하기

### 🛠️ 문제 🛠️

```
REST_INFO와 REST_REVIEW 테이블에서 서울에 위치한 식당들의 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수를 조회하는 SQL문을 작성해주세요. 이때 리뷰 평균점수는 소수점 세 번째 자리에서 반올림 해주시고 결과는 평균점수를 기준으로 내림차순 정렬해주시고, 평균점수가 같다면 즐겨찾기수를 기준으로 내림차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/131118)

<br/>

### 💡 코드 💡

```sql
-- 서울에 위치한 식당 ID, 식당 이름, 음식 종류, 즐겨찾기 수, 주소, 리뷰 평균 점수
-- 리뷰 평균 점수 : 소수점 세번째 자리에서 반올림
-- 평균점수를 기준으로 내림차순, 즐겨찾기 수를 기준으로 내림차순 정렬
SELECT I.REST_ID, I.REST_NAME, I.FOOD_TYPE, FAVORITES, ADDRESS, ROUND(AVG(R.REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO I
JOIN REST_REVIEW R ON I.REST_ID = R.REST_ID
GROUP BY I.REST_ID
HAVING ADDRESS LIKE '%서울%'
ORDER BY SCORE DESC, FAVORITES DESC;


```

<br/>

### 📙 개념 📙

[없음]
