package rosita.aliffia.rekomendasibuku.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.adapter.BookAdapter;
import rosita.aliffia.rekomendasibuku.api.ApiClient;
import rosita.aliffia.rekomendasibuku.api.ApiInterface;
import rosita.aliffia.rekomendasibuku.data.Book;
import rosita.aliffia.rekomendasibuku.response.ResponseBook;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Main Activity";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBook.
     */
    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ImageView caribuku;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    RecyclerView rvBook;
    ProgressBar pb;
    NestedScrollView nestedScrollView;
    List<Book> bookList = new ArrayList<>();
    BookAdapter bookAdapter;
    int page = 1;
    String keyword = "";
    ApiInterface apiInterface;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_book, container,false);

        nestedScrollView = v.findViewById(R.id.nested_scroll_book);
        rvBook = v.findViewById(R.id.rv_book);
        pb = v.findViewById(R.id.pb_showbook);

        searchView = v.findViewById(R.id.searchView);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        bookAdapter = new BookAdapter(getActivity(),bookList);
        bookAdapter.notifyDataSetChanged();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                pb.setVisibility(View.VISIBLE);
                bookList.clear();
                getData(s,page);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });


        getData(keyword,page);
        Log.d(TAG, "onCreateView: "+bookList);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight() + 16){
                    page++;
                    pb.setVisibility(View.VISIBLE);
//                    Log.d(TAG, "onScrollChange: "+page);
                    getData(keyword,page);
//                    Log.d(TAG, "onScrollChange: "+bookList);
                }

            }
        });
        return v;
    }

    private void getData(String keywords,int i) {
        Call<ResponseBook> responseBookCall = apiInterface.searchBook(keywords,i);
        responseBookCall.enqueue(new Callback<ResponseBook>() {
            @Override
            public void onResponse(Call<ResponseBook> call, Response<ResponseBook> response) {
                Log.d(TAG, "onResponse: "+response.message()+" "+response.code());
                if (response.isSuccessful()){
                    pb.setVisibility(View.GONE);
                       List<Book> temp = response.body().getBookList();
                       for (int i = 0; i<temp.size(); i++){
                           Book book = new Book();
                           book.setJudul(temp.get(i).getJudul());
                           book.setDeskripsi(temp.get(i).getDeskripsi());
                           book.setFoto(temp.get(i).getFoto());
                           book.setPenerbit(temp.get(i).getPenerbit());
                           book.setId(temp.get(i).getId());
                           bookList.add(book);
                       }
                        bookAdapter = new BookAdapter(getActivity(),bookList);
                    rvBook.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rvBook.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseBook> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onClick(View view) {

    }
}