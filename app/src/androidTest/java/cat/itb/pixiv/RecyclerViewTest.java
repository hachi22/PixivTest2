package cat.itb.pixiv;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class RecyclerViewTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void CheckRecyclerViewIllustrationsRecommended1() {
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withId(R.id.input_text_login_username))
                .perform(replaceText("primer"), closeSoftKeyboard());
        onView(withId(R.id.input_text_login_password))
                .perform(replaceText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .check(matches(isClickable()))
                .perform(click());

        //Esto es porque si no no encuentra los recyclers porque aún no están cargados

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recycler_view_illustrations_ranking))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3,click()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.illustration_text_view_oc_tittle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.illustration_text_view_oc_username))
                .check(matches(isDisplayed()));
        onView(withId(R.id.illustration_oc_ProfileImage))
                .check(matches(isDisplayed()));
        onView(withId(R.id.illustratrion_oc_Image))
                .check(matches(isDisplayed()));
        onView(withId(R.id.floatingActionButton_illustration))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()));

    }
}