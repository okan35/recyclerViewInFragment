package com.example.nasaappjava;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.random;

public class CountryFragment extends Fragment {


    ArrayList<PlanetModel> planetModels = new ArrayList<PlanetModel>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity())
                .setActionBarTitle("Planets");

        int randomNum = ThreadLocalRandom.current().nextInt(1, 508 + 1);
        String url = "https://restcountries.eu/rest/v2/callingcode/"+ randomNum;
        try {
            String planet = new GetPlanetsAsyncTask(url).execute().get();
            JSONObject planetJson = new JSONObject(planet);
            //System.out.println("hdurl "  + planetJson.getString("hdurl"));
            planetModels.add(new PlanetModel(planetJson.getString("title"),planetJson.getString("hdurl"))) ;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity) getActivity())
                .onBackPressedCustom();
        ((MainActivity) getActivity())
                .setActionBarTitle("Nasa App");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_planets, container, false);
        rootView.setBackgroundColor(Color.WHITE);


        RecyclerView planetsRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_recycler_view);
        planetsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


     /*   planetModels.add(new PlanetModel("planet 1"));
        planetModels.add(new PlanetModel("planet 2"));
        planetModels.add(new PlanetModel("planet 3"));
        planetModels.add(new PlanetModel("planet 4"));*/

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),planetModels);

        planetsRecyclerView.setAdapter(recyclerAdapter);



        return rootView;
    }
}
