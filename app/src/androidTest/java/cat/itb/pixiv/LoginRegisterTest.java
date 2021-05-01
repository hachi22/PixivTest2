package cat.itb.pixiv;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class LoginRegisterTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void login(){
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withId(R.id.input_text_login_username))
                .perform(replaceText("primer"), closeSoftKeyboard());
        onView(withId(R.id.input_text_login_password))
                .perform(replaceText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .check(matches(isClickable()))
        .perform(click());

    }

    @Test
    public void register(){
        onView(withId(R.id.registerButton))
                .perform(click());
        onView(withId(R.id.input_text_username))
                .perform(typeText("test2"))
                .check(matches(withText("test2")))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.input_text_password))
                .perform(typeText("123456789"))
                .check(matches(withText("123456789")))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.input_text_password_repeat))
                .perform(typeText("123456789"))
                .check(matches(withText("123456789")))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.registerButton))
                .check(matches(isClickable()));
        //no hago click porque no quiero que se añada un nuevo user a la database
    }
}

