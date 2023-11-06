package satta.win

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface WikiApiService {
    @POST("api.php")
    fun hitCountCheck(@Query("token") token: String,@Query("phone") phone: String): Observable<TokenBean>

    companion object {


        var gson = GsonBuilder()
            .setLenient()
            .create()
        var okHttpClient = OkHttpClient()
        fun create(): WikiApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://sattawin.in/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }

}