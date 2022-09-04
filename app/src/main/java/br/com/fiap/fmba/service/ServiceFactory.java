package br.com.fiap.fmba.service;

import br.com.fiap.fmba.service.ordemservico.IOrdemServicoService;
import br.com.fiap.fmba.service.ordemservico.OrdemServicoService;

public final class ServiceFactory {

    public static <T> T getInstace(Class<T> service) {
        if (service.getSimpleName().equals(IOrdemServicoService.class.getSimpleName())) {
            return (T) new OrdemServicoService();
        }
        return null;
    }

}
