package br.com.fiap.fmba.usecase.ordemservico;

import java.util.List;

import br.com.fiap.fmba.ui.activity.adapter.ListaOrdemServicoAdapter;

public interface IOrdemServico {

    void getAll(ListaOrdemServicoAdapter listAdapter) throws Exception;

    void getById(long id) throws Exception;

    void insert(OrdemServico ordemServico) throws Exception;

    void update(OrdemServico ordemServico) throws Exception;

    void delete(long id) throws Exception;
}
