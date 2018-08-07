package com.huutri.demoproject.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

        @field:SerializedName("user")
        val user: User? = null
)