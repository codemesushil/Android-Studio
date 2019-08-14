package shop.books.bookiesh;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//this is recyclerview used for showing the books present in the cart.........................................
public class RecyclerViewAdapter_cart extends RecyclerView.Adapter<RecyclerViewAdapter_cart.MyViewHolder> {

    private Context mContext ;
    private List<book> mData ;
    RequestOptions option;
    Button button;


    public RecyclerViewAdapter_cart(Context mContext, List<book> mData)
    {
        this.mContext = mContext;
        this.mData = mData;
        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cart_row_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        button = view.findViewById(R.id.buttonrem);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ConfirmRemove.class);
                i.putExtra("bkid",mData.get(viewHolder.getAdapterPosition()).getBkid());
                i.putExtra("image",mData.get(viewHolder.getAdapterPosition()).getbkurl());
                mContext.startActivity(i);

            }
        });

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tv_bkname.setText(mData.get(position).getbkname());
        holder.tv_bkoriginalprice.setText("₹ "+mData.get(position).getbkoriginalprice());
        holder.tv_bkauthor.setText(mData.get(position).getbkauthor());
        holder.tv_bkrent.setText("₹ "+mData.get(position).getbkrent());
        holder.noofdays.setText(mData.get(position).getdays());
        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData.get(position).getbkurl()).apply(option).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_bkname ;
        TextView noofdays;
        TextView tv_bkoriginalprice ;
        TextView tv_bkauthor ;
        TextView tv_bkrent;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            view_container = itemView.findViewById(R.id.container1);
            tv_bkname= itemView.findViewById(R.id.tvbkname);
            tv_bkoriginalprice = itemView.findViewById(R.id.cartoriginalprice);
            tv_bkauthor = itemView.findViewById(R.id.cartauthor);
            tv_bkrent = itemView.findViewById(R.id.tvrent);
            img_thumbnail = itemView.findViewById(R.id.imgbook);
            noofdays = itemView.findViewById(R.id.noofdays);
        }
    }


}