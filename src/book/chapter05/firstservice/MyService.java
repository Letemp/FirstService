package book.chapter05.firstservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

//自定义服务类
public class MyService extends Service {

	private static final String TAG="MyService";
	private int count=0;
	private boolean quit=false;//线程中循环是否停止的标志
	private MyBinder myBinder=new MyBinder();//创建自定义的MyBinder对象
	public class MyBinder extends Binder{
		//MyBinder的构造方法，观察什么时候创建
		public MyBinder(){
			Log.i(TAG,"MyBinder Constructure invoked!");
		}
		//MyBinder中提供的获取数据的方法
		public int getCount(){
			return count;
			
		}
	}
	
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onRebind invoked!");
		super.onRebind(intent);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onUnbind invoked!");
		return super.onUnbind(intent);
	}

	//重写onBind方法,返回创建的对象
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onBind invoked!");
		return myBinder;
	}

	//重写onCreate方法
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onCreate invoked!");
		super.onCreate();
		new Thread(){
			public void run(){
				//判断是否继续执行循环
				while(!quit){
					try{
						Thread.sleep(500);//休眠0.5秒
						count++;//数据递增
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	//重写onDestroy方法
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onDestroy invoked!");
		super.onDestroy();
		quit=true;//改变循环是否退出的标志，否则子线程一直在循环
	}

	//重写onStartCommand方法
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onStartCommand invoked!");
		return super.onStartCommand(intent, flags, startId);
	}

}
