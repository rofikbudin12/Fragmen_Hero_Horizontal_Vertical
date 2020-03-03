package rofik.sman3.fragmenthero.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import rofik.sman3.fragmenthero.Model.Hero;
import rofik.sman3.fragmenthero.R;

public class Fragment_Detail extends Fragment {

    public static final String EXTRA_DETAIL = "extra_detail" ;

    private ImageView ivPhoto;
    private TextView tvName, tvDescription;

    private Hero heroes;

    public Fragment_Detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        heroes = new Hero();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            heroes = bundle.getParcelable(EXTRA_DETAIL);
        }

        ivPhoto = view.findViewById(R.id.iv_foto);
        tvName = view.findViewById(R.id.tv_name);
        tvDescription = view.findViewById(R.id.tv_deskripsi);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert heroes != null;
        tvName.setText(heroes.getName());
        tvDescription.setText(heroes.getDetail());
        Glide.with(getActivity())
                .load(heroes.getPhoto())
                .apply(new RequestOptions().override(200,200))
                .into(ivPhoto);

    }

}
