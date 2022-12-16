package ke.co.branch.core.network.utils

import ke.co.branch.core.utils.DataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(
     private val dataStore: DataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            dataStore.getToken().firstOrNull()
        }
        return if (token != null) {
            val newRequest =
                chain.request().newBuilder().addHeader("X-Branch-Auth-Token", token).build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(chain.request())
        }

    }
}