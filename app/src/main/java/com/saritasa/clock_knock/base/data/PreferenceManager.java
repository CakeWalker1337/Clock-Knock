package com.saritasa.clock_knock.base.data;

public interface PreferenceManager{

    void saveAccessToken(String aAccessToken);

    String getAccessToken();

    void saveUsername(String aUsername);

    String getUsername();

    void clearAllData();
}
