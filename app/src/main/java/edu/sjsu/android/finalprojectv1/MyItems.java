package edu.sjsu.android.finalprojectv1;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Array;
import java.util.ArrayList;

import edu.sjsu.android.finalprojectv1.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class MyItems extends Fragment {



    ArrayList<Item> data; // full data
    ArrayList<Item> favorites; // data to display
    MyAdapter adapter;
    RecyclerView view;

    public MyItems() {
    }

    public ArrayList<Item> getData(){
        return data;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();
        favorites = new ArrayList<>();

        if(getArguments() != null){
            data = getArguments().getParcelableArrayList(getString(R.string.arraylist_key));
            favorites = getArguments().getParcelableArrayList(getString(R.string.favorites_key));

            /*
            for(int i = 0; i < data.size(); i++){
                if(data.get(i).isFavorite()){
                    favorites.add(data.get(i));
                }
            }

             */
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_items_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new MyAdapter(data, this::onClick);
            recyclerView.setAdapter(adapter);

            getActivity().setTitle("Favorites");

            //need to save instance

            //TODO: get item from the main list and add it here
            if(getArguments() != null){
                Item i = getArguments().getParcelable(getString(R.string.argument_key));

                //if the data isn't already in the list, add it
                if(!data.contains(i)){
                    data.add(i);
                }

            }

            //data.add(new Item(R.drawable.smt, "sample", "desc"));

            //this not working?
            swipeToDelete();


        }
        return view;
    }

    public void onClick(int position) {
        goDetail(position);
    }


    public void goDetail(int position){
        Item selected = data.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argument_key), selected);
        NavController controller = NavHostFragment.findNavController(this);
        controller.navigate(R.id.action_myItems_to_detailFragment, bundle);

    }

    public void swipeToDelete(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int swipeDir) {
                        int position = viewHolder.getLayoutPosition();
                        data.remove(position);
                        adapter.notifyItemRemoved(position);
                    }
                };
        ItemTouchHelper itemTouch = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouch.attachToRecyclerView(view);

    }


}