package com.utch.apiconsume

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class Adapter (private val context: MainActivity,
               private val datos: ArrayList<Products>): RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item,parent, false))
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (titulo, url, precio, imgurl) = datos.get(position)
        holder.titulo.text = titulo
        holder.precio.text = precio

        holder.btn.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(i)
        }

        Picasso.get().load(imgurl).placeholder(R.drawable.ic_do_not_disturb_alt_black_24dp).into(holder.image)

        holder.itemView.setOnClickListener{
            Toast.makeText(context,titulo, Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image = view.img
        val titulo = view.titulo
        val precio = view.precio
        val btn = view.btnGo
    }


}