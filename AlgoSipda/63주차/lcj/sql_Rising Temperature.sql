# 이전 날보다 더 높은 기온인 날 뽑기
select w1.id Id
from weather w1
join weather w2
on 
    datediff(w1.recordDate, w2.recordDate) = 1
    and w1.temperature > w2.temperature;