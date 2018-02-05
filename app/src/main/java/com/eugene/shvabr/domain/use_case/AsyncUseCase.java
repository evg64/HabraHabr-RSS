package com.eugene.shvabr.domain.use_case;

/**
 * Interactor, работающий асинхронно.
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
