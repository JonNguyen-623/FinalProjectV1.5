package edu.sjsu.android.finalprojectv1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/*
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
*/

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemFragment extends Fragment {

    ArrayList<Item> data;
    ArrayList<Item> favorites;

    public ItemFragment() {
        //empty constructor - has to be empty
    }


    //all the data is actually created here
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            //detail retrieves Item, data list, favorite list here

            data = getArguments().getParcelableArrayList(getString(R.string.arraylist_key));
            favorites = getArguments().getParcelableArrayList(getString(R.string.favorites_key));
        }
        else{
            //initialize full data list and favorite list
            data = new ArrayList<>();
            favorites = new ArrayList<>();

            //TODO: add the data here - from API

            //bogus data
            data.add(new Item(R.drawable.fav, "Favorites", "placeholder"));
            data.add(new Item(R.drawable.smt, getString(R.string.placeholder), "SMT: .77"));
            data.add(new Item(R.drawable.smt2, getString(R.string.placeholder2), "PTS: 2.00"));
            data.add(new Item(R.drawable.niyari, getString(R.string.placeholder3), "SRK: 0.80"));
            data.add(new Item(R.drawable.smt, "Megaten", "MT: 0.88"));

            //getData(); - for use with API

            //TODO: find a way to get to the favorites fragment without going through menu
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);


        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //recyclerView.setAdapter(new MyAdapter(data));

            //pass the onClick method to adapter using lambda
            MyAdapter adapter = new MyAdapter(data, this::onClick);

            recyclerView.setAdapter(adapter);
            getActivity().setTitle("Cryptocurrency List");

        }
        return view;
    }

    public void onClick(int position) {


        if(position == 0){
            //go to the menu
            Bundle bundle = new Bundle();
            //send full list, favorites list
            bundle.putParcelableArrayList(getString(R.string.arraylist_key), data);
            bundle.putParcelableArrayList(getString(R.string.favorites_key), favorites);
            NavController controller = NavHostFragment.findNavController(this);
            controller.navigate(R.id.action_itemFragment_to_myItems, bundle);

        }

        else goDetail(position);
    }


    public void goDetail(int position){
        //This basically sends item information to the detail fragment
        Item selected = data.get(position);
        Bundle bundle = new Bundle();

        //send Item picked, full list, favorites list
        bundle.putParcelable(getString(R.string.argument_key), selected); //put in item
        bundle.putParcelableArrayList(getString(R.string.arraylist_key), data);
        bundle.putParcelableArrayList(getString(R.string.favorites_key), favorites);

        //then go to detail
        NavController controller = NavHostFragment.findNavController(this);
        controller.navigate(R.id.action_itemFragment_to_detailFragment, bundle);

    }


    /**
     * Newly added 5/8
     *
    private void getData() {
        // creating a variable for storing our string.
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        // creating a variable for request queue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // making a json object request to fetch data from API.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // inside on response method extracting data
                // from response and passing it to array list
                // on below line we are making our progress
                // bar visibility to gone.
                try {
                    // extracting data from json.
                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        String symbol = dataObj.getString("symbol");
                        String name = dataObj.getString("name");
                        JSONObject quote = dataObj.getJSONObject("quote");
                        JSONObject USD = quote.getJSONObject("USD");
                        double price = USD.getDouble("price");
                        // adding all data to our array list.

                        data.add(new Item(R.drawable.smt, name, "Option1"));
                    }
                    Toast.makeText(getContext(), "GOT DATA!", Toast.LENGTH_SHORT).show();
                    // notifying adapter on data change.
                } catch (JSONException e) {
                    // handling json exception.
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Something went amiss with getting the json data. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // displaying error response when received any error.
                Toast.makeText(getContext(), "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                // in this method passing headers as
                // key along with value as API keys.
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "329fbc44-fbc0-4b77-9b2b-0f992dce5521");
                // at last returning headers
                return headers;
            }
        };
        // calling a method to add our
        // json object request to our queue.
        queue.add(jsonObjectRequest);
    }
     */

}