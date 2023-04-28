package edu.sjsu.android.finalprojectv1;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sjsu.android.finalprojectv1.databinding.FragmentItemBinding;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<Item> mValues;
    //public static ItemFragment itemFrag;

    private final OnItemClickListener listener;
    //int pos;


    public MyAdapter(List<Item> item, OnItemClickListener listener){
        mValues = item;
        this.listener = listener;

    }

    //create empty ViewHolder. No data passed in yet
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(
        //                parent.getContext()), parent, false), itemFrag);

        FragmentItemBinding binding = FragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, listener);


    }

    //replace contents of ViewHolder with data
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Item item = mValues.get(position);
        holder.image.setImageResource(item.getImageId());
        holder.name.setText(item.getName());

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }


    //start innerclass - custom ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder //implements RecyclerViewInterface
    {
        public final TextView name;
        public final ImageView image;

        //public ItemFragment itemFragment;
        //public final View.OnClickListener listener;
        //RecyclerViewInterface recyclerViewInterface;

        public ViewHolder(FragmentItemBinding binding, OnItemClickListener listener){ //RecyclerViewInterface r) {
            //This is a constructor - defines the container
            super(binding.getRoot());

            image = binding.image;
            name = binding.content;
            binding.getRoot().setOnClickListener(view -> listener.onClick(getLayoutPosition()));


        }


    }
}
