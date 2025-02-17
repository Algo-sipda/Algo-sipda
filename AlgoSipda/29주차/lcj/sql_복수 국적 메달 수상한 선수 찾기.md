## Programmers 복수 국적 메달 수상한 선수 찾기


### 🛠️ 문제 🛠️

```
도시와 시즌 정보가 기록되어 있습니다. records 테이블에는 역대 올림픽 참가 선수들의 신체 정보와 획득한 메달 정보가 기록되어 있습니다. 이 테이블은 다른 테이블과 매핑할 수 있는 ID 정보도 가지고 있습니다. teams 테이블에는 국가 정보가 기록되어 있습니다.

2000년 이후의 메달 수상 기록만 고려했을 때, 메달을 수상한 올림픽 참가 선수 중 2개 이상의 국적으로 메달을 수상한 기록이 있는 선수의 이름을 조회하는 쿼리를 작성해주세요. 조회된 선수의 이름은 오름차순으로 정렬되어 있어야 합니다.

예를 들어, Viktor An 선수는 2006년 토리노에서 열린 동계 올림픽과 2014년 소치에서 열린 동계 올림픽에서 메달을 수상했는데, 2006년에는 대한민국(KOR) 국적으로 메달을 수상했고 2014년에는 러시아(RUS) 국적으로 메달을 수상했습니다.
```

[문제 바로가기](https://solvesql.com/problems/multiple-medalist/)

<br/>

### 💡 코드 💡

```sql
select distinct
  a.name
from
  records r
  join athletes a on a.id = r.athlete_id
  join games g on r.game_id = g.id
  join teams t on r.team_id = t.id
where
  r.medal is not null
  and g.year >= 2000
GROUP BY
  r.athlete_id
HAVING
  COUNT(distinct t.team) > 1
order by
  a.name;
```

<br/>

### 📙 개념 📙

[없음]
