package com.saritasa.clock_knock.features.main.data;

import com.saritasa.clock_knock.base.data.BaseRepository;

public interface MainRepository extends BaseRepository{

    boolean checkAccessToken();
}
