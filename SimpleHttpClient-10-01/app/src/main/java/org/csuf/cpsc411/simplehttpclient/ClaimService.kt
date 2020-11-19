package org.csuf.cpsc411.simplehttpclient

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import java.lang.reflect.Type

class ClaimService(val ctx: Context) {

    lateinit var claimList: MutableList<Claim>

    inner class GetAllServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                Log.d("Person Service", "The response JSON string is ${String(responseBody)}")
                val gson = Gson()
                val claimListType: Type = object : TypeToken<List<Claim>>() {}.type
                claimList = gson.fromJson(String(responseBody), claimListType)
                Log.d("Claim Service", "The Claim List: ${claimList}")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            TODO("Not yet implemented")
        }
    }

    inner class addServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                val respStr = String(responseBody)
                Log.d("Claim Service", "The add Service response : ${respStr}")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            TODO("Not yet implemented")
        }
    }

    //this function is used to add a claim to the claim database
    fun addClaim(pObj: Claim) {
        val client = AsyncHttpClient()
        val requestUrl = "http://192.168.138.224:8080/ClaimService/add"
        val pJsonString = Gson().toJson(pObj)
        val entity = StringEntity(pJsonString)
        client.post(ctx, requestUrl, entity, "application/json", addServiceRespHandler())
    }

    fun getAll() {
        val client = AsyncHttpClient()
        val requestUrl = "http://192.168.138.224:8080/ClaimService/GetAll"
        Log.d("Claim Service", "About Sending the HTTP Request. ")
        client.get(requestUrl, GetAllServiceRespHandler())
    }
}