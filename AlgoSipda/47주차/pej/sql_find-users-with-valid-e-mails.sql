-- [SQL] find-users-with-valid-e-mails/ https://leetcode.com/problems/find-users-with-valid-e-mails/
-- 대소문자 구분에는 COLLATE 를 사용한다 

SELECT user_id, name, mail
FROM Users
WHERE mail COLLATE utf8mb3_bin REGEXP '^[a-zA-Z][a-zA-Z0-9._-]*@leetcode\\.com$';
