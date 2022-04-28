package com.apm.trackify.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItem(
    val userName: String,
    val following: List<String>,
    val routes: List<String>,
    val followers: Long
) : Parcelable