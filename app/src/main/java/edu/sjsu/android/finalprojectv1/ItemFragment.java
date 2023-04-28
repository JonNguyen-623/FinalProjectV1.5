package edu.sjsu.android.finalprojectv1;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ItemFragment extends Fragment {

    ArrayList<Item> data;

    public ItemFragment() {
        //empty constructor - has to be empty
    }


    //all the data is actually created here
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>(); //initialize ArrayList <Item>

        //TODO: add the data here
        //using placeholders for now
        //argument for item is image, name, content
        // we can edit Item class to change things around

        /*
        data.add(new Item(R.drawable.hu, getString(R.string.item1),
                "text"));
        data.add(new Item(R.drawable.ra, getString(R.string.item2),
                "text"));
        data.add(new Item(R.drawable.fo, getString(R.string.item3),
                "text"));
        data.add(new Item(R.drawable.br, getString(R.string.item4),
                "text"));
        data.add(new Item(R.drawable.bo, getString(R.string.item5),
                "text"));

         */

        //sample items
        data.add(new Item(R.drawable.smt, getString(R.string.placeholder), "option1"));
        data.add(new Item(R.drawable.smt, getString(R.string.placeholder), "option2"));
        data.add(new Item(R.drawable.smt, getString(R.string.placeholder), "option3"));
        data.add(new Item(R.drawable.smt, getString(R.string.placeholder), "option4"));



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

        }
        return view;
    }

    public void onClick(int position) {
        //if (position == data.size() - 1) {
            // showDialog(position);

        //}
        //else goDetail(position);
        goDetail(position);
    }

    public void showDialog(int position){
        if(getContext() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Halt!");
            builder.setMessage("Warning: The last item on the list was selected. Continue?");

            builder.setPositiveButton("Yes", (dialog, id) ->{
                //When user selects yes
                goDetail(position);
            });

            builder.setNegativeButton("No", (dialog, id) ->{
                //When user selects no
                //do nothing
            });
            builder.create().show();
        }
    }

    public void goDetail(int position){
        Item selected = data.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argument_key), selected);
        NavController controller = NavHostFragment.findNavController(this);
        controller.navigate(R.id.action_itemFragment_to_detailFragment, bundle);

    }
}