SELECT c.name as Компания, count(*) as Количество_важных_персон
FROM sqlexam.person as p
         JOIN sqlexam.company as c
              ON p.company_id = c.id
GROUP BY c.id
HAVING count(*) =
       (SELECT COUNT(*) max_cnt
        FROM sqlexam.person p
        GROUP BY p.company_id
        ORDER BY max_cnt DESC
        LIMIT 1);