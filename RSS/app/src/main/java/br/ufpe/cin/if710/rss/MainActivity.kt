package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.io.InputStream
import java.net.URL
import java.nio.charset.StandardCharsets

class MainActivity : Activity() {
    private val RSS_FEED = "http://rss.cnn.com/rss/edition.rss"
    //private val RSS_FEED = "http://cin.ufpe.br/~lfvg/"

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
        //var a:InputStream = URL(RSS_FEED).openStream()
        //var doc =  Jsoup.connect(RSS_FEED).get()
        var doc = Jsoup.connect(RSS_FEED).get().text().replace("\u00a0", " ").replace("\u003e", ">")!!



        //var a = doc?.html()
        var lista = ParserRSS.parse(doc)
        var temp = lista?.listIterator()
      //  var test = temp.next().toString()
       // while(temp.hasNext()){
        //    val t:ItemRSS = temp.next()
       // }


        return /*doc.html()*/ temp?.next()!!.toString()
    }
}
