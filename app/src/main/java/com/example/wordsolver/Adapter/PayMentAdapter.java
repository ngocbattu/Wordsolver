package com.example.wordsolver.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.example.wordsolver.MainActivity;
import com.example.wordsolver.PaymentPackagesActivity;
import com.example.wordsolver.R;
import com.example.wordsolver.interfaces.ItemClickPackagesPayMent;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class PayMentAdapter extends RecyclerView.Adapter<PayMentAdapter.PayMentViewHodler> {
    private PaymentPackagesActivity context;
    private List<ProductDetails> list;
    private BillingClient billingClient;

    public PayMentAdapter(PaymentPackagesActivity context, List<ProductDetails> list, BillingClient billingClient) {
        this.context = context;
        this.list = list;
        this.billingClient = billingClient;
    }


    public void loadData(List<ProductDetails> lst) {
            this.list.clear();
            this.list.addAll(lst);
    }

    @NonNull
    @Override
    public PayMentViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recy_view_payment,null);
        return new PayMentViewHodler(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull PayMentViewHodler holder, int position) {
        ProductDetails productDetails = list.get(position);
        holder.txtName.setText(productDetails.getName());
        holder.txtPrice.setText(productDetails.getOneTimePurchaseOfferDetails().getFormattedPrice()+"");
        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BillingFlowParams.ProductDetailsParams params = BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(productDetails).build();
                List<BillingFlowParams.ProductDetailsParams> lst = new ArrayList<>();
                lst.add(params);
                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder().setProductDetailsParamsList(lst)
                        .build();
                int reponse = billingClient.launchBillingFlow(context, billingFlowParams)
                        .getResponseCode();
                switch (reponse) {
                    case BillingClient.BillingResponseCode.BILLING_UNAVAILABLE:
                        Toast.makeText(context, "BILLING_UNAVAILABLE", Toast.LENGTH_SHORT).show();
                        break;
                    case BillingClient.BillingResponseCode.DEVELOPER_ERROR:
                        Toast.makeText(context, "DEVELOPER_ERROR", Toast.LENGTH_SHORT).show();
                        break;
                    case BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED:
                        Toast.makeText(context, "FEATURE_NOT_SUPPORTED", Toast.LENGTH_SHORT).show();
                        break;
                    case BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED:
                        Toast.makeText(context, "ITEM_ALREADY_OWNED", Toast.LENGTH_SHORT).show();
                        break;

                    case BillingClient.BillingResponseCode.SERVICE_DISCONNECTED:
                        Toast.makeText(context, "SERVICE_DISCONNECTED", Toast.LENGTH_SHORT).show();
                        break;
                    case BillingClient.BillingResponseCode.SERVICE_TIMEOUT:
                        Toast.makeText(context, "SERVICE_TIMEOUT", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PayMentViewHodler extends RecyclerView.ViewHolder{
        TextView txtName , txtPrice;
        ConstraintLayout mConstraintLayout;
        public PayMentViewHodler(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNamePackages);
            txtPrice = itemView.findViewById(R.id.txtPricePackages);
            mConstraintLayout = itemView.findViewById(R.id.ContrainList);
            mConstraintLayout = itemView.findViewById(R.id.ContrainList);
        }
    }
}
