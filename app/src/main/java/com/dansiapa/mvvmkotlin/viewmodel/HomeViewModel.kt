package com.dansiapa.mvvmkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dansiapa.mvvmkotlin.api.ApiClient
import com.dansiapa.mvvmkotlin.api.RestInterface
import com.dansiapa.mvvmkotlin.model.News
import com.dansiapa.mvvmkotlin.model.TotalNews
import com.dansiapa.mvvmkotlin.utils.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeViewModel : ViewModel(){

    private var newsLiveData: MutableLiveData<List<News>?>? = null
    private var newsList: MutableList<News>? = null
    private var countryCode: String? = null
    private var apiKey: String? = null

    fun MainViewModel() {
        newsLiveData = MutableLiveData<List<News>?>()
        newsList = ArrayList<News>()
    }

    fun setApiKey(apiKey: String?) {
        this.apiKey = apiKey
    }

    fun setCountryCode(countryCode: String?) {
        this.countryCode = countryCode
        getNews(countryCode, "")
    }

    fun getNewsLiveData(): MutableLiveData<List<News>?>? {
        return newsLiveData
    }

    private fun getRestInterface(): RestInterface? {
        val restInterface: Array<RestInterface?> = arrayOfNulls<RestInterface>(1)
        restInterface[0] = ApiClient.getClient(Util.API_BASE_URL).create(RestInterface::class.java)
        return restInterface[0]
    }

    private fun getNews(langCode: String?, category: String) {
        val restInterface: RestInterface? = getRestInterface()
        val call: Call<TotalNews>
        newsList!!.clear()
        newsLiveData!!.setValue(null)
        call = if (category != "") {
            restInterface.getTotalNews(langCode, category, apiKey)
        } else {
            restInterface.getTotalNews(langCode, apiKey)
        }
        call.enqueue(object : Callback<TotalNews?> {
            override fun onResponse(call: Call<TotalNews?>, response: Response<TotalNews?>) {
                if (response.body() != null) {
                    val totalNews: TotalNews? = response.body()
                    fillNewsList(totalNews)
                }
            }

            override fun onFailure(call: Call<TotalNews?>, t: Throwable) {
                newsLiveData!!.setValue(null)
            }
        })
    }

    private fun getSearchedNews(keyword: String) {
        val restInterface: RestInterface? = getRestInterface()
        val call: Call<TotalNews>
        newsList!!.clear()
        newsLiveData!!.setValue(null)
        call = restInterface.getSearchedTotalNews(keyword, apiKey)
        call.enqueue(object : Callback<TotalNews?> {
            override fun onResponse(call: Call<TotalNews?>, response: Response<TotalNews?>) {
                if (response.body() != null) {
                    val totalNews: TotalNews? = response.body()
                    fillNewsList(totalNews)
                }
            }

            override fun onFailure(call: Call<TotalNews?>, t: Throwable) {
                newsLiveData!!.setValue(null)
            }
        })
    }

    private fun fillNewsList(totalNews: TotalNews?) {
        newsList!!.addAll(totalNews.getNewsList())
        newsLiveData!!.value = newsList
    }

    fun newsCategoryClick(category: Any) {
        getNews(countryCode, category.toString())
    }

    fun searchNews(keyword: String) {
        getSearchedNews(keyword)
    }
}
}