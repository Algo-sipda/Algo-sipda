## Programmers 대장균의 크기에 따라 분류하기
### 🛠️ 문제 🛠️
```
PRODUCT 테이블과 OFFLINE_SALE 테이블에서 상품코드 별 매출액(판매가 * 판매량) 합계를 출력하는 SQL문을 작성해주세요. 결과는 매출액을 기준으로 내림차순 정렬해주시고 매출액이 같다면 상품코드를 기준으로 오름차순 정렬해주세요.
```

### 💡 코드 💡
```sql
SELECT PRODUCT_CODE, (SUM(SALES_AMOUNT) * P.PRICE) AS SALES
FROM PRODUCT P
INNER JOIN OFFLINE_SALE OS
ON P.PRODUCT_ID = OS.PRODUCT_ID
GROUP BY P.PRODUCT_ID
ORDER BY SALES DESC, PRODUCT_CODE ASC;
```
<br/>
