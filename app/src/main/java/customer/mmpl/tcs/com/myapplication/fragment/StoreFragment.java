package customer.mmpl.tcs.com.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import customer.mmpl.tcs.com.myapplication.R;
import customer.mmpl.tcs.com.myapplication.adapter.GiftsAdapter;
import customer.mmpl.tcs.com.myapplication.adapter.StoreAdapter;
import customer.mmpl.tcs.com.myapplication.modul.Movies;
import customer.mmpl.tcs.com.myapplication.volley.Contacts_URL;
import customer.mmpl.tcs.com.myapplication.volley.NetworkController;
import customer.mmpl.tcs.com.myapplication.volley.VolleyRequest;

/**
 * Created by BeAsT on 22-Mar-18.
 */

public class StoreFragment extends Fragment {

    private static final String TAG = "StoreFragment";
    private RecyclerView recyclerView;
    private List<Movies> movieList;
    private StoreAdapter storeAdapter;
    private GiftsAdapter mAdapter1;
    public NetworkController networkController;
    public VolleyRequest volleyRequest;

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        storeAdapter = new StoreAdapter(getActivity(), movieList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(storeAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fetchStoreItems();

        return view;
    }


    private void fetchStoreItems() {
        networkController = NetworkController.getNetworkController(getContext());
        volleyRequest = VolleyRequest.getInstances(getContext());
        networkController.MethodPost1(Contacts_URL.test, new NetworkController.VolleyInterfaceArray() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
//                Log.e(TAG, "onSuccessssssssssssssssssssss: " + jsonArray );
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Movies movies = new Movies();
                        movies.setImage(jsonObject.getString("image"));
                        movies.setTitle(jsonObject.getString("title"));
                        movieList.add(movies);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }finally {
                        storeAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onError(VolleyError error) {
//                Log.e(TAG, "errorrrrrrrrrrrrrrrrrr: " + error );
            }
        });
    }
}