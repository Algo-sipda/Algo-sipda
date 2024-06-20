## Programmers 대장균의 크기에 따라 분류하기2
### 🛠️ 문제 🛠️
```
대장균 개체의 크기를 내름차순으로 정렬했을 때 상위 0% ~ 25% 를 'CRITICAL', 26% ~ 50% 를 'HIGH', 51% ~ 75% 를 'MEDIUM', 76% ~ 100% 를 'LOW' 라고 분류합니다. 대장균 개체의 ID(ID) 와 분류된 이름(COLONY_NAME)을 출력하는 SQL 문을 작성해주세요. 이때 결과는 개체의 ID 에 대해 오름차순 정렬해주세요 . 단, 총 데이터의 수는 4의 배수이며 같은 사이즈의 대장균 개체가 서로 다른 이름으로 분류되는 경우는 없습니다.
```
<br/>

### 💡 코드 💡
```sql
SELECT ED.ID,
    CASE
        WHEN ED.PER <= 0.25 THEN "CRITICAL"
        WHEN ED.PER <= 0.5 THEN "HIGH"
        WHEN ED.PER <= 0.75 THEN "MEDIUM"
        ELSE "LOW"
    END AS COLONY_NAME
FROM (
    SELECT ID, PERCENT_RANK()
                OVER(ORDER BY SIZE_OF_COLONY DESC) AS PER
    FROM ECOLI_DATA
) AS ED
ORDER BY ED.ID ASC;
```
- 다른 사람 풀이
```sql
SELECT ID,
    CASE
        WHEN NTILE(4) OVER (ORDER BY SIZE_OF_COLONY) = 1 THEN 'LOW'
        WHEN NTILE(4) OVER (ORDER BY SIZE_OF_COLONY) = 2 THEN 'MEDIUM'
        WHEN NTILE(4) OVER (ORDER BY SIZE_OF_COLONY) = 3 THEN 'HIGH'
        ELSE 'CRITICAL'
        END AS COLONY_NAME
FROM ECOLI_DATA
ORDER BY ID
```
<br/>

### 📙 Mysql 개념 📙
1. PERCENT_RANK()
- 결과 집합 내 행의 백분위수 순위를 계산하는 상대 순위 함수
- 지정된 순서 열에 있는 모든 값 중에서 현재 행 값의 상대적 위치를 나타내는 0과 1사이의 값을 반환합니다.
- 계산은 순위를 기준으로 하지만 결과는 0과 1사이에서 정규화됩니다.
    ```sql
    SELECT name, score, PERCENT_RANK() OVER (ORDER BY score DESC) AS pct_rank FROM students;
    ```

<br/>

2. NTILE(n)
- 행을 지정된 수의 그룹(n)으로 분배
- 각 그룹은 가능한 경우 동일한 수의 행을 포함
- 값이 같은 행은 같은 그룹에 배치
    ```sql
    SELECT id, product, sale_date, amount,
    NTILE(4) OVER (ORDER BY amount) AS quartile 
    FROM sales;
    ```