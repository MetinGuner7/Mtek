package com.mghg.mtek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mghg.mtek.fragments.FragmentEczane;
import com.mghg.mtek.fragments.FragmentHaberler;
import com.mghg.mtek.fragments.FragmentLogin;
import com.mghg.mtek.fragments.FragmentProfil;
import com.mghg.mtek.fragments.FragmentSingIn;

public class MainActivity extends AppCompatActivity {
    Veritabani db;
    BottomNavigationView bottomNavigationAna;
    private Fragment geciciFragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Veritabani(getApplicationContext());

        bottomNavigationAna = findViewById(R.id.bottom_navigation_ana);
        getSupportFragmentManager().beginTransaction().add(R.id.framlayout__ana, new FragmentHaberler()).commit();

        bottomNavigationAna.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.item_navigation_haberler){
                    geciciFragment = new FragmentHaberler();
                }
                else if (item.getItemId() == R.id.item_navigation_profil){
                    geciciFragment = new FragmentProfil();
                }else if (item.getItemId() == R.id.item_navigation_eczaneler){
                    geciciFragment = new FragmentEczane();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout__ana, geciciFragment).commit();

                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ust_sag_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_cikis:
                // Action goes here
                sharedPreferences = getSharedPreferences("girisBilgisi",MODE_PRIVATE);
                editor = sharedPreferences.edit();

                editor.putString("kullaniciAdi","boş");
                editor.putString("sifre","boş");
                editor.commit();

                startActivity(new Intent(getApplicationContext(),Giris.class));
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
