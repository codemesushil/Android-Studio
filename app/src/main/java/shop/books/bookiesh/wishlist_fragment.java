package shop.books.bookiesh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//this class is fragment which holds only books added as favorite by user.........................................
public class wishlist_fragment extends Fragment {


    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<book> lstBook;
    private RecyclerView recyclerView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_wishlist,null);
        getActivity().setTitle("Wishlist");
        lstBook = new ArrayList<>() ;
        recyclerView = view.findViewById(R.id.recyclerviewid12);
        jsonrequest();
        return view;
    }


    private void jsonrequest() {

        request = new JsonArrayRequest(Constants.JSON_URL, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                JSONObject jsonObject  = null ;
                for (int i = 0 ; i < response.length(); i++ )
                {
                    try
                    {
                        jsonObject = response.getJSONObject(i);
                        book book = new book();
                        book.setbkid(jsonObject.getString("bkid"));
                        book.setbkname(jsonObject.getString("name"));
                        book.setbkoriginalprice(jsonObject.getString("originalprice"));
                        book.setbkauthor(jsonObject.getString("author"));
                        book.setbkrent(jsonObject.getString("rent"));
                        book.setbkurl(jsonObject.getString("image"));
                       // book.setuid(jsonObject.getString("uid"));
                        lstBook.add(book);

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(lstBook);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request) ;
    }

    private void setuprecyclerview(List<book> lstBook)
    {
        RecyclerViewAdapter_main myadapter = new RecyclerViewAdapter_main(getContext(), lstBook) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myadapter);
    }
}