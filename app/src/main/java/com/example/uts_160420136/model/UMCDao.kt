package com.example.uts_160420136.model

import androidx.room.*

@Dao
interface UMCDao {
    //USER
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user:User)

    @Query("SELECT * FROM 'user' WHERE userId=:id")
    fun selectUser(id:String) : User

    @Update
    fun updateUser(user:User)
    //USER

    //DOCTOR
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(vararg doctor:Doctor)

    @Query("SELECT * FROM 'doctor'")
    fun selectDoctor() : List<Doctor>

    @Query("SELECT * FROM 'doctor' WHERE doctorId=:id")
    fun selectDoctorById(id:String) : Doctor
    //DOCTOR

    //USER WITH PILL
    @Transaction
    @Query("Select * FROM 'user' WHERE userId=:id")
    fun getUserPill(id:String) : List<UserWithPills>
    //USER WITH PILL

    //USER WITH REPORT
    @Transaction
    @Query("Select * FROM 'user' WHERE userId=:id")
    fun getUserReport(id:String) : UserWithReport

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReport(report: Report)

    @Transaction
    @Delete
    fun deleteReport(report: Report)
    //USER WITH REPORT

    //USER WITH DOCTOR APPOINTMENT
    @Transaction
    @Query("SELECT * FROM 'user' WHERE userId=:id")
    fun getUserAppointment(id:String) : UserWithDoctorAppointment

    @Transaction
    @Query("UPDATE 'user' SET doctorId=:doctorId , appointmentUser=:dateTimeAppointment WHERE userId=:userId")
    fun addAppointment(userId:String, doctorId:String?, dateTimeAppointment:String?)
    //USER WITH DOCTOR APPOINTMENT
}