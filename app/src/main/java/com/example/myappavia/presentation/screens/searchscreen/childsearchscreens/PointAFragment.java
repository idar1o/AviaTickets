package com.example.myappavia.presentation.screens.searchscreen.childsearchscreens;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;

import com.example.myappavia.R;
import com.example.myappavia.data.API.AviaAPI;
import com.example.myappavia.data.API.ModelPlace.SearchedPlace;
import com.example.myappavia.databinding.FragmentPointABinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PointAFragment extends BottomSheetDialogFragment {

    FragmentPointABinding binding;
    RecyclerView a_rcView;
    AdapterPlacesSearchItem adapter;
    BottomSheetDialog dialog;
    BottomSheetBehavior bottomSheetBehavior;
    String title;

    private AdapterView.OnItemSelectedListener mListener;



    public PointAFragment(String title){
        this.title= title;
    }
    public String getTitle(){
        return title;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return dialog;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPointABinding.inflate(inflater, container, false);
        binding.textTitle.setText(title);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void init() {

        binding.closeBtn.setOnClickListener(view -> dismiss());
        a_rcView = binding.aRcView;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://autocomplete.travelpayouts.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AviaAPI aviaApi = retrofit.create(AviaAPI.class);

        binding.aSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                aviaApi.searchPlaces(s).enqueue(new Callback<ArrayList<SearchedPlace>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchedPlace>> call, Response<ArrayList<SearchedPlace>> response) {
                        ArrayList<SearchedPlace> arr = response.body();

                        adapter = new AdapterPlacesSearchItem(arr);
                        adapter.setOnItemClickListener(new AdapterPlacesSearchItem.OnItemClickListener() {
                            @Override
                            public void onItemClick(SearchedPlace item) {
                                ArrayList<String> data = new ArrayList<>();
                                data.add(item.getName());
                                data.add(item.getCountry_name());
                                data.add(item.getMain_airport_name());
                                Bundle bundle = new Bundle();
                                bundle.putStringArrayList("ItemData", data);
                                getParentFragmentManager().setFragmentResult("datafromPointA", bundle);
                                dismiss();
                            }
                        });
                        a_rcView.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(Call<ArrayList<SearchedPlace>> call, Throwable t) {
                        if (t instanceof IOException){
                            Log.d("LOL", "ошибка сети");
                        }else{
                            Log.d("LOL", "ошибка разбора данных или сервера");
                        }

                    }
                });
                return true;
            }
        });



    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        CoordinatorLayout layout = dialog.findViewById(R.id.bottomSheetLayout);
        assert layout != null;
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);

        // Добавьте здесь обработчики событий для кнопок, полей ввода и т. д.
    }


}
