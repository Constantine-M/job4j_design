insert into roles (name)
			values ('крановщик')
insert into roles (name)
			values ('бригадир')
			
insert into rules (name)
			values ('ломать кабины')
insert into rules (name)
			values ('проверка и приемка работ')

insert into role_rules (role_id, rule_id)
			values (1, 1)
insert into role_rules (role_id, rule_id)
			values (2, 1)
insert into role_rules (role_id, rule_id)
			values (2, 2)
			
insert into users (name, role_id)
			values ('Тихон', 1)
insert into users (name, role_id)
			values ('Святополк', 2)

insert into category (name)
			values ('строительство')

insert into states (in_process)
			values (true)
insert into states (in_process)
			values (false)

insert into item (context, user_id, category_id, state_id)
			values ('Сломать башенку.', 1, 1, 1)
insert into item (context, user_id, category_id, state_id)
			values ('Принять бетономешалку.', 2, 1, 2)

insert into comment (review, item_id)
			values ('Башенку сломали.', 1)
insert into comment (review, item_id)
			values ('Мужчина убежал жаловаться какой-то девушке.', 1)
insert into comment (review, item_id)
			values ('Бетономешалка выехала.', 2)
insert into comment (review, item_id)
			values ('Бетономешалка перевернулась.', 2)

insert into attachs (name, item_id)
			values ('Разрешение на снос башенки.', 1)
insert into attachs (name, item_id)
			values ('Фото с места ДТП.', 2)