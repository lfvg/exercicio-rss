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
    private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"
    //private val RSS_FEED = "http://cin.ufpe.br/~lfvg/"
    val rsssss = "<rss xmlns:atom=\"http://www.w3.org/2005/Atom\" xmlns:media=\"http://search.yahoo.com/mrss/\" version=\"2.0\">\n" +
            "<channel>\n" +
            "<link>http://g1.globo.com/brasil/index.html</link>\n" +
            "<description>\n" +
            "Últimas notícias sobre o Brasil, política, economia, coberturas especiais, e ainda condições das estradas e clima no nosso país.\n" +
            "</description>\n" +
            "<language>pt-BR</language>\n" +
            "<copyright>© Copyright Globo Comunicação e Participações S.A.</copyright>\n" +
            "<atom:link href=\"http://pox.globo.com/rss/g1/brasil/\" rel=\"self\" type=\"application/rss+xml\"/>\n" +
            "<image>\n" +
            "<url>\n" +
            "https://s2.glbimg.com/veNWQCjPmWVRAfzfLSJt35f_V58=/i.s3.glbimg.com/v1/AUTH_afd7a7aa13da4265ba6d93a18f8aa19e/pox/g1.png\n" +
            "</url>\n" +
            "<title>G1 > Brasil</title>\n" +
            "<link>http://g1.globo.com/brasil/index.html</link>\n" +
            "<width>144</width>\n" +
            "<height>144</height>\n" +
            "</image>\n" +
            "<item>\n" +
            "<title>\n" +
            "Livro é lançado em Uberaba para ajudar tratamento de criança com doença degenerativa\n" +
            "</title>\n" +
            "<link>\n" +
            "http://g1.globo.com/minas-gerais/triangulo-mineiro/noticia/livro-e-lancado-em-uberaba-para-ajudar-tratamento-de-crianca-com-doenca-degenerativa.ghtml\n" +
            "</link>\n" +
            "<guid isPermaLink=\"true\">\n" +
            "http://g1.globo.com/minas-gerais/triangulo-mineiro/noticia/livro-e-lancado-em-uberaba-para-ajudar-tratamento-de-crianca-com-doenca-degenerativa.ghtml\n" +
            "</guid>\n" +
            "<description>\n" +
            "'As amigas' mostra a superação de Anna Vitória Pereira Pontim, que sofre de amiotrofia espinhal. Tratamento custa cerca de R\$ 3 milhões. \n" +
            "</description>\n" +
            "<category>G1</category>\n" +
            "<pubDate>Wed, 12 Apr 2017 20:27:51 -0000</pubDate>\n" +
            "</item>\n" +
            "<title>\n" +
            "Criminosos arrombam agência bancária e furtam cofre em distrito de Luz, MG\n" +
            "</title>\n" +
            "<link>\n" +
            "http://g1.globo.com/mg/centro-oeste/noticia/criminosos-arrombam-agencia-bancaria-e-furtam-cofre-em-distrito-de-luz-mg.ghtml\n" +
            "</link>\n" +
            "<guid isPermaLink=\"true\">\n" +
            "http://g1.globo.com/mg/centro-oeste/noticia/criminosos-arrombam-agencia-bancaria-e-furtam-cofre-em-distrito-de-luz-mg.ghtml\n" +
            "</guid>\n" +
            "<description>\n" +
            "Cofre levado pelos assaltantes continha uma arma, coletes balísticos e uniforme de trabalho do vigilante.\n" +
            "</description>\n" +
            "<category>G1</category>\n" +
            "<pubDate>Wed, 12 Apr 2017 15:34:57 -0000</pubDate>\n" +
            "</item>\n" +
            "</channel>\n" +
            "</rss>"
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
