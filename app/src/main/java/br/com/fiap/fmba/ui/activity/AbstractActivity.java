package br.com.fiap.fmba.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.fiap.fmba.R;

public abstract class AbstractActivity<T> extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.menuListOrdemServico:
                intent = new Intent(this, ListaOrdemServicoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuAddOrdemServico:
                intent = new Intent(this, CadastroOrdemServicoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public TextWatcher getDateMask(final String mask, final EditText et) {
        final TextWatcher textWatcher = new TextWatcher() {
            boolean isUpdating;
            String oldTxt = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = this.unmask(s.toString());
                String maskCurrent = "";
                if (isUpdating) {
                    oldTxt = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > oldTxt.length()) {
                        maskCurrent += m;
                        continue;
                    }
                    try {
                        maskCurrent += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                et.setText(maskCurrent);
                et.setSelection(maskCurrent.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) { }

            private String unmask(String s) {
                return s.replaceAll("[.]", "").replaceAll("[-]", "")
                        .replaceAll("[/]", "").replaceAll("[(]", "")
                        .replaceAll("[)]", "");
            }
        };
        return textWatcher;
    }

}
