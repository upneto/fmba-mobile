package br.com.fiap.fmba.usecase.ordemservico;

import android.content.Context;

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
        this.ordemServico = FactoryImpl.newInstanceOf(context, IOrdemServico.class);
    }

    public void consultarTodos(ListaOrdemServicoAdapter listAdapter) throws Exception {
        this.ordemServico.getAll(listAdapter);
    }

    public void consultarPorPlaca(String placa, ListaOrdemServicoAdapter listAdapter) throws Exception {
        this.ordemServico.getById(placa, listAdapter);
    }

    public void incluir(OrdemServico novaOrdemServico) throws Exception {
        this.ordemServico.insert(novaOrdemServico);
    }

    public void alterar(OrdemServico ordemServico) throws Exception {
        this.ordemServico.update(ordemServico);
    }

    public void excluir(String placa) throws Exception {
        this.ordemServico.delete(placa);
    }
}
