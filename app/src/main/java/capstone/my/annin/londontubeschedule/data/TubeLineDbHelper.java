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

package capstone.my.annin.londontubeschedule.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import static capstone.my.annin.londontubeschedule.data.TubeLineContract.TubeLineEntry.TABLE_NAME;

//public class TubeLineDbHelper extends SQLiteOpenHelper
//{
//    // The name of the database
//    private static final String DATABASE_NAME = "tubeTasksDb.db";
//
//    // If you change the database schema, you must increment the database version
//    private static final int VERSION = 4;
//
//    /**
//     * Constructs a new instance of {@link TubeLineDbHelper}.
//     *
//     * @param context of the app
//     */
//    TubeLineDbHelper(Context context)
//    {
//        super(context, DATABASE_NAME, null, VERSION);
//    }
//
//    /**
//     * Called when the lines database is created for the first time.
//     */
//    @Override
//    public void onCreate(SQLiteDatabase db)
//    {
//        // Create lines table (careful to follow SQL formatting rules)
//        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
//                TubeLineContract.TubeLineEntry._ID + " INTEGER PRIMARY KEY , " +
//                TubeLineContract.TubeLineEntry.COLUMN_LINES_ID + " TEXT NOT NULL , " +
//                TubeLineContract.TubeLineEntry.COLUMN_LINES_NAME + " TEXT NOT NULL , " +
//                TubeLineContract.TubeLineEntry.COLUMN_LINES_STATUS_DESC + " TEXT NOT NULL , " +
//                TubeLineContract.TubeLineEntry.COLUMN_LINES_STATUS_REASON + " TEXT NOT NULL , " +
//
//                " UNIQUE (" + TubeLineContract.TubeLineEntry.COLUMN_LINES_NAME + ") ON CONFLICT REPLACE);";
//
//        db.execSQL(CREATE_TABLE);
//    }
//
//    /**
//     * This method discards the old table of data and calls onCreate to recreate a new one.
//     * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
//     */
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
//    {
//        //   db.execSQL("ALTER TABLE  " + TubeLineContract.TubeLineEntry.TABLE_NAME);
//        //  onCreate(db);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//}