package com.ensoft.imgurviewer.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ensoft.imgurviewer.view.fragment.ImageViewerFragment;
import com.imgurviewer.BuildConfig;
import com.imgurviewer.R;

public class ImageViewer extends AppActivity
{
	public static final String TAG = ImageViewer.class.getCanonicalName();
	public static final String PARAM_RESOURCE_PATH = "resourcePath";
	
	protected ImageViewerFragment imageViewer;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		
		setContentView( R.layout.activity_imageviewer );
	}
	
	@Override
	protected void onPostCreate( @Nullable Bundle savedInstanceState )
	{
		super.onPostCreate( savedInstanceState );
		
		loadResource();
	}
	
	protected void loadFragment( Uri uri )
	{
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace( R.id.image_viewer, imageViewer = ImageViewerFragment.newInstance( uri.toString() ) );
		fragmentTransaction.commitAllowingStateLoss();
	}
	
	protected void loadResource()
	{
		if ( null != getIntent().getExtras() && null != getIntent().getExtras().getString( PARAM_RESOURCE_PATH ) )
		{
			loadFragment( Uri.parse( getIntent().getExtras().getString( PARAM_RESOURCE_PATH ) ) );
		}
		else if ( getIntent().getData() != null )
		{
			Uri data = getIntent().getData();
			
			Log.v( TAG, "Data is: " + data.toString() );
			
			loadFragment( data );
		}
		else
		{
			if ( BuildConfig.DEBUG )
			{
				loadFragment( Uri.parse( "https://media.giphy.com/media/9r1gg8vm3lbTcQI1Gw/giphy.gif" ) );
			}
			else
			{
				TutorialActivity.newInstance( this );
				finish();
			}
		}
	}
}
