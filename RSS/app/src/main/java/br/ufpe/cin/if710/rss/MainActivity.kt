package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : Activity() {
    private val RSS_FEED: String = "http://leopoldomt.com/if1001/g1brasil.xml"
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //private val RSS_FEED = "http://cin.ufpe.br/~lfvg/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)

    }

    override fun onStart() {
        super.onStart()
        try {
            //o codigo assincrono vai aqui
            doAsync {
                var feedXML = getRSSFeed(RSS_FEED)
                uiThread {
                    viewAdapter = RSSAdapter(feedXML)
                    recyclerView = findViewById<RecyclerView>(R.id.conteudoRSS).apply {

                        setHasFixedSize(true)


                        layoutManager = viewManager


                        adapter = viewAdapter
                    }
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    //PORTAR ESSA PARTE
    //PORTAR ESSA PARTE
    //PORTAR ESSA PARTE
    private fun getRSSFeed(url: String): List<ItemRSS>{
        //var a:InputStream = URL(RSS_FEED).openStream()
        //var doc =  Jsoup.connect(RSS_FEED).get()
        //var doc = Jsoup.connect(RSS_FEED).get().text().replace("\u00a0", " ").replace("\u003e", ">")!!
        var doc = GetPage().getRssFeed(url)!!


        //var a = doc?.html()
        var lista = ParserRSS.parse(doc)
        var temp = lista?.listIterator()
      //  var test = temp.next().toString()
       // while(temp.hasNext()){
        //    val t:ItemRSS = temp.next()
       // }


        return lista// temp?.next()!!.toString()
    }
}
