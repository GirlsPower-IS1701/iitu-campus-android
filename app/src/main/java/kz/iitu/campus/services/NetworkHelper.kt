package kz.iitu.campus.services

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NetworkHelper {

    public suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                val resultWrapper = when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.GenericError(code, errorResponse)
                    }
                    else -> {
                        ResultWrapper.GenericError(null, null)
                    }
                }
                resultWrapper
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}