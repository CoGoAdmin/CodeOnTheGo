package com.itsaky.androidide.tooltips

import android.content.Context
import android.util.Log
import com.itsaky.androidide.tooltips.ide.IDETooltipDatabase.Companion.getDatabase
import com.itsaky.androidide.tooltips.ide.IDETooltipItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

object LoadTooltips {
    fun loadData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {

            val db = getDatabase(context)
            val dao = db.idetooltipDao()

            val jsonString: String =
                loadJsonFromAssets(context, "CoGoTooltips/misc/CoGoTooltips.json")
            val arrayObj: JSONArray = JSONArray(jsonString)
            try {
                for (index in 0 until arrayObj.length()) {
                    val jsonObj: JSONObject = arrayObj.get(index) as JSONObject
                    val tag = jsonObj.getString("tag")
                    val summary = jsonObj.getString("summary")
                    val detail = jsonObj.getString("detail")
                    val buttonList = jsonObj.get("buttonList") as JSONArray
                    val buttonsList = readJsonArrayOfArrays(context, buttonList)
                    //TODO JMT make this a when based on category
                    val item = IDETooltipItem(
                        tooltipTag = tag,
                        summary = summary,
                        detail = detail,
                        buttons = buttonsList
                    )

                    dao.insert(item)
                }

//TODO JMT do we really want this
//                val tooltipItemList: List<TooltipItem> = dao.getTooltipItems()
//                tooltipItemList.forEach { tooltipItem ->
//                    Log.d(
//                        "TooltipRoomDatabase",
//                        "after insert database - itemTag = ${tooltipItem.tooltipTag}, " +
//                                "summary = ${tooltipItem.summary}, detail=${tooltipItem.detail}"
//                    )
//                }


            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("loadData", "loading tooltip database failed - " + e.localizedMessage)
            }
        }
    }
}

fun readJsonArrayOfArrays(context: Context, jsonArray: JSONArray): ArrayList<Pair<String, String>> {
    val resultList = ArrayList<Pair<String, String>>()

    for (i in 0 until jsonArray.length()) {
        val innerArray = jsonArray.getJSONArray(i)
        val pear = Pair(innerArray.getString(0), innerArray.getString(1))
        resultList.add(pear)
    }

    return resultList
}

private fun loadJsonFromAssets(context: Context, fileName: String): String {
    val json: String?
    try {
        json = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ex: IOException) {
        ex.printStackTrace()
        return ""
    }
    return json
}

