package br.com.fiap.fmba.resources.exception;

import android.content.Context;
import android.widget.Toast;

public class BusinessException extends Exception {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Context context, String message, Throwable cause) {
        super(message, cause);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
