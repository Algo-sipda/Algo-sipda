## Pro 대장균의 크기에 따라 분류하기 2

---

### 문제
```
대장균 개체의 크기를 내름차순으로 정렬했을 때 
상위 0% ~ 25% 를 'CRITICAL', 26% ~ 50% 를 'HIGH', 51% ~ 75% 를 'MEDIUM', 76% ~ 100% 를 'LOW' 라고 분류합니다. 

대장균 개체의 ID(ID) 와 분류된 이름(COLONY_NAME)을 출력하는 SQL 문을 작성해주세요. 
이때 결과는 개체의 ID 에 대해 오름차순 정렬해주세요.
 
단, 총 데이터의 수는 4의 배수이며 같은 사이즈의 대장균 개체가 서로 다른 이름으로 분류되는 경우는 없습니다.
```

### 코드
```sql
WITH ECO AS (
    SELECT ID, RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) AS RNK, MAX(ID) OVER() AS TOTAL
    FROM ECOLI_DATA
)

SELECT ID,
       CASE
           WHEN RNK <= (TOTAL / 4) THEN 'CRITICAL'
           WHEN RNK <= (TOTAL / 2) THEN 'HIGH'
           WHEN RNK <= (TOTAL / 4 * 3) THEN 'MEDIUM'
           ELSE 'LOW'
           END AS COLONY_NAME
FROM ECO
ORDER BY ID;
```


### 문법 정리
#### 1. RANK()
```sql
-- RANK(): 데이터 집합 내에서 지정된 순서에 따라 각 행의 순위를 매기는 윈도우 함수

-- 예제
-- 각 부서(department_id) 내에서 급여(salary)를 기준으로 직원들의 순위를 매기기
SELECT
    employee_id,
    department_id,
    salary,
    RANK() OVER (PARTITION BY department_id ORDER BY salary DESC) AS rank
FROM
    employees;

```

#### 2. OVER()
```sql
-- OVER(): 윈도우 함수를 사용해 행의 집합에 대한 계산을 지정하는 윈도우 함수
-- 선택적으로 PARTITION BY와 ORDER BY를 포함할 수 있다.

-- PARTITION BY: 특정 열의 값에 따라 그룹화

-- 예제
-- 각 부서(department_id) 내에서 급여(salary)를 기준으로 직원들의 순위를 매기기
SELECT
    employee_id,
    department_id,
    salary,
    RANK() OVER (PARTITION BY department_id ORDER BY salary DESC) AS rank
FROM
    employees;

```

#### 3. 추가 윈도우 함수
```sql
-- DENSE_RANK(): 동일한 순위가 있어도 다음 순위를 건너뛰지 않는 함수

-- 예제
SELECT
    employee_id,
    department_id,
    salary,
    DENSE_RANK() OVER (PARTITION BY department_id ORDER BY salary DESC) AS dense_rank
FROM
    employees;

-- ROW_NUMBER(): 단순히 각 행에 대해 고유한 순위를 매기는 함수
SELECT
    employee_id,
    department_id,
    salary,
    ROW_NUMBER() OVER (PARTITION BY department_id ORDER BY salary DESC) AS row_number
FROM
    employees;

```