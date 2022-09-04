package br.com.fiap.fmba.ui.activity;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fiap.fmba.R;
import br.com.fiap.fmba.usecase.ordemservico.OrdemServico;
import br.com.fiap.fmba.resources.exception.ServiceException;
import br.com.fiap.fmba.ui.activity.adapter.ListaOrdemServicoAdapter;
import br.com.fiap.fmba.usecase.ordemservico.GestaoOrdemServico;

public class ListaOrdemServicoActivity extends AbstractActivity {

    private ListView listOrdemServico = null;
    private GestaoOrdemServico ordemServico = null;
    private ListaOrdemServicoAdapter adapter;
    List<OrdemServico> ordensServico = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.fiap.fmba.R.layout.activity_lista_ordem_servico);
        super.setTitle("Ordens de Serviço");

        this.listOrdemServico = findViewById(R.id.listOrdemServico);
        this.ordemServico = new GestaoOrdemServico(this);
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
                TextView txtNumeroOrdem = findViewById(R.id.txtFiltro);
                Integer numeroOrdem = txtNumeroOrdem.getText().length() == 0
                        ? null
                        : Integer.parseInt(txtNumeroOrdem.getText().toString());

                preencheLista(numeroOrdem);
            }
        });
    }

    private void preencheLista(Integer numeroOrdemServico) {
        try {
            this.ordensServico = this.ordemServico.consultarTodos();
            if(numeroOrdemServico == null) {
                this.adapter = new ListaOrdemServicoAdapter(this, ordensServico);
            } else {
                OrdemServico ordemEncontrada = null;
                for(OrdemServico item : this.ordensServico) {
                    if(item.getCodigo() == numeroOrdemServico) {
                        ordemEncontrada = item;
                        break;
                    }
                }

                if(ordemEncontrada != null) {
                    this.adapter = new ListaOrdemServicoAdapter(this, Arrays.asList(ordemEncontrada));
                }
                else {
                    Toast.makeText(this, "Ordem de Serviço não encontrada!", Toast.LENGTH_SHORT).show();
                    this.adapter = new ListaOrdemServicoAdapter(this, ordensServico);
                }
            }

            this.listOrdemServico.setAdapter(this.adapter);
            this.listOrdemServico.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    OrdemServico ordemServico = ordensServico.get(position);

                    Intent intent = new Intent(ListaOrdemServicoActivity.this, DetalheOrdemServicoActivity.class);
                    intent.putExtra("NUMERO", ordemServico.getCodigo());
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
}