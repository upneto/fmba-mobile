package br.com.fiap.fmba.usecase;

import android.content.Context;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractUseCase<T extends AbstractUseCase<T>> {

    protected Context context;

    protected AbstractUseCase(Context context) {
        final Class<?> type = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (!type.isInstance(this)) {
            throw new IllegalArgumentException();
        }
        this.context = context;
    }
}
