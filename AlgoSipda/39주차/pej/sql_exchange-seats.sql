-- [SQL] exchange-seats https://leetcode.com/problems/exchange-seats

-- 방법 1
-- id가 홀수로 끝나는 경우를 처리하기 위해 SELECT MAX(id) FROM Seat 사용 
SELECT (
    CASE WHEN a.row_id % 2 = 1 AND id = (SELECT MAX(id) FROM Seat) THEN id
    WHEN id % 2 = 1 THEN id + 1
    WHEN id % 2 = 0 THEN id - 1 
    END
) AS id , a.student FROM 
(
    SELECT id, student, ROW_NUMBER() OVER (ORDER BY id) AS row_id
    FROM Seat ORDER BY id
) as a
ORDER BY 
    CASE WHEN a.row_id % 2 = 0 THEN a.row_id - 1
    ELSE a.row_id + 1 
    END;

-- 방법 2
-- LEAD(id) : 다음 id 가져옴 (홀수번째)
-- LAG(id) : 이전 id 가져옴 (짝수번째)

SELECT 
  CASE 
    WHEN rn = total AND total % 2 = 1 THEN id -- 마지막 행이 짝이 없으면 그대로
    WHEN rn % 2 = 1 THEN LEAD(id) OVER (ORDER BY id)
    WHEN rn % 2 = 0 THEN LAG(id) OVER (ORDER BY id)
  END AS id,
  student
FROM (
    SELECT id, student, ROW_NUMBER() OVER (ORDER BY id) AS rn, COUNT(*) OVER () AS total
    FROM Seat
)
ORDER BY id;
