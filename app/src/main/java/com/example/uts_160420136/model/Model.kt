package com.example.uts_160420136.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity
data class Doctor (
    var name: String?,
    var specialist: String?,
    var bio: String?,
    var educations: String?,
    var address: String?,
    var experience: Int?,
    var patients: Int?,
    var rating: Double?,
    @SerializedName("appointment_date")
    var appointmentDate: String?,
    @SerializedName("appointment_time")
    var appointmentTime: String?,
    @SerializedName("photo_url")
    var photoUrl: String?,
){
    @PrimaryKey(autoGenerate = true)
    var doctorId:Int = 0
}

@Entity
data class User (
    var name: String?,
    var username: String?,
    var password: String?,
    var gmail: String?,
    @SerializedName("birth_of_date")
    var bod: String?,
    @SerializedName("number_phone")
    var numberPhone: String?,
    var address: String?,
    @SerializedName("blood_type")
    var bloodType: String?,
    @SerializedName("photo_url")
    var photoUrl: String?,
){
    @PrimaryKey(autoGenerate = true)
    var userId:Int = 0
}

@Entity
data class UserReport (
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("heart_rate")
    var heartRate: Int?,
    var thrombocyte: Int?,
    @SerializedName("blood_type")
    var bloodType: String?,
    var systolik: Int?,
    var diastolik: Int?,
){
    @PrimaryKey(autoGenerate = true)
    var UserReportId:Int = 0
}

@Entity
data class Pill (
    val name: String?,
    val frequency: String?,
    val time: String?,
){
    @PrimaryKey(autoGenerate = true)
    var pillId:Int = 0
}

@Entity(primaryKeys = ["userId", "pillId"])
data class UserPillCrossRef(
    val userId:Long,
    val pillId:Long
)

data class UserWithPills(
    @Embedded val user:User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "pillId",
        associateBy = Junction(UserPillCrossRef::class)
    )
    val pill:List<Pill>
)