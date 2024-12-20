-- 판매 정보 테이블에 회원 정보를 조인
-- 그 중에서 2021년 가입자만 필터링
-- 년, 월로 그룹
-- 구매 회원수 / 2021 가입 전체 회원수 구하고 반올림
-- 년, 월 오름차순
SELECT
    YEAR(OS.SALES_DATE) YEAR,
    MONTH(OS.SALES_DATE) MONTH,
    COUNT(DISTINCT (UI.USER_ID)) AS PURCHASED_USERS,
    ROUND(
        COUNT(DISTINCT (UI.USER_ID)) / (
            SELECT COUNT(USER_ID)
            FROM USER_INFO
            WHERE
                YEAR(JOINED) = 2021
        ),
        1
    ) PURCHASED_RATIO
FROM ONLINE_SALE OS
    LEFT JOIN USER_INFO UI ON OS.USER_ID = UI.USER_ID
WHERE
    YEAR(UI.JOINED) = 2021
GROUP BY
    YEAR(OS.SALES_DATE),
    MONTH(OS.SALES_DATE)
ORDER BY YEAR(OS.SALES_DATE), MONTH(OS.SALES_DATE);