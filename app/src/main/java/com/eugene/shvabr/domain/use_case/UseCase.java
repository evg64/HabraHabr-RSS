package com.eugene.shvabr.domain.use_case;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface UseCase<T extends UseCase.Callback> {

    void execute(T callback);

    /**
     * Marker-interface
     */
    interface Callback {
    }
}
