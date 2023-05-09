package edu.sjsu.android.finalprojectv1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import edu.sjsu.android.finalprojectv1.databinding.ActivityMainBinding;
import edu.sjsu.android.finalprojectv1.databinding.FragmentDetailBinding;

/**
 * Group added nothing
 */

public class DetailFragment extends Fragment {

    private String title;
    private ArrayList<Item> data; // full list
    private ArrayList<Item> myList; // favorites

    Item selected;

    //private ActivityMainBinding binding;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            selected = getArguments().getParcelable(getString(R.string.argument_key));
            data = getArguments().getParcelableArrayList(getString(R.string.arraylist_key));
            myList = getArguments().getParcelableArrayList(getString(R.string.favorites_key));
        }
    }

    //set the data?
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater);

        getActivity().setTitle("Information"); //if null
        if(selected != null){


            getActivity().setTitle(selected.getName() + " Information");

            binding.title.setText(selected.getName());
            binding.content.setText(selected.getDesc());
            binding.image.setImageResource(selected.getImageId());
            binding.content.setMovementMethod(new ScrollingMovementMethod());

            //added
            binding.itemButton.setOnClickListener(v->addItem()); //pass args
        }


        //binding.title.setText(title);
        return binding.getRoot();
    }

    public void addItem(){
        //Do not need to navigate
        if(selected != null){

            if(!myList.contains(selected)){
                myList.add(selected);
                selected.setFavorite(true);
                Toast.makeText(getContext(), "Added to favorites!",
                        Toast.LENGTH_LONG).show();

                Bundle bundle = new Bundle();

                bundle.putParcelableArrayList(getString(R.string.arraylist_key), data);
                bundle.putParcelableArrayList(getString(R.string.favorites_key), myList);

                //can edit this out, or go somewhere else
                NavController c = NavHostFragment.findNavController(this);
                c.navigate(R.id.action_global_myItems, bundle);
            }
            else{
                Toast.makeText(getContext(), "Already in favorites!",
                        Toast.LENGTH_LONG).show();
            }

        }

    }
}