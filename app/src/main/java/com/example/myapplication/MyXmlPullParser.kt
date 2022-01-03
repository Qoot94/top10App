package com.example.myapplication


import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class MyXmlPullParserHandler {
    private val tenList = ArrayList<Top>()
    private var text: String? = null
    private var appName = ""

    fun parse(): ArrayList<Top> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url = URL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
            parser.setInput(url.openStream(), null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> appName = when {
                        tagName.equals("im:name", ignoreCase = true) -> {
                            text.toString()
                        }
                        else -> {
                            if (appName.isNotEmpty()) {
                                val special = Top(appName)
                                tenList.add(special)
                            }
                            ""
                        }
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return tenList
    }
}