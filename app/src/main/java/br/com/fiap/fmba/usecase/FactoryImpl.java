package br.com.fiap.fmba.usecase;

import br.com.fiap.fmba.dao.OrdemServicoDao;
import br.com.fiap.fmba.usecase.ordemservico.IOrdemServico;

public final class FactoryImpl {

    /**
     * Retorna instancia correta
     * @param classInterface
     * @param <T>
     * @return
     */
    public static final <T> T newInstanceOf(Class<T> classInterface) {
        if(classInterface.getSimpleName().equals(IOrdemServico.class.getSimpleName())) {
            return (T) new OrdemServicoDao();
        }
        return null;
    }

}
