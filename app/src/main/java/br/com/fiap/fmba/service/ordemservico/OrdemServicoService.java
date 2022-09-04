package br.com.fiap.fmba.service.ordemservico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.fmba.model.OrdemServicoVO;
import br.com.fiap.fmba.resources.exception.ServiceException;
import br.com.fiap.fmba.service.AbstractService;

public class OrdemServicoService extends AbstractService implements IOrdemServicoService {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<OrdemServicoVO> consultarTodos() throws ServiceException {

        List<OrdemServicoVO> lista = new ArrayList<>();
        for(long i = 1; i <= 10; i++) {
            OrdemServicoVO ordem = new OrdemServicoVO();
            ordem.setCodigo(i);
            ordem.setDataInicio("01/01/2000");
            ordem.setDataFinal(SDF.format(new Date()));
            ordem.setNomeCliente("Ulisses Pereida da Silva");
            ordem.setVeiculo("Ford New Fiesta 2016");
            ordem.setPlaca("XPTO1234");

            lista.add(ordem);
        }

        return lista;
    }
}
