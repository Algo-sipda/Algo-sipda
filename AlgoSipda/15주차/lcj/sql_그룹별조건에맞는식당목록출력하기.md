## Programmers 
그룹별 조건에 맞는 식당 목록 출력하기

### 🛠️ 문제 🛠️

```
MEMBER_PROFILE와 REST_REVIEW 테이블에서 리뷰를 가장 많이 작성한 회원의 리뷰들을 조회하는 SQL문을 작성해주세요. 회원 이름, 리뷰 텍스트, 리뷰 작성일이 출력되도록 작성해주시고, 결과는 리뷰 작성일을 기준으로 오름차순, 리뷰 작성일이 같다면 리뷰 텍스트를 기준으로 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/131124)

<br/>

### 💡 코드 💡

```sql
SELECT MP.MEMBER_NAME, RR.REVIEW_TEXT, DATE_FORMAT(RR.REVIEW_DATE, "%Y-%m-%d") AS REVIEW_DATE
FROM MEMBER_PROFILE MP
JOIN REST_REVIEW RR
ON MP.MEMBER_ID = RR.MEMBER_ID
WHERE MP.MEMBER_ID = (SELECT MEMBER_ID
                      FROM REST_REVIEW
                      GROUP BY MEMBER_ID
                      ORDER BY COUNT(MEMBER_ID) DESC 
                      LIMIT 1)
ORDER BY REVIEW_DATE, REVIEW_TEXT;

```

<br/>

### 📙 개념 📙

[없음]
