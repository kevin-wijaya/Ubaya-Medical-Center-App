package com.example.uts_160420136.view

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.ActivityMainBinding
import com.example.uts_160420136.model.*
import com.example.uts_160420136.viewmodel.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var doctorViewModel:ListDoctorViewModel
    private lateinit var pillViewModel:ListPillViewModel
    private lateinit var serviceViewModel:ListServiceViewModel
    private lateinit var listArticleViewModel:ListArticleViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var dataBinding:ActivityMainBinding
    private lateinit var shared:SharedPreferences
    var doctorList:List<Doctor> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        val v = dataBinding.root
        setContentView(v)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment

        //menginisialisasi navhost terhadap navigation controller
        navController = (hostFragment).navController

        //AtionBar - NavController
        NavigationUI.setupActionBarWithNavController(this, navController, dataBinding.drawerLayout)

        //Setup NavView agar dihandle oleh navController
        NavigationUI.setupWithNavController(dataBinding.navView, navController)

        //BottomNav - Agar dapat pindah fragment sesuai dengan navController
        dataBinding.bottomNav.setupWithNavController(navController)

        // botomNav dan drawerLayout GONE apabila tidak berada home, search, profile
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.itemHome ||
                destination.id == R.id.itemSearch ||
                destination.id == R.id.itemService ||
                destination.id == R.id.itemProfile) {
                dataBinding.bottomNav.visibility = View.VISIBLE
                dataBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
            else {
                dataBinding.bottomNav.visibility = View.GONE
                dataBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        doctorViewModel = ViewModelProvider(this).get(ListDoctorViewModel::class.java)
        doctorViewModel.load()
        pillViewModel = ViewModelProvider(this).get(ListPillViewModel::class.java)
        pillViewModel.getPills()
        serviceViewModel = ViewModelProvider(this).get(ListServiceViewModel::class.java)
        serviceViewModel.selectServices()
        listArticleViewModel = ViewModelProvider(this).get(ListArticleViewModel::class.java)
        listArticleViewModel.selectArticles()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.checkUser()

        shared = this.getSharedPreferences("com.example.uts_160420136", Context.MODE_PRIVATE)
        var statusSeeding = shared.getInt("SEED",0)
        if(statusSeeding == 0) {
            var editor:SharedPreferences.Editor = shared.edit()
            editor.putInt("SEED", 1)
            editor.apply()
            setDoctor()
            setPill()
            setServices()
            setArticles()
            setUser()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        return NavigationUI.navigateUp(navController, drawerLayout)

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun setDoctor(){
        val d = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMMM yyyy")
        val timesFormat = SimpleDateFormat("HH")
        val date = dateFormat.format(d.time)
        val times = timesFormat.format(d.time)

        var time = ""

        if (times < "12" && times >= "10"){
            time = "10:00 AM"
        } else if (times < "14" && times >= "12"){
            time = "12:00 AM"
        } else if (times < "16" && times >= "14"){
            time = "14:00 AM"
        } else if (times < "18" && times >= "16"){
            time = "16:00 AM"
        } else if (times < "20" && times >= "18"){
            time = "18:00 AM"
        } else {
            time = "08:00 AM"
        }

        val doctors = listOf(
            Doctor(
                "Dr. Winny Budiharto",
                "Dokter Umum",
                "Dr. Winny Budiharto merupakan dokter Umum. Ia menyelesaikan studi kedokteran di Universitas Airlangga. Sekarang, ia berpraktik di Rumah Sakit Mitra Keluarga Kenjeran, Rumah Sakit Mitra.",
                "Sarjana Kedokteran, Universitas Airlangga, 2010",
                "Surabaya, Jawa Timur, Indonesia",
                13,
                542,
                100.0,
                date.toString(),
                time,
                "https://static.guesehat.com/static/directories_thumb/CRM111201000463_Winny_Budiharto.jpg"
            ),
            Doctor(
                "Dr. Adinta Agustiao",
                "Dokter Umum",
                "Dr. Adinta Agustia adalah seorang dokter umum yang memiliki pengalaman selama 3 tahun dalam bidangnya. Ia telah melayani sebanyak 97 pasien dan mendapat rating sebesar 95.0. Dr. Adinta Agustia akan menerima janji temu pada tanggal 14 Mei 2023 pukul 12.00 PM. Ia menyelesaikan pendidikan Sarjana Pendidikan Dokter di Universitas Wijaya Kusuma Surabaya pada tahun 2020. Ia berlokasi di Sampang, Jawa Timur, Indonesia",
                "Sarjana Pendidikan Dokter, Universitas Wijaya Kusuma Surabaya, 2020",
                "Sampang , Jawa Timur, Indonesia",
                3,
                97,
                95.0,
                date.toString(),
                time,
                "https://mobile.mayapadahospital.com:3000/files/prod/master/doctor/OBSTETRIC-GYNECOLOTY---dr--Beta-Meidarifni-Adiputri-SpOG.jpg"
            ),
            Doctor(
                "Dr. Angelina Purnamasari Tanoeisan",
                "Dokter Umum",
                "Dr. Angelina Purnamasari Tanoeisan adalah seorang dokter umum dengan pengalaman selama 4 tahun dalam memberikan pelayanan kesehatan kepada pasien. Dia lulus dari Universitas Sam Ratulangi pada tahun 2019 dengan gelar Sarjana Pendidikan Dokter.",
                "Sarjana Pendidikan Dokter, Universitas Sam Ratulangi, 2019",
                "Manado, Sulawesi Utara, Indonesia",
                4,
                169,
                100.0,
                date.toString(),
                time,
                "https://d1e8la4lqf1h28.cloudfront.net/images/436220_23-11-2022_12-57-8.jpeg"
            ),
            Doctor(
                "Dr. Harry Pribadi Sp.JP, FIHA",
                "Sp. Jantung & Pembuluh Darah",
                "Dr. Harry Pribadi Sp.JP, FIHA adalah seorang spesialis jantung dan pembuluh darah dengan pengalaman selama 6 tahun dalam memberikan pelayanan kesehatan kepada pasien. Dia lulus dari Universitas Kristen Maranatha pada tahun 2012 dengan gelar Sarjana Kedokteran, dan telah meraih Spesialis-1 Ilmu Penyakit Jantung dari Universitas Sam Ratulangi pada tahun 2022.",
                "Sarjana Kedokteran, Universitas Kristen Maranatha, 2012\n" + "Sp-1 Ilmu Penyakit Jantung, Universitas Sam Ratulangi, 2022",
                "Semarang, Jawa Tengah, Indonesia",
                6,
                294,
                100.0,
                date.toString(),
                time,
                "https://d1e8la4lqf1h28.cloudfront.net/images/424788_10-8-2022_15-55-31.jpeg"
            ),
            Doctor(
                "Drg. Felinda Gunawan M.Sc",
                "Dokter GIgi",
                "drg. Felinda Gunawan merupakan seorang Dokter Gigi Umum. Saat ini, beliau berpraktik di Beaudent West. Beliau dapat membantu layanan Konsultasi gigi umum.",
                "Sarjana Pendidikan Dokter Gigi, Universitas Hang Tuah, 2016",
                "Surabaya, Jawa Timur, Indonesia",
                6,
                545,
                96.0,
                date.toString(),
                time,
                "https://res.cloudinary.com/dk0z4ums3/image/upload/v1643336274/image_doctor/Drg.Felinda%20Gunawan.jpg.jpg"
            ),
            Doctor(
                "Dr. Effendy Gunawan Sp.OG",
                "Sp. Kandungan & Kebidanan",
                "dr. Effendy Gunawan, Sp.OG adalah Dokter Kandungan yang berpraktik di RS Medika BSD. Beliau menempuh pendidikan Spesialis Obstetri dan Ginekologi di Universitas Sam Ratulangi. Selain itu, beliau juga tergabung dalam Ikatan Dokter Indonesia (IDI) dan Perkumpulan Obstetri dan Ginekologi Indonesia (POGI). Adapun layanan medis yang dapat beliau berikan meliputi : Konsultasi mengenai kesehatan Kebidanan dan Kandungan.",
                " Sarjana Kedokteran, Universitas Kristen Maranatha, 2008\n" + "Sp-1 Ilmu Kebidanan dan Penyakit Kandungan, Universitas Sam Ratulangi, 2018",
                "Tangerang, Banten, Indoesia",
                15,
                504,
                98.0,
                date.toString(),
                time,
                "https://d1e8la4lqf1h28.cloudfront.net/thumbnails/b083b59a-d254-40d4-98c1-c88ab0efae5b_doctor_thumbnail_url.webp"
            )
        )
        doctorViewModel.doctorsLD.observe(this, Observer {
            if(it.isEmpty()) {
                doctorViewModel.addDoctor(doctors)
            }
            doctorList = it
        })
    }

    fun setPill(){
        val pills = listOf(
            Pill(
                "Paracetamol (500 mg)",
                "2 times a day",
                "After Eat"
            ),
            Pill(
                "Ibuprofen (400 mg)",
                "3 times a day",
                "After Eat"
            ),
            Pill(
                "Aspirin (81 mg)",
                "1 times a day",
                "Before Eat"
            ),
            Pill(
                "Omeprazole (20 mg)",
                "1 times a day",
                "Before Eat"
            ),
            Pill(
                "Amoxicillin (500 mg)",
                "3 times a day",
                "After Eat"
            )
        )

        pillViewModel.pillsLd.observe(this, Observer {
            if(it.isEmpty()){
                pillViewModel.addPill(pills)
            }
        })
    }

    fun setServices(){
        val services = listOf(
            Service(
                "General Check Up",
                "Layanan pemeriksaan menyeluruh untuk mengevaluasi kesehatan keseluruhan Anda.",
                R.drawable.general_checkup
            ),
            Service(
                "Dental Check Up",
                "Pemeriksaan gigi dan mulut untuk menjaga kesehatan gigi dan gusi.",
                R.drawable.dental_checkup
            ),
            Service(
                "Medical Check Up",
                "Evaluasi kesehatan komprehensif dengan berbagai pemeriksaan dan tes medis.",
                R.drawable.baseline_medical_services_24
            ),
            Service(
                "Covid 19 Test",
                "Tes deteksi Covid-19 menggunakan PCR dan rapid test.",
                R.drawable.covid_test
            ),
            Service(
                "Lab",
                "Laboratorium medis dengan beragam tes untuk diagnosis kondisi kesehatan.",
                R.drawable.laboratorium
            ),
            Service(
                "Online Shop",
                "Toko online menyediakan produk kesehatan dan perawatan berkualitas.\n\nwww.tokopedia.com/apotekubaya",
                R.drawable.sell
            )
        )

        serviceViewModel.servicesLd.observe(this, Observer {
            if(it.isEmpty()){
                serviceViewModel.addServices(services)
            }
        })
    }

    fun setArticles() {
        val articles = listOf(
            Article(
                "Kanker Serviks, Bisa Disembuhkan?",
                """
                    
                    Kanker serviks adalah salah satu penyakit kanker yang mempengaruhi wanita, dan merupakan penyebab kematian nomor dua setelah kanker payudara. Penyakit ini terkait erat dengan infeksi Human Papillomavirus (HPV), yang dapat menular melalui hubungan seksual. Faktor risiko kanker serviks termasuk kebiasaan bergonta-ganti pasangan seks, hubungan seksual di usia dini, dan merokok (aktif maupun pasif).

                    Meskipun belum ada cara pasti untuk mencegah kanker serviks, tindakan pencegahan seperti vaksinasi HPV dapat mengurangi risiko terinfeksi virus penyebab kanker ini. Selain itu, deteksi dini melalui skrining di laboratorium yang sudah terdaftar sangat penting untuk meningkatkan peluang kesembuhan. Rutin melakukan pemeriksaan dapat membantu mendeteksi sel kanker pada serviks lebih awal sehingga dapat segera diobati.

                    Selain upaya pencegahan dan deteksi dini, mengonsumsi beberapa jenis buah tertentu dapat membantu dalam pencegahan kanker serviks. Buah blueberry mengandung serat dan flavonoid tinggi yang membantu mengatur kadar gula darah serta memiliki sifat pelindung untuk membantu tubuh melawan banyak penyakit. Cherry juga bermanfaat dengan kandungan hormon melatonin yang membantu mengurangi masalah tidur atau insomia. Sementara itu, buah jeruk yang kaya akan vitamin C dapat melindungi kulit dari kerusakan dan membantu menjaga kulit tetap sehat dan bercahaya.

                    Meskipun mengonsumsi buah-buahan ini merupakan langkah yang baik dalam menjaga kesehatan secara umum, penting untuk diingat bahwa buah-buahan tidak menyembuhkan kanker serviks itu sendiri. Oleh karena itu, tetaplah melakukan langkah pencegahan yang tepat seperti vaksinasi dan pemeriksaan rutin untuk mengidentifikasi dan mengatasi kanker serviks secara dini, yang dapat meningkatkan peluang kesembuhan.
                """,
                "https://www.biofarma.co.id/media/image/originals/post/2023/07/18/cover-website.jpg",
                "Selasa, 18 Juli 2023",
            ),
            Article(
                "Polio - Gejala, Penyebab, dan Pencegahan",
                """
                    Poliomyelitis, atau polio, adalah penyakit virus yang sangat menular yang paling sering menyerang anak-anak di bawah usia 5 tahun. Virus polio masuk ke tubuh melalui makanan atau air yang terkontaminasi dengan feses orang yang terinfeksi. Setelah masuk ke tubuh, virus ini berkembang biak di usus dan kemudian menyerang sistem saraf, menyebabkan gejala seperti demam, kelelahan, sakit kepala, dan kelumpuhan. Beberapa kasus polio tidak menunjukkan gejala yang jelas, tetapi di kasus lain, kelumpuhan dapat menjadi permanen dan mengancam nyawa.

                    Sejak diluncurkan pada tahun 1988, upaya global untuk pemberantasan polio telah berhasil mengurangi kasus polio liar hingga lebih dari 99%, dengan virus polio tipe 2 diberantas pada tahun 1999 dan tipe 3 pada tahun 2020. Namun, belum lama ini, sebuah kasus polio positif dilaporkan pada seorang balita perempuan, mengingatkan kita semua akan pentingnya tetap waspada dan melakukan tindakan pencegahan seperti imunisasi atau vaksinasi polio.

                    Tidak ada pengobatan khusus untuk polio, namun, polio dapat dicegah dengan vaksinasi. Vaksin polio dapat melindungi anak seumur hidup dengan pemberian berkali-kali. Imunisasi polio telah mencegah lebih dari 20 juta orang dari kelumpuhan sejak tahun 1988. Pencegahan dan pemberantasan polio hanya dapat dicapai dengan upaya global untuk memvaksinasi seluruh populasi, menghentikan penularan virus, dan memastikan keberlanjutan pendekatan strategis dalam mengatasi polio.

                    Maka dari itu, penting bagi setiap individu, terutama orang tua, untuk memastikan bahwa anak-anak mereka divaksinasi melawan polio. Dengan memahami cara penularan dan gejala polio, serta pentingnya imunisasi, kita dapat bersama-sama melawan dan mencegah penyebaran penyakit ini. Berbagi informasi kesehatan ini kepada teman, keluarga, dan orang terdekat juga dapat membantu meningkatkan kesadaran akan pentingnya vaksinasi dan upaya pemberantasan polio.
                """.trimIndent(),
                "https://www.biofarma.co.id/media/image/originals/post/2023/03/30/polio-gejala.png",
                "Kamis, 30 Maret 2023",
            ),
            Article(
                "Kanker - Gejala, penyebab, dan pencegahan",
                """
                    Kanker adalah kelompok besar penyakit yang ditandai oleh pertumbuhan sel-sel abnormal di dalam tubuh, yang dapat menyerang berbagai bagian tubuh. Penyakit ini merupakan salah satu penyebab kematian terbanyak di dunia setelah stroke dan serangan jantung. Di Indonesia, jumlah kasus kanker terus meningkat, dan beberapa jenis kanker yang umum terjadi pada pria adalah kanker paru-paru, prostat, kolorektal, perut, dan hati, sementara pada wanita, kanker payudara, kolorektal, paru-paru, serviks, dan tiroid menjadi yang paling umum.

                    Penyebab terjadinya kanker adalah mutasi genetik pada sel yang menyebabkan pertumbuhan sel-sel tidak normal. Faktor risiko internal melibatkan riwayat keluarga dengan kanker, sedangkan faktor risiko eksternal termasuk usia di atas 65 tahun, merokok, mengkonsumsi alkohol secara berlebihan, pola makan tidak sehat, dan kurangnya aktivitas fisik. Infeksi kronis juga dapat meningkatkan risiko kanker, seperti infeksi HPV yang terkait dengan kanker serviks.

                    Gejala kanker bervariasi tergantung pada jenis dan organ yang terkena, tetapi beberapa gejala umum meliputi benjolan, nyeri, kelemahan, pucat, demam, dan perubahan kulit. Deteksi dini kanker sangat penting untuk meningkatkan peluang kesembuhan, dan antara 30% hingga 50% kanker dapat dicegah dengan menghindari faktor risiko dan melakukan skrining secara rutin.

                    Pencegahan kanker melibatkan menghindari faktor risiko dan melakukan pemeriksaan rutin untuk deteksi dini. Jika memiliki risiko tinggi, seperti merokok atau mengkonsumsi alkohol, sebaiknya segera berkonsultasi dengan dokter dan melakukan skrining kanker yang dianjurkan. Deteksi dini dengan pemeriksaan seperti pap smear dan CerviScan untuk kanker serviks dapat meningkatkan kesempatan pengobatan yang efektif. Dengan upaya pencegahan dan deteksi dini, kita dapat mengurangi beban kanker dan meningkatkan kualitas hidup serta peluang kesembuhan bagi penderita kanker.
                """,
                "https://www.biofarma.co.id/media/image/originals/post/2023/02/06/website-rev-1.png",
                "Senin, 6 Februari 2023",
            ),
            Article(
                "Kenali Kanker Neuroblastoma dan Gejalanya",
                """
                    Neuroblastoma merupakan salah satu jenis kanker langka yang sangat mematikan, terutama bagi anak-anak, tetapi juga dapat terjadi pada orang dewasa. Kanker ini berasal dari neuroblast, yaitu sel-sel saraf yang belum matang, yang seharusnya berkembang menjadi sel saraf, namun malah membentuk tumor padat. Neuroblastoma sering menyerang anak-anak yang baru lahir, tetapi juga bisa muncul pada usia lebih tua, sekitar 2-4 tahun.

                    Kanker ini memiliki gejala dan gejala dapat bervariasi tergantung pada bagian tubuh yang terkena. Misalnya, jika neuroblastoma menyerang perut, penderita dapat mengalami sakit perut yang berkepanjangan, kulit yang terasa keras ketika disentuh, diare, dan sembelit. Jika menyerang dada, gejala dapat meliputi nyeri dada yang berkepanjangan, sesak nafas dengan mengi, serta perubahan ukuran pupil dan penurunan pada kelopak mata.

                    Kanker neuroblastoma juga dapat menyerang saraf tulang belakang, menyebabkan tubuh terasa lemah, pincang, kelumpuhan, serta gangguan buang air kecil dan besar. Selain gejala pada bagian tubuh yang terkena, penderita juga bisa mengalami gejala lain seperti demam, penurunan berat badan secara drastis, nyeri tulang, adanya benjolan di bawah kulit, proptosis (menonjolnya bola mata dari rongga), serta lingkaran hitam seperti memar di sekitar mata.

                    Dengan karakteristik agresifnya, neuroblastoma dapat menyebar dengan cepat ke berbagai organ tubuh, seperti kelenjar getah bening, hati, kulit, tulang, dan paru-paru. Oleh karena itu, penting untuk mengenali gejala-gejala kanker langka ini dan jika ada gejala yang mencurigakan, segera kunjungi dokter untuk pemeriksaan lebih lanjut. Melalui kesadaran tentang penyakit ini, kita diingatkan untuk selalu menjaga kesehatan dan kebugaran serta memberikan dukungan dan perhatian pada penderita neuroblastoma.
                """,
                "https://www.biofarma.co.id/media/image/originals/post/2023/02/03/kanker-neuroblastoma-mobile.png",
                "Jumat, 3 Februari 2023",
            ),
            Article(
                "Selain dianggap berbahaya bagi kesehatan, ternyata makanan pedas juga memiliki dampak baik untuk kesehatan lho!",
                """
                    Mengkonsumsi makanan pedas selama ini sering dianggap berisiko bagi kesehatan tubuh karena dikaitkan dengan masalah sistem pencernaan seperti maag dan asam lambung naik. Namun, sebenarnya terdapat manfaat yang jarang kita sadari dari mengonsumsi makanan pedas. Misalnya, cabai yang menjadi bahan dasar makanan pedas, mengandung vitamin C, vitamin A, dan zat antioksidan yang dapat meningkatkan sistem kekebalan tubuh dan melindungi tubuh dari serangan penyakit.

                    Selain itu, rasa pedas yang dihasilkan oleh senyawa capsaicin pada cabai, dapat meningkatkan temperatur tubuh dan mempercepat kerja metabolisme, membantu dalam menurunkan berat badan dengan lebih cepat membakar kalori dalam tubuh. Lebih menarik lagi, kandungan capsaicin juga dapat membantu mencegah kanker dengan menghambat pertumbuhan sel kanker tanpa merusak sel sehat di sekitarnya. Selain manfaat tersebut, capsaicin pada cabai ternyata juga efektif dalam melawan inflamasi, yang berarti makanan pedas dapat mendukung kesehatan jantung.

                    Namun, perlu diingat bahwa mengonsumsi makanan pedas harus dilakukan dengan takaran yang tepat dan sesuai dengan kebutuhan tubuh. Mengonsumsi secara berlebihan tetap dapat berdampak buruk bagi kesehatan. Jadi, selama kita mampu mengatur porsi dan tidak berlebihan, manfaat dari makanan pedas dapat memberikan dukungan positif bagi kesehatan tubuh secara keseluruhan.
                """,
                "https://www.biofarma.co.id/media/image/originals/post/2023/01/12/makanan-pedas.jpeg",
                "Kamis, 12 Januari 2023"
            )
        )
        listArticleViewModel.articlesLd.observe(this, Observer {
            if (it.isEmpty()) {
                listArticleViewModel.addArticles(articles)
            }
        })
    }

    fun setUser() {
        var admin = User(
            "ubaya",
            "ubaya",
            "ubaya",
            "ubaya@ubaya.ac.id",
            "1968-03-11",
            "(031) 2981005",
            "Jl. Raya Kalirungkut, Kali Rungkut, Kec. Rungkut, Surabaya, Jawa Timur 60293",
            "-",
            "https://www.ubaya.ac.id/wp-content/uploads/sites/20/2023/05/logoUbaya200.png",
            null,
            null
        )
        userViewModel.usersLD.observe(this, Observer {
            if(it.isEmpty()){
                userViewModel.addUser(admin)
            }
        })
    }
}