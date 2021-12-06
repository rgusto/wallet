insert into cliente (nome, cpf) values ('Maria', '11111111111');
insert into cliente (nome, cpf) values ('João', '22222222222');

insert into conta (agencia, numero, titular_id) values ('1234', '123456', 1);
insert into conta (agencia, numero, titular_id) values ('4321', '654321', 2);

insert into lancamento(data_lancamento, tipo_operacao, valor, conta_id) values (current_timestamp, 0, 10, 1);
insert into lancamento(data_lancamento, tipo_operacao, valor, conta_id) values (current_timestamp, 0, 200, 2);

