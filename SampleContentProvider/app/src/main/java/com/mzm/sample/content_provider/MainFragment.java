package com.mzm.sample.content_provider;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mzm.sample.content_provider.provider.VersionContract;

/**
 * Created by Margaret on 11/22/15.
*
* A fragment that shows a list of Android versions.
* Use CursorLoader to load data
*
*/
public class MainFragment extends ListFragment implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private SimpleCursorAdapter mAdapter;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] fromFields = new String[] {VersionContract.Version.CODE_NAME};
        int[] toFields = new int[] {android.R.id.text1};

        getLoaderManager().initLoader(0, null, this);

        mAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                null,
                fromFields,
                toFields,
                0
        );

        setListAdapter(mAdapter);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        // Start a new or restarts an existing loader
        getLoaderManager().restartLoader(0, null, this);
    }

    /**
     * Called when a new Loader needs to be created
     *
     * @param i
     * @param bundle
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(getActivity(),
                VersionContract.Version.CONTENT_URI,
                VersionContract.Version.PROJECTION,
                null,
                null,
                null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_mainfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                //TODO: add data
                Toast.makeText(getActivity(), "To be implemented", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_delete:
                //TODO: delete data
                Toast.makeText(getActivity(), "To be implemented", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

/*    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Uri uri = Uri.parse(AndroidContract.Version.CONTENT_URI + "/" + id);
        intent.putExtra("uri", uri);
        startActivity(intent);

        super.onListItemClick(l, v, position, id);
    }*/
}