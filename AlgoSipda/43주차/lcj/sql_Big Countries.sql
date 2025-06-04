select name, population as population, area
from world
where 1 = 1
    and area >= 3000000 
    or population >= 25000000;
