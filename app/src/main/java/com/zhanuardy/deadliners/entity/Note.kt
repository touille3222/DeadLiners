package com.zhanuardy.deadliners.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var value: String? = null,
    var timing: String? = null,
    var date: String? = null,
    var time: String? = null
) : Parcelable