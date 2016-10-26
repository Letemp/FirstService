package book.chapter05.firstservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

//�Զ��������
public class MyService extends Service {

	private static final String TAG="MyService";
	private int count=0;
	private boolean quit=false;//�߳���ѭ���Ƿ�ֹͣ�ı�־
	private MyBinder myBinder=new MyBinder();//�����Զ����MyBinder����
	public class MyBinder extends Binder{
		//MyBinder�Ĺ��췽�����۲�ʲôʱ�򴴽�
		public MyBinder(){
			Log.i(TAG,"MyBinder Constructure invoked!");
		}
		//MyBinder���ṩ�Ļ�ȡ���ݵķ���
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

	//��дonBind����,���ش����Ķ���
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onBind invoked!");
		return myBinder;
	}

	//��дonCreate����
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onCreate invoked!");
		super.onCreate();
		new Thread(){
			public void run(){
				//�ж��Ƿ����ִ��ѭ��
				while(!quit){
					try{
						Thread.sleep(500);//����0.5��
						count++;//���ݵ���
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	//��дonDestroy����
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onDestroy invoked!");
		super.onDestroy();
		quit=true;//�ı�ѭ���Ƿ��˳��ı�־���������߳�һֱ��ѭ��
	}

	//��дonStartCommand����
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG,"MyService onStartCommand invoked!");
		return super.onStartCommand(intent, flags, startId);
	}

}
