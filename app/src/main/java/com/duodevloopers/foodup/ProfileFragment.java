package com.duodevloopers.foodup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.duodevloopers.foodup.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private FragmentProfileBinding binding;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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

        binding.editUsername.setOnClickListener(this);
        binding.editUserEmail.setOnClickListener(this);
        binding.editUserDept.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();

        switch (v.getId()){
            case R.id.edit_username:
                bundle.putInt(Constant.USER_EDIT_SELECTION, Constant.USER_NAME_VALUE);
                navController.navigate(R.id.action_profileFragment_to_editUserProfileFragment, bundle);
                break;
            case R.id.edit_user_email:
                bundle.putInt(Constant.USER_EDIT_SELECTION, Constant.USER_EMAIL_VALUE);
//                bundle.putString(Constant.USER_EMAIL,binding.userEmail.toString());
                navController.navigate(R.id.action_profileFragment_to_editUserProfileFragment, bundle);
                break;
            case R.id.edit_user_dept:
                bundle.putInt(Constant.USER_EDIT_SELECTION, Constant.USER_DEPT_VALUE);
                navController.navigate(R.id.action_profileFragment_to_editUserProfileFragment, bundle);
                break;
        }
    }
}

