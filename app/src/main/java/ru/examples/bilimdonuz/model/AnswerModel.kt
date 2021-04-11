package ru.examples.bilimdonuz.model

import android.os.Parcel
import android.os.Parcelable

data class AnswerModel(
        var title: String? ="",
        var answerA: String? ="",
        var answerB: String? ="",
        var answerC: String? ="",
        var answer: String? =""
): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(answerA)
        parcel.writeString(answerB)
        parcel.writeString(answerC)
        parcel.writeString(answer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnswerModel> {
        override fun createFromParcel(parcel: Parcel): AnswerModel {
            return AnswerModel(parcel)
        }

        override fun newArray(size: Int): Array<AnswerModel?> {
            return arrayOfNulls(size)
        }
    }
}