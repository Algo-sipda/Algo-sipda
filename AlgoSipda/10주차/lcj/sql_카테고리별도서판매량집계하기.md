## Programmers 카테고리 별 도서 판매량 집계하기

### 🛠️ 문제 🛠️

```
2022년 1월의 카테고리 별 도서 판매량을 합산하고, 카테고리(CATEGORY), 총 판매량(TOTAL_SALES) 리스트를 출력하는 SQL문을 작성해주세요.
결과는 카테고리명을 기준으로 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/144855)

<br/>

### 💡 코드 💡

```sql
SELECT CATEGORY, SUM(SALES) AS TOTAL_SALES
FROM BOOK B, BOOK_SALES BS
WHERE 1=1
    AND B.BOOK_ID = BS.BOOK_ID
    AND BS.SALES_DATE LIKE "2022-01%"
GROUP BY CATEGORY
ORDER BY CATEGORY;
```

<br/>

### 📙 Mysql 개념 📙
[없음]
