-- 공간 정보
-- 공간 둘 이상 등록한 사람
-- 공간 정보를 아이디 순으로 조회

SELECT *
FROM PLACES
WHERE HOST_ID IN (
    SELECT HOST_ID
    FROM PLACES
    GROUP BY HOST_ID
    HAVING COUNT(*) > 1
)
ORDER BY ID;
