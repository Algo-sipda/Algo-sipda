# 유효한 일련번호 규칙
# 1. SN으로 시작해야 함(대소문자 구분 -> regexp_like에서 맨 마지막에 'c'넣기)
# 2. 숫자만 정확하게 뒤에 나옴
# 3. 하이픈 뒤에 4 숫자가 정확히 나와야함
# -> regexp에서 \\b는 단어의 경계를 구분하는 부분.
# product_id 오름차순 정렬
select
    *
from
    products
where
    REGEXP_LIKE(description, '\\bSN[0-9]{4}-[0-9]{4}\\b', 'c')
order by
    product_id;