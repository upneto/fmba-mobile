package br.com.fiap.fmba.resources.exception;

import android.content.Context;
import android.widget.Toast;

public class DaoException extends Exception {

    public DaoException() {
        super();
    }

    public DaoException(String message) { super(message); }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Context context, String message, Throwable cause) {
        super(message, cause);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
