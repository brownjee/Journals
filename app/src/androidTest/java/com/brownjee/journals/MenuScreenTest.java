package com.brownjee.journals;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Brownjee on 6/28/2018.
 */

@RunWith(AndroidJUnit4.class)
public class MenuScreenTest {
    @Rule
    public ActivityTestRule<MenuScreen> mActivityTestRule = new ActivityTestRule<>(MenuScreen.class);

    @Test
    public void testFabOnClick() throws Exception {
        Intents.init();

        onView(withId(R.id.fab)).perform(click());

        intended(hasComponent(MainActivity.class.getName()));

        Intents.release();
    }
}
