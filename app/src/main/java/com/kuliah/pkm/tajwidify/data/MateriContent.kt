package com.kuliah.pkm.tajwidify.data

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Parcelize
data class MateriContent(
    @DocumentId
    val materiContentId: String,
    val page: Long,
    val title: String,
    val materi: String
) : Parcelable {
    constructor() : this("", 0L, "", "")
}
