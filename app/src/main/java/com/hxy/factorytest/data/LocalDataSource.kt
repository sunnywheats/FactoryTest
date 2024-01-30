package com.hxy.factorytest.data

import android.content.Context
import com.hxy.factorytest.R
import com.hxy.factorytest.data.model.TestItem
import com.hxy.factorytest.util.Const
import com.hxy.factorytest.util.FeatureConfig

class LocalDataSource(private val context: Context){
    //main item
    private lateinit var itemTest:TestItem
    private lateinit var itemResult:TestItem
    private lateinit var fullTest:TestItem
    private lateinit var agingTest:TestItem
    //item test
    private lateinit var versionTest:TestItem
    private lateinit var rfCallTest:TestItem
    private lateinit var rtcTest:TestItem
    private lateinit var memoryTest:TestItem
    private lateinit var googleKeyTest:TestItem
    private lateinit var lcdTest:TestItem
    private lateinit var backlightTest:TestItem

    init{
        itemTest =
            TestItem(
                context.getString(R.string.menu_item_test),
                ".ui.TestItemActivity",
                FeatureConfig.SUPPORT_ITEM_TEST
            )
        itemResult =
            TestItem(
                context.getString(R.string.menu_item_result),
                ".ui.TestResultActivity",
                FeatureConfig.SUPPORT_ITEM_RESULT
            )
        fullTest =
            TestItem(
                context.getString(R.string.menu_full_test),
                ".ui.TestFullActivity",
                FeatureConfig.SUPPORT_FULL_TEST
            )
        agingTest =
            TestItem(
                context.getString(R.string.menu_aging_test),
                ".ui.TestAgingActivity",
                FeatureConfig.SUPPORT_AGING_TEST
            )
        versionTest =
            TestItem(
                context.getString(R.string.test_version),
                ".item.version.TestVersionActivity",
                FeatureConfig.SUPPORT_VERSION,
                TestSpf(context).getState(Const.KEY_VERSION),
                FeatureConfig.ORDER_VERSION,
                "test_version"
            )
        rfCallTest =
            TestItem(
                context.getString(R.string.test_rf_call),
                ".ItemResultActivity",
                FeatureConfig.SUPPORT_RF_CALL,
                TestSpf(context).getState("test_rf_call"),
                FeatureConfig.ORDER_RF_CALL,
                "test_rf_call"
            )
        rtcTest =
            TestItem(
                context.getString(R.string.test_rtc),
                ".FullTestActivity",
                FeatureConfig.SUPPORT_RTC,
                TestSpf(context).getState("test_rtc"),
                FeatureConfig.ORDER_RTC,
                "test_rtc"
            )
        memoryTest =
            TestItem(
                context.getString(R.string.test_memory),
                ".AgingTestActivity",
                FeatureConfig.SUPPORT_MEMORY,
                TestSpf(context).getState("test_memory"),
                FeatureConfig.ORDER_MEMORY,
                "test_memory"
            )
        googleKeyTest =
            TestItem(
                context.getString(R.string.test_googlekey),
                ".AgingTestActivity",
                FeatureConfig.SUPPORT_GOOGLEKEY,
                TestSpf(context).getState("test_googlekey"),
                FeatureConfig.ORDER_GOOGLEKEY,
                "test_googlekey"
            )
        lcdTest =
            TestItem(
                context.getString(R.string.test_lcd),
                ".AgingTestActivity",
                FeatureConfig.SUPPORT_LCD,
                TestSpf(context).getState("test_lcd"),
                FeatureConfig.ORDER_LCD,
                "test_lcd"
            )
        backlightTest =
            TestItem(
                context.getString(R.string.test_backlight),
                ".AgingTestActivity",
                FeatureConfig.SUPPORT_BACKLIGHT,
                TestSpf(context).getState("test_backlight"),
                FeatureConfig.ORDER_BACKLIGHT,
                "test_backlight"
            )
    }

    fun getAllTest(): List<TestItem> {
        val items = arrayOf(versionTest, rfCallTest, rtcTest, memoryTest,googleKeyTest,lcdTest,backlightTest)
        //sort
        items.sortBy {item -> item.order}
        //isSupport
        val newMenus = items.filter { item -> item.isSupport }
        return newMenus
    }

    fun getMainTest(): List<TestItem> {
        val menus = arrayOf(itemTest, itemResult, fullTest, agingTest)
        //isSupport
        val newMenus = menus.filter { menu -> menu.isSupport }
        return newMenus
    }

}