package com.saritasa.clock_knock.features.login.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepository;
import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

import io.reactivex.Single;

/**
 * An interface for providing login methods to repository object
 */
public interface LoginRepository extends BaseRepository{

    /**
     * Gets the domain username object single from entity object single
     *
     * @return Domain username object single
     */
    @NonNull
    Single<UsernameDomain> getUsername();
}
