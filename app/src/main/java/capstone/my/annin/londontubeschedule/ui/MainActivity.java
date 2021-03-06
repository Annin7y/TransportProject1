/* Copyright 2020 Anastasia Annin

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package capstone.my.annin.londontubeschedule.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import capstone.my.annin.londontubeschedule.R;
//import capstone.my.annin.londontubeschedule.data.TubeLineContract;
import capstone.my.annin.londontubeschedule.maps.StationMapActivity;
//import capstone.my.annin.londontubeschedule.recyclerviewadapters.FavoritesAdapter;

import capstone.my.annin.londontubeschedule.scrollbehavior.DisableSwipeBehavior;

public class MainActivity extends AppCompatActivity
{
        public TubeLineFragment fragmentTubeLine;
        public OvergroundLineFragment fragmentOverground;
        private final String TUBE_LINE_FRAGMENT = "tube_line_fragment";
        private final String TUBE_OVERGROUND_FRAGMENT = "overground_fragment";
        private boolean isSnackbarShowing = false;
        private static final String SNACKBAR_STATE = "snackbar_state";
        CoordinatorLayout mCoordinatorLayout;
        @BindView(R.id.pb_loading_indicator)
        ProgressBar mLoadingIndicator;
        private Context context;
        ShowSnackbar showSnackbar;
        ViewPager viewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);

                // Set the content of the activity to use the activity_main.xml layout file
                setContentView(R.layout.activity_main);

                mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

                context = getApplicationContext();
                // Find the view pager that will allow the user to swipe between fragments
                viewPager = (ViewPager) findViewById(R.id.viewpager);

                //setSupportActionBar(toolbar);

                // Create an adapter that knows which fragment should be shown on each page
             //   CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

                // Set the adapter onto the view pager
               // viewPager.setAdapter(adapter);

                viewPager.setAdapter(new CategoryAdapter(this, getSupportFragmentManager()));

                // Find the tab layout that shows the tabs
                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

                // Connect the tab layout with the view pager. This will
                //   1. Update the tab layout when the view pager is swiped
                //   2. Update the view pager when a tab is selected
                //   3. Set the tab layout's tab names with the view pager's adapter's titles
                //      by calling onPageTitle()
                tabLayout.setupWithViewPager(viewPager);

                if (savedInstanceState == null)
                {
                        if (isNetworkStatusAvailable(getApplicationContext()))
                        {
                                fragmentTubeLine = (TubeLineFragment)
                                        getSupportFragmentManager().findFragmentByTag(TUBE_LINE_FRAGMENT);
                                fragmentOverground = (OvergroundLineFragment)
                                        getSupportFragmentManager().findFragmentByTag(TUBE_OVERGROUND_FRAGMENT);

                        } else
                                {
                                Snackbar
                                        .make(mCoordinatorLayout, R.string.snackbar_internet, Snackbar.LENGTH_INDEFINITE)
                                        .setAction(R.string.snackbar_retry, new MainActivity.MyClickListener())
                                        .setBehavior(new DisableSwipeBehavior())
                                        .show();
                                isSnackbarShowing = true;
                                showErrorMessage();

                        }
                } else {
                        isSnackbarShowing = savedInstanceState.getBoolean(SNACKBAR_STATE);
                        if (isSnackbarShowing)
                        {
                                Snackbar
                                        .make(mCoordinatorLayout, R.string.snackbar_internet, Snackbar.LENGTH_INDEFINITE)
                                        .setAction(R.string.snackbar_retry, new MainActivity.MyClickListener())
                                        .setBehavior(new DisableSwipeBehavior())
                                        .show();
                }
                        }

        }


    public class MyClickListener implements View.OnClickListener
        {
                @Override
                public void onClick(View v)
                {
                        if (isNetworkStatusAvailable(context))
                      {
//                           fragmentTubeLine = (TubeLineFragment)
//                                     getSupportFragmentManager().findFragmentByTag(TUBE_LINE_FRAGMENT);
//                              fragmentOverground = (OvergroundLineFragment)
//                                    getSupportFragmentManager().findFragmentByTag(TUBE_OVERGROUND_FRAGMENT);
                            viewPager.setAdapter(new CategoryAdapter(MainActivity.this, getSupportFragmentManager()));

             //     showSnackbar.showSnackbar();
//                            fragmentOverground.showSnackbar();
//                              fragmentTubeLine.showSnackbar();
//                              isSnackbarShowing = false;

                        }
                        else
                        {
                                Snackbar
                                        .make(mCoordinatorLayout, R.string.snackbar_internet, Snackbar.LENGTH_INDEFINITE)
                                        .setAction(R.string.snackbar_retry, new MainActivity.MyClickListener())
                                        .setBehavior(new DisableSwipeBehavior())
                                        .show();
                                isSnackbarShowing = true;
                                showErrorMessage();
                        }
                }
        }


        //Display if there is no internet connection
        //Display if there is no internet connection
        public void showErrorMessage()
        {
                Snackbar
                        .make(mCoordinatorLayout, R.string.snackbar_internet, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.snackbar_retry, new MainActivity.MyClickListener())
                        .setBehavior(new DisableSwipeBehavior())
                        .show();
                // mLineRecyclerView.setVisibility(View.VISIBLE);
               // mLoadingIndicator.setVisibility(View.VISIBLE);
        }

//        public void setOnDataListener(TubeLineFragment fragmentTubeLine)
//        {
//                this.fragmentTubeLine = fragmentTubeLine;
//        }

     //   public void updateShowSnackbar(ShowSnackbar listener)
     //   {
           //     showSnackbar = listener;
     //   }

//        public void setOnDataOvergroundListener(OvergroundLineFragment fragmentOverground)
//        {
//                this.fragmentOverground= fragmentOverground;
//        }

        public static boolean isNetworkStatusAvailable(Context context)
        {
                ConnectivityManager cm =
                        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                return activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
        }

        @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
           //Code based on the following stackoverflow post:
           // https://stackoverflow.com/questions/63982922/what-is-this-r-id-home
            int id = item.getItemId();

                    if (id == R.id.map_bar_imageview)
                    {
                        Intent intent = new Intent(this, StationMapActivity.class);
                        startActivity(intent);
                        return true;
                    }
                                return super.onOptionsItemSelected(item);
                }



        @Override
   protected void onSaveInstanceState(Bundle outState)
   {
       outState.putBoolean(SNACKBAR_STATE, isSnackbarShowing);
       super.onSaveInstanceState(outState);
   }
        }


//import static capstone.my.annin.londontubeschedule.data.TubeLineContentProvider.LOG_TAG;


//public class MainActivity extends AppCompatActivity implements LineAdapter.LineAdapterOnClickHandler, TubeLineAsyncTaskInterface
// //LoaderManager.LoaderCallbacks<Cursor>
//{
//    // Tag for logging
//    private static final String TAG = MainActivity.class.getSimpleName();
//
//    @BindView(R.id.recyclerview_main)
//    RecyclerView mLineRecyclerView;
//    private LineAdapter lineAdapter;
//    private ArrayList<Line> lineArrayList = new ArrayList<>();
//    private Context context;
//    private static final String KEY_LINES_LIST = "line_list";
//    private static final String KEY_SORT_ORDER = "sort_order";
//    private String selectedSortOrder = "line_list";
//    private static final String SORT_BY_FAVORITES = "line_favorites";
//    private static final String SORT_BY_LINES = "line_sort";
//    private static final String KEY_LOADING_INDICATOR = "loading_indicator";
//    CoordinatorLayout mCoordinatorLayout;
//
//    @BindView(R.id.pb_loading_indicator)
//    ProgressBar mLoadingIndicator;
//
//    //private FavoritesAdapter favoritesAdapter;
//    private FavoritesRoomAdapter favoritesRoomAdapter;
//    private static final int FAVORITES_LOADER_ID = 0;
//    private int mPosition = RecyclerView.NO_POSITION;
//    private FirebaseAnalytics mFirebaseAnalytics;
//
//    //ViewModel variable
//    private LineViewModel mLineViewModel;
//    private static final String SNACKBAR_STATE = "snackbar_state";
//    private boolean isSnackbarShowing = false;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        context = getApplicationContext();
//
//        // Bind the views
//        ButterKnife.bind(this);
//
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//
//        mCoordinatorLayout = findViewById(R.id.coordinatorLayout);
//
//        //favoritesAdapter = new FavoritesAdapter(this, context);
//        favoritesRoomAdapter = new FavoritesRoomAdapter(this, context);
//        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);
//
//        lineAdapter = new LineAdapter(this, lineArrayList, context);
//
//        mLineRecyclerView.setAdapter(lineAdapter);
//
//        RecyclerView.LayoutManager mLineLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        mLineRecyclerView.setLayoutManager(mLineLayoutManager);
//
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            //Disable swipe for items not in Favorites; code sample based on the first answer in this stackoverflow post:
//            //https://stackoverflow.com/questions/30713121/disable-swipe-for-position-in-recyclerview-using-itemtouchhelper-simplecallback
//            @Override
//            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
//            {
//                if (!(viewHolder instanceof FavoritesRoomAdapter.FavoritesRoomAdapterViewHolder))
//                {
//                    return 0;
//                }
//                return super.getSwipeDirs(recyclerView, viewHolder);
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir)
//            {
//                int position = viewHolder.getAdapterPosition();
//                mLineViewModel.delete(favoritesRoomAdapter.getMovieAt(position));
//            }
//
//        }).attachToRecyclerView(mLineRecyclerView);
////            @Override
////            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
////            {
////                if (viewHolder instanceof LinesAdapter.LinesAdapterViewHolder) return 0;
////                return super.getSwipeDirs(recyclerView, viewHolder);
////            }
//
//
////
////            // Called when a user swipes left or right on a ViewHolder
////            @Override
////            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir)
////            {
////                // Here is where you'll implement swipe to delete
////
////                //Construct the URI for the item to delete
////                //[Hint] Use getTag (from the adapter code) to get the id of the swiped item
////                // Retrieve the id of the task to delete
////                String id = (String) viewHolder.itemView.getTag();
////
////                // Build appropriate uri with String row id appended
////                String stringId = id;
////                Uri uri = TubeLineContract.TubeLineEntry.CONTENT_URI;
////                uri = uri.buildUpon().appendPath(stringId).build();
////
////                int rowsDeleted = getContentResolver().delete(uri, null, null);
////               // Log.v("CatalogActivity", rowsDeleted + " rows deleted from the line database");
////                Timber.v( "%s rows deleted from the line database", rowsDeleted);
////
////                getSupportLoaderManager().restartLoader(FAVORITES_LOADER_ID, null, MainActivity.this);
////            }
//
//        /*
//         *  Starting the asyncTask so that lines load upon launching the app.
//         */
//        if (savedInstanceState == null)
//        {
//            if (isNetworkStatusAvailable(this))
//            {
//
//                TubeLineAsyncTask myLineTask = new TubeLineAsyncTask(this);
//                myLineTask.execute(NetworkUtils.buildLineStatusUrl());
//            } else
//                {
//                Snackbar
//                        .make(mCoordinatorLayout, R.string.snackbar_internet, Snackbar.LENGTH_INDEFINITE)
//                        .setAction(R.string.snackbar_retry, new MyClickListener())
//                        .setBehavior(new DisableSwipeBehavior())
//                        .show();
//                isSnackbarShowing = true;
//                showErrorMessage();
//            }
//        } else
//            {
//            selectedSortOrder = savedInstanceState.getString(KEY_SORT_ORDER, "line_list");
//            isSnackbarShowing = savedInstanceState.getBoolean(SNACKBAR_STATE);
//            if (isSnackbarShowing)
//            {
//                Snackbar
//                        .make(mCoordinatorLayout, R.string.snackbar_internet, Snackbar.LENGTH_INDEFINITE)
//                        .setAction(R.string.snackbar_retry, new MyClickListener())
//                        .setBehavior(new DisableSwipeBehavior())
//                        .show();
//            }
//            if (selectedSortOrder == SORT_BY_FAVORITES)
//            {
//                mLineRecyclerView.setAdapter(favoritesRoomAdapter);
//                mLineViewModel.loadAllLines().observe(this, new Observer<List<Line>>()
//                {
//                    @Override
//                    public void onChanged(@Nullable List<Line> line)
//                    {
//                        favoritesRoomAdapter.setLine(line);
//                    }
//                });
//
////                    getSupportLoaderManager().initLoader(FAVORITES_LOADER_ID, null, MainActivity.this);
////                    mLineRecyclerView.setAdapter(favoritesAdapter);
//
//            } else
//                {
//                lineArrayList = savedInstanceState.getParcelableArrayList(KEY_LINES_LIST);
//                lineAdapter.setLineList(lineArrayList);
//
//            }
//            if (savedInstanceState != null)
//            {
//                mLoadingIndicator.setVisibility(View.INVISIBLE);
//            } else {
//                mLoadingIndicator.setVisibility(View.VISIBLE);
//            }
//            //  Log.v(LOG_TAG, "SORTORDER= " + selectedSortOrder);
//            Timber.v(selectedSortOrder, "SORTORDER= ");
//            // Log.i("list", linesArrayList.size() + "");
//            Timber.i("list: " + lineArrayList.size());
//        }
//    }
//
//    public class MyClickListener implements View.OnClickListener
//    {
//        @Override
//        public void onClick(View v)
//        {
//            //CP Provider Code Commented out
//            // Run the AsyncTask in response to the click
//           // if (selectedSortOrder == SORT_BY_FAVORITES)
//  //          {
//
//
////              getSupportLoaderManager().initLoader(FAVORITES_LOADER_ID, null, MainActivity.this);
////               mLineRecyclerView.setAdapter(favoritesAdapter);
//     //   } else
//         //   {
//
//            if (isNetworkStatusAvailable(context))
//            {
//                TubeLineAsyncTask myLineTask = new TubeLineAsyncTask(MainActivity.this);
//                myLineTask.execute();
//            }
//            else
//            {
//                Snackbar
//                        .make(mCoordinatorLayout, R.string.snackbar_internet, Snackbar.LENGTH_INDEFINITE)
//                        .setAction(R.string.snackbar_retry, new MyClickListener())
//                        .setBehavior(new DisableSwipeBehavior())
//                        .show();
//                isSnackbarShowing = true;
//                showErrorMessage();
//            }
//        }
//    }
//
//    @Override
//    public void returnLineData(ArrayList<Line> simpleJsonLineData)
//    {
//        mLoadingIndicator.setVisibility(View.INVISIBLE);
//        if (null != simpleJsonLineData)
//        {
//            lineAdapter = new LineAdapter(this, simpleJsonLineData, MainActivity.this);
//            lineArrayList = simpleJsonLineData;
//            mLineRecyclerView.setAdapter(lineAdapter);
//            lineAdapter.setLineList(lineArrayList);
//        } else
//            {
//            showErrorMessage();
//            Timber.e("Problem parsing lines JSON results" );
//        }
//    }
//
//    @Override
//    public void onClick(Line line)
//    {
//        Intent intent = new Intent(MainActivity.this, StationListActivity.class);
//        intent.putExtra("Line", line);
//        intent.putParcelableArrayListExtra("lineList", lineArrayList);
//        startActivity(intent);
//
//        //log event when the user selects a line
//        Bundle params = new Bundle();
//        params.putParcelable("line_select", line);
//        mFirebaseAnalytics.logEvent("line_select",params);
//
//    }
//
//    //Display if there is no internet connection
//    public void showErrorMessage()
//    {
//        Snackbar
//               .make(mCoordinatorLayout, R.string.snackbar_internet, Snackbar.LENGTH_INDEFINITE)
//                .setAction(R.string.snackbar_retry, new MyClickListener())
//                .setBehavior(new DisableSwipeBehavior())
//              .show();
//       // mLineRecyclerView.setVisibility(View.VISIBLE);
//     mLoadingIndicator.setVisibility(View.VISIBLE);
//    }
//
//    public static boolean isNetworkStatusAvailable(Context context)
//    {
//        ConnectivityManager cm =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        return activeNetwork != null &&
//                activeNetwork.isConnectedOrConnecting();
//    }
//
////    @Override
////    public Loader<Cursor> onCreateLoader(int id, final Bundle loaderArgs)
////    {
////        return new AsyncTaskLoader<Cursor>(this)
////        {
////
////            // Initialize a Cursor, this will hold all the task data
////            Cursor mFavoritesData = null;
////
////            // onStartLoading() is called when a loader first starts loading data
////            @Override
////            protected void onStartLoading()
////            {
////                if (mFavoritesData != null)
////                {
////                    // Delivers any previously loaded data immediately
////                    deliverResult(mFavoritesData);
////                }
////                else
////                {
////                    // Force a new load
////                    forceLoad();
////                }
////            }
////
////            // loadInBackground() performs asynchronous loading of data
////            @Override
////            public Cursor loadInBackground()
////            {
////                // Will implement to load data
////
////                // Query and load all task data in the background; sort by priority
////                // [Hint] use a try/catch block to catch any errors in loading data
////                try
////                {
////                    return getContentResolver().query(TubeLineContract.TubeLineEntry.CONTENT_URI,
////                            null,
////                            null,
////                            null,
////                             TubeLineContract.TubeLineEntry.COLUMN_LINES_ID);
////                }
////                catch (Exception e)
////                {
////                    Log.e(LOG_TAG, "Failed to asynchronously load data.");
////                    e.printStackTrace();
////                    return null;
////                }
////            }
////
////            // deliverResult sends the result of the load, a Cursor, to the registered listener
////            public void deliverResult(Cursor data)
////            {
////                mFavoritesData = data;
////                super.deliverResult(data);
////            }
////        };
////    }
////
////    /**
////     * Called when a previously created loader has finished its load.
////     *
////     * @param loader The Loader that has finished.
////     * @param data   The data generated by the Loader.
////     */
////    @Override
////    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
////    {
////        favoritesAdapter.swapCursor(data);
////        if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
////        mLineRecyclerView.smoothScrollToPosition(mPosition);
////    }
////
////    /**
////     * Called when a previously created loader is being reset, and thus
////     * making its data unavailable.
////     * onLoaderReset removes any references this activity had to the loader's data.
////     *
////     * @param loader The Loader that is being reset.
////     */
////    @Override
////    public void onLoaderReset(Loader<Cursor> loader)
////    {
////        favoritesAdapter.swapCursor(null);
////    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
//        MenuInflater inflater = getMenuInflater();
//        /* Use the inflater's inflate method to inflate our menu layout to this menu */
//        inflater.inflate(R.menu.main, menu);
//        /* Return true so that the menu is displayed in the Toolbar */
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        TubeLineAsyncTask myLineTask = new TubeLineAsyncTask(this);
//
//        switch (item.getItemId())
//        {
//            case R.id.most_frequented_lines:
//
//                favoritesRoomAdapter = new FavoritesRoomAdapter(this, MainActivity.this);
//                mLineRecyclerView.setAdapter(favoritesRoomAdapter);
//                mLineViewModel.loadAllLines().observe(this, new Observer<List<Line>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Line> line)
//                    {
//                        favoritesRoomAdapter.setLine(line);
//                    }
//                });
//                selectedSortOrder = SORT_BY_FAVORITES;
////                getSupportLoaderManager().restartLoader(FAVORITES_LOADER_ID, null, MainActivity.this);
////                favoritesAdapter = new FavoritesAdapter(this, MainActivity.this);
////                mLineRecyclerView.setAdapter(favoritesAdapter);
////                selectedSortOrder = SORT_BY_FAVORITES;
//                return true;
//
//            case R.id.line_list:
//                myLineTask.execute();
//                selectedSortOrder = SORT_BY_LINES;
//                return true;
//
//            case R.id.settings:
//                Intent intent = new Intent(this, SettingsActivity.class);
//                startActivity(intent);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//
//    }}
//
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState)
//    {
//        outState.putString(KEY_SORT_ORDER, selectedSortOrder);
//        outState.putParcelableArrayList(KEY_LINES_LIST, lineArrayList);
//        outState.putBoolean(SNACKBAR_STATE, isSnackbarShowing);
//        super.onSaveInstanceState(outState);
//    }
//}