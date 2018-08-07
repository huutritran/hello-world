package com.huutri.demoproject.data.remote.request

import com.google.gson.annotations.SerializedName

data class User(
	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("login")
	val email: String? = null
)