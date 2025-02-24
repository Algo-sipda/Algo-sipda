-- 공간 정보
-- 공간 둘 이상 등록한 사람
-- 공간 정보를 아이디 순으로 조히

-- 호스트별로 등록한 개수 카운트
-- 2개 이상인 사람 필터링

SELECT *
FROM PLACES
WHERE HOST_ID IN (
    SELECT HOST_ID
    FROM PLACES
    GROUP BY HOST_ID
    HAVING COUNT(*) > 1
)
ORDER BY ID;