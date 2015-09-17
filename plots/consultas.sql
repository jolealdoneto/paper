select offloading, maxt, mint, maxb, minb from (
	select 'YES' as offloading, max(time) as maxt, min(time) as mint, max(battery) as maxb, min(battery) as minb from utility
UNION ALL
	select 'NO' as offloading, max(time) as maxt, min(time) as mint, max(battery) as maxb, min(battery) as minb from no_offloading
)

select datediff('SECOND', parsedatetime('12:43:09', 'HH:mm:ss'), parsedatetime(time, 'HH:mm:ss')) as timestamp, battery-83 as battery , url from no_offloading order by time asc

select datediff('SECOND', parsedatetime('18:41:06', 'HH:mm:ss'), parsedatetime(time, 'HH:mm:ss')) as timestamp, battery-85 as battery, url from utility order by time asc