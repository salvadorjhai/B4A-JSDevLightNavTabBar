package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
com.salvadorjhai.widgets.JSDevLightNavTabBar _navigationtabbar = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd1 = null;
com.salvadorjhai.widgets.JSDevLightNavTabBarModel _model1 = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd2 = null;
com.salvadorjhai.widgets.JSDevLightNavTabBarModel _model2 = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd3 = null;
com.salvadorjhai.widgets.JSDevLightNavTabBarModel _model3 = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd4 = null;
com.salvadorjhai.widgets.JSDevLightNavTabBarModel _model4 = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd5 = null;
com.salvadorjhai.widgets.JSDevLightNavTabBarModel _model5 = null;
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="Activity.Color = 0xFF413550";
mostCurrent._activity.setColor((int) (0xff413550));
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="Dim navigationTabBar As JSDevLightNavTabBar";
_navigationtabbar = new com.salvadorjhai.widgets.JSDevLightNavTabBar();
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="navigationTabBar.Initialize(\"navigationTabBar\")";
_navigationtabbar.Initialize(mostCurrent.activityBA,"navigationTabBar");
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="Activity.AddView(navigationTabBar, 0dip, 0dip, 10";
mostCurrent._activity.AddView((android.view.View)(_navigationtabbar.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (56)));
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="Dim bd1 As BitmapDrawable";
_bd1 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="bd1.Initialize(LoadBitmap(File.DirAssets, \"Novel_";
_bd1.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Novel_48px.png").getObject()));
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="Dim model1 As JSDevLightNavTabBarModel";
_model1 = new com.salvadorjhai.widgets.JSDevLightNavTabBarModel();
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="model1.Initialize(bd1, 0xFFDE5853)";
_model1.Initialize(processBA,(android.graphics.drawable.Drawable)(_bd1.getObject()),(int) (0xffde5853));
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="model1.Title = \"Heart\"";
_model1.setTitle("Heart");
RDebugUtils.currentLine=131088;
 //BA.debugLineNum = 131088;BA.debugLine="model1.BadgeTitle = \"1\"";
_model1.setBadgeTitle("1");
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="navigationTabBar.AddModel(model1)	' Add model to";
_navigationtabbar.AddModel(_model1);
RDebugUtils.currentLine=131091;
 //BA.debugLineNum = 131091;BA.debugLine="Dim bd2 As BitmapDrawable";
_bd2 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="bd2.Initialize(LoadBitmap(File.DirAssets, \"Trophy";
_bd2.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Trophy_48px.png").getObject()));
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="Dim model2 As JSDevLightNavTabBarModel";
_model2 = new com.salvadorjhai.widgets.JSDevLightNavTabBarModel();
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="model2.Initialize(bd2, 0xFFF7B970)";
_model2.Initialize(processBA,(android.graphics.drawable.Drawable)(_bd2.getObject()),(int) (0xfff7b970));
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="model2.Title = \"Cup\"";
_model2.setTitle("Cup");
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="model2.BadgeTitle = \"2\"";
_model2.setBadgeTitle("2");
RDebugUtils.currentLine=131097;
 //BA.debugLineNum = 131097;BA.debugLine="navigationTabBar.AddModel(model2) ' Add model to";
_navigationtabbar.AddModel(_model2);
RDebugUtils.currentLine=131099;
 //BA.debugLineNum = 131099;BA.debugLine="Dim bd3 As BitmapDrawable";
_bd3 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="bd3.Initialize(LoadBitmap(File.DirAssets, \"Diplom";
_bd3.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Diploma_48px.png").getObject()));
RDebugUtils.currentLine=131101;
 //BA.debugLineNum = 131101;BA.debugLine="Dim model3 As JSDevLightNavTabBarModel";
_model3 = new com.salvadorjhai.widgets.JSDevLightNavTabBarModel();
RDebugUtils.currentLine=131102;
 //BA.debugLineNum = 131102;BA.debugLine="model3.Initialize(bd3, 0xFF76ACCF)";
_model3.Initialize(processBA,(android.graphics.drawable.Drawable)(_bd3.getObject()),(int) (0xff76accf));
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="model3.Title = \"Diploma\"";
_model3.setTitle("Diploma");
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="model3.BadgeTitle = \"3\"";
_model3.setBadgeTitle("3");
RDebugUtils.currentLine=131105;
 //BA.debugLineNum = 131105;BA.debugLine="navigationTabBar.AddModel(model3)";
_navigationtabbar.AddModel(_model3);
RDebugUtils.currentLine=131107;
 //BA.debugLineNum = 131107;BA.debugLine="Dim bd4 As BitmapDrawable";
_bd4 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=131108;
 //BA.debugLineNum = 131108;BA.debugLine="bd4.Initialize(LoadBitmap(File.DirAssets, \"Flag F";
_bd4.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Flag Filled _48px.png").getObject()));
RDebugUtils.currentLine=131109;
 //BA.debugLineNum = 131109;BA.debugLine="Dim model4 As JSDevLightNavTabBarModel";
_model4 = new com.salvadorjhai.widgets.JSDevLightNavTabBarModel();
RDebugUtils.currentLine=131110;
 //BA.debugLineNum = 131110;BA.debugLine="model4.Initialize(bd4, 0xFFDD6396)";
_model4.Initialize(processBA,(android.graphics.drawable.Drawable)(_bd4.getObject()),(int) (0xffdd6396));
RDebugUtils.currentLine=131111;
 //BA.debugLineNum = 131111;BA.debugLine="model4.Title = \"Flag\"";
_model4.setTitle("Flag");
RDebugUtils.currentLine=131112;
 //BA.debugLineNum = 131112;BA.debugLine="model4.BadgeTitle = \"4\"";
_model4.setBadgeTitle("4");
RDebugUtils.currentLine=131113;
 //BA.debugLineNum = 131113;BA.debugLine="navigationTabBar.AddModel(model4)";
_navigationtabbar.AddModel(_model4);
RDebugUtils.currentLine=131115;
 //BA.debugLineNum = 131115;BA.debugLine="Dim bd5 As BitmapDrawable";
_bd5 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
RDebugUtils.currentLine=131116;
 //BA.debugLineNum = 131116;BA.debugLine="bd5.Initialize(LoadBitmap(File.DirAssets, \"Medal";
_bd5.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Medal First Place_48px.png").getObject()));
RDebugUtils.currentLine=131117;
 //BA.debugLineNum = 131117;BA.debugLine="Dim model5 As JSDevLightNavTabBarModel";
_model5 = new com.salvadorjhai.widgets.JSDevLightNavTabBarModel();
RDebugUtils.currentLine=131118;
 //BA.debugLineNum = 131118;BA.debugLine="model5.Initialize(bd5, 0xFF71D1B4)";
_model5.Initialize(processBA,(android.graphics.drawable.Drawable)(_bd5.getObject()),(int) (0xff71d1b4));
RDebugUtils.currentLine=131119;
 //BA.debugLineNum = 131119;BA.debugLine="model5.Title = \"Medal\"";
_model5.setTitle("Medal");
RDebugUtils.currentLine=131120;
 //BA.debugLineNum = 131120;BA.debugLine="model5.BadgeTitle = \"777\"";
_model5.setBadgeTitle("777");
RDebugUtils.currentLine=131121;
 //BA.debugLineNum = 131121;BA.debugLine="navigationTabBar.AddModel(model5)";
_navigationtabbar.AddModel(_model5);
RDebugUtils.currentLine=131123;
 //BA.debugLineNum = 131123;BA.debugLine="navigationTabBar.SetModels";
_navigationtabbar.SetModels();
RDebugUtils.currentLine=131125;
 //BA.debugLineNum = 131125;BA.debugLine="navigationTabBar.TitleMode = navigationTabBar.TIT";
_navigationtabbar.setTitleMode(_navigationtabbar.TITLE_MODE_ACTIVE);
RDebugUtils.currentLine=131126;
 //BA.debugLineNum = 131126;BA.debugLine="navigationTabBar.BadgeGravity = navigationTabBar.";
_navigationtabbar.setBadgeGravity(_navigationtabbar.BADGE_GRAVITY_BOTTOM);
RDebugUtils.currentLine=131127;
 //BA.debugLineNum = 131127;BA.debugLine="navigationTabBar.BadgePosition = navigationTabBar";
_navigationtabbar.setBadgePosition(_navigationtabbar.BADGE_POSITION_CENTER);
RDebugUtils.currentLine=131128;
 //BA.debugLineNum = 131128;BA.debugLine="navigationTabBar.Typeface = Typeface.DEFAULT";
_navigationtabbar.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT);
RDebugUtils.currentLine=131129;
 //BA.debugLineNum = 131129;BA.debugLine="navigationTabBar.IsBadged = True";
_navigationtabbar.setIsBadged(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131132;
 //BA.debugLineNum = 131132;BA.debugLine="navigationTabBar.IsBadgeUseTypeface = True";
_navigationtabbar.setIsBadgeUseTypeface(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131133;
 //BA.debugLineNum = 131133;BA.debugLine="navigationTabBar.BadgeBgColor = Colors.RED";
_navigationtabbar.setBadgeBgColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=131134;
 //BA.debugLineNum = 131134;BA.debugLine="navigationTabBar.BadgeTitleColor = Colors.WHITE";
_navigationtabbar.setBadgeTitleColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=131135;
 //BA.debugLineNum = 131135;BA.debugLine="navigationTabBar.IsSwiped = True";
_navigationtabbar.setIsSwiped(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131136;
 //BA.debugLineNum = 131136;BA.debugLine="navigationTabBar.BgColor = 0xFF5F5071";
_navigationtabbar.setBgColor((int) (0xff5f5071));
RDebugUtils.currentLine=131137;
 //BA.debugLineNum = 131137;BA.debugLine="navigationTabBar.BadgeSize = 20";
_navigationtabbar.setBadgeSize((float) (20));
RDebugUtils.currentLine=131138;
 //BA.debugLineNum = 131138;BA.debugLine="navigationTabBar.TitleSize = 10";
_navigationtabbar.setTitleSize((float) (10));
RDebugUtils.currentLine=131139;
 //BA.debugLineNum = 131139;BA.debugLine="navigationTabBar.IconSizeFraction = 0.5";
_navigationtabbar.setIconSizeFraction((float) (0.5));
RDebugUtils.currentLine=131142;
 //BA.debugLineNum = 131142;BA.debugLine="navigationTabBar.BehaviorEnabled = True";
_navigationtabbar.setBehaviorEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131143;
 //BA.debugLineNum = 131143;BA.debugLine="navigationTabBar.ModelIndex = 0";
_navigationtabbar.setModelIndex((int) (0));
RDebugUtils.currentLine=131151;
 //BA.debugLineNum = 131151;BA.debugLine="model5.ShowBadge";
_model5.ShowBadge();
RDebugUtils.currentLine=131152;
 //BA.debugLineNum = 131152;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="End Sub";
return "";
}
public static String  _navigationtabbar_onendtabselected(com.salvadorjhai.widgets.JSDevLightNavTabBarModel _model,int _index) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "navigationtabbar_onendtabselected", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "navigationtabbar_onendtabselected", new Object[] {_model,_index}));}
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub navigationTabBar_OnEndTabSelected(model As JSD";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="LogColor(model.Title, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("1327681",_model.getTitle(),anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="model.HideBadge";
_model.HideBadge();
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="End Sub";
return "";
}
}