package com.twitter.loader;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<String>> {

    private static final int STRING_LOADER = 1;
    private ListView mList;
    private ArrayAdapter<String> mListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        mListAdapter = new ArrayAdapter<>(getContext().getApplicationContext(), R.layout.list_item);
        mList = (ListView) view.findViewById(R.id.list);
        mList.setAdapter(mListAdapter);
        Toast.makeText(getContext(), "Adapter created", Toast.LENGTH_SHORT).show();

        getLoaderManager().initLoader(STRING_LOADER, null, this);
        return view;
    }

    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        return new StringLoader(getContext().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
        mListAdapter.clear();
        mListAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {
        mListAdapter.clear();
    }
}
