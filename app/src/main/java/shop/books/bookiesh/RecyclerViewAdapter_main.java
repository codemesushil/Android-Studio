package shop.books.bookiesh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

//this is recyclerview used for displaying all the available books in explore fragment to the all users................
public class RecyclerViewAdapter_main extends RecyclerView.Adapter<RecyclerViewAdapter_main.MyViewHolder> {

    private Context mContext ;
    private List<book> mData ;
    RequestOptions option;


    public RecyclerViewAdapter_main(Context mContext, List<book> mData)
    {
        this.mContext = mContext;
        this.mData = mData;
        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.book_row_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.view_container.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, BookActivity.class);
                i.putExtra("bkid",mData.get(viewHolder.getAdapterPosition()).getBkid());
                i.putExtra("name",mData.get(viewHolder.getAdapterPosition()).getbkname());
                i.putExtra("originalprice",mData.get(viewHolder.getAdapterPosition()).getbkoriginalprice());
                i.putExtra("author",mData.get(viewHolder.getAdapterPosition()).getbkauthor());
                i.putExtra("rent",mData.get(viewHolder.getAdapterPosition()).getbkrent());
                i.putExtra("image",mData.get(viewHolder.getAdapterPosition()).getbkurl());
                i.putExtra("available",mData.get(viewHolder.getAdapterPosition()).getavailable());
                mContext.startActivity(i);

            }
        });
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tv_bkname.setText(mData.get(position).getbkname());
        holder.tv_bkoriginalprice.setText(mData.get(position).getbkoriginalprice());
        holder.tv_bkauthor.setText(mData.get(position).getbkauthor());
        holder.tv_bkrent.setText(mData.get(position).getbkrent());
        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData.get(position).getbkurl()).apply(option).into(holder.img_thumbnail);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_bkname ;
        TextView tv_bkoriginalprice ;
        TextView tv_bkauthor ;
        TextView tv_bkrent;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            tv_bkname= itemView.findViewById(R.id.name);
            tv_bkoriginalprice = itemView.findViewById(R.id.originalprice);
            tv_bkauthor = itemView.findViewById(R.id.author);
            tv_bkrent = itemView.findViewById(R.id.rent);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
