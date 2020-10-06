--
-- Estrutura da tabela `tb_produtos`
--

CREATE TABLE `tb_servicos` (
  `id_servico` bigint(20) NOT NULL,
  `desc_servico` varchar(255) DEFAULT NULL,
  `nm_servico` varchar(255) NOT NULL,
  `vl_servico` decimal(19,2) NOT NULL,
  `qt_servico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Índices para tabela `tb_produtos`
--
ALTER TABLE `tb_servicos`
  ADD PRIMARY KEY (`id_servicos`);



--
-- AUTO_INCREMENT de tabela `tb_produtos`
--
ALTER TABLE `tb_servicos`
  MODIFY `id_servico` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;