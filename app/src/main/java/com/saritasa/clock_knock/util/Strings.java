package com.saritasa.clock_knock.util;

/**
 * A final class for string constants
 */
public final class Strings{

    /**
     * Private key for the Jira app
     */
    public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALep6TOTmsoYat97BcBMJkPkWimFnAi8GF7miE02XsQHUe4gLrluM5vF/fJZ0ZeJMjjVu+rlMWg7895tA6eDKcPnQbxEww69jp/4QooxK6ojHY1i2xkc4iJYKOHlMJa6i4t6p3Alp+gHw63t5SFkvNDcxKesUqwIHm6E+4/lSMEJAgMBAAECgYBQGLyH7rZRroZlQfyHGW6SqzKa6xbaRFWIhZcpvS1k5iBX7hcSbTn6lEUkfQFHyeBqIuQaE6wRXhwDg51VX4FFnhbDnmjIn7fhlOGOaI0TMMCCzmgXATAjlvsmmbKta1JYGLY+LzIfzt+Fpxt27x8J7nrZru05aCJgISlOTF5NgQJBAN25m8p7AA3SC9W6I1Pp+CEYwyPpdclGTNpt56aLeCzpnUCzJ0UCdLQKHeozBK5fDg5Pabi3F49l99ypK7oOVRECQQDUDhciSxNZO/+Mot39zVHB7NM2avKqSxRi2ALKgYzQXUMxJX7QCYk1gotArIgVaMJ6uI+0QXXqgNaSvZNZKcx5AkEA0a8ocBJmeKt92b/QjRklOVeLpDiy1YgQQjnR6yTWn9LGjlte9dpet60hoUxiTu1CA7KCTteIBmruPtYVrjzDoQJAa/1s/JLdHKF4Z8d9Y8YO78JKYbkfMWggzqGQ8k39RxNnp3yf0xQ4It8DSYFToAuElvisfjCj+zN3kq0mf87QiQJBAMZJl8a5NlpcFCmCzfNtudetADL/86R4BUf6Xaw2eNy6Oh0qTzn+qlaox2PSc/ZszEa4ciQwych8qZpln1Npfsc=";

    /**
     * Consumer key for the Jira app
     */
    public static final String CONSUMER_KEY = "OauthKey";

    /**
     * Marker for getting verification key from HTML-page
     */
    public static final String SEARCH_MARKER = "\'Clock-Knock App\'";

    /**
     * Javascript interface key
     */
    public static final String JS_INTERFACE_KEY = "jsinterface";

    /**
     * Web user agent data
     */
    public static final String WEB_USER_AGENT = "Chrome/56.0.0.0 Mobile";

    /**
     * Shared preferences key for auth access token
     */
    public static final String PREFERENCE_ACCESS_TOKEN = "access_token";

    /**
     * Shared preferences key for auth secret token
     */
    public static final String PREFERENCE_SECRET_TOKEN = "secret_token";

    /**
     * Shared preferences key for username
     */
    public static final String PREFERENCE_USERNAME = "username";

    /**
     * Shared preferences key for timestamp
     */
    public static final String PREFERENCE_START_TIMESTAMP = "timer_timestamp";

    /**
     * Shared preferences key for task id
     */
    public static final String PREFERENCE_TASK_ID = "timer_task_id";

    /**
     * Action for starting service
     */
    public static final String START_SERVICE_ACTION = "StartService";

    /**
     * Action for ending service
     */
    public static final String END_SERVICE_ACTION = "EndService";

    /**
     * Action for stop timer in fragment
     */
    public static final String STOP_TIMER_ACTION = "Stop";

    /**
     * Action for show fragment without stopping timer
     */
    public static final String SHOW_ACTIVITY_ACTION = "Show";

    /**
     * String for creating an extra for holding the task id
     */
    public static final String TASK_ID_EXTRA = "task";

    /**
     * String for creating an extra for holding the timestamp
     */
    public static final String TIMESTAMP_EXTRA = "timestamp";
}
