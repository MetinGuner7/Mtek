package com.mghg.mtek.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mghg.mtek.MainActivity;
import com.mghg.mtek.R;
import com.mghg.mtek.models.Profil;

import static android.content.Context.MODE_PRIVATE;

public class FragmentProfil extends Fragment {
    EditText tvProfilAd, tvProfilEmail, tvPRofilSoyad;
    TextView tvProfilKullaniciAdi;
    Button btnProfilKayit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String kullaniciAdi, sifre;
    private Fragment geciciFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profil,container,false);
        tvProfilKullaniciAdi = rootView.findViewById(R.id.tvprofilkullanıcıAdi);
        tvProfilAd = rootView.findViewById(R.id.tvprofilAd);
        tvPRofilSoyad = rootView.findViewById(R.id.tvprofilSoyAd);
        tvProfilEmail = rootView.findViewById(R.id.tvemail);
        btnProfilKayit = rootView.findViewById(R.id.btnProfilKayit);

        sharedPreferences = getActivity().getSharedPreferences("girisBilgisi",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        kullaniciAdi = sharedPreferences.getString("kullaniciAdi", "boş");
        sifre = sharedPreferences.getString("sifre", "boş");

        if (!kullaniciAdi.equals("boş") && !sifre.equals("boş")){
            tvProfilKullaniciAdi.setText(kullaniciAdi);
        }
        btnProfilKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvProfilAd.getText().toString().equals("") || tvPRofilSoyad.getText().toString().equals("") ||
                        tvProfilEmail.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "İsim, soyisim ve email alanından herhangi birini boş bırakamazsınız", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Kişisel bilgileriniz başarıyla güncellendi", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framlayout__ana, new FragmentHaberler()).commit();
                }
            }
        });


        return rootView;
    }
}
