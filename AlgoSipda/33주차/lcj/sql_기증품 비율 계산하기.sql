-- 소장품 중 부분 기증품을 포함한 기증품의 비율이 얼마나 되는지 계산
-- 기증품의 비율은 백분율로 계산 -> 소수점 아래 넷째 자리에서 반올림 해 셋째 자리까지 표시
SELECT
  round(
    100.0 * (
      SELECT
        count(DISTINCT artwork_id)
      FROM
        artworks
      WHERE
        credit LIKE '%gift%'
    ) / count(DISTINCT artwork_id),
    3
  ) ratio
FROM
  artworks;