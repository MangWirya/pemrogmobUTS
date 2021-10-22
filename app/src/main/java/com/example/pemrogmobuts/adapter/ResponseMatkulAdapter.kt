package com.example.pemrogmobuts.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pemrogmobuts.MainMatkulActivity
import com.example.pemrogmobuts.R
import com.example.pemrogmobuts.UpdateMatkulActivity
//import com.example.pemrogmobile2021.crud.UpdatePetaniActivity
import com.example.pemrogmobuts.model.DataItem
import com.example.pemrogmobuts.model.ResponseAddMatkul
import com.example.pemrogmobuts.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseMatkulAdapter (val matkul: List<DataItem>?): RecyclerView.Adapter<ResponseMatkulAdapter.MatkulHolder>() {
    lateinit var mContext : Context
    lateinit var adapter: ResponseMatkulAdapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseMatkulAdapter.MatkulHolder {
        return MatkulHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_matkul, parent, false))
    }
    override fun onBindViewHolder(holder: ResponseMatkulAdapter.MatkulHolder, position: Int) {
        holder.bindMatkul(matkul?.get(position))
        var popupMenu = PopupMenu(holder.itemView.context, holder.itemView)
        popupMenu.menu.add(Menu.NONE,0,0, "Edit")
        popupMenu.menu.add(Menu.NONE,1,1, "Delete")
        popupMenu.setOnMenuItemClickListener { menuItem ->
          val id = menuItem.itemId
        mContext = holder.itemView.context
        if (id==0){
         var bundle = Bundle()
         var idTmp = matkul?.get(position)?.id.toString()

        bundle.putString("idMatkul", idTmp)
        var intent = Intent(mContext, UpdateMatkulActivity::class.java)
        intent.putExtras(bundle)
        mContext.startActivity(intent)
        }
        else if(id==1){
          var idTmp = matkul?.get(position)?.id.toString()
        NetworkConfig().getService()
          .deleteMatkul(idTmp.toInt())
          .enqueue(object : Callback<ResponseAddMatkul?> {
          override fun onFailure(call: Call<ResponseAddMatkul?>, t: Throwable) {
            Toast.makeText(holder.itemView.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
        }
        override fun onResponse(
         call: Call<ResponseAddMatkul?>,
        response: Response<ResponseAddMatkul?>
        ) {
          Toast.makeText(holder.itemView.context, "Berhasil Hapus", Toast.LENGTH_SHORT).show()

        (mContext as MainMatkulActivity).finish()
         var intent = Intent(mContext, MainMatkulActivity::class.java)
          mContext.startActivity(intent)
        }
        })
         }
         false
          }
        holder.itemView.setOnClickListener(View.OnClickListener { view ->
          popupMenu.show()
         })
         }

    override fun getItemCount(): Int {
        return matkul?.size?:0
    }
    class MatkulHolder (view: View) : RecyclerView.ViewHolder(view) {
        lateinit var txtKode: TextView
        lateinit var txtNama: TextView
        lateinit var txtHari: TextView
        lateinit var txtSesi: TextView
        lateinit var txtSks: TextView
        lateinit var txtNimprogmob: TextView
        fun bindMatkul(matkul: DataItem?) {
            itemView.apply {
                txtKode = findViewById(R.id.kode)
                txtNama = findViewById(R.id.nama)
                txtHari = findViewById(R.id.hari)
                txtSesi = findViewById(R.id.sesi)
                txtSks = findViewById(R.id.sks)
                txtNimprogmob = findViewById(R.id.nimProgmob)

                txtKode.text = matkul?.kode
                txtNama.text = matkul?.nama
                txtHari.text = matkul?.hari
                txtSesi.text = matkul?.sesi
                txtSks.text = matkul?.sks
                txtNimprogmob.text = matkul?.nimProgmob
            }
        }
    }
}