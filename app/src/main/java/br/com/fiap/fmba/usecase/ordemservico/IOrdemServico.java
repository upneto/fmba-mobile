package br.com.fiap.fmba.usecase.ordemservico;

import java.util.List;

public interface IOrdemServico {

    List<OrdemServico> getAll() throws Exception;

    OrdemServico getById(long id) throws Exception;

    void insert(OrdemServico ordemServico) throws Exception;

    void update(OrdemServico ordemServico) throws Exception;

    void delete(long id) throws Exception;
}
