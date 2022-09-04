package br.com.fiap.fmba.usecase;

import android.content.Context;

import br.com.fiap.fmba.dao.OrdemServicoDao;
import br.com.fiap.fmba.usecase.ordemservico.IOrdemServico;

public final class FactoryImpl {

    /**
     * Retorna instancia correta
     * @param classInterface
     * @param <T>
     * @return
     */
    public static final <T> T newInstanceOf(final Context context, Class<T> classInterface) {
        if(classInterface.getSimpleName().equals(IOrdemServico.class.getSimpleName())) {
            return (T) new OrdemServicoDao(context);
        }
        return null;
    }

}
