package br.com.fiap.fmba.usecase.ordemservico;

import android.content.Context;

import java.util.List;

import br.com.fiap.fmba.resources.exception.ServiceException;
import br.com.fiap.fmba.usecase.AbstractUseCase;
import br.com.fiap.fmba.usecase.FactoryImpl;

public class GestaoOrdemServico extends AbstractUseCase<GestaoOrdemServico>  {

    private IOrdemServico ordemServico = null;

    public GestaoOrdemServico(Context context) {
        super(context);
        this.ordemServico = FactoryImpl.newInstanceOf(IOrdemServico.class);
    }

    public List<OrdemServico> consultarTodos() throws ServiceException {
        try {
            return this.ordemServico.getAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void incluir(OrdemServico novaOrdemServico) {

    }

    public void alterar(OrdemServico ordemServico) {

    }

    public void excluir(int id) {

    }
}
