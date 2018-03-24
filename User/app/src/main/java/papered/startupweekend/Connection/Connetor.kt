package papered.startupweekend.Connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by PaperEd on 2018-03-24.
 */
object Connetor {
    var api: API

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("baseURL will here")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(API::class.java)
    }
}