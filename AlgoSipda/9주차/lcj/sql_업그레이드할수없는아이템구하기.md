## Programmers 업그레이드 할 수 없는 아이템 구하기

### 🛠️ 문제 🛠️

```
더 이상 업그레이드할 수 없는 아이템의 아이템 ID(ITEM_ID), 아이템 명(ITEM_NAME), 아이템의 희귀도(RARITY)를 출력하는 SQL 문을 작성해 주세요. 이때 결과는 아이템 ID를 기준으로 내림차순 정렬해 주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/273712)

<br/>

### 💡 코드 💡

```sql
SELECT ITEM_ID,ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE 1=1
    AND ITEM_ID NOT IN (SELECT DISTINCT PARENT_ITEM_ID
                       FROM ITEM_TREE
                       WHERE PARENT_ITEM_ID IS NOT NULL)
ORDER BY ITEM_ID DESC;
```

<br/>

### 📙 Mysql 개념 📙

[없음]
