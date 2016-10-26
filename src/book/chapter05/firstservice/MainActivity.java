package book.chapter05.firstservice;

import book.chapter05.firstservice.MyService.MyBinder;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final String TAG = null;
	Button start,bind;
	Button stop,unbind,getData;
	MyBinder myBinder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    start=(Button)this.findViewById(R.id.btnStart);
		stop=(Button)this.findViewById(R.id.btnStop);
		getData=(Button)this.findViewById(R.id.btnGet);
		final Intent intent =new Intent();
		intent.setAction("book.chapter05.firstservice.MyService");
		start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(intent);
			}
			
		} );
		stop.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(intent);
			}
			
		} );
		
		getData.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "count="+myBinder.getCount(), 100).show();
			}
			
		});
		
		bind=(Button)this.findViewById(R.id.btnBind);
		unbind=(Button)this.findViewById(R.id.btnUnBind);
		bind.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(intent,conn,Service.BIND_AUTO_CREATE);
			}
			
		} );
		
		unbind.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(conn);
			}
			
		});
	}

	//创建ServiceConnection对象，在绑定服务时需要传递一个ServiceConnection对象。
	private ServiceConnection conn=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i(TAG,"MainActivity onServiceConnected inviked!");
			myBinder=(MyBinder)service;//将传递的参数强制类型转换成MyBinder对象
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.i(TAG,"MainActivity onServiceDisConnected inviked!");
			
		}
		
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
