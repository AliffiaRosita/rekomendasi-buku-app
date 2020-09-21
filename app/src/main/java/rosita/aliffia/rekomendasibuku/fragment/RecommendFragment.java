package rosita.aliffia.rekomendasibuku.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.adapter.BookAdapter;
import rosita.aliffia.rekomendasibuku.api.ApiClient;
import rosita.aliffia.rekomendasibuku.api.ApiInterface;
import rosita.aliffia.rekomendasibuku.data.Book;
import rosita.aliffia.rekomendasibuku.response.ResponseRekomendasi;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "RecommendFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecommendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView rv;
    ApiInterface apiInterface;
    List<Book> bookList;
    BookAdapter bookAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recommend, container, false);
        rv= v.findViewById(R.id.rv_rekomendasi);
        bookAdapter = new BookAdapter(getActivity(),bookList);
        bookAdapter.notifyDataSetChanged();
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        getRecommend();
        return v;
    }

    private void getRecommend() {
        Call<ResponseRekomendasi> call = apiInterface.getRecommend();
        call.enqueue(new Callback<ResponseRekomendasi>() {
            @Override
            public void onResponse(Call<ResponseRekomendasi> call, Response<ResponseRekomendasi> response) {
                if (response.isSuccessful()){
                    if (response.body().getRekomendasi() != null) {
                        bookList = response.body().getRekomendasi();
                        bookAdapter = new BookAdapter(getActivity(), bookList);
                        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rv.setAdapter(bookAdapter);
                    }
                    else {
                        Toast.makeText(getActivity(), "Rekomendasi belum ada, silahkan isi rating dahulu", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRekomendasi> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}