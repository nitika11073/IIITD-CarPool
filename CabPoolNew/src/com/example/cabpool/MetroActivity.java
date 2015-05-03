package com.example.cabpool;

import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cabpoolnew.R;
import com.parse.Parse;
import com.parse.ParseObject;

public class MetroActivity extends ActionBarActivity implements OnItemSelectedListener {
	ParseObject Driver,Passenger;
	String user,driveTimeInMin,driveTimeInSec,vehicle,seats;
	String name="neha";
	Button submit;
	Context ctx=this;
	int hour,min;
	
	private TimePicker timePicker1;
	   private TextView time;
	   private Calendar calendar;
	   private String format = "";
	   
	   private Spinner spinner,spinner1,spinner2;
		 
		  private static final String[]pathsIfBike = {"1"};
		  private static final String[]pathsIfCar = {"1", "2", "3" , "4" };	  
		  private static final String[]transport = {"Car", "Bike"};
		  private static final String[] User={"Driver","Passenger"};
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metro);
		
		Parse.initialize(this, "FGN5I5LFZGNlxvb6dBZm2h7DSDkElHjXXYULR0ZT", "2Z060lnTv3eUwZ0TVeTDamLamsKwzr8v2Y11zikU");
		Driver = new ParseObject("Driver");		
		Passenger = new ParseObject("Passenger");	
		
		submit=(Button) findViewById(R.id.button1);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		 timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
		 timePicker1.setIs24HourView(true);
	
	      time = (TextView) findViewById(R.id.textView1);
	      calendar = Calendar.getInstance();
	 	 hour = calendar.get(Calendar.HOUR_OF_DAY);
	     min = calendar.get(Calendar.MINUTE);
	      
	     spinner1 = (Spinner)findViewById(R.id.spinner1);
	     ArrayAdapter<String>adapterNew1 = new ArrayAdapter<String>(MetroActivity.this,
	             android.R.layout.simple_spinner_item,User);    

		
		spinner = (Spinner)findViewById(R.id.spinner);
	     ArrayAdapter<String>adapter = new ArrayAdapter<String>(MetroActivity.this,
	             android.R.layout.simple_spinner_item,transport);
	     
	     spinner2 = (Spinner)findViewById(R.id.spinner2);
	     ArrayAdapter<String>adapterNew = new ArrayAdapter<String>(MetroActivity.this,
	             android.R.layout.simple_spinner_item,pathsIfCar);    

	     adapterNew1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     adapterNew.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    
	     spinner1.setAdapter(adapterNew1);
	     spinner1.setOnItemSelectedListener(this);
		
	     spinner.setAdapter(adapter);
	     spinner.setOnItemSelectedListener(this);
		

		  spinner2.setAdapter(adapterNew);
		  spinner2.setOnItemSelectedListener(this);

		
		 submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				user=spinner1.getSelectedItem().toString();	
				Log.d("user", user);				
			    driveTimeInMin=time.getText().toString();
			    Log.d("time", driveTimeInMin);
				vehicle=spinner.getSelectedItem().toString();
				   Log.d("vehicle", vehicle);
				seats=spinner2.getSelectedItem().toString();
				   Log.d("seats", seats);
			     hour=timePicker1.getCurrentHour();
			     min=timePicker1.getCurrentMinute();
				 if (user.equals("Driver"))
			     {
			    	 	Driver.put("Destination", "Gobind Puri");
						Driver.put("hour", Integer.toString(hour));
						Driver.put("minute", Integer.toString(min));
						Driver.put("vehicle", vehicle);
						Driver.put("seats", seats);
						//Log.d("Yayyyy Data Sent!!!",user);
						Toast.makeText(getApplicationContext(), "Your request is submitted.Contact you Soon!!", Toast.LENGTH_LONG).show();
							
						Driver.saveInBackground();

			     }
				 if (user.equals("Passenger"))
			     {
			    	 	Passenger.put("Destination", "Gobind Puri");
			    	 	Passenger.put("hour", Integer.toString(hour));
			    	 	Passenger.put("minute", Integer.toString(min));
			    	 	Passenger.put("vehicle", vehicle);
			    	 	Passenger.put("seats", seats);
						//Log.d("Yayyyy Data Sent!!!",driveDestination);
			    	 	Toast.makeText(getApplicationContext(), "Data is recorded", Toast.LENGTH_LONG).show();							
						Passenger.saveInBackground();


			     }

			}
		});
	     
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.metro, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_metro,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		if(parent.getItemAtPosition(position).toString().equals("Bike"))
		{
			ArrayAdapter<String>adapterIfBike = new ArrayAdapter<String>(MetroActivity.this,
		             android.R.layout.simple_spinner_item,pathsIfBike);
			spinner2.setAdapter(adapterIfBike);
			 spinner2.setOnItemSelectedListener(this);
		}
		else if(parent.getItemAtPosition(position).toString().equals("Car"))
		{
			ArrayAdapter<String>adapterNew = new ArrayAdapter<String>(MetroActivity.this,
		             android.R.layout.simple_spinner_item,pathsIfCar);  
			spinner2.setAdapter(adapterNew);
			 spinner2.setOnItemSelectedListener(this);
		}
		 switch (position) {
         case 0:
             // Whatever you want to happen when the first item gets selected
             break;
         case 1:
             // Whatever you want to happen when the second item gets selected
             break;
         case 2:
             // Whatever you want to happen when the thrid item gets selected
             break;

     }
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
