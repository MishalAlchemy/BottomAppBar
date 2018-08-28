package adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.appbarbottom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import interfaces.CustomItemClick;
import model.Profile;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    ArrayList<Profile> arrayList;
    CustomItemClick customItemClick;

    public ItemAdapter(ArrayList<Profile> arrayList, CustomItemClick customItemClick) {
        this.arrayList = arrayList;
        this.customItemClick = customItemClick;
    }

    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ItemAdapter.MyViewHolder vh = new ItemAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Profile profile = arrayList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(profile.imageUrl)
                .into(holder.imageView);

        ViewCompat.setTransitionName(holder.imageView, profile.name);
        ViewCompat.setTransitionName(holder.imageView, profile.detail);
        holder.textName.setText(profile.name);
        holder.textAddress.setText(profile.detail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemClick.onClick(holder.getAdapterPosition(), profile, holder.imageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView textName, textAddress;

        public MyViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textAddress = itemView.findViewById(R.id.textDescription);
            imageView = itemView.findViewById(R.id.item_animal_square_image);

        }
    }

}

