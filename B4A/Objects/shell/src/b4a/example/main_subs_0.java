package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,28);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
RemoteObject _navigationtabbar = RemoteObject.declareNull("com.salvadorjhai.widgets.JSDevLightNavTabBar");
RemoteObject _bd1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
RemoteObject _model1 = RemoteObject.declareNull("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");
RemoteObject _bd2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
RemoteObject _model2 = RemoteObject.declareNull("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");
RemoteObject _bd3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
RemoteObject _model3 = RemoteObject.declareNull("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");
RemoteObject _bd4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
RemoteObject _model4 = RemoteObject.declareNull("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");
RemoteObject _bd5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.BitmapDrawable");
RemoteObject _model5 = RemoteObject.declareNull("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 28;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 31;BA.debugLine="Activity.Color = 0xFF413550";
Debug.ShouldStop(1073741824);
main.mostCurrent._activity.runVoidMethod ("setColor",BA.numberCast(int.class, 0xff413550));
 BA.debugLineNum = 34;BA.debugLine="Dim navigationTabBar As JSDevLightNavTabBar";
Debug.ShouldStop(2);
_navigationtabbar = RemoteObject.createNew ("com.salvadorjhai.widgets.JSDevLightNavTabBar");Debug.locals.put("navigationTabBar", _navigationtabbar);
 BA.debugLineNum = 35;BA.debugLine="navigationTabBar.Initialize(\"navigationTabBar\")";
Debug.ShouldStop(4);
_navigationtabbar.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("navigationTabBar")));
 BA.debugLineNum = 36;BA.debugLine="Activity.AddView(navigationTabBar, 0dip, 0dip, 10";
Debug.ShouldStop(8);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_navigationtabbar.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 56)))));
 BA.debugLineNum = 39;BA.debugLine="Dim bd1 As BitmapDrawable";
Debug.ShouldStop(64);
_bd1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd1", _bd1);
 BA.debugLineNum = 40;BA.debugLine="bd1.Initialize(LoadBitmap(File.DirAssets, \"Novel_";
Debug.ShouldStop(128);
_bd1.runVoidMethod ("Initialize",(Object)((main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Novel_48px.png"))).getObject())));
 BA.debugLineNum = 41;BA.debugLine="Dim model1 As JSDevLightNavTabBarModel";
Debug.ShouldStop(256);
_model1 = RemoteObject.createNew ("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");Debug.locals.put("model1", _model1);
 BA.debugLineNum = 42;BA.debugLine="model1.Initialize(bd1, 0xFFDE5853)";
Debug.ShouldStop(512);
_model1.runVoidMethod ("Initialize",main.processBA,(Object)((_bd1.getObject())),(Object)(BA.numberCast(int.class, 0xffde5853)));
 BA.debugLineNum = 43;BA.debugLine="model1.Title = \"Heart\"";
Debug.ShouldStop(1024);
_model1.runMethod(true,"setTitle",BA.ObjectToString("Heart"));
 BA.debugLineNum = 44;BA.debugLine="model1.BadgeTitle = \"1\"";
Debug.ShouldStop(2048);
_model1.runMethod(true,"setBadgeTitle",BA.ObjectToString("1"));
 BA.debugLineNum = 45;BA.debugLine="navigationTabBar.AddModel(model1)	' Add model to";
Debug.ShouldStop(4096);
_navigationtabbar.runVoidMethod ("AddModel",(Object)(_model1));
 BA.debugLineNum = 47;BA.debugLine="Dim bd2 As BitmapDrawable";
Debug.ShouldStop(16384);
_bd2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd2", _bd2);
 BA.debugLineNum = 48;BA.debugLine="bd2.Initialize(LoadBitmap(File.DirAssets, \"Trophy";
Debug.ShouldStop(32768);
_bd2.runVoidMethod ("Initialize",(Object)((main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Trophy_48px.png"))).getObject())));
 BA.debugLineNum = 49;BA.debugLine="Dim model2 As JSDevLightNavTabBarModel";
Debug.ShouldStop(65536);
_model2 = RemoteObject.createNew ("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");Debug.locals.put("model2", _model2);
 BA.debugLineNum = 50;BA.debugLine="model2.Initialize(bd2, 0xFFF7B970)";
Debug.ShouldStop(131072);
_model2.runVoidMethod ("Initialize",main.processBA,(Object)((_bd2.getObject())),(Object)(BA.numberCast(int.class, 0xfff7b970)));
 BA.debugLineNum = 51;BA.debugLine="model2.Title = \"Cup\"";
Debug.ShouldStop(262144);
_model2.runMethod(true,"setTitle",BA.ObjectToString("Cup"));
 BA.debugLineNum = 52;BA.debugLine="model2.BadgeTitle = \"2\"";
Debug.ShouldStop(524288);
_model2.runMethod(true,"setBadgeTitle",BA.ObjectToString("2"));
 BA.debugLineNum = 53;BA.debugLine="navigationTabBar.AddModel(model2) ' Add model to";
Debug.ShouldStop(1048576);
_navigationtabbar.runVoidMethod ("AddModel",(Object)(_model2));
 BA.debugLineNum = 55;BA.debugLine="Dim bd3 As BitmapDrawable";
Debug.ShouldStop(4194304);
_bd3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd3", _bd3);
 BA.debugLineNum = 56;BA.debugLine="bd3.Initialize(LoadBitmap(File.DirAssets, \"Diplom";
Debug.ShouldStop(8388608);
_bd3.runVoidMethod ("Initialize",(Object)((main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Diploma_48px.png"))).getObject())));
 BA.debugLineNum = 57;BA.debugLine="Dim model3 As JSDevLightNavTabBarModel";
Debug.ShouldStop(16777216);
_model3 = RemoteObject.createNew ("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");Debug.locals.put("model3", _model3);
 BA.debugLineNum = 58;BA.debugLine="model3.Initialize(bd3, 0xFF76ACCF)";
Debug.ShouldStop(33554432);
_model3.runVoidMethod ("Initialize",main.processBA,(Object)((_bd3.getObject())),(Object)(BA.numberCast(int.class, 0xff76accf)));
 BA.debugLineNum = 59;BA.debugLine="model3.Title = \"Diploma\"";
Debug.ShouldStop(67108864);
_model3.runMethod(true,"setTitle",BA.ObjectToString("Diploma"));
 BA.debugLineNum = 60;BA.debugLine="model3.BadgeTitle = \"3\"";
Debug.ShouldStop(134217728);
_model3.runMethod(true,"setBadgeTitle",BA.ObjectToString("3"));
 BA.debugLineNum = 61;BA.debugLine="navigationTabBar.AddModel(model3)";
Debug.ShouldStop(268435456);
_navigationtabbar.runVoidMethod ("AddModel",(Object)(_model3));
 BA.debugLineNum = 63;BA.debugLine="Dim bd4 As BitmapDrawable";
Debug.ShouldStop(1073741824);
_bd4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd4", _bd4);
 BA.debugLineNum = 64;BA.debugLine="bd4.Initialize(LoadBitmap(File.DirAssets, \"Flag F";
Debug.ShouldStop(-2147483648);
_bd4.runVoidMethod ("Initialize",(Object)((main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Flag Filled _48px.png"))).getObject())));
 BA.debugLineNum = 65;BA.debugLine="Dim model4 As JSDevLightNavTabBarModel";
Debug.ShouldStop(1);
_model4 = RemoteObject.createNew ("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");Debug.locals.put("model4", _model4);
 BA.debugLineNum = 66;BA.debugLine="model4.Initialize(bd4, 0xFFDD6396)";
Debug.ShouldStop(2);
_model4.runVoidMethod ("Initialize",main.processBA,(Object)((_bd4.getObject())),(Object)(BA.numberCast(int.class, 0xffdd6396)));
 BA.debugLineNum = 67;BA.debugLine="model4.Title = \"Flag\"";
Debug.ShouldStop(4);
_model4.runMethod(true,"setTitle",BA.ObjectToString("Flag"));
 BA.debugLineNum = 68;BA.debugLine="model4.BadgeTitle = \"4\"";
Debug.ShouldStop(8);
_model4.runMethod(true,"setBadgeTitle",BA.ObjectToString("4"));
 BA.debugLineNum = 69;BA.debugLine="navigationTabBar.AddModel(model4)";
Debug.ShouldStop(16);
_navigationtabbar.runVoidMethod ("AddModel",(Object)(_model4));
 BA.debugLineNum = 71;BA.debugLine="Dim bd5 As BitmapDrawable";
Debug.ShouldStop(64);
_bd5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.BitmapDrawable");Debug.locals.put("bd5", _bd5);
 BA.debugLineNum = 72;BA.debugLine="bd5.Initialize(LoadBitmap(File.DirAssets, \"Medal";
Debug.ShouldStop(128);
_bd5.runVoidMethod ("Initialize",(Object)((main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("Medal First Place_48px.png"))).getObject())));
 BA.debugLineNum = 73;BA.debugLine="Dim model5 As JSDevLightNavTabBarModel";
Debug.ShouldStop(256);
_model5 = RemoteObject.createNew ("com.salvadorjhai.widgets.JSDevLightNavTabBarModel");Debug.locals.put("model5", _model5);
 BA.debugLineNum = 74;BA.debugLine="model5.Initialize(bd5, 0xFF71D1B4)";
Debug.ShouldStop(512);
_model5.runVoidMethod ("Initialize",main.processBA,(Object)((_bd5.getObject())),(Object)(BA.numberCast(int.class, 0xff71d1b4)));
 BA.debugLineNum = 75;BA.debugLine="model5.Title = \"Medal\"";
Debug.ShouldStop(1024);
_model5.runMethod(true,"setTitle",BA.ObjectToString("Medal"));
 BA.debugLineNum = 76;BA.debugLine="model5.BadgeTitle = \"777\"";
Debug.ShouldStop(2048);
_model5.runMethod(true,"setBadgeTitle",BA.ObjectToString("777"));
 BA.debugLineNum = 77;BA.debugLine="navigationTabBar.AddModel(model5)";
Debug.ShouldStop(4096);
_navigationtabbar.runVoidMethod ("AddModel",(Object)(_model5));
 BA.debugLineNum = 79;BA.debugLine="navigationTabBar.SetModels";
Debug.ShouldStop(16384);
_navigationtabbar.runVoidMethod ("SetModels");
 BA.debugLineNum = 81;BA.debugLine="navigationTabBar.TitleMode = navigationTabBar.TIT";
Debug.ShouldStop(65536);
_navigationtabbar.runMethod(true,"setTitleMode",_navigationtabbar.getField(true,"TITLE_MODE_ACTIVE"));
 BA.debugLineNum = 82;BA.debugLine="navigationTabBar.BadgeGravity = navigationTabBar.";
Debug.ShouldStop(131072);
_navigationtabbar.runMethod(true,"setBadgeGravity",_navigationtabbar.getField(true,"BADGE_GRAVITY_BOTTOM"));
 BA.debugLineNum = 83;BA.debugLine="navigationTabBar.BadgePosition = navigationTabBar";
Debug.ShouldStop(262144);
_navigationtabbar.runMethod(true,"setBadgePosition",_navigationtabbar.getField(true,"BADGE_POSITION_CENTER"));
 BA.debugLineNum = 84;BA.debugLine="navigationTabBar.Typeface = Typeface.DEFAULT";
Debug.ShouldStop(524288);
_navigationtabbar.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").getField(false,"DEFAULT"));
 BA.debugLineNum = 85;BA.debugLine="navigationTabBar.IsBadged = True";
Debug.ShouldStop(1048576);
_navigationtabbar.runMethod(true,"setIsBadged",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 88;BA.debugLine="navigationTabBar.IsBadgeUseTypeface = True";
Debug.ShouldStop(8388608);
_navigationtabbar.runMethod(true,"setIsBadgeUseTypeface",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 89;BA.debugLine="navigationTabBar.BadgeBgColor = Colors.RED";
Debug.ShouldStop(16777216);
_navigationtabbar.runMethod(true,"setBadgeBgColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 90;BA.debugLine="navigationTabBar.BadgeTitleColor = Colors.WHITE";
Debug.ShouldStop(33554432);
_navigationtabbar.runMethod(true,"setBadgeTitleColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 91;BA.debugLine="navigationTabBar.IsSwiped = True";
Debug.ShouldStop(67108864);
_navigationtabbar.runMethod(true,"setIsSwiped",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 92;BA.debugLine="navigationTabBar.BgColor = 0xFF5F5071";
Debug.ShouldStop(134217728);
_navigationtabbar.runMethod(true,"setBgColor",BA.numberCast(int.class, 0xff5f5071));
 BA.debugLineNum = 93;BA.debugLine="navigationTabBar.BadgeSize = 20";
Debug.ShouldStop(268435456);
_navigationtabbar.runMethod(true,"setBadgeSize",BA.numberCast(float.class, 20));
 BA.debugLineNum = 94;BA.debugLine="navigationTabBar.TitleSize = 10";
Debug.ShouldStop(536870912);
_navigationtabbar.runMethod(true,"setTitleSize",BA.numberCast(float.class, 10));
 BA.debugLineNum = 95;BA.debugLine="navigationTabBar.IconSizeFraction = 0.5";
Debug.ShouldStop(1073741824);
_navigationtabbar.runMethod(true,"setIconSizeFraction",BA.numberCast(float.class, 0.5));
 BA.debugLineNum = 98;BA.debugLine="navigationTabBar.BehaviorEnabled = True";
Debug.ShouldStop(2);
_navigationtabbar.runVoidMethod ("setBehaviorEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 99;BA.debugLine="navigationTabBar.ModelIndex = 0";
Debug.ShouldStop(4);
_navigationtabbar.runMethod(true,"setModelIndex",BA.numberCast(int.class, 0));
 BA.debugLineNum = 107;BA.debugLine="model5.ShowBadge";
Debug.ShouldStop(1024);
_model5.runVoidMethod ("ShowBadge");
 BA.debugLineNum = 108;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,114);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 114;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 116;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,110);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 110;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8192);
 BA.debugLineNum = 112;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _navigationtabbar_onendtabselected(RemoteObject _model,RemoteObject _index) throws Exception{
try {
		Debug.PushSubsStack("navigationTabBar_OnEndTabSelected (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,118);
if (RapidSub.canDelegate("navigationtabbar_onendtabselected")) { return b4a.example.main.remoteMe.runUserSub(false, "main","navigationtabbar_onendtabselected", _model, _index);}
Debug.locals.put("model", _model);
Debug.locals.put("index", _index);
 BA.debugLineNum = 118;BA.debugLine="Sub navigationTabBar_OnEndTabSelected(model As JSD";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 119;BA.debugLine="LogColor(model.Title, Colors.Blue)";
Debug.ShouldStop(4194304);
main.mostCurrent.__c.runVoidMethod ("LogImpl","1327681",_model.runMethod(true,"getTitle"),main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 120;BA.debugLine="model.HideBadge";
Debug.ShouldStop(8388608);
_model.runVoidMethod ("HideBadge");
 BA.debugLineNum = 121;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}