-- [SQL] 175. Combine Two Tables https://leetcode.com/problems/combine-two-tables/description/

select p.lastName, p.firstName, a.city, a.state 
from Person p left join Address a on p.personId = a.personId;