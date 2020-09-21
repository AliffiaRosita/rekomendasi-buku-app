package rosita.aliffia.rekomendasibuku.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import rosita.aliffia.rekomendasibuku.data.Book;

public class ResponseBook {
    @SerializedName("current_page")
    int currentPage;
    @SerializedName("last_page")
    int last_page;
    @SerializedName("per_page")
    int perPage;
    @SerializedName("data")
   List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getLast_page() {
        return last_page;
    }

    public int getPerPage() {
        return perPage;
    }
}
