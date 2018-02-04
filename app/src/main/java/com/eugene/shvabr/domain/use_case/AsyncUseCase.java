package com.eugene.shvabr.domain.use_case;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface AsyncUseCase<T extends AsyncUseCase.Callback> {

    void execute(T callback);

    void unsubscribe();

    /**
     * Marker-interface
     */
    interface Callback {
    }
}
