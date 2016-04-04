package com.mytorch.sweety.fragment_json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//declare all fields
    ArrayList<Contacts> al;
    MyAdapter adp;
    ListView lv;
    Button btn;
    Myfragment myfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //THIS CODE NO USE AS OF NOW THE FOR OUR  EXAPMLE  SO I AM COMMENTING....
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

// STEP 1: INTIALIZE ALL VARAIBLES
        btn= (Button) findViewById(R.id.button);
        lv= (ListView) findViewById(R.id.listView);
        al=new ArrayList<Contacts>();
        adp=new MyAdapter();
        lv.setAdapter(adp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myservice m = new Myservice();
                m.execute();
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //GET DATA BASED ON POSITION
                Contacts obj= al.get(position);

                //FIND IF FRAGMENT IS AVAIABLE OR NOT
                myfragment=(Myfragment)getSupportFragmentManager().findFragmentById(R.id.fragment1);
                //IF AVAILABLE PASS DATA TO FRAGMENT

                if(myfragment !=null){

                    myfragment.myMethod(obj);
                }
            }
        });
    }

//sTEP 2: CREATE SEPERATE CLASS FOR SERVICE

    private class Myservice extends AsyncTask<Void,Void,String >{


        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection  con=null;
            //sTEP 3 CREATE URL CONNECCTION AND IMPEETS TRY CATCH ....AS..
            try{
                URL url  = new URL("http://api.androidhive.info/contacts/");
                con=(HttpURLConnection)url.openConnection();

                // lIKE THS ALSO WE CAN READ BUT ONELINE CODE IS SO..

                /*InputStream is=con.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(isr);*/

                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String s=br.readLine();
                while(s!= null){
                    sb.append(s);
                  s=br.readLine();
                }
                return sb.toString();

            }catch(MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
           return null;
        }
//  STEP 4; CREATEA AN SEPERATE CLASS OF CONTACTS SO THAT  WE CAN HOLD THE RESULT OF JSON AND

// IMPLEMENT THE POST EXECUTE MOTHOD TO PARSE AND SENDING THE DATA TO OUT CUSTOM CLASS HOLDING RESULT OF JSON DATA

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject obj= new JSONObject(s);
                JSONArray contacts=obj.getJSONArray("contacts");
                for(int i=1;i<contacts.length();i++){
                    JSONObject contact=contacts.getJSONObject(i);
                    String name1=contact.getString("name");
                    String email2=contact.getString("email");
                    JSONObject ob =contact.getJSONObject("phone");
                    String mobile=ob.getString("mobile");

                    Contacts obj1=new Contacts();
                    obj1.setName(name1);
                    obj1.setEmail(email2);
                    obj1.setPhone(mobile);

                    al.add(obj1);
                }
                adp.notifyDataSetChanged();

            }catch(JSONException e){
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }

//STEP 5: CREATE SEPERATE ADAPTER BCZ WE HAVE MULIPLE DAT TO SHOW IN LIST VIEW SOWE ARE COUSTOMIZING IT FOR THAT
    //STEP 5.1 CREATE AN ROW.XML FOR SHOWING THE DATA IIN THE LISTVIEW
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {

            return al.size();
        }

        @Override
        public Object getItem(int position) {
            return al.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View v= getLayoutInflater().inflate(R.layout.row,null);
            TextView Ltxt= (TextView) v.findViewById(R.id.textView);
            TextView mtxt= (TextView) v.findViewById(R.id.textView2);
            TextView stxt= (TextView) v.findViewById(R.id.textView3);
//STEP 6 AFTER THE INTILIZING CREATE AN VARAIBLE  FOR CONTACT CLASS AND ASSIGN IT WITH THE ARRAYLIST OF POSITION
            // GET ALL THE DATA FROMTHE CONACTS BCZ WE HAVE ALL READY IMPLEDMEMTED GETTERS AND SETTERS  CHECK IT OK
            Contacts contac=al.get(position);
            String name=contac.getName();
            String email=contac.getEmail();
            String mobile=contac.getPhone();

            Ltxt.setText(name);
            mtxt.setText(email);
            stxt.setText(mobile);

            return v;

        }

    }




}
