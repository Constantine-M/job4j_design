create table plankton(
	id serial primary key,
	group_name varchar(100),
	dwelling_depth bigint,
	features text
);

insert into 
	plankton (
		group_name, 
		dwelling_depth, 
		features) 
	values (
		'Фитопланктон', 
		75 , 
		'Фитопланктон поглощает большое количество углекислого газа в процессе фотосинтеза и вносит почти 50% кислорода, который мы вдыхаем в атмосферу. Мертвый планктон образует слой осадка, окаменевший, он производит желаемую нефть.')

update 
	plankton 
		set 
		group_name = 'Офисный', 
		dwelling_depth = 0, 
		features = 'Обитает в теплых, сухих помещениях, рядом с кулером и кофемашиной. Приносит пользу только за деньги. Периодически подвергается процессу выгорания'

delete from plankton