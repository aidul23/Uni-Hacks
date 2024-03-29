package com.duodevloopers.foodup.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.duodevloopers.foodup.Constant.Constant;
import com.duodevloopers.foodup.Model.User;
import com.duodevloopers.foodup.R;
import com.duodevloopers.foodup.databinding.FragmentProfileBinding;
import com.duodevloopers.foodup.myapp.MyApp;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ProfileFragment";
    private FragmentProfileBinding binding;
    private NavController navController;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.username.setText(MyApp.Companion.getLoggedInUser().name);
        binding.userDept.setText(MyApp.Companion.getLoggedInUser().department);
        binding.userId.setText(MyApp.Companion.getLoggedInUser().id);
        binding.userNumber.setText(MyApp.Companion.getLoggedInUser().number);

        binding.editUsername.setOnClickListener(this);
        binding.editUserEmail.setOnClickListener(this);
        binding.editUserDept.setOnClickListener(this);
        binding.editUserProfilePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();

        switch (v.getId()){
            case R.id.edit_username:
                bundle.putInt(Constant.USER_EDIT_SELECTION, Constant.USER_NAME_VALUE);
                bundle.putString(Constant.USER_NAME, (String) binding.username.getText());
                navController.navigate(R.id.action_profileFragment_to_editUserProfileFragment, bundle);
                break;
            case R.id.edit_user_email:
                bundle.putInt(Constant.USER_EDIT_SELECTION, Constant.USER_EMAIL_VALUE);
                bundle.putString(Constant.USER_EMAIL, (String) binding.userEmail.getText());
                navController.navigate(R.id.action_profileFragment_to_editUserProfileFragment, bundle);
                break;
            case R.id.edit_user_dept:
                bundle.putInt(Constant.USER_EDIT_SELECTION, Constant.USER_DEPT_VALUE);
                bundle.putString(Constant.USER_DEPT, (String) binding.userDept.getText());
                navController.navigate(R.id.action_profileFragment_to_editUserProfileFragment, bundle);
                break;
            case R.id.edit_user_profile_pic:
                ImagePicker.with(this)
                        .galleryOnly()
                        .crop() //Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Uri uri = data.getData();
        binding.profileImage.setImageURI(uri);

    }
}

