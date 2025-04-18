SELECT YEAR(SALES_DATE) YEAR, MONTH(SALES_DATE) MONTH, U.GENDER, COUNT(DISTINCT U.USER_ID) USERS
FROM ONLINE_SALE O LEFT JOIN USER_INFO U ON O.USER_ID = U.USER_ID
WHERE U.GENDER IS NOT NULL
GROUP BY YEAR, MONTH, U.GENDER
ORDER BY YEAR, MONTH, U.GENDER;