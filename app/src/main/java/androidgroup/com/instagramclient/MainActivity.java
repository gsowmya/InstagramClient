package androidgroup.com.instagramclient;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private static final String ClientId ="2d1e3ab5aacb4962aed7729e998bc2e3";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }

    private void fetchPopularPhotos(){

        String url = "https://api.instagram.com/v1/media/popular?client_id="+ ClientId;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,null,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                JSONArray photosJSON = null;

                try {
                    photosJSON = response.getJSONArray("data");
                    for(int i=0;i<photosJSON.length();i++){

                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        String profilePicture = photoJSON.getJSONObject("user").getString("profile_picture");
                        photo.setProfilePicture(profilePicture);
                        String userName = photoJSON.getJSONObject("user").getString("username");
                        photo.setUserName(userName);
                        String createdTime = photoJSON.getJSONObject("caption").getString("created_time");
                        photo.setCreatedTime(createdTime);
                        String imageUrl = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.setCreatedImage(imageUrl);
                        String caption = photoJSON.getJSONObject("caption").getString("text");
                        photo.setCaption(caption);
                        String likesCount = photoJSON.getJSONObject("likes").getString("count");
                        photo.setLikes(likesCount + " likes");
                        String commentsCount = photoJSON.getJSONObject("comments").getString("count");
                        photo.setComments("view all "+commentsCount + " comments");
                        photos.add(photo);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

                photosAdapter.notifyDataSetChanged();
                Log.i(LOG_TAG,response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            photos = new ArrayList<InstagramPhoto>();
            photosAdapter = new InstagramPhotosAdapter(getActivity(),photos);
            ListView photosList = (ListView) rootView.findViewById(R.id.lvPhotos);
            photosList.setAdapter(photosAdapter);
            fetchPopularPhotos();
            return rootView;
        }
    }
}
