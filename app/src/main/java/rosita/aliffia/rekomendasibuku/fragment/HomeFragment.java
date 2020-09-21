package rosita.aliffia.rekomendasibuku.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.activity.DetailActivity;
import rosita.aliffia.rekomendasibuku.adapter.BookHomeAdapter;
import rosita.aliffia.rekomendasibuku.api.ApiClient;
import rosita.aliffia.rekomendasibuku.api.ApiInterface;
import rosita.aliffia.rekomendasibuku.data.BookHome;
import rosita.aliffia.rekomendasibuku.response.ResponseBookHome;


public class HomeFragment extends Fragment {

    ImageView caribuku;
    RecyclerView rvBook;
    private ApiInterface apiInterface;
    String TAG= "HomeFragment";
    public List<BookHome> bookHomeList;
    public BookHomeAdapter bookHomeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container,false);
        caribuku = v.findViewById(R.id.cari_buku);
        rvBook = v.findViewById(R.id.rv_home_book);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        bookHomeAdapter = new BookHomeAdapter(bookHomeList,getActivity());
        bookHomeAdapter.notifyDataSetChanged();
        loadBook();

        return v;
    }

    private void loadBook() {
        Call<ResponseBookHome> responseBookHomeCall = apiInterface.mostRated();
        responseBookHomeCall.enqueue(new Callback<ResponseBookHome>() {
            @Override
            public void onResponse(Call<ResponseBookHome> call, Response<ResponseBookHome> response) {
                bookHomeList = response.body().getBookHomes();
                 bookHomeAdapter = new BookHomeAdapter(bookHomeList,getActivity());
                rvBook.setAdapter(bookHomeAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
                rvBook.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ResponseBookHome> call, Throwable t) {
                  Toast.makeText(getActivity(), "t", Toast.LENGTH_SHORT).show();
            }
        });
    }

}