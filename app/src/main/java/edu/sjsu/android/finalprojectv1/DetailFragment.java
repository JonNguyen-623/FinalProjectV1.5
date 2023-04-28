package edu.sjsu.android.finalprojectv1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sjsu.android.finalprojectv1.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {

    private String title;

    Item selected;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
        //get all arguments of the item
        selected = getArguments().getParcelable(getString(R.string.argument_key));

    }

    //set the data?
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater);
        if(selected != null){
            //get null errors with the following
            //int imgID = selected.getImageId(); //null?
            //String desc = selected.getDesc(); // description
            binding.title.setText(selected.getName());
            binding.content.setText(selected.getDesc());
            binding.image.setImageResource(selected.getImageId());
            binding.content.setMovementMethod(new ScrollingMovementMethod());

        }
        //binding.title.setText(title);
        return binding.getRoot();
    }
}