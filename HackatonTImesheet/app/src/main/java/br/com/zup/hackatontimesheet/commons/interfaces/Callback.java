package br.com.zup.hackatontimesheet.commons.interfaces;

/**
 * Created by joaoh on 06/05/2018.
 */

public interface Callback<T> {
    void onSuccess(T response);
    void onError();
}
