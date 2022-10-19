package br.com.fiap.fmba.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.com.fiap.fmba.R;
import br.com.fiap.fmba.bin.usecase.ordemservico.OrdemServico;

public class ListaOrdemServicoAdapter extends ArrayAdapter<OrdemServico> {

    private int lastPosition = -1;

    // View lookup cache
    private static class ViewHolder {
        TextView txtCodigoOrdemServico;
        TextView txtPlacaOrdemServico;
        TextView txtVeiculoOrdemServico;
    }

    public ListaOrdemServicoAdapter(final Context context, final List<OrdemServico> ordensServico) {
        super(context, R.layout.ordem_servico_row_item, ordensServico);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OrdemServico dataModel = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ordem_servico_row_item, parent, false);
            viewHolder.txtPlacaOrdemServico = (TextView) convertView.findViewById(R.id.txtPlacaOrdemServico);
            viewHolder.txtVeiculoOrdemServico = (TextView) convertView.findViewById(R.id.txtVeiculoOrdemServico);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.txtPlacaOrdemServico.setText(dataModel.getPlaca());
        viewHolder.txtVeiculoOrdemServico.setText(dataModel.getVeiculo());

        return convertView;
    }
}
