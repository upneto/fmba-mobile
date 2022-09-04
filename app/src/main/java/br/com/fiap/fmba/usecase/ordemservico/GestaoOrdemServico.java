package br.com.fiap.fmba.usecase.ordemservico;

import android.content.Context;

import java.util.List;

import br.com.fiap.fmba.ui.activity.adapter.ListaOrdemServicoAdapter;
import br.com.fiap.fmba.usecase.AbstractUseCase;
import br.com.fiap.fmba.usecase.FactoryImpl;

/**
 * Use case de ordem de servico
 */
public class GestaoOrdemServico extends AbstractUseCase<GestaoOrdemServico>  {

    private IOrdemServico ordemServico = null;

    public GestaoOrdemServico(Context context) {
        super(context);
        this.ordemServico = FactoryImpl.newInstanceOf(IOrdemServico.class);
    }

    public void consultarTodos(ListaOrdemServicoAdapter listAdapter) throws Exception {
        this.ordemServico.getAll(listAdapter);
    }

    public void incluir(OrdemServico novaOrdemServico) throws Exception {
        this.ordemServico.insert(novaOrdemServico);
    }

    public void alterar(OrdemServico ordemServico) throws Exception {
        this.ordemServico.update(ordemServico);
    }

    public void excluir(int id) throws Exception {
        this.ordemServico.delete(id);
    }
}
