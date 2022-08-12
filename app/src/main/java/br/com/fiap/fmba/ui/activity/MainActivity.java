package br.com.fiap.fmba.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.fiap.fmba.R;
import br.com.fiap.fmba.resources.exception.LoginException;
import br.com.fiap.fmba.usecase.login.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        super.setTitle("FMBA");

        this.preparaBotaoLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView txtSenha = findViewById(R.id.txtSenha);
        txtSenha.setText(new String());
    }

    private void preparaBotaoLogin() {
        super.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView txtUsuario = findViewById(R.id.txtUsuario);
                TextView txtSenha = findViewById(R.id.txtSenha);

                String usuario = txtUsuario.getText().toString();
                String senha = txtSenha.getText().toString();

                Login login = Login.getInstance(MainActivity.this, usuario, senha);

                // Valida usuario digitado
                if(!login.validaUsuario().isValid()) {
                    txtUsuario.setError("Usuário Inválido");
                    txtUsuario.setText(new String());
                }

                // Valida senha digitada
                if(!login.validaSenha().isValid()) {
                    txtSenha.setError("Senha Inválida");
                }

                // Efetua login
                try {
                    if(login.executa().isValid()) {
                        startActivity(new Intent(MainActivity.this, ListaOrdemServicoActivity.class));
                    }
                } catch (LoginException e) {
                    txtUsuario.setText(new String());
                }

                txtSenha.setText(new String());
            }
        });
    }
}
