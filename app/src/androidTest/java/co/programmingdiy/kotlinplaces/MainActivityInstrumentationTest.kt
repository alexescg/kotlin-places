package co.programmingdiy.kotlinplaces

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withSpinnerText
import org.hamcrest.Matchers.*
import android.support.test.espresso.intent.matcher.IntentMatchers.toPackage
import android.support.test.espresso.intent.rule.IntentsTestRule

/**
 * Main Activity tests validation
 *
 * @author alex
 * @since 4/23/17.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {
    @Rule @JvmField
    val mainActivityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testShouldFindSpinner() {
        onView(withId(R.id.spinner))
    }

    @Test
    fun spinnerLoadsStringResource() {
        val places =
                InstrumentationRegistry.getTargetContext()
                        .resources
                        .getStringArray(R.array.places)
        val testWord : String = places[0].toString()
        onView(withId(R.id.spinner)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(testWord))).perform(click())
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString(testWord))))
    }

    @Test
    fun testShouldFindButton(){
        onView(withId(R.id.btn_Find))
    }

    @Test
    fun testButtonShouldLaunchIntent() {
        onView(withId(R.id.btn_Find)).perform(click())
        intended(toPackage("com.google.android.apps.maps"))
    }
}