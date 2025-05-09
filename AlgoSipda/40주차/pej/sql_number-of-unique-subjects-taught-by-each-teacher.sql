-- [SQL] https://leetcode.com/problems/number-of-unique-subjects-taught-by-each-teacher/description/

SELECT teacher_id, count(distinct(subject_id)) as cnt FROM TEACHER GROUP BY TEACHER_ID;
