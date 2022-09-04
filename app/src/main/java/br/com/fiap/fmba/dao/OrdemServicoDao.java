package br.com.fiap.fmba.dao;

import br.com.fiap.fmba.ui.activity.adapter.ListaOrdemServicoAdapter;
import br.com.fiap.fmba.usecase.ordemservico.IOrdemServico;
import br.com.fiap.fmba.usecase.ordemservico.OrdemServico;

public class OrdemServicoDao extends AbstractDao implements IOrdemServico {

    public OrdemServicoDao() {
        super("ORDEM_SERVICO");
    }

    public OrdemServicoDao(String DATABASE) {
        super(DATABASE);
    }

    @Override
    public void getAll(ListaOrdemServicoAdapter listAdapter) throws Exception {
        super.getAll(OrdemServico.class, listAdapter);
    }

    @Override
    public void getById(String placa, ListaOrdemServicoAdapter listAdapter) throws Exception {
        super.getById(new OrdemServico(placa), listAdapter);
    }

    @Override
    public void insert(OrdemServico ordemServico) throws Exception {
        super.insert(ordemServico);
    }

    @Override
    public void update(OrdemServico ordemServico) throws Exception {
        super.update(ordemServico);
    }

    @Override
    public void delete(String placa) throws Exception {
        super.delete(new OrdemServico(placa));
    }
}
