package com.mghg.mtek.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.mghg.mtek.Giris;
import com.mghg.mtek.MainActivity;
import com.mghg.mtek.R;
import com.mghg.mtek.models.Eczaneler;
import com.mghg.mtek.models.ApiUtils;
import com.mghg.mtek.models.EczanelerDao;
import com.mghg.mtek.models.Profil;
import com.mghg.mtek.models.Result;
import com.mghg.mtek.rceyclerview.EczanelerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FragmentEczane extends Fragment {
    private EczanelerDao kisilerDao;
    ArrayList<Profil> profilArrayList;
    RecyclerView rcListEczaneler;
    EczanelerAdapter adapter;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String kullaniciAdi, sifre;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_eczaneler,container,false);
        setHasOptionsMenu(true);
        profilArrayList = new ArrayList<>();

        toolbar = rootView.findViewById(R.id.toolbar_eczaneler);
        toolbar.setTitle("Eczaneler");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        sharedPreferences = getActivity().getSharedPreferences("girisBilgisi",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        rcListEczaneler = rootView.findViewById(R.id.rcListEczaneler);
        kisilerDao = ApiUtils.getEczanelerDaoInterfae();

        ((SimpleItemAnimator) rcListEczaneler.getItemAnimator()).setSupportsChangeAnimations(false);

        rcListEczaneler.setHasFixedSize(true);
        rcListEczaneler.setLayoutManager(new LinearLayoutManager(getContext()));

        EczaneleriGetir();

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.ust_sag_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //arama(newText);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_cikis) {
            editor.putString("kullaniciAdi", "boş");
            editor.putString("sifre", "boş");
            editor.commit();
            startActivity(new Intent(getContext(), Giris.class));

            return true;
        } else if (id == R.id.action_ara) {

        }

        return super.onOptionsItemSelected(item);
    }

    public void EczaneleriGetir(){


        kisilerDao.tumEczaneler().enqueue(new Callback<Eczaneler>() {
            @Override
            public void onResponse(Call<Eczaneler> call, retrofit2.Response<Eczaneler> response) {

                List<Result> resultList = response.body().getResult();


                adapter = new EczanelerAdapter(getActivity(), resultList);
                rcListEczaneler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Eczaneler> call, Throwable t) {

            }
        });
    }

    public void arama(String kelime){

        kisilerDao.ilArama(kelime).enqueue(new Callback<Eczaneler>() {
            @Override
            public void onResponse(Call<Eczaneler> call, Response<Eczaneler> response) {
                List<Result> liste = response.body().getResult();

                adapter = new EczanelerAdapter(getActivity(),liste);
                rcListEczaneler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Eczaneler> call, Throwable t) {

            }
        });

    }

}
