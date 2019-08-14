package shop.books.bookiesh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

//this is adapter class for walkthrough of the app at the begining.............................................
public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public  SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slide_images={
            R.drawable.imgbkgrnd,
            R.drawable.images,
            R.drawable.thirdwalk
    };


    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageView= view.findViewById(R.id.myslideimg);

        imageView.setImageResource(slide_images[position]);

        container.addView(view);
     return view;
    }

    public void destroyItem(ViewGroup container, int position , Object object){
        container.removeView((ConstraintLayout)object);
    }
}
