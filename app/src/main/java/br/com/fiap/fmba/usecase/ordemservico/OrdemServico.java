package br.com.fiap.fmba.usecase.ordemservico;

import android.content.Context;

import java.util.List;

import br.com.fiap.fmba.model.OrdemServicoVO;
import br.com.fiap.fmba.resources.exception.ServiceException;
import br.com.fiap.fmba.service.ServiceFactory;
import br.com.fiap.fmba.service.ordemservico.IOrdemServicoService;
import br.com.fiap.fmba.usecase.AbstractUseCase;

public class OrdemServico extends AbstractUseCase<OrdemServico>  {

    private IOrdemServicoService ordemServicoService = null;

    public OrdemServico(Context context) {
        super(context);
        this.ordemServicoService = ServiceFactory.getInstace(IOrdemServicoService.class);
    }

    public List<OrdemServicoVO> consultarTodos() throws ServiceException {
        return this.ordemServicoService.consultarTodos();
    }

    public void incluir(OrdemServicoVO novaOrdemServico) {

    }

    public void excluir(int id) {

    }
}
