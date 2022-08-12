package br.com.fiap.fmba.ui.activity;

import android.os.Bundle;

public class ManutencaoOrdemServicoActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.fiap.fmba.R.layout.activity_manutencao_ordem_servico);

        super.setTitle("FMBA - Manutenção");
    }
}