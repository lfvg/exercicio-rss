package br.ufpe.cin.if710.rss

import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.net.URI
import org.jetbrains.anko.toast


class RSSAdapter(private val lista: List<ItemRSS>): RecyclerView.Adapter<RSSAdapter.RSSViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.itemlista, parent, false)
        return RSSViewHolder(item)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: RSSViewHolder, position: Int) {
        var listaArray = lista.toTypedArray()
        holder.itemView.findViewById<TextView>(R.id.item_titulo).setText(listaArray[position].title)
        holder.itemView.findViewById<TextView>(R.id.item_data).setText(listaArray[position].pubDate)
        holder.itemView.setOnClickListener({
            val url = Uri.parse(listaArray[position].link)
            val i = Intent(Intent.ACTION_VIEW, url)
            //i.setData(Uri.parse(listaArray[position].link))
            startActivity(it.context, i, null)
        })
    }

    class RSSViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var titulo: TextView? = null
        var data: TextView? = null
        init {
            this.titulo = itemView.findViewById(R.id.item_titulo)
            this.data = itemView.findViewById(R.id.item_data)
        }
    }
}