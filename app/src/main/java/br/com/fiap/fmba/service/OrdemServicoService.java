package br.com.fiap.fmba.service;

import java.util.List;

import br.com.fiap.fmba.dao.AbstractDao;
import br.com.fiap.fmba.usecase.ordemservico.IOrdemServico;
import br.com.fiap.fmba.usecase.ordemservico.OrdemServico;

public class OrdemServicoService extends AbstractService implements IOrdemServico {

    @Override
    public List<OrdemServico> getAll() throws Exception {
        return null;
    }

    @Override
    public OrdemServico getById(long id) throws Exception {
        return null;
    }

    @Override
    public void insert(OrdemServico ordemServico) throws Exception {

    }

    @Override
    public void update(OrdemServico ordemServico) throws Exception {

    }

    @Override
    public void delete(long id) throws Exception {

    }
}