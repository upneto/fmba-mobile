package br.com.fiap.fmba.usecase.login;

import android.content.Context;

import br.com.fiap.fmba.resources.exception.LoginException;
import br.com.fiap.fmba.resources.util.Encryptor;
import br.com.fiap.fmba.service.ServiceFactory;
import br.com.fiap.fmba.service.login.ILoginService;
import br.com.fiap.fmba.usecase.AbstractUseCase;

public class Login extends AbstractUseCase<Login> {

    private ILoginService loginService;
    private boolean valid;
    private String usuario, senha;

    private Login(Context context) {
        super(context);
        this.loginService = ServiceFactory.getInstace(ILoginService.class);
    }

    public static Login getInstance(Context context, String usuario, String senha) {
        Login login = new Login(context);
        login.usuario = usuario;
        login.senha = senha;
        return login;
    }

    public Login validaUsuario() {
        this.valid = true;
        if(usuario.isEmpty()) {
            this.valid = false;
        }
        return this;
    }

    public Login validaSenha() {
        this.valid = true;
        if(senha.isEmpty()) {
            this.valid = false;
        }
        return this;
    }

    public Login executa() throws LoginException {
        if(this.valid) {
            final String CRIPTO_SENHA = this.cripto(this.senha);
            this.loginService.execute(this.usuario, CRIPTO_SENHA);
            this.valid = true;
        }
        return this;
    }

    private String cripto(String senha) throws LoginException {
        try {
            return Encryptor.getInstance().execute(senha);
        } catch (Exception e) {
            throw new LoginException(context, "Erro na execução do login! Produre o suporte.", e);
        }
    }

    // ---------- Getters and Setters -------------

    public boolean isValid() {
        return valid;
    }
}
