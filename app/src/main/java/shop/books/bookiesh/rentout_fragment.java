package shop.books.bookiesh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


//this class is used for renting out the books which user owns...............................
public class rentout_fragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_rentout,null);
        ImageView imgcap = view.findViewById(R.id.imglease);
        getActivity().setTitle("Rentout");
        imgcap.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),UploadImg.class));
    }
}

