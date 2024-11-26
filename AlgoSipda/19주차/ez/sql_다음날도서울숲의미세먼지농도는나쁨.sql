with
    dust as (
        select
            measured_at as today,
            lead(measured_at) over (
                order by
                    measured_at
                ) as next_day,
            pm10,
            lead(pm10) over (
                order by
                    measured_at
                ) as next_pm10
        from
            measurements
    )

select
    today,
    next_day,
    pm10,
    next_pm10
from
    dust
where
        pm10 < next_pm10