## Programmers 조건에 부합하는 중고거래 상태 조회하기

### 🛠️ 문제 🛠️

```
USED_GOODS_BOARD 테이블에서 2022년 10월 5일에 등록된 중고거래 게시물의 게시글 ID, 작성자 ID, 게시글 제목, 가격, 거래상태를 조회하는 SQL문을 작성해주세요. 거래상태가 SALE 이면 판매중, RESERVED이면 예약중, DONE이면 거래완료 분류하여 출력해주시고, 결과는 게시글 ID를 기준으로 내림차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/164672)

<br/>

### 💡 코드 💡

```sql
-- 2022년 10월 5일에 등록된 중고거래 게시물
-- 게시글 ID, 작성자 ID, 게시글 제목, 가격, 거래 상태
-- 거래상태 => SALE: 판매중, RESERVED: 예약중, DONE: 거래 완료
-- 게시글 ID 내림차순
SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, CASE 
                                                WHEN STATUS = "SALE" THEN "판매중"
                                                WHEN STATUS = "RESERVED" THEN "예약중"
                                                WHEN STATUS = "DONE" THEN "거래완료"
                                        END AS STATUS
FROM USED_GOODS_BOARD
WHERE CREATED_DATE = "2022-10-05"
ORDER BY BOARD_ID DESC;
```

<br/>

### 📙 Mysql 개념 📙

[없음]
