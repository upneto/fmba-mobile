package br.com.fiap.fmba.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.fiap.fmba.R;
import br.com.fiap.fmba.bin.service.LoginService;
import br.com.fiap.fmba.bin.service.payload.LoginRequestPayload;
import br.com.fiap.fmba.bin.service.payload.LoginResponsePayload;

/**
 * Active inicial utilizada comm tela de autenticação do usuário
 */
public class MainActivity extends AppCompatActivity {

    private TextView txtUsuario = null;
    private TextView txtSenha = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        super.setTitle("FMBA");

        this.txtUsuario = findViewById(R.id.txtUsuario);
        this.txtSenha = findViewById(R.id.txtSenha);

        this.preparaBotaoLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView txtSenha = findViewById(R.id.txtSenha);
        txtSenha.setText(new String());
    }

    /**
     * Prepara as regras de execução do botão de efetivação do login
     */
    private void preparaBotaoLogin() {
        super.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String USUARIO = txtUsuario.getText().toString();
                final String SENHA = txtSenha.getText().toString();

                // Efetua login
                (new LoginAsyncTask()).execute(new LoginRequestPayload(USUARIO, SENHA));
            }
        });
    }

    /**
     * Executa login assincrono
     */
    private class LoginAsyncTask extends AsyncTask<LoginRequestPayload, Integer, LoginResponsePayload> {

        private LoginService loginService = new LoginService();

        @Override
        protected LoginResponsePayload doInBackground(LoginRequestPayload... loginRequestPayloads) {
            Log.i("AsyncTask", "AsyncTask Thread: " + Thread.currentThread().getName());
            LoginRequestPayload request = loginRequestPayloads[0];
            return loginService.login(request.getUsuario(), request.getSenha());
        }

        @Override
        protected void onPostExecute(LoginResponsePayload loginResponsePayload) {
            if(loginResponsePayload != null) {
                txtSenha.setText(new String());
                startActivity(new Intent(MainActivity.this, ListaOrdemServicoActivity.class));
            }
            else {
                Toast.makeText(MainActivity.this, "Usuário e (ou) senha invalidos!", Toast.LENGTH_SHORT).show();
                txtUsuario.setError("Campo 'usuário' inválido!");
                txtSenha.setError("Campo 'senha' inválido!");
            }
        }
    }
}
