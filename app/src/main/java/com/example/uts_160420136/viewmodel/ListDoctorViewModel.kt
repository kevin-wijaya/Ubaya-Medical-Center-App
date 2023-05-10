package com.example.uts_160420136.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uts_160420136.model.Doctor

class ListDoctorViewModel: ViewModel() {
    val doctorsLD = MutableLiveData<List<Doctor>>()
    val loadingDoneLD = MutableLiveData<Boolean>()
    val loadingErrorLD = MutableLiveData<Boolean>()

    fun load() {
        val doctor1 = Doctor("6", "Dr. Effendy Gunawan Sp.OG", "Sp. Kandungan & Kebidanan", "dr. Effendy Gunawan, Sp.OG adalah Dokter Kandungan yang berpraktik di RS Medika BSD. Beliau menempuh pendidikan Spesialis Obstetri dan Ginekologi di Universitas Sam Ratulangi. Selain itu, beliau juga tergabung dalam Ikatan Dokter Indonesia (IDI) dan Perkumpulan Obstetri dan Ginekologi Indonesia (POGI). Adapun layanan medis yang dapat beliau berikan meliputi : Konsultasi mengenai kesehatan Kebidanan dan Kandungan.", " Sarjana Kedokteran, Universitas Kristen Maranatha, 2008\nSp-1 Ilmu Kebidanan dan Penyakit Kandungan, Universitas Sam Ratulangi, 2018", "Tangerang, Banten, Indoesia", 15, 504, 98.0       , "16 Mei 2023", "10.00 AM", "https://d1e8la4lqf1h28.cloudfront.net/thumbnails/b083b59a-d254-40d4-98c1-c88ab0efae5b_doctor_thumbnail_url.webp")
        val doctor2 = Doctor("5", "Drg. Felinda Gunawan M.Sc", "Dokter GIgi", "drg. Felinda Gunawan merupakan seorang Dokter Gigi Umum. Saat ini, beliau berpraktik di Beaudent West. Beliau dapat membantu layanan Konsultasi gigi umum.", "Sarjana Pendidikan Dokter Gigi, Universitas Hang Tuah, 2016", "Surabaya, Jawa Timur, Indonesia", 6, 545, 96.0, "14 Mei 2023", "08.00 AM", "https://res.cloudinary.com/dk0z4ums3/image/upload/v1643336274/image_doctor/Drg.Felinda%20Gunawan.jpg.jpg")
        val doctor3 = Doctor("4","Dr. Harry Pribadi Sp.JP, FIHA","Sp. Jantung & Pembuluh Darah","Dr. Harry Pribadi Sp.JP, FIHA adalah seorang spesialis jantung dan pembuluh darah dengan pengalaman selama 6 tahun dalam memberikan pelayanan kesehatan kepada pasien. Dia lulus dari Universitas Kristen Maranatha pada tahun 2012 dengan gelar Sarjana Kedokteran, dan telah meraih Spesialis-1 Ilmu Penyakit Jantung dari Universitas Sam Ratulangi pada tahun 2022.","Sarjana Kedokteran, Universitas Kristen Maranatha, 2012\nSp-1 Ilmu Penyakit Jantung, Universitas Sam Ratulangi, 2022","Semarang, Jawa Tengah, Indonesia",6,294,100.0,"15 Mei 2023","08.00 AM","https://res.cloudinary.com/dk0z4ums3/image/upload/v1643336274/image_doctor/Drg.Felinda%20Gunawan.jpg.jpg")

        doctorsLD.value = arrayListOf<Doctor>(doctor1, doctor2, doctor3, doctor1, doctor2, doctor3, doctor1, doctor2, doctor3)
        loadingDoneLD.value = true
        loadingErrorLD.value = false

    }
}