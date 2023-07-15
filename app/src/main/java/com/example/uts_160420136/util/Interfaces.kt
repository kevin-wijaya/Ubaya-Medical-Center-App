package com.example.uts_160420136.util

import android.view.View
import com.example.uts_160420136.model.User

interface ButtonEditUser{
    fun onSaveClick(view:View, user:User)
}

interface ButtonDetailDoctor{
    fun onClick(view: View)
}

interface ButtonChatDoctor{
    fun onClickChat(view: View)
}

interface ButtonMakeAppointment{
    fun onClickAppointment(view: View)
}