SELECT CASE
    WHEN SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'PYTHON') > 0
        AND 0 < ANY (SELECT SKILL_CODE & CODE FROM SKILLCODES WHERE CATEGORY = 'FRONT END') THEN 'A'
    WHEN SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME ='C#') > 0 THEN 'B'
    WHEN 0 < ANY (SELECT SKILL_CODE & CODE FROM SKILLCODES WHERE CATEGORY = 'FRONT END') THEN 'C'
    END AS GRADE,
    ID, EMAIL
FROM DEVELOPERS
GROUP BY GRADE, ID, EMAIL
HAVING GRADE IS NOT NULL
ORDER BY GRADE, ID;