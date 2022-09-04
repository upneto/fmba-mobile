package br.com.fiap.fmba.resources.exception;

import android.content.Context;
import android.widget.Toast;

public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) { super(message); }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Context context, String message, Throwable cause) {
        super(message, cause);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
