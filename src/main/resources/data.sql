INSERT INTO HISTORICO_PRECO_COMBUSTIVEL(regiao_sigla, estado_sigla, municipio, revenda, cnpj, produto, data_da_coleta, valor_de_compra, valor_de_venda, unidade_de_medida, bandeira)
VALUES ('CO', 'DF', 'BRASILIA', 'AUTO POSTO BRAGA LTDA', 7890, 'DIESEL', '2018-01-03', 0, 3.699, 'R$ / litro', 'IPIRANGA');

INSERT INTO `endereco` (`id`, `bairro`, `cidade`, `complemento`, `estado`, `numero`, `rua`) VALUES
(1, 'oitizeiro', 'João Pessoa', 'casa', 'Paraiba', '111', 'pr jose severino de oliveira'),
(2, 'Cristo', 'João Pessoa', '23', 'PB', '23', 'pr jose severino de oliveira');

INSERT INTO `perfil` (`id`, `cd_perfil`, `descricao`) VALUES
(1, 'ADMIN', 'administrador'),
(2, 'ANALIST', 'analista');

INSERT INTO `pessoa` (`id`, `cpf`, `dt_nascimento`, `email`, `nome`, `telefone`, `id_endereco`) VALUES
(1, '07585463430', '1988-10-23 12:01:07', 'andrefariasti@gmail.com', 'André Luis Farias', '83988631130', 1),
(2, '01313434171', '1988-10-23 12:01:07', 'fracarlaoc@gmail.com', 'Francelly carla farias', '83988631130', 2);

INSERT INTO `usuario` (`id`, `login`, `senha`, `id_pessoa`) VALUES
(1, 'andre', '12345', 1),
(2, 'fran', '12345', 2);

INSERT INTO `usr_perfil` (`id`, `id_perfil`, `id_usuario`) VALUES
(1, 1, 1),
(2, 2, 2);