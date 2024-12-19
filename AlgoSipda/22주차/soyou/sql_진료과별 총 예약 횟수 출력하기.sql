SELECT mcdp_cd as "진료과 코드", COUNT(*) as "5월예약건수"
FROM appointment
WHERE DATE_FORMAT(apnt_ymd, "%Y%m") = "202205"
GROUP BY mcdp_cd
ORDER BY 2, 1