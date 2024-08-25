## Programmers ROOT 아이템 구하기

### 🛠️ 문제 🛠️

```
ROOT 아이템을 찾아 아이템 ID(ITEM_ID), 아이템 명(ITEM_NAME)을 출력하는 SQL문을 작성해 주세요. 이때, 결과는 아이템 ID를 기준으로 오름차순 정렬해 주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/273710)

<br/>

### 💡 코드 💡

```sql
-- root 아이템을 찾아 작성(ITEM_ID, ITEM_NAME)
-- 아이템 ID 기준으로 오름차순 정렬
SELECT I.ITEM_ID, I.ITEM_NAME
FROM ITEM_INFO I, ITEM_TREE T
WHERE 1=1
    AND I.ITEM_ID = T.ITEM_ID
    AND PARENT_ITEM_ID IS NULL
ORDER BY I.ITEM_ID;
```

<br/>

### 📙 Mysql 개념 📙

[없음]
