package br.com.fiap.fmba.ui.activity;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fmba.R;
import br.com.fiap.fmba.bin.usecase.model.OrdemServicoVO;
import br.com.fiap.fmba.bin.usecase.ordemservico.OrdemServico;
import br.com.fiap.fmba.ui.activity.adapter.ListaOrdemServicoAdapter;

public class ListaOrdemServicoActivity extends AbstractActivity {

    private ListView listOrdemServico = null;
    private OrdemServico ordemServico = null;
    private ListaOrdemServicoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.fiap.fmba.R.layout.activity_lista_ordem_servico);
        super.setTitle("Ordens de Serviço");

        this.listOrdemServico = findViewById(R.id.listOrdemServico);
        this.ordemServico = new OrdemServico(this);
        this.adapter = new ListaOrdemServicoAdapter(this, new ArrayList<OrdemServicoVO>());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        this.preencheLista(null);
        this.preparaBotaoFiltro();
    }
    private void preparaBotaoFiltro() {
        findViewById(R.id.btnFiltro).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView txtPlaca = findViewById(R.id.txtFiltro);
                Integer codigo = txtPlaca.getText().toString() != null && !txtPlaca.getText().toString().isEmpty()
                        ? Integer.parseInt(txtPlaca.getText().toString())
                        : 0;
                // Executa consulta
                (new PesquisarAsyncTask()).execute(codigo);
            }
        });
    }

    private void preencheLista(Integer codigo) {
        try {
            if(codigo != null) {
                this.ordemServico.consultarPor(codigo, this.adapter);
            }
            else {
                this.ordemServico.consultarLista(this.adapter);
            }
            this.listOrdemServico.setAdapter(this.adapter);
            this.listOrdemServico.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    OrdemServicoVO ordemServico = adapter.getItem(position);

                    Intent intent = new Intent(ListaOrdemServicoActivity.this, DetalheOrdemServicoActivity.class);
                    intent.putExtra("VEICULO", ordemServico.getVeiculo());
                    intent.putExtra("PLACA", ordemServico.getPlaca());
                    intent.putExtra("CLIENTE", ordemServico.getNomeCliente());
                    intent.putExtra("DATA_INICIO", ordemServico.getDataInicio());
                    intent.putExtra("DATA_FINAL", ordemServico.getDataFinal());

                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Executa login assincrono
     */
    private class PesquisarAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            Integer codigo = (integers != null || integers.length > 0) ? integers[0] : null;
            try {
                if(codigo != 0) {
                    ordemServico.consultarPor(codigo, adapter);
                }
                else {
                    ordemServico.consultarLista(adapter);
                }
            } catch (Exception e) { }
            return null;
        }
    }
}