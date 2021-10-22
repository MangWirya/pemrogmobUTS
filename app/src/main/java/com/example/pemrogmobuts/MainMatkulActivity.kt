package com.example.pemrogmobuts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pemrogmobuts.adapter.ResponseMatkulAdapter
import com.example.pemrogmobuts.model.DataItem
import com.example.pemrogmobuts.model.ResponseMatkul
import com.example.pemrogmobuts.network.NetworkConfig
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMatkulActivity : AppCompatActivity() {
    lateinit var rvMatkul: RecyclerView
    lateinit var fabAddMatkul: FloatingActionButton

    // override fun onCreate(savedInstanceState: Bundle?) {
    //   super.onCreate(savedInstanceState)
    // setContentView(R.layout.activity_get_petani)

    override fun onRestart() {
        super.onRestart()
        //setContentView(R.layout.activity_get_petani)

        rvMatkul = findViewById(R.id.rvMatkul)
        fabAddMatkul = findViewById(R.id.fabAddMatkul)

        NetworkConfig().getService()
            .getMatkulAll().enqueue(object : Callback<ResponseMatkul?> {
                override fun onFailure(call: Call<ResponseMatkul?>, t: Throwable) {
                    Toast.makeText(this@MainMatkulActivity, t.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<ResponseMatkul?>,
                    response: Response<ResponseMatkul?>
                ) {
                    rvMatkul.apply {
                        layoutManager = LinearLayoutManager(this@MainMatkulActivity)
                        adapter = ResponseMatkulAdapter(response.body()?.data as List<DataItem>?)

                    }
                }
            })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_matkul)

        rvMatkul = findViewById(R.id.rvMatkul)
        fabAddMatkul = findViewById(R.id.fabAddMatkul)

        NetworkConfig().getService()
            .getMatkulAll().enqueue(object : Callback<ResponseMatkul?> {
                override fun onFailure(call: Call<ResponseMatkul?>, t: Throwable) {
                    Toast.makeText(this@MainMatkulActivity, t.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<ResponseMatkul?>,
                    response: Response<ResponseMatkul?>
                ) {
                    rvMatkul.apply {
                        layoutManager = LinearLayoutManager(this@MainMatkulActivity)
                        adapter = ResponseMatkulAdapter(response.body()?.data as List<DataItem>?)

                    }
                }
            })

        fabAddMatkul.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this@MainMatkulActivity, AddMatkulActivity::class.java)
            startActivity(intent)
        })
    }
}