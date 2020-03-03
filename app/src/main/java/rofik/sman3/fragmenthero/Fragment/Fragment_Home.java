package rofik.sman3.fragmenthero.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import rofik.sman3.fragmenthero.Adapter.Hero_Adapter;
import rofik.sman3.fragmenthero.Model.Hero;
import rofik.sman3.fragmenthero.Model.Heroes_Data;
import rofik.sman3.fragmenthero.R;

public class Fragment_Home extends Fragment {
    private ArrayList<Hero> heroes = new ArrayList<>();
    private Hero_Adapter adapterHorizontal, adapterVertical;
    private RecyclerView rvHorizontal, rvVertical;

    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        rvHorizontal = view.findViewById(R.id.rv_horizontal_list);
        rvVertical = view.findViewById(R.id.rv_vertical_list);

        adapterHorizontal = new Hero_Adapter(heroes, getActivity(),1);
        adapterVertical = new Hero_Adapter(heroes, getActivity(),2);

        return view;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        heroes.addAll(Heroes_Data.getListData());

        rvHorizontal.setHasFixedSize(true);
        rvVertical.setHasFixedSize(true);
        rvHorizontal.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvVertical.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvHorizontal.setAdapter(adapterHorizontal);
        rvVertical.setAdapter(adapterVertical);
    }
}
