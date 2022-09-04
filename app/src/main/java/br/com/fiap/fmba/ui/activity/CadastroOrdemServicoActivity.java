package br.com.fiap.fmba.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.fmba.R;
import br.com.fiap.fmba.usecase.ordemservico.OrdemServico;
import br.com.fiap.fmba.usecase.ordemservico.GestaoOrdemServico;

public class CadastroOrdemServicoActivity extends AbstractActivity {

    private EditText txtVeiculo = null;
    private EditText txtPlaca = null;
    private EditText txtCliente = null;
    private EditText txtDataInicio = null;
    private EditText txtDataFinal = null;

    private GestaoOrdemServico ordemServico = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.fiap.fmba.R.layout.activity_cadastro_ordem_servico);
        super.setTitle("Cadastrar nova Ordem de Serviço");

        this.txtVeiculo = findViewById(R.id.txtNewVeiculo);
        this.txtPlaca = findViewById(R.id.txtNewPlaca);
        this.txtCliente = findViewById(R.id.txtNewCliente);
        this.txtDataInicio = findViewById(R.id.txtNewDataInicio);
        this.txtDataInicio.addTextChangedListener(super.getDateMask("##/##/####", txtDataInicio));
        this.txtDataFinal = findViewById(R.id.txtNewDataFim);
        this.txtDataFinal.addTextChangedListener(super.getDateMask("##/##/####", txtDataFinal));

        this.ordemServico = new GestaoOrdemServico(this);

        this.buildBotaoCadastrar();
    }

    private void buildBotaoCadastrar() {
        findViewById(R.id.btnCadastrarOrdemServico).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdemServico ordem = new OrdemServico();
                ordem.setVeiculo(txtVeiculo.getText().toString());
                ordem.setPlaca(txtPlaca.getText().toString());
                ordem.setNomeCliente(txtCliente.getText().toString());
                ordem.setDataInicio(txtDataInicio.getText().toString());
                ordem.setDataFinal(txtDataFinal.getText().toString());
                try {
                    ordemServico.incluir(ordem);
                } catch (Exception e) {
                    Toast.makeText(CadastroOrdemServicoActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}