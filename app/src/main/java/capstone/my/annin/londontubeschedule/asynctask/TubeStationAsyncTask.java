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

package capstone.my.annin.londontubeschedule.asynctask;

import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;

import capstone.my.annin.londontubeschedule.pojo.Station;
import capstone.my.annin.londontubeschedule.utils.JSONUtils;
import capstone.my.annin.londontubeschedule.utils.NetworkUtils;

public class TubeStationAsyncTask extends AsyncTask<String, Void, ArrayList<Station>>
{
    private static final String TAG = TubeStationAsyncTask.class.getSimpleName();

    private TubeStationAsyncTaskInterface listener;

    public TubeStationAsyncTask(TubeStationAsyncTaskInterface listener)
    {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Station> doInBackground(String... params)
    {
        if (params.length == 0)
        {
            return null;
        }
        String lineId = params[0];

        URL stationRequestUrl = NetworkUtils.buildStationUrl(lineId);

        try
        {
            String jsonStationResponse = NetworkUtils
                    .makeHttpStationRequest(stationRequestUrl);

            return JSONUtils.extractFeatureFromStationJson(jsonStationResponse);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Station> mStationList)
    {
        super.onPostExecute(mStationList);
        /*the if method is commented out because the error message will be displayed in the Main Activity if there is no internet connection
        the if statement is included in the returnData method in the Main Activity
        */
        //   if (mStationList != null) {}
        listener.returnStationData(mStationList);
    }
}