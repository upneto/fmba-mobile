package br.com.fiap.fmba.service.ordemservico;

import java.util.List;

import br.com.fiap.fmba.model.OrdemServicoVO;
import br.com.fiap.fmba.resources.exception.ServiceException;

public interface IOrdemServicoService {

    List<OrdemServicoVO> consultarTodos() throws ServiceException;
}
