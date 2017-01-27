package examples.csci567.file_manipulator;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        try{}
        catch(Exception e)
        {
            Log.e("File_manipulator", e.toString());
        }
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
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

        View rootView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button read = (Button) rootView.findViewById(R.id.button1);
            Button write = (Button) rootView.findViewById(R.id.button2);
            Button append = (Button) rootView.findViewById(R.id.button3);
            read.setOnClickListener(this);
            write.setOnClickListener(this);
            append.setOnClickListener(this);
            return rootView;
        }
        @Override
        public void onClick(View view)
        {
            switch (view.getId()) {
                case R.id.button2:
                    EditText edit = (EditText) rootView.findViewById(R.id.edit_text);
                    String text = edit.getText().toString();

                    //FileWriter filewriter;
                    //BufferedWriter bufferedWriter = null;
                    try {

                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/text.txt");
                        FileWriter filewriter = new FileWriter(file);
                        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
                        bufferedWriter.write(text);
                        bufferedWriter.close();

                    } catch (Exception e)
                    {
                       Log.e("File_manipulator",e.toString());
                    }
                    break;
                case R.id.button1:
                    String contents = "";

                    try {

                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/text.txt");
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader =new BufferedReader(fileReader);
                        while(bufferedReader.ready())
                        {
                            contents = contents+ '\n' +bufferedReader.readLine();

                        }

                        bufferedReader.close();


                    } catch (Exception e) {
                        Log.e("File_manipulator", e.toString());
                        contents="";
                    }
                    finally {
                        TextView text1 =(TextView) rootView.findViewById(R.id.text_view);
                        text1.setText(contents);

                    }
                    break;
                case R.id.button3:

                    File file = new File(Environment.getExternalStorageDirectory().getPath()+"/text.txt");
                    EditText edit1 = (EditText) rootView.findViewById(R.id.edit_text);
                    String text1 = " ";
                    text1=text1+edit1.getText().toString();

                    //text1 =text2 + text1;
                    //File file2 = new File(Environment.getExternalStorageDirectory().getPath() + "/text.txt");
                    //FileWriter fw;
                    //BufferedWriter bw = null;
                    try {
                        FileWriter fileWriter = new FileWriter(file,true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(text1);
                        bufferedWriter.close();



                    } catch (IOException e)
                    {
                        Log.e("File_manipulator",e.toString());
                    }

                    break;




            }
        }
    }
}
