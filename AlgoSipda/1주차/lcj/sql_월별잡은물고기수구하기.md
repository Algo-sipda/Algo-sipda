## Programmers 대장균의 크기에 따라 분류하기
### 🛠️ 문제 🛠️
```
월별 잡은 물고기의 수와 월을 출력하는 SQL문을 작성해주세요.

잡은 물고기 수 컬럼명은 FISH_COUNT, 월 컬럼명은 MONTH로 해주세요.
결과는 월을 기준으로 오름차순 정렬해주세요.
단, 월은 숫자형태 (1~12) 로 출력하며 9 이하의 숫자는 두 자리로 출력하지 않습니다. 잡은 물고기가 없는 월은 출력하지 않습니다.
```

### 💡 코드 💡
```sql
SELECT COUNT(FISH_TYPE) AS FISH_COUNT, MONTH(TIME) AS MONTH
FROM FISH_INFO
GROUP BY MONTH
ORDER BY MONTH;
```
<br/>

### 📙 Mysql 개념 📙
1. GROUP BY란?
- 같은 값을 가진 행을 그룹짓는 SQL 명령어
- GROUP BY는 COUNT(), MAX(), MIN(), SUM(), AVG() 등 집계 함수와 함께 사용된다.
- GROUP BY절은 각 그룹의 하나만을 리턴한다.
- FROM절과 WHERE절 뒤에 위치한다.
- SQL 7.0이후 부터는 GROUP BY를 할 때 비용을 따져서 정렬하여 그룹핑을 하는 경우도 있고, Hash Match를 통해서 정렬이 이루어지지 않을 수도 있다. 따라서 GROUP BY를 할 때 반드시 ORDER BY는 되지 않으므로 명시적으로 정렬을 원한다면 ORDER BY를 사용하여야 하고 ORDER BY를 사용 하므로써 추가 비용이 발생을 하게 된다.
- GROUP BY 실행 순서
    - FROM -> WHERE -> GROUP BY -> SELECT -> DISTINCT -> ORDER BY -> LIMIT
- SELECT 문에 있는 모든 열은 집계 함수가 되거나 GROUP BY 절에 나타나야 한다. GROUP BY 절을 사용하는데 만약 SELECT 문에 집계 함수를 사용하지 않거나 GROUP BY 절에 언급되지 않은 열이 존재한다면 오류가 발생한다.
