package shop.books.bookiesh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


//this is recyclerview used for showing the address present for the user.........................................
public class RecyclerView_Order extends RecyclerView.Adapter<RecyclerView_Order.MyViewHolder> {

    private Context mContext ;
    private List<Delivery> mData ;
    RequestOptions option;
    Button button;

    public RecyclerView_Order(Context mContext, List<Delivery> mData)
    {
        this.mContext = mContext;
        this.mData = mData;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.address_row_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        button = view.findViewById(R.id.saveandnext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Payment.class);
                i.putExtra("name",mData.get(viewHolder.getAdapterPosition()).getname());
                i.putExtra("totalbill",mData.get(viewHolder.getAdapterPosition()).gettotalbill());
                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tvfullname.setText(mData.get(position).getname());
        holder.tvaddress.setText(mData.get(position).gethouse()+" "+mData.get(position).getarea());
        holder.tvcitystate.setText(mData.get(position).getcity()+","+mData.get(position).getstate());
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvfullname ;
        TextView tvaddress;
        TextView tvcitystate ;
        LinearLayout view_container;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            view_container = itemView.findViewById(R.id.container2);
            tvfullname= itemView.findViewById(R.id.fullname);
            tvcitystate = itemView.findViewById(R.id.citystate);
            tvaddress = itemView.findViewById(R.id.addr);
        }
    }


}