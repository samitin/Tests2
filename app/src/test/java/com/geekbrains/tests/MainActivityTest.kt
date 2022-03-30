package com.geekbrains.tests

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityEditText_NotNull() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(R.id.searchEditText)
            TestCase.assertNotNull(editText)
        }
    }
    @Test
    fun activityEditText_HasText() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(R.id.searchEditText)
            TestCase.assertEquals("Enter keyword e.g. android", editText.hint)
            TestCase.assertEquals("", editText.text.toString())
        }
    }
    @Test
    fun activityEditText_IsVisible() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(R.id.searchEditText)
            TestCase.assertEquals(View.VISIBLE, editText.visibility)
        }
    }
    @Test
    fun activityButtonToDetails_NotNull() {
        scenario.onActivity {
            val toDetailsButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertNotNull(toDetailsButton)
        }
    }
    @Test
    fun activityButtonToDetails_HasText() {
        scenario.onActivity {
            val button = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertEquals("to details", button.text)
        }
    }
    @Test
    fun activityButtonToDetails_IsVisible() {
        scenario.onActivity {
            val button = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertEquals(View.VISIBLE, button.visibility)
        }
    }

    @Test
    fun activityTextView_NotNull() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            TestCase.assertNotNull(totalCountTextView)
        }
    }

    @Test
    fun activityTextView_IsInVisible() {
        scenario.onActivity {
            val textView = it.findViewById<TextView>(R.id.totalCountTextView)
            TestCase.assertEquals(View.INVISIBLE, textView.visibility)
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}