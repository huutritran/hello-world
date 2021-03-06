package com.ggg.common.ws

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

/**
 * Created by TuanNguyen on 12/12/17.
 */
class ApiResponse<T> {

    var code: Int = 500
    var body: T? = null
    var errorMessage: String? = "" // the message to dev

    constructor(e: Throwable) {
        this.code = 500
        this.body = null
        errorMessage = e.message
    }


    fun isSuccessful(): Boolean = code in 200..299

    constructor(response: Response<T>) {

        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            response.errorBody()?.let {
                val errorBody = it.string()
                try {

                    val jsonObj: JSONObject = JSONObject(errorBody)
                    when {
                        jsonObj.has("error") -> message = jsonObj.getString("error")
                        jsonObj.has("Message") -> message = jsonObj.getString("Message")
                        else -> message = it.string()
                    }
                } catch(e: JSONException) {
                    message = "Invalid data format"
                } catch (ignored: IOException) {
                    message = "Invalid data format"
                }

            }

            if (message == null || message!!.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }

    }


}