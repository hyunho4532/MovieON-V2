package com.example.intentname

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intentname.adapter.MyAdapter
import com.example.intentname.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_movie_rank.*

@Suppress("UNCHECKED_CAST")
class MovieRankActivity : AppCompatActivity() {

    var myCalendar: Calendar = Calendar.getInstance()
    var baseUrl = "http://www.kobis.or.kr"
    var movieKey = "79087d2a1af27f32f2300b1619a278d8"
    var retrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_rank)

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitInterface = retrofit?.create(
            RetrofitInterface::class.java
        )

        val myDatePicker =
            OnDateSetListener { view, year, month, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateLabel()
            }

        cv_movie_calendar_click.setOnClickListener {
            DatePickerDialog(
                this@MovieRankActivity, myDatePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        cv_calendar_select_click.setOnClickListener {
            retrofitInterface?.getBoxOffice(movieKey, tv_calendar_select.text.toString(), 0)
                ?.enqueue(object : Callback<Map<String?, Any?>> {
                    override fun onResponse(
                        call: Call<Map<String?, Any?>>,
                        response: Response<Map<String?, Any?>>
                    ) {
                        val boxOfficeResult =
                            response.body()!!["boxOfficeResult"] as Map<*, *>?
                        val jsonList =
                            boxOfficeResult!!["dailyBoxOfficeList"] as ArrayList<*>?
                        val myAdapter = MyAdapter(jsonList as ArrayList<MutableMap<String, Any>>?)
                        day_movie_recycler.adapter = myAdapter
                    }

                    override fun onFailure(
                        call: Call<Map<String?, Any?>>,
                        t: Throwable) {

                    }
                })
        }
    }

    private fun updateLabel() {
        val myFormat = "yyyyMMdd"
        val simpleDateFormat = SimpleDateFormat(myFormat, Locale.KOREA)
        tv_calendar_select!!.text = simpleDateFormat.format(myCalendar.time)
    }
}