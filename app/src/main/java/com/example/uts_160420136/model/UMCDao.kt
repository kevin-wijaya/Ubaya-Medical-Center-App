package com.example.uts_160420136.model

import androidx.room.*

@Dao
interface UMCDao {
    //USER
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user:User)

    @Query("SELECT * FROM 'user' WHERE userId=:id")
    fun selectUser(id:Int) : User

    @Query("SELECT * FROM 'user'")
    fun selectUsers() : List<User>

    @Query("SELECT * FROM 'user' WHERE username=:username AND password=:password")
    fun CheckUserLogin(username:String, password:String) : User

    @Query("SELECT * from 'user' WHERE username=:username")
    fun CheckUserRegister(username:String) : User

    @Update
    fun updateUser(user:User)
    //USER

    //DOCTOR
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(vararg doctor:Doctor)

    @Query("SELECT * FROM 'doctor'")
    fun selectDoctor() : List<Doctor>

    @Query("SELECT * FROM 'doctor' WHERE doctorId=:id")
    fun selectDoctorById(id:Int) : Doctor
    //DOCTOR

    //PILL
    @Query("SELECT * FROM 'pill'")
    fun getAllPills() : List<Pill>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPill(vararg pill: Pill)
    //PILL

    //USER WITH PILL
    @Transaction
    @Query("Select * FROM 'user' WHERE userId=:id")
    fun getUserPill(id:Int) : List<UserWithPills>

    @Transaction
    @Query("INSERT INTO 'userpillcrossref'('userId', 'pillId') VALUES(:userId, :pillId)")
    fun addUserPill(userId:Long, pillId:Long)
    //USER WITH PILL

    //USER WITH REPORT
    @Transaction
    @Query("Select * FROM 'user' WHERE userId=:id")
    fun getUserReport(id:Int) : UserWithReport

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
    fun getUserAppointment(id:Int) : UserWithDoctorAppointment

    @Transaction
    @Query("UPDATE 'user' SET doctorId=:doctorId , appointmentUser=:dateTimeAppointment WHERE userId=:userId")
    fun addAppointment(userId:Int, doctorId:Int?, dateTimeAppointment:String?)
    //USER WITH DOCTOR APPOINTMENT

    //SERVICES
    @Query("SELECT * FROM 'service'")
    fun selectServices() : List<Service>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertService(vararg service: Service)
    //SERVICES

    //ARTICLE
    @Query("SELECT * FROM 'article'")
    fun selectArticles() : List<Article>

    @Query("SELECT * FROM 'article' WHERE artileId=:id")
    fun selectArticle(id:Int) : Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(vararg article: Article)
    //ARTICLE
}