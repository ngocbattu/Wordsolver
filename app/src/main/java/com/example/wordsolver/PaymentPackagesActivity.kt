package com.example.wordsolver

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.*
import com.example.wordsolver.Adapter.PayMentAdapter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList

class PaymentPackagesActivity : AppCompatActivity() {
    private val inapp_type_1 = "free_image_animal_15_day"
    private val inapp_type_2 = "free_image_animal_1_day"
    private val inapp_type_3 = "free_image_animal_30_day"
    private val inapp_type_4 = "free_image_animal_3_day"
    private val inapp_type_5 = "free_image_animal_7_day"

    lateinit var billingClient: BillingClient
     lateinit var  rcyPayMent : RecyclerView
     lateinit var adapter : PayMentAdapter

     private var listProductDetails : MutableList<ProductDetails> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_packages)
        rcyPayMent = findViewById(R.id.rcyPayment)

        val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->

            }

         billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
        adapter = PayMentAdapter(this@PaymentPackagesActivity,listProductDetails,billingClient)
        rcyPayMent.layoutManager = LinearLayoutManager(this@PaymentPackagesActivity)



        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    val list : MutableList<QueryProductDetailsParams.Product> = mutableListOf()
                    list.add(QueryProductDetailsParams.Product.newBuilder().setProductId(inapp_type_1).setProductType(BillingClient.ProductType.INAPP).build())
                    list.add(QueryProductDetailsParams.Product.newBuilder().setProductId(inapp_type_2).setProductType(BillingClient.ProductType.INAPP).build())
                    list.add(QueryProductDetailsParams.Product.newBuilder().setProductId(inapp_type_3).setProductType(BillingClient.ProductType.INAPP).build())
                    list.add(QueryProductDetailsParams.Product.newBuilder().setProductId(inapp_type_4).setProductType(BillingClient.ProductType.INAPP).build())
                    list.add(QueryProductDetailsParams.Product.newBuilder().setProductId(inapp_type_5).setProductType(BillingClient.ProductType.INAPP).build())

                    val queryProductDetailsParams = QueryProductDetailsParams.newBuilder().setProductList(list).build()
                    billingClient.queryProductDetailsAsync(queryProductDetailsParams) {
                            billingResult,
                            productDetailsList ->
                            listProductDetails = productDetailsList
                        rcyPayMent.adapter = PayMentAdapter(this@PaymentPackagesActivity,listProductDetails,billingClient)
                        loadRecyclerView(listProductDetails)
                    }
                }
            }
            override fun onBillingServiceDisconnected() {

            }
        })
    }

    fun loadRecyclerView( list : MutableList<ProductDetails>){
        runOnUiThread { adapter.loadData(list) }
    }
}