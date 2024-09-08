## Pro 저자 별 카테고리 별 매출액 집계하기

---

### 문제
```
2022년 1월의 도서 판매 데이터를 기준으로 저자 별, 카테고리 별 매출액(TOTAL_SALES = 판매량 * 판매가) 을 구하여, 저자 ID(AUTHOR_ID), 저자명(AUTHOR_NAME), 카테고리(CATEGORY), 매출액(SALES) 리스트를 출력하는 SQL문을 작성해주세요.
결과는 저자 ID를 오름차순으로, 저자 ID가 같다면 카테고리를 내림차순 정렬해주세요.
```

### 코드
```sql
WITH SALE_DURATION AS (
    SELECT * FROM BOOK_SALES WHERE YEAR(SALES_DATE) = 2022 AND MONTH(SALES_DATE) = 1
)

SELECT A.AUTHOR_ID, A.AUTHOR_NAME, B.CATEGORY, SUM(D.SALES * B.PRICE) AS TOTAL_SALES
FROM BOOK B, AUTHOR A, SALE_DURATION D
WHERE B.AUTHOR_ID = A.AUTHOR_ID AND B.BOOK_ID = D.BOOK_ID
GROUP BY A.AUTHOR_ID, B.CATEGORY
ORDER BY A.AUTHOR_ID, B.CATEGORY DESC;


```