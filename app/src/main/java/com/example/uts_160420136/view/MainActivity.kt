package com.example.uts_160420136.view

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uts_160420136.R
import com.example.uts_160420136.model.Doctor
import com.example.uts_160420136.viewmodel.ListDoctorViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var doctorViewModel:ListDoctorViewModel
    var doctorList:List<Doctor> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        //menginisialisasi navhost terhadap navigation controller
        navController = (hostFragment).navController

        //AtionBar - NavController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        //Setup NavView agar dihandle oleh navController
        NavigationUI.setupWithNavController(navView, navController)

        //BottomNav - Agar dapat pindah fragment sesuai dengan navController
        bottomNav.setupWithNavController(navController)

        // botomNav dan drawerLayout GONE apabila tidak berada home, search, profile
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.itemHome ||
                destination.id == R.id.itemSearch ||
                destination.id == R.id.itemProfile) {
                bottomNav.visibility = View.VISIBLE
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
            else {
                bottomNav.visibility = View.GONE
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        doctorViewModel = ViewModelProvider(this).get(ListDoctorViewModel::class.java)
        doctorViewModel.load()
        setDoctor()
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
                    "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBUWFRYWFRIZGBgYGBIYFRgYGBISGBkYGhgaGRoYGBgcIS4lHB4rIRgZJjomKy8xNTU1GiQ7QDszPy40NTEBDAwMEA8QHxISHjQhJSs2NDE0NDE0MT0xNjYxNDQxNDQ0NDQ0NDQ0MTQ0NDQ0NDQ3NDY0NDQ0NDQxNjQ1NDc/P//AABEIAOEA4QMBIgACEQEDEQH/xAAcAAEAAQUBAQAAAAAAAAAAAAAAAgEDBAUGBwj/xABDEAACAQIDBAYGCAQEBwEAAAABAgADEQQSIQUxQVEiYXGBkaEGExQyscEHUmJygtHh8EKSorIjMzTCU3N0o7Pi8RX/xAAZAQEAAwEBAAAAAAAAAAAAAAAAAQIDBAX/xAAmEQEBAAIBBAICAQUAAAAAAAAAAQIRAxIhMUETMgQi8BRCUWGh/9oADAMBAAIRAxEAPwD2aIiAiJB1uCDxBHjAreLzmNgbPy1Wvkvhh7MuVAhZWWnVzMb/AGhoNLljx0sPRFLD49qbMG/xhe6jpBS+cFQOldyCxueiLnSG3xTet/4/664ROdxG066k0iUNS6ZCtOo4KlWYjJmGoykXLAag79JhYTbNVnuNGq08GFDXanTZjiSzEA639WBYHU5RfjB/T5a26+Jzi7TrtU9nBpioC4arldqZCpTa2TMCGPrR0c2gBOu6YuydpV2VkD0wUNao7PmcMDXrKFQ5hlQCnbMb2BGmkHwZa3f5t10Cc/szadWuc65EQGkGR1ZqhzIjnpBgEPTsBlN7X0vp0Aks8sLjdVWIiQqREQEREBERAREQEREBERAREQEREBERAREQNbitlU6jZjmVrAFkepRLAbgxRhe1za+6+kuexItI01pqUswyNqGvckNe97km5N73N5myklPVl421OLwYqaPhqTagktZ92guCt72Zu4nnIf8A5iWy+x0spDLuSwXVrEZfdLa2HE3m6iF/kutNMdngqKZwlL1YJIXolb3OoXLYHr6z31GzKZyq2EpBVJyiyMFzdIlRlsLtvHfNvEI+SsR9nUi4qNSQutrMVUsLbrNa+lzbtmZEsYnEKilmNh5k8gOchS23yvyD1VG9gO0gfGc5idrO245F4Ae8e0/lMLE11RTUrOtNPr1GC38ZpOK+x1fttP8A4i/zLLqVVO5gewg/CcDgtuYWs2SjiqTvwQNlY/dDe93TLdip4qeY085b4pfFNx28TlsFtp10fpL1+8O/j3zocLiUqLmRrjjzHURwmeWNx8jIiIlQiIgIiICIiAiIgIiICIiAiIgIiICIiAiIgW6tQKpYmwAuZymNxZqNmO7+FeQ/ObD0hxWq0wftN8h8/Ca7B07m53D4zfjx1Oqoqxj8UmGpNWqC5UdFRvJO5R1kzxvbb4nGVDVqknflTXIi/VQfPeZ6HteocXXKL/l0iR1F9zHu3ePOZibJRRbIO2Y8vLbdR0cfDNbyeQHYlRbGx01BFwQeY5T1D0H9IWrp7PiDeqg6Dne6DmeLjjzGvObRcAtvdFuyabaWxSpFWiMrocykb7iVw5bjVsuGWdnS1aRRrcDu/KTw+IZGDK1j5HqI4yzsjaK4miGtZx0XX6jjeOziOoyqzu7ZRyd5XYbOxy1VuNGFsy8j+UzZxGDxhpOrcNzDmp3/AJ907RGBAINwdQeqcuePTVk4iJQIiICIiAiIgIiICIiAiIgIiICIiAlImJtStkpO32bDtOg+MTvdDlsTWzu78ybdm4eVpjbexho4aye+9kTnnbj3C57peorcgdk1u1HFTFBP4aCAkfbfd4KAfxTp5L04dluPHqy0ubEwIp01XjpmPM8ZtQglrDUplC049Ou1a9XLVSjpMzMJQpeNI25JL4bEiqP8uoVSsOAP8D9xNj1Ezo8UlmvwbXv4/vrmNtDBBlZTqrAgyOya5ehlc3ek2RzztubvUqfGdHDl/bWHNjPtCsNJ0/o7ic1PKTqmn4eHwtOaqjf3TZejj2rBeaVPJk/Oa8k3ixdZEROVJERAREQEREBERAREQEREBERAREQKTS+k1WyKv1muewD9RN1Of9JjcovIMfGw+Rl+Obyg1eE3jvnF4PEValSs6ADPWclz9VTkUDuWdgtTKjv9VHbwF/lOFTCYhcPSVGyEhczcRc3Jt3y3Pl4jbhne10Zo4pTcYhT1ZP1mXhMVWzBXQdbKdPAzjKuzsSldVR6zoxp2qesQKBYZ86sh3HMbaaWtOwwDOMyswcKei9iuZeFwRoezT4DC+PLWXd8Nu50vNNjHrubI4ReJ4zb4l7ID2TR46gzsVaoyJkY3TQlyNBmvcKN+mp3XGt6zyt6WvZWt/rGvyNm17LyOwWdMSyOwYVkYAjTp092nMqzfyzndnejrk1GrKE6JCZKlV2L5gQ93Y2AAItxzbptcNhnpthqjm+SqoY9VQFNf5ppjlrKd1LjcsbuadPVl3YBPtoHBaDE9rvYf2GUxC9IjrMyvRVc1WpU5kov3E6P9xc9868vrXI66IiciSIiAiIgIiICIiAiIgIiICIiAiIgUnM7aa9Yjkqjyv85005nbiWqk8wCPC3ymnD9kVp8WP8KsvNCv8xC/OKCBhu0G6U2i3+G5HFRfuYE/CQ2XVuJT8m/tI6/x5vG1krgxwEhicqaCZrVABeapnDHMzAXNgJzt4y8S/QHdJYVQyyuJy5LXExcLUysADmDDhJqJ4Zfso5TC2nTGRl5g26jwPcbGbIPNbtWoLGN6NbXMfiCFLr7zBMn33AC+bDwm/wDRnDhBlG5ERf34Tm8Omd6QO5ER2+9kCL/v8p1uwF6Dtza3cAPzM787+jz/AG28RE5giIgIiICIiAiIgIiICIiAiIgIiICaT0hpdFXHA2PYd3765u5ZxFEOrKdxFpON1djiHQMGQ7iDbsIsZqtl1SpKneND28ZvsRhipKnRkOh+B7DNNi6ZR8xFs2/lfjbqO/xmn5GPVjMp6bfj59NuN9s8YgcZZp4NGa+VTxF1BI7CdwmNiKebcxHG4tfzmuqeuDgesLDhdvV/2i15yYx2Y49V1tv22cpvclhybpL4Hf3ytHConuqBytoB2DcJpzicS2liOs1FA7eibmWqPrnJBrEKNDlObXkCw1MvYvlw3Gbtbx6++01eJqF2Cj+IgeJt+vdL2TKLXJtxJue8yWz6OZ83K4HadCe4G34uqRhj1ZSMM8unG1tKKhVLc93YNBOp2RTy0U6xm/m1+c5ivut3Tsqa2AHIAeE6+a9o4InERMEkREBERAREQEREBERAREQEREBERAREQND6QU8pRwPst8R85qK1JXUgjQ+I6x1zoduuBTsTqWXL28fK80CnTUG3Nbny3junTx98Vb2rSFGQ5W1HA85J8PnFrzb4igHBHvDq94TS1SyNb3h1e93rMOThsu8XVx80va+URsx+JUjstMhaOQCWV2gSbBah6gjn5S/6mo2pUoOJfQ26hv8AG0zmGWXprlyzXesLF4kKNeYVQNWZjuVRxY/vSb3Z2FKIMwAYi7AagfZB49vE3PGY+zNnqWWsyaLm9Xe+Y30zm+7Td235TZVGvoJ18eExjj5M+qoYalndRzYeA1PlOwnP7ES9Qn6q+ZNvznQTLlv7aVisREzSREQEREBERAREQEREBERAREQKSstPVA3nu4y09Y8NOs6yZLRkzHr4tV6zyHzPCYrIzasxI4Dd5SLUxaXxwntG2r2k5bpMeItyHUJZVOQ71Nj++2bGvhbqerXwmsNAg9FtPqtYj8JO7snRNa1EUekeJJ5XWn8RYy362mhu7kngoJqEfEzJpoCLMjeVvIyVwu5UXrNifBd/iJKGK20tLim4X6zBaa/1kSVy69OmUW46LHVxyK7wD1y41Rb5vebgzcPuruX49cgDfXfAuvU/QSJFhYbzvkqa8fP8pcppfqHxgZWxqioWzaXyi/Ab9/Kb2aShS03b9ZVajoeidPqnUd3KY54bu4tK3UrMSli7jVSOveJkK4O43mNlnlKcREgIiICIiAiIgIiICUiWMVVyjrOgid7oRfFAGwFyNDwHjKDM282HVp5y1TTS8zNwl7qeEMQU7SZW/wA4b3e+TUDdfx0k2ijSDCXGlVTnG9CKpoesGeF7F9NcTQVUrKK6qApLHLUFtLZ/4rdevXPeTPm/bVLJicQm7LXxC9wqNbytNOK72aehYT0vwTgXqmmeK1FZQPxC6+c2eH2thXtlxVJidwV0Yk9QveeNuQBrMjZK0PWqcTn9XZrhMuYNa6mxBuL6EdfVY6VGntC4pLkAWI4ur0x3XHS7tJfojMdAX6yMqDsHGeO1dv1adRvZsRWSmT0EZybAD6tyo1B3c4rek+McWbF1LHkwTzUAxJDVezYnFU0F3qKoG8swUeJjYm0KOKz+pfOtMqrMAcpYjNlUn3tLG401E8Fqvm6TsWI1u5Lnxae8/R/sj2fA0lK5XqD1tTnmcAgHsUKv4ZXkvTDTfKlpCpS4y/aCJjtLHpLvHLd2SrJykqgsQe49hk5O/YtCqy/aHXv8ZkUcQrabjyO/9ZQjSY9SmDI1KbZ8TCw2IN8rc7KefUZmzOyypViIgIiICIiBSavaNS7BeQ8z+k2TNYEmaNmJYtzN5pxY7u0VsMKdLGXazWUy3hxpeTxPu9unfw/fXJv2FVHREurqNZCmOiOyENxpu5ylSMpB0PcdZcgCIQo0+efTlCm0cYBxqI1vvU0b/dPoYzwb6SqWXaVf7a0H/wC2qf7JpxeRybEnf3CTkWEmBNxaq8B2yaPzBv8AGQqaESYaR7G69Etle04yhSYXTOHqDhkTpsD1GwX8U+irzyz6Gtl/6jFMN5FGmeoWeoQeslB+Az1OYcl3kIkSNpMyCqOI8dfjKQQcqRYnw1+Ea2F5ceRPCWgq0ttLjDdLaC5PbEGLjU0mfhamZFPMa9u4+cw8ZqZc2ebZl5G47D+o85OU3jsjPiImSSIiAiIgYm0HstuZt3cZrKgmXj2uwHIef7tMcre06OOaxVvln4UWURitVtzkqA0mJi3Kuo4NpKa3klnpukKK2uveO+Tpbofge6U/0JyJkpQwE8Q+ldMu0AeDYel5PUH5T24Txv6ZEtisO31qTj+V/wD2l+P7Dg2i8CVM6hafevfFRrAntht/dNz6J7O9pxuHpWupdXfj0E6bA9Ry5fxSluh7h6GbL9mwWHpEWYIGf773d/6mI7pvIictu+6SRIkpB2sIEA/Sy79L/wD2VffKUktv3nUyTiT7QkZYw7b/AN9sus/RJ6pjYPcZMnaiGI3iTQ5WU8+ie/d5gS3X94CXK69HrGo7pe+NIbGJbpPmUHmAZcmCxERApBMrLVZrCJN0a6vrc85ReEk4lKWtp1elGbR3Szi1uy9RJ8pepCYtVulbkDMp9lmVQOkuVBcGY+HaZUrl2pEUNwJUyCbyJOVFBPKPprodLCP/ANQn/jYfAz1iedfTTQJwlFx/BiFB7HRx8csvhf2HkAaTziY6seUkOudMorUbUdhnpP0L7OzVMRiSPcVaKnrYh38lTxM8wc69k+hPo62T7NgaKkdOoPXP1NUAIHcuUd0y5Muw6iIiYhLai5v4fnDa6ePZLiiAkAZMy2dDEGPiXsCIwo6Mx8cwAdjy89w87TJpmyDsE1v1VWaerGZLjSWcIvGZDiRb3ENnN0Sv1SfA6j5zMmrwrWqkfWHmNR85tJnnNVaKxESqVJgY2prYTNdrAma1xNOOd9q1UarJ0qVhI0xMrhL5XXZCqTXYy4rJyYOveBmHwM2SzD2gNUPJ18zl+crj5WKT2MzlOk1h3zPotpJzntESbeD4y5I74Q6dmkySracl9KFDPs2tpfK1Bu5aqXPheddNX6TYL12ExNIb3o1VX72Q5fO0mXVg+cckgycZcpPcA89fGHHRPYZ26F3Y2zfXV8PRIuKlWmrfdZhn/pzT6aRQAABYDQDkJ4h9FmB9ZjUci4o03e/JmARfJ3P4Z7jOXl86FJRjbWSkN56h8ZmFNeJ3mSEqZSSIu0ts2vdDmWKp0l5iNftOroo4u6IOPHMT4KZsK+igdk1dQhqtFL6h2e3UiMp/qdPGbOvqRL3zIquYddJdcSlMaSTTO3us1uJOVlbkQZuFNxNVjFuDMvZtTMi9Wh7t3laTnO0qIzIiJkstV90wGiJrx+EVNJfiJOSqa7phbS90fep/3iIlcfssiZk4fdETTLwiL6yib27oiYrLkt1fdPYYiIh8wYf3R2D4SZ3HsMpE7R6P9Cvv4r7mF+NSetRE5eT7JDI09w7JWJmgMiYiSLNTfMerETXFDUUP9Yn/ACav99Obl/elYlsvKGQkq0RMvazExW6V2P7rfe+URLZfUjZRETFL/9k="
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
            doctorList = it
            if(it.isEmpty()) {
                doctorViewModel.addDoctor(doctors)
            }
        })
    }
}