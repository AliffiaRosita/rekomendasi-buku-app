package rosita.aliffia.rekomendasibuku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.adapter.BookHomeAdapter;
import rosita.aliffia.rekomendasibuku.adapter.ReviewAdapter;
import rosita.aliffia.rekomendasibuku.api.ApiClient;
import rosita.aliffia.rekomendasibuku.api.ApiInterface;
import rosita.aliffia.rekomendasibuku.data.DetailBook;
import rosita.aliffia.rekomendasibuku.data.Review;
import rosita.aliffia.rekomendasibuku.response.ResponseAllRate;
import rosita.aliffia.rekomendasibuku.response.ResponseDetail;
import rosita.aliffia.rekomendasibuku.response.ResponseSaveRate;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvDesc, tvPenerbit, tvTempat, tvAverage,tvIsbn;
    ImageView foto;
    RatingBar ratingBar;
    ApiInterface apiInterface;
    String TAG = "detail";
    int idBook;
    DetailBook detailBook;
    List<Review> reviews;
    RecyclerView rvReview;
    ReviewAdapter reviewAdapter;
    Button btnRate;
    EditText etUlasan;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvDesc = findViewById(R.id.tv_desc);
        tvAverage = findViewById(R.id.detail_rating);
        foto = findViewById(R.id.foto_detail);
        tvTempat = findViewById(R.id.detail_tempat);
        tvPenerbit = findViewById(R.id.detail_penerbit);
        tvIsbn = findViewById(R.id.detail_isbn);

        rvReview = findViewById(R.id.rv_ulasan);
        ratingBar = findViewById(R.id.ratingBar);
        btnRate = findViewById(R.id.btn_rate);
        etUlasan = findViewById(R.id.et_ulasan);

        reviewAdapter = new ReviewAdapter(reviews, DetailActivity.this);
        reviewAdapter.notifyDataSetChanged();
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
         idBook = getIntent().getIntExtra("EXTRA_ID",0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        loadDetail();
        loadReview();
        btnRate.setOnClickListener(this);
    }

    private void loadReview() {
        Call<ResponseAllRate> responseAllRateCall = apiInterface.allRate(idBook);
        responseAllRateCall.enqueue(new Callback<ResponseAllRate>() {
            @Override
            public void onResponse(Call<ResponseAllRate> call, Response<ResponseAllRate> response) {
                reviews = response.body().getReviews();
                reviewAdapter = new ReviewAdapter(reviews,DetailActivity.this);
                rvReview.setAdapter(reviewAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this);
                rvReview.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ResponseAllRate> call, Throwable t) {
                Toast.makeText(DetailActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDetail() {

        Call<ResponseDetail> responseDetailCall= apiInterface.bookDetail(idBook);

        responseDetailCall.enqueue(new Callback<ResponseDetail>() {
            @Override
            public void onResponse(Call<ResponseDetail> call, Response<ResponseDetail> response) {
                if (response.isSuccessful()){
                    detailBook = response.body().getDetailBook();
                    collapsingToolbarLayout.setTitle(""+detailBook.getJudul());
                    if (detailBook.getFoto() == null){
                        Glide.with(DetailActivity.this).load(R.drawable.emptyimage).into(foto);
                    }else{
                        Glide.with(DetailActivity.this).load(detailBook.getFoto()).into(foto);
                    }

                    tvDesc.setText(""+detailBook.getDeskripsi());
                    tvPenerbit.setText(""+detailBook.getPenerbit());
                    tvTempat.setText(""+detailBook.getTempatTerbit());
                    tvAverage.setText(""+detailBook.getAverage());
                    tvIsbn.setText(""+detailBook.getIsbn());

                    if (detailBook.getUserRating() != 0 ){
                        ratingBar.setRating(detailBook.getUserRating());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseDetail> call, Throwable t) {
                Toast.makeText(DetailActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_rate:
                float ratingValue= ratingBar.getRating();
                String ulasan = etUlasan.getText().toString();
                if (ratingValue == 0){
                    Toast.makeText(this, "Rating wajib diisi", Toast.LENGTH_SHORT).show();
                }else {
                    saveRate(ratingValue,ulasan);
                }
        }
    }

    private void saveRate(float rating,String ulasan) {
        Call<ResponseSaveRate> rateCall = apiInterface.saveRate(idBook,rating,ulasan);
        rateCall.enqueue(new Callback<ResponseSaveRate>() {
            @Override
            public void onResponse(Call<ResponseSaveRate> call, Response<ResponseSaveRate> response) {
                String message = response.body().getMessage();
                Toast.makeText(DetailActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                loadReview();
            }

            @Override
            public void onFailure(Call<ResponseSaveRate> call, Throwable t) {
                Toast.makeText(DetailActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}