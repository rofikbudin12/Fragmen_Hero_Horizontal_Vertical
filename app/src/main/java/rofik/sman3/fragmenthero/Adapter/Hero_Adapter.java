package rofik.sman3.fragmenthero.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import rofik.sman3.fragmenthero.Fragment.Fragment_Detail;
import rofik.sman3.fragmenthero.Model.Hero;
import rofik.sman3.fragmenthero.R;

public class Hero_Adapter extends RecyclerView.Adapter<Hero_Adapter.ViewHolder> {

    private ArrayList<Hero> heroes;
    private Context context;
    private int type;

    public Hero_Adapter(ArrayList<Hero> heroes, Context context, int type) {
        this.heroes = heroes;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (type) {
            case 1 :
                view = LayoutInflater.from(context).inflate(R.layout.item_column_heroes, parent, false);
                return new ViewHolder(view);
            case  2 :
                view = LayoutInflater.from(context).inflate(R.layout.item_row_heroes, parent, false);
                return new ViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.BindItem(heroes.get(position), context);
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.iv_foto);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_deskripsi);
        }

        void BindItem(final Hero hero, final Context context) {

            tvName.setText(hero.getName());
            Glide.with(context)
                    .load(hero.getPhoto())
                    .apply(new RequestOptions().override(200,200))
                    .into(ivPhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,hero.getName(),Toast.LENGTH_SHORT).show();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Fragment_Detail.EXTRA_DETAIL, hero);

                    Fragment_Detail fragment = new Fragment_Detail();
                    fragment.setArguments(bundle);

                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
            });

            if (type == 2)
                tvDescription.setText(hero.getDetail());
        }
    }
}

