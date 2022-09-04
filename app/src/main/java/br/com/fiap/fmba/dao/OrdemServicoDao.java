package br.com.fiap.fmba.dao;

import android.widget.ArrayAdapter;

import java.util.AbstractMap;
import java.util.List;

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
    public void getById(long id) throws Exception {
        super.getById(OrdemServico.class,
                new AbstractMap.SimpleEntry<String, Object>("codigo", id));
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
    public void delete(long id) throws Exception {
        super.delete(new AbstractMap.SimpleEntry<String, Object>("codigo", id));
    }
}
