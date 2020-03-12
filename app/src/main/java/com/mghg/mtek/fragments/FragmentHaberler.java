package com.mghg.mtek.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import com.mghg.mtek.Giris;
import com.mghg.mtek.R;
import com.mghg.mtek.models.Xmlitems;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.content.Context.MODE_PRIVATE;


public class FragmentHaberler extends Fragment {
    public String url;
    ArrayList<Xmlitems> xmlitemsArrayList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_haberler, container,false);
        setHasOptionsMenu(true);

        toolbar = rootView.findViewById(R.id.toolbar_haberler);
        toolbar.setTitle("Haberler");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        sharedPreferences = getActivity().getSharedPreferences("girisBilgisi",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        return rootView;
    }


    /*private class XmlParseIslemleri extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {


            Xmlitems xmlitems = null;
            try {
                URL url = new URL("https://www.donanimhaber.com/rss/tum/");

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(new InputSource(url.openStream()));
                document.getDocumentElement().normalize();

                NodeList nodeList = document.getElementsByTagName("item");

                for(int i =0; i < nodeList.getLength(); i++){
                    Node node = nodeList.item(i);
                    Element element = (Element) node;
                    NodeList nodeListTittle = element.getElementsByTagName("title");
                    Element elementTittle = (Element) nodeListTittle.item(0);
                    xmlitems.setTitle(elementTittle.getChildNodes().item(0).getNodeName());

                    NodeList nodeListLink = element.getElementsByTagName("link");
                    Element elementLink = (Element) nodeListLink.item(0);
                    xmlitems.setLink(elementLink.getChildNodes().item(0).getNodeName());

                    NodeList nodeListpubDate = element.getElementsByTagName("pubDate");
                    Element elementpubDate = (Element) nodeListpubDate.item(0);
                    xmlitems.setPubDate(elementpubDate.getChildNodes().item(0).getNodeName());

                    NodeList nodeListenclosure = element.getElementsByTagName("enclosure");
                    Element elementenclosure = (Element) nodeListenclosure.item(0);
                    xmlitems.setEnclosure(elementenclosure.getChildNodes().item(0).getNodeName());

                    //Xml dosyasını cekemedim https ssl problemi olduğunu düşündüm, araştırdım nette baya, veriyi alamadım bir türlü
                    // zamanında kısıtlı olmasından diğer işlemlere geçtim
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }  catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

        }
    }*/

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
}
