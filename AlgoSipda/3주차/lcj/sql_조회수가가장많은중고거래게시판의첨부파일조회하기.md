## Programmers 조회수가 가장 많은 중고거래 게시판의 첨부파일 조회하기
### 🛠️ 문제 🛠️
```
USED_GOODS_BOARD와 USED_GOODS_FILE 테이블에서 조회수가 가장 높은 중고거래 게시물에 대한 첨부파일 경로를 조회하는 SQL문을 작성해주세요. 첨부파일 경로는 FILE ID를 기준으로 내림차순 정렬해주세요. 기본적인 파일경로는 /home/grep/src/ 이며, 게시글 ID를 기준으로 디렉토리가 구분되고, 파일이름은 파일 ID, 파일 이름, 파일 확장자로 구성되도록 출력해주세요. 조회수가 가장 높은 게시물은 하나만 존재합니다.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/164671)

<br/>

### 💡 코드 💡
```sql
SELECT (CONCAT("/home/grep/src/", UGB.BOARD_ID,"/",UGF.FILE_ID,UGF.FILE_NAME ,UGF.FILE_EXT)) AS FILE_PATH
FROM USED_GOODS_BOARD UGB
JOIN USED_GOODS_FILE UGF
ON UGB.BOARD_ID = UGF.BOARD_ID
WHERE VIEWS = (SELECT MAX(VIEWS) FROM USED_GOODS_BOARD LIMIT 1)
ORDER BY UGF.FILE_ID DESC;
```

<br/>

### 📙 Mysql 개념 📙
[없음]