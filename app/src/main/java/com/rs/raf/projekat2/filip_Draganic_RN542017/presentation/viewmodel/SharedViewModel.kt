package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Pacijent
import timber.log.Timber
import java.util.*

class SharedViewModel : ViewModel(){

    companion object{
        const val CEKAONICA = 0
        const val HOSPITALIZOVAN = 1
        const val OTPUSTEN = 2
    }

    private val cekaonicaLista : MutableList<Pacijent> = mutableListOf()
    private val hospitalizovaniLista : MutableList<Pacijent> = mutableListOf()
    private val otpusteniLista : MutableList<Pacijent> = mutableListOf()


    private val cekaonicaData : MutableLiveData<List<Pacijent>> = MutableLiveData()
    private val hospitalizovaniData : MutableLiveData<List<Pacijent>> = MutableLiveData()
    private val otpusteniData : MutableLiveData<List<Pacijent>> = MutableLiveData()

    private val cekaonicaStanje : MutableLiveData<List<Pacijent>> = MutableLiveData()
    private val hospitalizovaniStanje : MutableLiveData<List<Pacijent>> = MutableLiveData()
    private val otpusteniStanje : MutableLiveData<List<Pacijent>> = MutableLiveData()


    fun getCekaonicaData() : MutableLiveData<List<Pacijent>> {
        return cekaonicaData
    }
    fun getHospitalizovaniData() : MutableLiveData<List<Pacijent>> {
        return hospitalizovaniData
    }
    fun getOtpusteniData() : MutableLiveData<List<Pacijent>> {
        return otpusteniData
    }


    fun getCekaonicaStanje() : MutableLiveData<List<Pacijent>> {
        return cekaonicaStanje
    }
    fun getHospitalizovaniStanje() : MutableLiveData<List<Pacijent>> {
        return hospitalizovaniStanje
    }
    fun getOtpusteniStanje() : MutableLiveData<List<Pacijent>> {
        return otpusteniStanje
    }


    fun testPacijenti() {
        val imena = listOf<String>("Finn", "Colton", "Macey", "Ori", "Slade", "Noelle", "Erich")
        val prezimena = listOf<String>("Martinez", "Curtis", "Ruiz", "Calderon","Rollins", "Cameron", "Espinoza", "Dyer")
        val simptomi = listOf<String>("Abdominal Pain of Absolutely No Significance -  as in why come to the ER type of pain. ",
            "Acute Exacerbation of Chronic Nonsense -  self explanatory. ",
            "Arrestogenic Shock -  sudden life threatening illness caused by getting arrested.",
            "Biscuit Poisoning -  what morbidly obese patients have.",
            "Chandelier Sign - jumping off the bed to belly palpation,  seen with extreme pain.",
            "Crapacardia -  a bad EKG that needs to be repeated",
            "Cyberchondria -  worrying about all the worst possibilities after reading the internet. ",
            "Dead Shovel -  a patient who has a heart attack shoveling snow.",
            "Dizziness of No Possible Cause:  a top 10 ER diagnosis. ",
            "GonoherpasyphilAIDS - has all the STDs from sleeping with the entire football team.",
            "I sign -  as in the sound of pain in Spanish (e.g. aye...aye...AYE...aye).",
            "Insurance Pain -  pain that presents when insurance settlements are involved. ",
            "Polybabydaddia -  a woman who comes to the ER with multiple kids from different daddies.",
            "Ridiculitis - because every symptom needs respect. ")
        val bool = listOf<Boolean>(true, false)

        val rnds = (0..2).random()
        val simptom = simptomi[(0 until simptomi.size).random()]

        val bulvrednost = bool[(0..1).random()]
        dodajPacijenta(Pacijent(UUID.randomUUID(),imena[(0 until imena.size).random()],
            prezimena[(0 until prezimena.size).random()], Date() , simptom, simptom,
            null, null ) ,0)


    }


    fun dodajPacijenta(pacijent: Pacijent, stanje: Int){
        Timber.e("%s ,stanje = %s", pacijent.toString(), stanje)
        when(stanje){
            CEKAONICA -> {
                cekaonicaLista.add(pacijent)
                cekaonicaData.value = cekaonicaLista
                cekaonicaStanje.value = cekaonicaLista
            }
            HOSPITALIZOVAN ->{
                hospitalizovaniLista.add(pacijent)
                hospitalizovaniData.value = hospitalizovaniLista
                hospitalizovaniStanje.value = hospitalizovaniLista
            }
            else ->{
                otpusteniLista.add(pacijent)
                otpusteniData.value = otpusteniLista
                otpusteniStanje.value = otpusteniLista
            }
        }


    }

    fun overwritePacijenta(pacijent: Pacijent){
        for(x in hospitalizovaniLista){
            if (x.id == pacijent.id){
                val index = hospitalizovaniLista.indexOf(x)
                hospitalizovaniLista[index] = pacijent
            }
        }
    }


    fun premestiPacijenta(pacijent: Pacijent, stanje: Int, uStanje: Int){

        when(stanje){
            CEKAONICA->{
                when(uStanje){
                    HOSPITALIZOVAN -> {
                        //Iz cekaonice u Hospitalizovano stanje. Hospitalizacija dugme
                        cekaonicaLista.remove(pacijent)
                        pacijent.datumHospitalizacije = Date()
                        hospitalizovaniLista.add(pacijent)

                        cekaonicaData.value = cekaonicaLista
                        cekaonicaStanje.value = cekaonicaLista
                        hospitalizovaniData.value = hospitalizovaniLista
                        hospitalizovaniStanje.value = hospitalizovaniLista
                    }
                    OTPUSTEN ->{
                        //Iz cekaonice u otpustenu listu. Zdrav dugme
                        cekaonicaLista.remove(pacijent)
                        pacijent.datumOtpustanja = Date()
                        otpusteniLista.add(pacijent)
                        cekaonicaData.value = cekaonicaLista
                        cekaonicaStanje.value = cekaonicaLista
                        otpusteniData.value = otpusteniLista
                        otpusteniStanje.value = otpusteniLista

                    }
                    else -> {
                        Timber.e("Greska u Shared View Model 1")
                    }
                }
            }
            HOSPITALIZOVAN -> {
                //Iz Hospitalizovanog stanja u otpusteno stanje Otpusteno dugme
                hospitalizovaniLista.remove(pacijent)
                pacijent.datumOtpustanja = Date()
                otpusteniLista.add(pacijent)
                otpusteniData.value = otpusteniLista
                otpusteniStanje.value = otpusteniLista
                hospitalizovaniData.value = hospitalizovaniLista
                hospitalizovaniStanje.value = hospitalizovaniLista
            }
            else -> {
                Timber.e("Greska u Shared View Model 2")

            }
        }
    }

    fun pretraziPacijenta(lista: Int, string: String){
        /*
        Razlog koriscenja liste za svaki fragment pojedinacno je taj sto je koriscenje jedne liste pravilo delay pri menjanju fragmenta.
        Prvo bi se pokazali podaci koji su inace u toj listi pa bi se tek onda pokazali podaci koji su pretrazivani
         */
        val tempLista : MutableList<Pacijent> = mutableListOf()

        when(lista){
            CEKAONICA -> {

                cekaonicaLista.forEach {if (it.ime.toLowerCase().contains(string.toLowerCase()) || it.prezime.toLowerCase().contains(string.toLowerCase())) tempLista.add(it) }
                cekaonicaData.value = tempLista
                //if (string == "") cekaonicaData.value = cekaonicaLista


            }
            HOSPITALIZOVAN -> {
                hospitalizovaniLista.forEach {if (it.ime.toLowerCase().contains(string.toLowerCase()) || it.prezime.toLowerCase().contains(string.toLowerCase())) tempLista.add(it) }
                hospitalizovaniData.value = tempLista
                //if (string == "") hospitalizovaniData.value = hospitalizovaniLista

            }
            else -> {
                otpusteniLista.forEach {if (it.ime.toLowerCase().contains(string.toLowerCase()) || it.prezime.toLowerCase().contains(string.toLowerCase())) tempLista.add(it) }
                otpusteniData.value = tempLista
               // if (string == "") otpusteniData.value = otpusteniLista

            }
        }

    }





}