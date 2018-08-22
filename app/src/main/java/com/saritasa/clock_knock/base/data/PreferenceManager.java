package com.saritasa.clock_knock.base.data;

import com.saritasa.clock_knock.util.Strings;

public interface PreferenceManager{

    void saveAccessToken(String aAccessToken);

    String getAccessToken();

    void saveUsername(String aUsername);

    String getUsername();

    void clearAllData();

    void saveSecretToken(String aSecretToken);

    String getSecretToken();

}
