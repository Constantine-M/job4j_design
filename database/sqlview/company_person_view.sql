create view show_max_count_persons
as SELECT COUNT(*) max_cnt
   FROM sqlexam.person p
   GROUP BY p.company_id
   ORDER BY max_cnt DESC
   LIMIT 1;

SELECT c.name as Компания, count(*) as Количество_важных_персон
FROM sqlexam.person as p
         JOIN sqlexam.company as c
              ON p.company_id = c.id
GROUP BY c.id
HAVING count(*) =
       (SELECT * FROM show_max_count_persons);