package com.mghg.mtek;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mghg.mtek.fragments.FragmentLogin;
import com.mghg.mtek.fragments.FragmentSingIn;

public class Giris extends AppCompatActivity {
BottomNavigationView bottomNavigationGiris;
private Fragment geciciFragment;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
String kullaniciAdi, sifre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris_activity);
        sharedPreferences = getSharedPreferences("girisBilgisi",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        kullaniciAdi = sharedPreferences.getString("kullaniciAdi", "boş");
        sifre = sharedPreferences.getString("sifre", "boş");

        if (!kullaniciAdi.equals("boş") && !sifre.equals("boş")){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        bottomNavigationGiris = findViewById(R.id.bottomNavigationGiris);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_tutucu, new FragmentLogin()).commit();

        bottomNavigationGiris.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.item_navigation_login){
                    geciciFragment = new FragmentLogin();
                }
                else if (item.getItemId() == R.id.item_navigation_signin){
                    geciciFragment = new FragmentSingIn();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu, geciciFragment).commit();

                return true;
            }
        });
    }
}
