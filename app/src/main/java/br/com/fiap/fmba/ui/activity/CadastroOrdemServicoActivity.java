package br.com.fiap.fmba.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.fmba.R;
import br.com.fiap.fmba.bin.service.LoginService;
import br.com.fiap.fmba.bin.service.payload.LoginRequestPayload;
import br.com.fiap.fmba.bin.service.payload.LoginResponsePayload;
import br.com.fiap.fmba.bin.usecase.model.OrdemServicoVO;
import br.com.fiap.fmba.bin.usecase.ordemservico.OrdemServico;

public class CadastroOrdemServicoActivity extends AbstractActivity {

    private EditText txtVeiculo = null;
    private EditText txtPlaca = null;
    private EditText txtCliente = null;
    private EditText txtDataInicio = null;
    private EditText txtDataFinal = null;

    private OrdemServico ordemServico = null;

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

        this.ordemServico = new OrdemServico(this);

        this.buildBotaoCadastrar();
    }

    private void buildBotaoCadastrar() {
        findViewById(R.id.btnCadastrarOrdemServico).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdemServicoVO ordem = new OrdemServicoVO();
                ordem.setVeiculo(txtVeiculo.getText().toString());
                ordem.setPlaca(txtPlaca.getText().toString());
                ordem.setNomeCliente(txtCliente.getText().toString());
                ordem.setDataInicio(txtDataInicio.getText().toString());
                ordem.setDataFinal(txtDataFinal.getText().toString());

                // Executa
                (new CadastroAsyncTask()).execute(ordem);
            }
        });
    }

    /**
     * Executa login assincrono
     */
    private class CadastroAsyncTask extends AsyncTask<OrdemServicoVO, Integer, OrdemServicoVO> {

        private OrdemServico ordemServico = new OrdemServico(CadastroOrdemServicoActivity.this);

        @Override
        protected OrdemServicoVO doInBackground(OrdemServicoVO... ordemServicoVOS) {
            Log.i("AsyncTask", "AsyncTask Thread: " + Thread.currentThread().getName());
            OrdemServicoVO request = ordemServicoVOS[0];
            try {
                this.ordemServico.inserir(request);
            } catch (Exception e) {
                Toast.makeText(CadastroOrdemServicoActivity.this, "Não foi possível realizar a operação!", Toast.LENGTH_LONG).show();
            }
            return request;
        }

        @Override
        protected void onPostExecute(OrdemServicoVO ordemServicoVO) {
            if(ordemServicoVO != null) {
                startActivity(new Intent(CadastroOrdemServicoActivity.this, ListaOrdemServicoActivity.class));
                Toast.makeText(CadastroOrdemServicoActivity.this, "Operação realizada com sucesso!", Toast.LENGTH_LONG).show();
            }
        }
    }
}