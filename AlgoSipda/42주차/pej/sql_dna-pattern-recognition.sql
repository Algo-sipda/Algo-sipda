-- [SQL] dna-pattern-recognition https://leetcode.com/problems/dna-pattern-recognition/

-- 방법1 : case when 이랑 like 
select sample_id, dna_sequence, species, 
case when dna_sequence like 'ATG%' then 1 else 0 end as has_start, 
case when dna_sequence like '%TAA' or dna_sequence like '%TAG' or dna_sequence like '%TGA' then 1 else 0 end as has_stop, 
case when dna_sequence like '%ATAT%' then 1 else 0 end as has_atat, 
case when dna_sequence like '%GGG%' then 1 else 0 end as has_ggg
from Samples
order by sample_id;

-- 방법2 : REGEXP 정규 표현식 
-- SELECT
--     *,
--     dna_sequence REGEXP '^ATG' AS has_start, -- 문자열의 시작 
--     dna_sequence REGEXP 'TAA$|TAG$|TGA$' AS has_stop, -- 문자열의 끝
--     dna_sequence REGEXP 'ATAT' AS has_atat,
--     dna_sequence REGEXP 'GGG' AS has_ggg
-- FROM samples
-- ORDER BY sample_id