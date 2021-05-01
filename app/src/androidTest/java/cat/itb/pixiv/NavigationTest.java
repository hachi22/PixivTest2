package cat.itb.pixiv;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class NavigationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void navigationTestTabLayoutToMangaFromIllustrations() {
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withId(R.id.input_text_login_username))
                .perform(replaceText("primer"), closeSoftKeyboard());
        onView(withId(R.id.input_text_login_password))
                .perform(replaceText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .check(matches(isClickable()))
                .perform(click());


        ViewInteraction tabView = onView(
                allOf(withContentDescription("Manga"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());
        onView(withId(R.id.fragment_manga))
                .check(matches(isDisplayed()));
    }
    @Test
    public void navigationTestTabLayoutToNovelsFromManga() {

        navigationTestTabLayoutToMangaFromIllustrations();


        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("Novels"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout),
                                        0),
                                2),
                        isDisplayed()));
        tabView2.perform(click());
    }
    @Test
    public void navigationTestTabLayoutToIllustrationsFromNovels() {

        navigationTestTabLayoutToNovelsFromManga();

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Illustrations"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout),
                                        0),
                                0),
                        isDisplayed()));
        tabView3.perform(click());
    }
    @Test
    public void openNavigationDrawerFromHome() {

        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withId(R.id.input_text_login_username))
                .perform(replaceText("primer"), closeSoftKeyboard());
        onView(withId(R.id.input_text_login_password))
                .perform(replaceText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .check(matches(isClickable()))
                .perform(click());


        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigation Drawer open"),
                        childAtPosition(
                                allOf(withId(R.id.top_appbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }
    @Test
    public void selectItemSubmitWorkFromNavigationDrawer() {

        openNavigationDrawerFromHome();

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.submitWork),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.navigator_view),
                                                0)),
                                5),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
