package com.mghg.mtek.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.mghg.mtek.models.Profil;
import com.mghg.mtek.R;
import com.mghg.mtek.Veritabani;

public class FragmentLogin extends Fragment {
    private EditText edSifre, edKullaniciAdi;
    private Button btnGiris;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Veritabani db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        sharedPreferences = getContext().getSharedPreferences("girisBilgisi", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        edKullaniciAdi = rootView.findViewById(R.id.edKullaniciAdi);
        edSifre = rootView.findViewById(R.id.edSifre);
        btnGiris = rootView.findViewById(R.id.btnGiris);
        db = new Veritabani(getContext());

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edKullaniciAdi.getText().toString().equals("") || edSifre.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Kullanıcı adı veya şifre alanını boş bırakamazsınız", Toast.LENGTH_LONG).show();
                } else {
                    Profil dogrulama = db.kullaniciKaydiVarMi(edKullaniciAdi.getText().toString(), edSifre.getText().toString());

                    if (dogrulama != null) {
                        //kullanıcı kaydı var ise önce hafızaya alıyoruz, bir sonraki girişte hafızada kayıt olduğundan direk giriş yapacak
                        editor.putString("kullaniciAdi", edKullaniciAdi.getText().toString());
                        editor.putString("sifre", edSifre.getText().toString());
                        editor.commit();

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Kullanıcı adı veya şifreniz yanlış girildi", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return rootView;

    }
}
