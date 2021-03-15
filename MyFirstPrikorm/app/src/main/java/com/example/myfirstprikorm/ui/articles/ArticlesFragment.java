package com.example.myfirstprikorm.ui.articles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.myfirstprikorm.R;

public class ArticlesFragment extends Fragment {

    private ListView list_art;
    private String[] array_art;
    private ArrayAdapter<String> adapter_art;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_articles,container, false);

        list_art = root.findViewById(R.id.listView_art);
        array_art = getResources().getStringArray(R.array.articles);
        adapter_art = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, array_art);
        list_art.setAdapter(adapter_art);
        return root;
    };
}