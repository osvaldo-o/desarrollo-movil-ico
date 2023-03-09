package fes.aragon.playermp3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Modelo(var nameFile: String, var nameImage: Int, var path: String, var extension: String) : Parcelable{
}
