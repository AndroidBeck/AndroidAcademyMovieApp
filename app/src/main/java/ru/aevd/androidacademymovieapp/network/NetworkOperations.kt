package ru.aevd.androidacademymovieapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.aevd.androidacademymovieapp.BuildConfig
import ru.aevd.androidacademymovieapp.data.Movie

class NetworkOperations {
    private val coroutineContext = Job() + Dispatchers.IO

    //TODO 5: ? add exceptionHandler
    suspend fun loadMovies(): List<Movie> = withContext(coroutineContext) {
        RetrofitModule.moviesApi.getMovies()
    }
}

private class MoviesApiHeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalRequestUrl = originalRequest.url
        val request = originalRequest.newBuilder()
                .url(originalRequestUrl)
                .addHeader(API_KEY_HEADER, apiKey)
                .build()
        return  chain.proceed(request = request)
    }

}

private object RetrofitModule {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    //TODO 2: remove logging interceptor in prod
    private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(MoviesApiHeaderInterceptor())
            .build()

    //@ExperimentalSerializationApi
    @Suppress("EXPERIMENTAL_API_USAGE")
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(getBaseUrl())
        //TODO 3: check addConverterFactory
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    //TODO 1: you can do it better - watch in the end of the lecture
    val moviesApi: MoviesApi = retrofit.create(MoviesApi::class.java)
}

private const val API_KEY_HEADER = "api_key"
const val apiKey = "dabd3c0c727815f7c80ed545b298933b"
fun getApiKey(): String = apiKey
fun getBaseUrl(): String = BuildConfig.BASE_URL
private val TAG = NetworkOperations::class.java.simpleName