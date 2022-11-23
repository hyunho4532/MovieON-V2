package com.example.intentname.movieName.data.country;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.intentname.R;

public class GlideCountryData {

    public void GlideCountryLoading (@NonNull TextView tvNationAlt, View itemView, ImageView ivCountry) {

        if (tvNationAlt.getText().toString().equals("한국") || tvNationAlt.getText().toString().equals("대한민국")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.korean)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("일본")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.japan)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("미국")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.american)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("중국")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.china)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("프랑스")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.france)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("영국")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.uk)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("인도")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.india)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("독일")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.germany)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("러시아")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.russia)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("호주")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.austraila)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("홍콩")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.hongkong)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("대만")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.tiwan)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("태국")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.thailand)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("스페인")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.spain)
                    .into(ivCountry);
        }
        else if (tvNationAlt.getText().toString().equals("이탈리아")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.italy)
                    .into(ivCountry);
        }
        else {
            Glide.with(itemView.getContext())
                    .load(R.drawable.country)
                    .into(ivCountry);
        }
    }
}
