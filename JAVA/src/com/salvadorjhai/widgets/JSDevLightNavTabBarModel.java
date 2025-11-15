package com.salvadorjhai.widgets;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;
import com.gigamole.navigationtabbar.ntb.NavigationTabBar.Model;
import com.gigamole.navigationtabbar.ntb.NavigationTabBar.Model.Builder;

import android.graphics.drawable.Drawable;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.ShortName;

@ShortName("JSDevLightNavTabBarModel")
@DependsOn(values = { "JSDevLightNavTabBar.aar", 
		"android-support-v4", "android-support-design" })
public class JSDevLightNavTabBarModel extends AbsObjectWrapper<NavigationTabBar.Model> {

	public void Initialize(final BA ba, Drawable icon, int color) {		
		this.setObject(new Model(new Builder(icon, color)));
	}
	
	public ModelBuilder Initialize2(final BA ba, ModelBuilder builder) {		
		this.setObject(new Model(builder.getObjectOrNull()));
		return builder;
	}
	
	public String getTitle() {	
		return this.getObjectOrNull().getTitle();		
	}

	public void setTitle(String title) {
		this.getObjectOrNull().setTitle(title);
	}


	public int getColor() {		
		return this.getObjectOrNull().getColor();
	}


	public void setColor(int color) {		
		this.getObjectOrNull().setColor(color);
	}

	public boolean getIsBadgeShowed() {		
		return this.getObjectOrNull().isBadgeShowed();
	}

	public String getBadgeTitle() {	
		return this.getObjectOrNull().getBadgeTitle();
	}

	public void setBadgeTitle(String badgeTitle) {		
		this.getObjectOrNull().setBadgeTitle(badgeTitle);
	}

	public void UpdateBadgeTitle(String badgeTitle) {	
		this.getObjectOrNull().updateBadgeTitle(badgeTitle);
	}

	public void ToggleBadge() {	
		this.getObjectOrNull().toggleBadge();
	}

	public void ShowBadge() {	
		this.getObjectOrNull().showBadge();
	}

	public void HideBadge() {	
		this.getObjectOrNull().hideBadge();
	}
	
	@ShortName("JSDevLightNavTabBarModelBuilder")
	public class ModelBuilder extends AbsObjectWrapper<NavigationTabBar.Model.Builder > {
		
		public ModelBuilder() {}
		
		public ModelBuilder Initialize(Drawable icon, int color) {
			this.setObject(new Builder(icon, color));			
			return this;
		}

		public ModelBuilder badgeTitle(String title) {
			this.getObjectOrNull().badgeTitle(title);
			return this;
		}

		public NavigationTabBar.Model build() {
			return this.getObjectOrNull().build();
		}

		public ModelBuilder selectedIcon(Drawable icon) {
			this.getObjectOrNull().selectedIcon(icon);
			return this;
		}

		public ModelBuilder title(String title) {
			this.getObjectOrNull().title(title);
			return this;
		}
	}
		
	
}
