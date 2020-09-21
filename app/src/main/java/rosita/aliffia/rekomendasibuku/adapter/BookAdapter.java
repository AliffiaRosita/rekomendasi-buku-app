package rosita.aliffia.rekomendasibuku.adapter;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.activity.DetailActivity;
import rosita.aliffia.rekomendasibuku.api.ItemClickListener;
import rosita.aliffia.rekomendasibuku.data.Book;
import rosita.aliffia.rekomendasibuku.response.ResponseBook;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    List<Book> bookList;
    Book book;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        book = bookList.get(position);
        holder.listPenerbit.setText(""+book.getPenerbit());
        holder.listDesc.setText(""+book.getDeskripsi());
        holder.listJudul.setText(""+book.getJudul());
        if (book.getFoto() == null) {
            Glide.with(context)
                    .load(R.drawable.emptyimage)
                    .override(95, 130)
                    .into(holder.cover);
        } else {
            Glide.with(context)
                    .load(book.getFoto())
                    .override(95, 130)
                    .into(holder.cover);
        }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int post) {
                int id = bookList.get(post).getId();
                Intent i= new Intent(context, DetailActivity.class);
                i.putExtra("EXTRA_ID",id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView cover;
        TextView listJudul, listDesc, listPenerbit;
        CardView cardView;
        ItemClickListener itemClickListener;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.img_cover_book);
            listJudul = itemView.findViewById(R.id.list_judul);
            listDesc = itemView.findViewById(R.id.list_deskripsi);
            listPenerbit = itemView.findViewById(R.id.list_penerbit);
            cardView = itemView.findViewById(R.id.card_book);

            cardView.setOnClickListener(this);

        }
        public void setItemClickListener(ItemClickListener ic){this.itemClickListener = ic;}

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view,getLayoutPosition());
        }
    }
}
