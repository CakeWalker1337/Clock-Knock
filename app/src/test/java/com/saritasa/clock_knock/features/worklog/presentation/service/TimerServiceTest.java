package com.saritasa.clock_knock.features.worklog.presentation.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Timer service test class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimerServiceTest{

    @Mock
    private TimerService mTimerService;

    @Before
    public void setUp() {
        mTimerService = new TimerService();
    }

    @Test
    public void longTimeToFormatCorrect() {
        assertEquals("00:00:35", mTimerService.longTimeToFormat(35000));
        assertEquals("00:01:27", mTimerService.longTimeToFormat(87000));
        assertEquals("01:23:11", mTimerService.longTimeToFormat(4991000));
    }
}
