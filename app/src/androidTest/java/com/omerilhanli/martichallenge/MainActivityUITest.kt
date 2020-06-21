package com.omerilhanli.martichallenge

import android.text.TextUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.omerilhanli.martichallenge.ui.adapter.viewholder.PlacesViewHolder
import com.omerilhanli.martichallenge.ui.main.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @get:Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_TypeTextPlace_ClickSearchButton_CheckListCompletelyFull() {
        val vInteractionEtSearch = onView(withId(R.id.et_search)).check(matches(ViewMatchers.isDisplayed()))
        vInteractionEtSearch.perform(typeText(typeTextString))
        val vInteractionImgSearch = onView(withId(R.id.img_search_icon)).check(matches(ViewMatchers.isDisplayed()))
        vInteractionImgSearch.perform(click())

        if (getRecyclerListSize() > 0) {
            onView(withId(R.id.recycler_places)).perform(RecyclerViewActions.actionOnItemAtPosition<PlacesViewHolder>(1, click()));
        }
    }

    @Test
    fun test_SearchText_GoMap_ThenGoDetail_CheckNameFieldEmptyOrNot() {
        val vInteractionEtSearch = onView(withId(R.id.et_search)).check(matches(ViewMatchers.isDisplayed()))
        vInteractionEtSearch.perform(typeText(typeTextString))
        val vInteractionImgSearch = onView(withId(R.id.img_search_icon)).check(matches(ViewMatchers.isDisplayed()))
        vInteractionImgSearch.perform(click())

        if (getRecyclerListSize() > 0) {
            onView(withId(R.id.recycler_places)).perform(RecyclerViewActions.actionOnItemAtPosition<PlacesViewHolder>(1, click()));
        }

        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains(mActivityTestRule.activity.getString(R.string.Generic_title)))
        marker.click()

        onView(withId(R.id.img_place_detail_photo)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_place_detail_name)).check(matches(ViewMatchers.isDisplayed())).check(matches(not(TextUtils.isEmpty(""))))
    }

    private fun getRecyclerListSize(): Int {
        return mActivityTestRule.activity.findViewById<RecyclerView>(R.id.recycler_places).adapter?.itemCount ?: 0
    }

    companion object {
        const val typeTextString = "bar"
    }
}