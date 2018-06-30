package com.brownjee.journals;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.brownjee.journals.sqlitehelper.LogTableHelper;

public class MenuScreen extends AppCompatActivity implements View.OnClickListener {

    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        displayDashBoard();
    }

    private void displayDashBoard() {
        LogTableHelper log=new LogTableHelper(getApplicationContext(),"log.db",null,1);
        log.getReadableDatabase();
        Cursor c=log.fetchReport();

        String[] columns=new String[]
                {
                        log.LOG_ID,
                        log.LOG_TITLE,
                        log.LOG_DATA,
                };

        int[] to = new int[]
                {
                        R.id.id,
                        R.id.title,
                        R.id.content
                };

        dataAdapter=new SimpleCursorAdapter(this,R.layout.dashboard_list,c,columns,to,0);
        final ListView lv=(ListView)findViewById(R.id.dashboard);
        lv.setAdapter(dataAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {


                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(MenuScreen.this);


                alertDialogBuilder.setTitle("What Action do you want to Performed?");

                // set dialog message
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                Cursor cr=dataAdapter.getCursor();
                                cr.moveToPosition(position);
                                String sring=cr.getString(0);
                                cr.moveToFirst();
                                Intent i = new Intent(MenuScreen.this, ModifyActivity.class);
                                i.putExtra("id", sring);
                                startActivity(i);
                                MenuScreen.this.finish();
                            }
                        })
                        .setNeutralButton("Delete",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Cursor cr=dataAdapter.getCursor();
                                cr.moveToPosition(position);
                                String sring=cr.getString(0);
                                Toast.makeText(getApplicationContext(),sring,Toast.LENGTH_LONG).show();

                                LogTableHelper l=new LogTableHelper(getApplicationContext(),"log.db", null, 1);
                                l.getWritableDatabase();
                                l.Delete(sring);
                                displayDashBoard();
                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton("Read",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Cursor cr=dataAdapter.getCursor();
                                cr.moveToPosition(position);
                                String sring=cr.getString(0);
                                //	    Toast.makeText(getApplicationContext(),sring+"",Toast.LENGTH_LONG).show();
                                cr.moveToFirst();
                                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                                i.putExtra("id", sring);
                                startActivity(i);
                                MenuScreen.this.finish();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        finish();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void onBackPressed()
    {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }



}
