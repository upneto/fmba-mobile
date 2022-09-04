package br.com.fiap.fmba.dao;

import java.util.AbstractMap;
import java.util.List;

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
    public List<OrdemServico> getAll() throws Exception {
        return super.getAll(OrdemServico.class);
    }

    @Override
    public OrdemServico getById(long id) throws Exception {
        return super.getById(OrdemServico.class,
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
