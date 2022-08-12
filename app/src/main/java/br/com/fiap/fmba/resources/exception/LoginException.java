package br.com.fiap.fmba.resources.exception;

import android.content.Context;
import android.widget.Toast;

public class LoginException extends Exception {

    public LoginException (Context context, String message, Throwable error) {
        super(error);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
