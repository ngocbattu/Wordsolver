package com.example.wordsolver

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.*
import com.example.wordsolver.Adapter.AlphabetAdapter
import com.example.wordsolver.Model.Alphabet
import com.example.wordsolver.Model.SeriesOfQuestions
import com.example.wordsolver.databinding.ActivityMainBinding
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList

class MainActivity : AppCompatActivity(){
    private val inapp_type_1 = "free_image_animal_15_day"
   lateinit var rcyAlphabet : RecyclerView
   lateinit var txtChuCanTim : TextView
   lateinit var txtChuBanChon : TextView
   lateinit var btnCheckDapAn :Button
   lateinit var txtThoiGian : TextView
   val listAlphabet : MutableList<Alphabet> = mutableListOf()
    val listSeriesOfQuestions : MutableList<SeriesOfQuestions> = mutableListOf()
   lateinit var  alphabetAdapter : AlphabetAdapter
     var  location : Int = 0
    var count : Int = 0
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        insertListAlphabet()
        getListAlphabet()
        inserAnswer()
        setSeri(listSeriesOfQuestions.get(count))
        checkAlphabet()
        time60s()
        val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->

            }

        var billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
      binding.imageBuy.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "aaaa", Toast.LENGTH_SHORT).show()
          startActivity(Intent(this@MainActivity,PaymentPackagesActivity::class.java))
//          try {
//              billingClient.startConnection(object : BillingClientStateListener {
//                  override fun onBillingSetupFinished(billingResult: BillingResult) {
//                      if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
//                          val queryProductDetailsParams =
//                              QueryProductDetailsParams.newBuilder()
//                                  .setProductList(
//                                      ImmutableList.of(
//                                          QueryProductDetailsParams.Product.newBuilder()
//                                              .setProductId(inapp_type_1)
//                                              .setProductType(BillingClient.ProductType.INAPP)
//                                              .build()))
//                                  .build()
//
//                          billingClient.queryProductDetailsAsync(queryProductDetailsParams) {
//                                  billingResult,
//                                  productDetailsList ->
//                              listProductDetails = productDetailsList
//                              for (i in listProductDetails.indices){
//                                  val productDetailsParamsList = listOf(
//                                      BillingFlowParams.ProductDetailsParams.newBuilder()
//                                          .setProductDetails(listProductDetails.get(i))
//                                          .build()
//                                  )
//
//                                  val billingFlowParams = BillingFlowParams.newBuilder()
//                                      .setProductDetailsParamsList(productDetailsParamsList)
//                                      .build()
//                                  val billingResult = billingClient.launchBillingFlow(this@MainActivity, billingFlowParams)
//                              }
//
//                          }
//
//                      }
//                  }
//                  override fun onBillingServiceDisconnected() {
//
//                  }
//              })
//          }catch (e:Exception){
//              e.printStackTrace()
//          }

        })

    }

    private fun setSeri(alphabet:SeriesOfQuestions) {
       for (i in listSeriesOfQuestions.indices){
           if(listSeriesOfQuestions.get(i).idDapAn == alphabet.idDapAn){
               txtChuCanTim.text = listSeriesOfQuestions.get(i).answer
           }
       }

    }

    fun init(){
        rcyAlphabet = findViewById(R.id.rcyBangChuCai)
        txtChuCanTim = findViewById(R.id.txtChuCanTim)
        txtChuBanChon = findViewById(R.id.txtDapAnCuaBan)
        btnCheckDapAn = findViewById(R.id.btnCheckDapAn)
        txtThoiGian = findViewById(R.id.txtThoiGian)

    }
    fun getListAlphabet(){
        rcyAlphabet.layoutManager = GridLayoutManager(this@MainActivity,5)
        alphabetAdapter = AlphabetAdapter(this@MainActivity,listAlphabet)
        rcyAlphabet.adapter = alphabetAdapter

        alphabetAdapter.setOnClickItem(object : AlphabetAdapter.ItemClick{
            override fun clickWord(poistion: Int) {
                Log.d("TAG", "clickWord: $poistion" )
                for (i in listAlphabet.indices){
                    if(listAlphabet.get(i).idChuCai == poistion){
                        location = listAlphabet.get(i).idChuCai
                        txtChuBanChon.text = txtChuBanChon.text.toString() + listAlphabet.get(i).chuCai

                    }
                }
            }

        })
    }
    fun insertListAlphabet(){
        listAlphabet.add(Alphabet(0,"A"))
        listAlphabet.add(Alphabet(1,"B"))
        listAlphabet.add(Alphabet(2,"C"))
        listAlphabet.add(Alphabet(3,"D"))
        listAlphabet.add(Alphabet(4,"E"))
        listAlphabet.add(Alphabet(5,"F"))
        listAlphabet.add(Alphabet(6,"G"))
        listAlphabet.add(Alphabet(7,"H"))
        listAlphabet.add(Alphabet(8,"I"))
        listAlphabet.add(Alphabet(9,"J"))
        listAlphabet.add(Alphabet(10,"K"))
        listAlphabet.add(Alphabet(11,"L"))
        listAlphabet.add(Alphabet(12,"M"))
        listAlphabet.add(Alphabet(13,"N"))
        listAlphabet.add(Alphabet(14,"O"))
        listAlphabet.add(Alphabet(15,"P"))
        listAlphabet.add(Alphabet(16,"Q"))
        listAlphabet.add(Alphabet(17,"R"))
        listAlphabet.add(Alphabet(18,"S"))
        listAlphabet.add(Alphabet(19,"T"))
        listAlphabet.add(Alphabet(20,"U"))
        listAlphabet.add(Alphabet(21,"V"))
        listAlphabet.add(Alphabet(22,"W"))
        listAlphabet.add(Alphabet(23,"X"))
        listAlphabet.add(Alphabet(24,"Y"))
        listAlphabet.add(Alphabet(25,"Z"))
    }
    fun inserAnswer(){
        listSeriesOfQuestions.add(SeriesOfQuestions(0,"DOG"))
        listSeriesOfQuestions.add(SeriesOfQuestions(1,"CAT"))
        listSeriesOfQuestions.add(SeriesOfQuestions(2,"HI"))
        listSeriesOfQuestions.add(SeriesOfQuestions(3,"JSON"))
        listSeriesOfQuestions.add(SeriesOfQuestions(4,"KOPEOI"))
    }

    fun nextSeri(){
        if(count == listAlphabet.size - 1 ){
            val dialog : Dialog = Dialog(this@MainActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dia_log_game_over)

            val btnChoiLai : Button = dialog.findViewById(R.id.btnChoiLai)
            btnChoiLai.setText("You Win")
            btnChoiLai.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
                time60s()
                setSeri(listSeriesOfQuestions.get(0))
            })
            dialog.show()
            Toast.makeText (this, "Chúc mừng bạn đã win game", Toast.LENGTH_SHORT).show ()
            return
        }else {
            count++;
            setSeri(listSeriesOfQuestions.get(count))
        }
    }
    fun checkAlphabet(){
        btnCheckDapAn.setOnClickListener(View.OnClickListener {
            if(txtChuBanChon.text.toString().equals(txtChuCanTim.text.toString())){
                Toast.makeText(this, "Chúc mừng bạn nha ! giỏi ghê gớm >>", Toast.LENGTH_SHORT).show()
                nextSeri()
                txtChuBanChon.text = ""
            }else {
                txtChuBanChon.text = ""
                Toast.makeText(this, "Ao trình quá sai rồi bạn ơi lew lew >>", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun time60s(){
        object : CountDownTimer(60000,1000){
            override fun onTick(p0: Long) {
                txtThoiGian.setText((p0/1000).toString())
            }
            override fun onFinish() {
                val dialog : Dialog = Dialog(this@MainActivity)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.dia_log_game_over)

                val btnChoiLai : Button = dialog.findViewById(R.id.btnChoiLai)
                btnChoiLai.setOnClickListener(View.OnClickListener {
                    dialog.dismiss()
                    time60s()
                    setSeri(listSeriesOfQuestions.get(0))
                })
                dialog.show()
            }
        }.start()
    }
}




