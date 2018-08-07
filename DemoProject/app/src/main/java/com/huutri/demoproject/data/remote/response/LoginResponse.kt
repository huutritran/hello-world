package com.huutri.demoproject.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

        @field:SerializedName("authentication_token")
        val authenticationToken: String? = null,

        @field:SerializedName("id")
        val id: String? = null
)