create view show_students_with_2_or_more_books
    as select s.name as student, count(a.name), a.name as author from sqlview.students as s
        join sqlview.orders o on s.id = o.student_id
        join sqlview.books b on o.book_id = b.id
        join sqlview.authors a on b.author_id = a.id
        group by (s.name, a.name) having count(a.name) >= 2;

select * from show_students_with_2_or_more_books;