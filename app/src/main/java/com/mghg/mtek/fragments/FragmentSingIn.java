package com.mghg.mtek.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mghg.mtek.MainActivity;
import com.mghg.mtek.R;
import com.mghg.mtek.Veritabani;

public class FragmentSingIn extends Fragment {
    EditText edSigninSifreTekrar, edSigninKullaniciAdi, edSigninSifre;
    Button btnSigninKayitol;
    Veritabani db;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_singin, container,false);

        sharedPreferences = getContext().getSharedPreferences("girisBilgisi", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        edSigninKullaniciAdi = rootView.findViewById(R.id.edSigninKullaniciAdi);
        edSigninSifre = rootView.findViewById(R.id.edSigninSifre);
        edSigninSifreTekrar = rootView.findViewById(R.id.edSigninSifreTekrar);
        btnSigninKayitol = rootView.findViewById(R.id.btnSigninKayitol);
        db = new Veritabani(getContext());

        btnSigninKayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean kontrol = db.kullaniciAdiBosMu(edSigninKullaniciAdi.getText().toString());
                if (edSigninKullaniciAdi.getText().length() < 3){
                    Toast.makeText(getContext(),"Kullanici Adıniz en az 3 karakterden oluşmak zorunda",Toast.LENGTH_LONG).show();
                    edSigninKullaniciAdi.setBackgroundColor(Color.parseColor("#FFEBEE"));
                }else if(edSigninKullaniciAdi.getText().length() > 40){
                    Toast.makeText(getContext(),"Kullanici Adıniz en fazla 40 karakter olabilir",Toast.LENGTH_LONG).show();
                    edSigninKullaniciAdi.setBackgroundColor(Color.parseColor("#FFEBEE"));
                }else if(edSigninSifre.getText().length() < 3){
                    Toast.makeText(getContext(),"Şifreniz en az 3 karakterden oluşmak zorunda",Toast.LENGTH_LONG).show();
                    edSigninSifre.setBackgroundColor(Color.parseColor("#FFEBEE"));
                }else if(kontrol){
                    Toast.makeText(getContext(),"Bu kullanıcı ismi daha önce kullanılmış",Toast.LENGTH_LONG).show();
                    edSigninKullaniciAdi.setBackgroundColor(Color.parseColor("#FFEBEE"));
                }else if(edSigninSifre.getText().equals(edSigninSifreTekrar.getText())){
                    Toast.makeText(getContext(),"Şİfreniz tekrar eden şifreniz ile uyuşmuyor",Toast.LENGTH_LONG).show();
                }else{
                    db.profilKayitOl(edSigninKullaniciAdi.getText().toString(),edSigninSifre.getText().toString());

                    editor.putString("kullaniciAdi", edSigninKullaniciAdi.getText().toString());
                    editor.putString("sifre", edSigninSifre.getText().toString());
                    editor.commit();

                    startActivity(new Intent(getContext(), MainActivity.class));
                }
            }
        });

        return rootView;
    }
}
