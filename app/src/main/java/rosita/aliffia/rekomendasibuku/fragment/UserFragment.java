package rosita.aliffia.rekomendasibuku.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.activity.LoginActivity;
import rosita.aliffia.rekomendasibuku.preference.AppPreference;
import rosita.aliffia.rekomendasibuku.preference.UserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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


    TextView tvnama, tvnim,tvfakultas;
    UserModel userModel;
    ImageView thumbnail;
    AppPreference appPreference;
    Button btnChangePass, btnChangeProfile, btnLogout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appPreference = new AppPreference(getContext());
        userModel = appPreference.getUser();
        View v = inflater.inflate(R.layout.fragment_user,container,false);
        tvnama = v.findViewById(R.id.nama_user);
        tvnim = v.findViewById(R.id.nim_user);
        tvfakultas = v.findViewById(R.id.fakultas_user);
        thumbnail = v.findViewById(R.id.user_image_thumb);

        btnLogout  = v.findViewById(R.id.btn_logout);
        btnChangePass  = v.findViewById(R.id.btn_change_pass);
        btnChangeProfile  = v.findViewById(R.id.btn_change_profile);

        Glide.with(this).load(userModel.getFotoProfil()).into(thumbnail);
        tvnama.setText(userModel.getName());
        tvnim.setText(userModel.getNim());
        tvfakultas.setText("Fakultas "+userModel.getFakultas());
        btnLogout.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_logout:
                userModel.setNim("");
                userModel.setToken("");
                userModel.setActive(false);
                userModel.setAngkatan("");
                userModel.setFotoProfil("");
                userModel.setName("");
                userModel.setUserId("");
                appPreference.setUser(userModel);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
                break;

        }
    }
}