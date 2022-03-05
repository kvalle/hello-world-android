package com.example.helloworldapp

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.ImageView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.isEmptyString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HelloInstrumentedTest {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun elephant_greets_her_brother() {

        // Before we start the hello button should be disabled, and the text field empty
        onView(withId(R.id.helloButton)).check(matches(isNotEnabled()))
        onView(withId(R.id.whoToGreetField)).check(matches(withText(isEmptyString())))

        // Make selections
        onView(withId(R.id.whoToGreetField)).perform(typeText("brother"))
        closeSoftKeyboard()
        onView(withId(R.id.animals_radio_button_option_elephant)).perform(click())

        // Now it should be possible to click the button
        onView(withId(R.id.helloButton)).check(matches(isEnabled()))

        // The elephant should be selected and displayed
        onView(withId(R.id.animals_radio_button_option_elephant)).check(matches(isChecked()))
        onView(withId(R.id.kittyImage)).check(matches(withImageDrawable(R.drawable.elephant)))

        // Say "Hello!"
        onView((withId(R.id.helloButton))).perform(click())

        // The snackbar should contain the correct message
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Hello, brother!")))
    }


    /*
    Helper functions from https://gist.github.com/frankiesardo/7490059
    */
    fun withImageDrawable(resourceId: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has image drawable resource $resourceId")
            }

            override fun matchesSafely(imageView: ImageView): Boolean {
                return sameBitmap(imageView.getContext(), imageView.getDrawable(), resourceId)
            }
        }
    }

    private fun sameBitmap(context: Context, drawable: Drawable?, resourceId: Int): Boolean {
        var drawable: Drawable? = drawable
        var otherDrawable: Drawable = context.getResources().getDrawable(resourceId)
        if (drawable == null || otherDrawable == null) {
            return false
        }
        if (drawable is StateListDrawable && otherDrawable is StateListDrawable) {
            drawable = drawable.getCurrent()
            otherDrawable = otherDrawable.getCurrent()
        }
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val otherBitmap = (otherDrawable as BitmapDrawable).bitmap
            return bitmap.sameAs(otherBitmap)
        }
        return false
    }

}