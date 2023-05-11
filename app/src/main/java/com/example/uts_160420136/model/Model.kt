package com.example.uts_160420136.model

import com.google.gson.annotations.SerializedName

data class Doctor (
    val id: Int?,
    val name: String?,
    val specialist: String?,
    val bio: String?,
    val educations: String?,
    val address: String?,
    val experience: Int?,
    val patients: Int?,
    val rating: Double?,
    @SerializedName("appointment_date")
    val appointmentDate: String?,
    @SerializedName("appointment_time")
    val appointmentTime: String?,
    @SerializedName("photo_url")
    val photoUrl: String?,
)

data class User (
    val id: Int?,
    val name: String?,
    val username: String?,
    val password: String?,
    val gmail: String?,
    @SerializedName("birth_of_date")
    val bod: String?,
    @SerializedName("number_phone")
    val numberPhone: String?,
    val address: String?,
    @SerializedName("blood_type")
    val bloodType: String?,
    @SerializedName("photo_url")
    val photoUrl: String?,
)
data class UserReport (
    val id: Int?,
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("heart_rate")
    val heartRate: Int?,
    val thrombocyte: Int?,
    @SerializedName("blood_type")
    val bloodType: String?,
    val systolik: Int?,
    val diastolik: Int?,
)

data class Pill (
    val name: String?,
    val frequency: String?,
    val time: String?,
)