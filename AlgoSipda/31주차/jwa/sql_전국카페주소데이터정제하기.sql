SELECT
  SUBSTR(address, 1, INSTR(address, ' ') - 1) AS sido,
  SUBSTR(
    address,
    INSTR(address, ' ') + 1,
    INSTR(SUBSTR(address, INSTR(address, ' ') + 1), ' ') - 1
  ) AS sigungu,
  COUNT(*) as cnt
FROM cafes
GROUP BY sido, sigungu
ORDER BY cnt desc;
