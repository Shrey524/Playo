package com.example.playo.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playo.R
import com.example.playo.adapter.RecyclerAdapter
import com.example.playo.models.Api
import com.example.playo.models.ArticlesItem
import com.example.playo.models.SortedApiData
import com.example.playo.network.Client
import kotlinx.android.synthetic.main.activity_home_page.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePage : AppCompatActivity() {

    private val BaseUrl:String = "https://newsapi.org/v2/"
    private val ApiKey: String = "fddaf730520f441f900ce00b7aa006f1"

    private lateinit var adapter: RecyclerAdapter

    var recyclerDataList : List<SortedApiData> = ArrayList()

    //creating a list from the API response
    private fun createList(data: Api?): List<SortedApiData> {
        val dataList : ArrayList<SortedApiData> = ArrayList()
        var response : List<ArticlesItem> = data?.articles as List<ArticlesItem>

        for(items in response){
            var title : String = items.title
            var author : String
            if(items.author!=null){
               author  = items.author
            }else{
                continue
            }
            var description : String
            if(items.description!=null){
                description  = items.description
            }else{
                continue
            }
            var photo : String
            if(items.urlToImage!=null){
                photo  = items.urlToImage
            }else{
                continue
            }
            var url : String = items.url

            var sortedApiData = SortedApiData(title,author,description,photo,url)
            dataList.add(sortedApiData)
        }
        return dataList
    }

    // function for apiCall which will create the recyclerView if there is a response
    private fun apiCall(search: String, item_search: Boolean) {

        var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: Client = retrofit.create(Client::class.java)
        if (!item_search){
            val call: retrofit2.Call<Api?>? = api.getResponse("techcrunch",ApiKey)

            call?.enqueue(object : Callback<Api?> {
                override fun onFailure(call: retrofit2.Call<Api?>, t: Throwable?) {
                    Toast.makeText(this@HomePage, "Failure in getting api response", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: retrofit2.Call<Api?>, response: Response<Api?>) {
                    val data: Api? = response.body()
                    news_recycler.visibility = View.VISIBLE
                    if(data == null){
                        Toast.makeText(this@HomePage, "Can't find any news", Toast.LENGTH_LONG).show()
                    }
                    //Creating the 2nd RecyclerView
                    recyclerDataList = createList(data)
                    adapter = RecyclerAdapter(this@HomePage, recyclerDataList)
                    val mLayoutManager = LinearLayoutManager(this@HomePage, LinearLayoutManager.VERTICAL, false)
                    news_recycler.layoutManager = mLayoutManager
                    news_recycler.itemAnimator = DefaultItemAnimator()
                    news_recycler.setHasFixedSize(true)
                    news_recycler.adapter = adapter
                }
            })
        }else{
            val call: retrofit2.Call<Api?>? = api.getSearchResult(search,"2021-11-22","popularity",ApiKey)

            call?.enqueue(object : Callback<Api?> {
                override fun onFailure(call: retrofit2.Call<Api?>, t: Throwable?) {
                    Toast.makeText(this@HomePage, "Failure in getting api response", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: retrofit2.Call<Api?>, response: Response<Api?>) {
                    val data: Api? = response.body()
                    news_recycler.visibility = View.VISIBLE
                    if(data == null){
                        Toast.makeText(this@HomePage, "Can't find any news", Toast.LENGTH_LONG).show()
                    }
                    //Creating the 2nd RecyclerView
                    recyclerDataList = createList(data)
                    adapter = RecyclerAdapter(this@HomePage, recyclerDataList)
                    val mLayoutManager = LinearLayoutManager(this@HomePage, LinearLayoutManager.VERTICAL, false)
                    news_recycler.layoutManager = mLayoutManager
                    news_recycler.itemAnimator = DefaultItemAnimator()
                    news_recycler.setHasFixedSize(true)
                    news_recycler.adapter = adapter
                }
            })
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        //setting a variable to check whether an item is searched or not
        var item_search: Boolean = false
        Toast.makeText(this@HomePage, "Here are Top Headlines from TECH CRUNCH", Toast.LENGTH_LONG).show()

        //default api call and creating a recycler view
        apiCall("default", item_search)

        //changing the recyclerview when searchicon is clicked
        searchIcon.setOnClickListener {
            if(searchbar!=null){
                item_search = true
                apiCall(searchbar.text.toString(),item_search)
            }else{
                Toast.makeText(this@HomePage, "Input a category to search first", Toast.LENGTH_LONG).show()
            }
        }
    }
}