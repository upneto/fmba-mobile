package br.com.fiap.fmba.service;

import android.accounts.AuthenticatorException;
import android.util.Log;

import br.com.fiap.fmba.resources.util.Encryptor;
import br.com.fiap.fmba.service.payload.LoginRequestPayload;
import br.com.fiap.fmba.service.payload.LoginResponsePayload;

public class LoginService extends AbstractService {

    private static final String URL = "https://fmba-backend-gateway.herokuapp.com/login";

    /**
     * Efetua login
     * @return
     * @throws AuthenticatorException
     */
    public LoginResponsePayload login(String usuario, String senha) {
        LoginRequestPayload request = null;
        LoginResponsePayload response = null;
        try {
            request = new LoginRequestPayload(usuario, Encryptor.getInstance().execute(senha));
            response = super.doPost(URL, request, LoginResponsePayload.class);
            token = response.getToken();
        } catch (Exception e) {
            Log.e("LoginService", e.getMessage(), e);
        }
        return response;
    }
}
