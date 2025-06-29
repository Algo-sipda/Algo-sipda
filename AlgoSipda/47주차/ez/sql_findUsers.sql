SELECT *
FROM Users
WHERE REGEXP_LIKE(mail,'^[a-zA-Z][a-zA-Z0-9_.-]*@leetcode\\.com$','c');
-- 'c': 대소문자 구분