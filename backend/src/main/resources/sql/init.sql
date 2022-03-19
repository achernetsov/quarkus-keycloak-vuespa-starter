create  table robots (
	id serial primary key,
	name varchar(30),
	owner varchar(30)
)

create index robot_owner_ids on robots (owner)

CREATE UNIQUE INDEX robot_name_by_owner ON robots (name, owner)

insert into robots (name, owner) values ('robot2', 'alice')