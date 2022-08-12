package br.com.fiap.fmba.service.login;

import br.com.fiap.fmba.resources.exception.LoginException;

public interface ILoginService {

    void execute(String usuario, String Senha) throws LoginException;

}
