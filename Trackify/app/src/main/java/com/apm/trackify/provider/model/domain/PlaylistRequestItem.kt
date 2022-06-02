package com.apm.trackify.provider.model.domain

import android.os.Parcelable
import com.apm.trackify.provider.repository.data.Duration
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaylistRequestItem(
    var name: String = "",
    var duration: Duration = Duration.SHORT
) : Parcelable