package br.com.fiap.fmba.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.fiap.fmba.R;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        super.setTitle("FMBA");

        this.mAuth = FirebaseAuth.getInstance();

        this.preparaBotaoLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            this.mAuth = FirebaseAuth.getInstance();
        }
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

                // Efetua login
                try {
                    mAuth.signInWithEmailAndPassword(usuario, senha).addOnCompleteListener(MainActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(MainActivity.this, ListaOrdemServicoActivity.class));
                                    } else {
                                        Toast.makeText(MainActivity.this, "Email ou senha invalidos!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } catch (Exception e) {
                    txtUsuario.setText(new String());
                }

                txtSenha.setText(new String());
            }
        });
    }
}
