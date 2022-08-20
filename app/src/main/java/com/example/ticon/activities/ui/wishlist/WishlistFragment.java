package com.example.ticon.activities.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticon.R;
import com.example.ticon.activities.MyEmoticonAdapter;
import com.example.ticon.activities.SavedModel;
//import com.example.ticon.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class WishlistFragment extends Fragment {

//    private FragmentGalleryBinding binding;
    private ArrayList<SavedModel> savedModelArrayList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        GalleryViewModel galleryViewModel =
//                new ViewModelProvider(this).get(GalleryViewModel.class);
//
//        binding = FragmentGalleryBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;

        View view = inflater.inflate(R.layout.activity_search, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listRView);

        savedModelArrayList = new ArrayList<>();
        savedModelArrayList.add(new SavedModel(R.drawable.animal_friends1, R.drawable.ic_favorite_pink, "Animal Friends", "JS"));
        savedModelArrayList.add(new SavedModel(R.drawable.mr_donothing1, R.drawable.ic_favorite_pink, "Mr Donothing", "Hoo"));

        MyEmoticonAdapter myEmoticonAdapter = new MyEmoticonAdapter(getContext(), savedModelArrayList);
        recyclerView.setAdapter(myEmoticonAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;

    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}