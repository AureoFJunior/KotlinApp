package br.com.interativo.teste

import android.os.Parcel
import android.os.Parcelable

class userClass(var Username: String, var Password: String) : Parcelable {

    var Id: Int = 0
    //var Username: String = ""
    //var Password: String = ""

    constructor(parcel: Parcel)  : this("","") {
        Id = parcel.readInt()
        Username = parcel.readString()!!
        Password = parcel.readString()!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(Username)
        parcel.writeString(Password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<userClass> {
        override fun createFromParcel(parcel: Parcel): userClass {
            return userClass(parcel)
        }

        override fun newArray(size: Int): Array<userClass?> {
            return arrayOfNulls(size)
        }
    }


}