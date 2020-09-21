package rosita.aliffia.rekomendasibuku.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.activity.DetailActivity;
import rosita.aliffia.rekomendasibuku.api.ItemClickListener;
import rosita.aliffia.rekomendasibuku.data.BookHome;

public class BookHomeAdapter extends RecyclerView.Adapter<BookHomeAdapter.BookHomeViewHolder> {
    private BookHome bookHome;
    private List<BookHome> listBook;
    Context context;

    public BookHomeAdapter(List<BookHome> listBook, Context context) {
        this.listBook = listBook;
        this.context = context;
    }

    @NonNull
    @Override
    public BookHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_home, parent, false);
        return new BookHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHomeViewHolder holder, int position) {
        bookHome = listBook.get(position);
        holder.tvJudul.setText("" + bookHome.getJudul());
        holder.tvAverage.setText("" + bookHome.getAverage());
        if (bookHome.getFoto() == null) {
            Glide.with(context)
                    .load(R.drawable.emptyimage)
                    .override(95, 130)
                    .into(holder.foto);
        } else {
            Glide.with(context)
                    .load(bookHome.getFoto())
                    .override(95, 130)
                    .into(holder.foto);
        }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int post) {
                int id = listBook.get(position).getId();

                    Intent i= new Intent(context, DetailActivity.class);
                i.putExtra("EXTRA_ID",id);
                context.startActivity(i);
            }
        });

}


    @Override
    public int getItemCount() {
        return listBook.size();
    }
    public class BookHomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvJudul, tvAverage;
        ImageView foto;
        CardView cardView;
        ItemClickListener itemClickListener;
        public BookHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.judul_buku_home);
            tvAverage = itemView.findViewById(R.id.average_home);
            foto = itemView.findViewById(R.id.thumbnail_buku_home);
            cardView = itemView.findViewById(R.id.card_home);
            cardView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener ic){this.itemClickListener = ic;}
        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view,getLayoutPosition());
        }
    }
}
