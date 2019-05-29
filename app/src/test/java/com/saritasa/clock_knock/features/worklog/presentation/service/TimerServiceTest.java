package com.saritasa.clock_knock.features.worklog.presentation.service;

import com.saritasa.clock_knock.util.DateTimeFormatter;
import com.saritasa.clock_knock.util.Strings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Timer service test class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimerServiceTest{


    @Test
    public void longTimeToFormatCorrect() {
        assertEquals("00:00:35", DateTimeFormatter.longTimeMillisToFormat(35000, Strings.TIME_PATTERN));
        assertEquals("00:01:27", DateTimeFormatter.longTimeMillisToFormat(87000, Strings.TIME_PATTERN));
        assertEquals("01:23:11", DateTimeFormatter.longTimeMillisToFormat(4991000, Strings.TIME_PATTERN));
    }

    @Test
    public void longTimeToFormat2Correct(){
        assertEquals("35s", DateTimeFormatter.longTimeMillisToFormat(35000, Strings.TIME_PATTERN));
        assertEquals("00:01:27", DateTimeFormatter.longTimeMillisToFormat(87000, Strings.TIME_PATTERN));
        assertEquals("01:23:11", DateTimeFormatter.longTimeMillisToFormat(4991000, Strings.TIME_PATTERN));
    }
}
