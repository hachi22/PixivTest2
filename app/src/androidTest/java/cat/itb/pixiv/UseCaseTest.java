package cat.itb.pixiv;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
public class UseCaseTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void likeTest() {
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withId(R.id.input_text_login_username))
                .perform(replaceText("primer"), closeSoftKeyboard());
        onView(withId(R.id.input_text_login_password))
                .perform(replaceText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .check(matches(isClickable()))
                .perform(click());

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

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingActionButton_illustration),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

    }

    @Test
    public void FollowAndUnfollowTest() {
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withId(R.id.input_text_login_username))
                .perform(replaceText("primer"), closeSoftKeyboard());
        onView(withId(R.id.input_text_login_password))
                .perform(replaceText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .check(matches(isClickable()))
                .perform(click());

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

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.followButtonIllustration), withText("follow"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.followButtonIllustration), withText("following"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton5.perform(click());
    }
    @Test
    public void UploadAnIllustrationTest(){
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

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.illustrations_button), withText("Illustrations"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.design_bottom_sheet),
                                        0),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.button_edit_image), withText("Edit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                2)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.crop_image_menu_crop), withText("Crop"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                2),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.edit_text_title_submit_illustration),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_text_title_submit_illustration),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Ejemplo"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.edit_description_title_submit_illustration),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_text_description_submit_illustration),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("ejemplo123"), closeSoftKeyboard());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.radio_button_public_illustrations), withText("Make public"),
                        childAtPosition(
                                allOf(withId(R.id.group_radio_button_submit_illustrations),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                7)),
                                0)));
        materialRadioButton.perform(scrollTo(), click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.submitIllustrationManga), withText("submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                8)));
        materialButton6.perform(scrollTo(), click());
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
