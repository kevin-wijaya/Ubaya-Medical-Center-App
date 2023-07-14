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
    var appointmentDate: String?,
    var appointmentTime: String?,
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
    var bod: String?,
    var numberPhone: String?,
    var address: String?,
    var bloodType: String?,
    var photoUrl: String?,
){
    @PrimaryKey(autoGenerate = true)
    var userId:Int = 0
}

@Entity
data class Report (
    var userId: Int?,
    var heartRate: Int?,
    var thrombocyte: Int?,
    var bloodType: String?,
    var systolik: Int?,
    var diastolik: Int?,
){
    @PrimaryKey(autoGenerate = true)
    var reportId:Int = 0
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

data class UserWithReport(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val report:Report
)