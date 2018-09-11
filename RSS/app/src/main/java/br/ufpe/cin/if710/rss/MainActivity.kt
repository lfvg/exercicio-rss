package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class MainActivity : Activity() {
    //private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"
    private val RSS_FEED = "http://cin.ufpe.br/~lfvg/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        try{
            //o codigo assincrono vai aqui
            doAsync{
                var feedXML = getRSSFeed(RSS_FEED)
                uiThread{
                    conteudoRSS.setText(feedXML)
                }
            }
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }
    //PORTAR ESSA PARTE
    //PORTAR ESSA PARTE
    //PORTAR ESSA PARTE
    private fun getRSSFeed(url: String): String{
        var doc = Jsoup.connect(RSS_FEED).get()
        return doc.html()
    }
}
