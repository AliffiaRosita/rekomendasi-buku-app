package rosita.aliffia.rekomendasibuku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.data.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    List<Review> reviews;
    Review review;
    String ulasan;
    Context context;

   public ReviewAdapter(List<Review> reviews, Context context){
       this.reviews = reviews;
       this.context  = context;
   }
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ulasan,parent,false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        review = reviews.get(position);
        holder.tvNama.setText(""+review.getNamaPengunjung());

        if (review.getUlasan()== null){
            ulasan = "";
        }else{
            ulasan = review.getUlasan();
        }
        if (review.getFoto() == null) {
            Glide.with(context)
                    .load(R.drawable.emptyimage)
                    .override(95, 130)
                    .into(holder.foto);
        } else {
            Glide.with(context)
                    .load(review.getFoto())
                    .override(95, 130)
                    .into(holder.foto);
        }
        holder.tvUlasan.setText(""+ulasan);
        holder.tvRating.setText(""+review.getRating());

    }

    @Override
    public int getItemCount() {
       if (reviews == null ){
           return 0;
       }else{
           return reviews.size();
       }
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
       TextView tvNama, tvRating, tvUlasan;
       ImageView foto;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.ulasan_nama);
            tvRating = itemView.findViewById(R.id.ulasan_rate);
            tvUlasan= itemView.findViewById(R.id.ulasan_isi);
            foto = itemView.findViewById(R.id.ulasan_foto);
        }
    }
}
