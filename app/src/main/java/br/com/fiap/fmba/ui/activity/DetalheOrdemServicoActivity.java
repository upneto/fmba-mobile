package br.com.fiap.fmba.ui.activity;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import br.com.fiap.fmba.R;
import br.com.fiap.fmba.bin.usecase.ordemservico.OrdemServico;

public class DetalheOrdemServicoActivity extends AbstractActivity {

    private TextView txtPlaca = null;
    private TextView txtVeiculo = null;
    private TextView txtCliente = null;
    private TextView txtDataInicio = null;
    private TextView txtDataFinal = null;

    private OrdemServico ordemServico = null;

    private AlertDialog.Builder confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_ordem_servico);
        setTitle("Detalhe da Ordem de Serviço");

        this.txtPlaca = findViewById(R.id.txtPlaca);
        this.txtVeiculo = findViewById(R.id.txtVeiculo);
        this.txtCliente = findViewById(R.id.txtCliente);
        this.txtDataInicio = findViewById(R.id.txtDataInicio);
        this.txtDataFinal = findViewById(R.id.txtDataFinal);

        this.ordemServico = new OrdemServico(this);

        this.buildDeleteButton();
        this.buildConfirmDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        String veiculo = (String) extras.get("VEICULO");
        String placa = (String) extras.get("PLACA");
        String cliente = (String) extras.get("CLIENTE");
        String data_inicio = (String) extras.get("DATA_INICIO");
        String data_final = (String) extras.get("DATA_FINAL");

        this.txtPlaca.setText(placa);
        this.txtVeiculo.setText(veiculo);
        this.txtCliente.setText(cliente);
        this.txtDataInicio.setText(data_inicio);
        this.txtDataFinal.setText(data_final);
    }

    private void buildDeleteButton() {
        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.show();
            }
        });
    }

    private void buildConfirmDialog() {
        this.confirmDialog = new AlertDialog.Builder(this)
                .setTitle("Exclusão de Ordem de Serviço")
                .setMessage("Deseja excluir a Ordem se Serviço?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO FIXIT
//                        try {
//                            ordemServico.excluir(txtPlaca.getText().toString());
//                        } catch (Exception e) {
//                            Toast.makeText(DetalheOrdemServicoActivity.this,
//                                    e.getMessage(),
//                                    Toast.LENGTH_SHORT).show();
//                        }
                        DetalheOrdemServicoActivity.this.finish();
                    }
                }).setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
    }
}