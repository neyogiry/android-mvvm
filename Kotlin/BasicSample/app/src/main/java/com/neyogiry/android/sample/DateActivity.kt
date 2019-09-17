package com.neyogiry.android.sample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_date.*
import android.widget.Toast

class DateActivity : AppCompatActivity() {

    private var mViewModel: DateViewModel? = null
    private var adapter: DateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        adapter = DateAdapter(ArrayList())
        rv_dates.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_dates.adapter = adapter

        fab_add_date.setOnClickListener { mViewModel?.addDate() }

        mViewModel = ViewModelProviders.of(this).get(DateViewModel::class.java)
        mViewModel?.getLoadingStatus()?.observe(this, LoadingObserver())
        mViewModel?.getMessage()?.observe(this, messageObserver)
        mViewModel?.getDateList()?.observe(this,
            Observer { list ->
                if (list == null) return@Observer
                adapter?.updateList(list as ArrayList<String>)
            })
    }


    //Observer
    inner class LoadingObserver: Observer<Boolean> {
        override fun onChanged(isLoading: Boolean?) {
            if(isLoading == null) return
            progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
        }

    }

    private val messageObserver = Observer<String> { message ->
        if (message == null) return@Observer
        Toast.makeText(this@DateActivity, message, Toast.LENGTH_SHORT).show()
    }

}
