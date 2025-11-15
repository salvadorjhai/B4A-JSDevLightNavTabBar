/*
 * Material:
 * https://github.com/DevLight-Mobile-Agency/NavigationTabBar
 * 
 * r3	- Added IsHidden property
 * r2	- implemented Designer custom view support
 * r1	- Initial version
 * 		 - JSDevLightNavTabBar
 * 		 - JSDevLightNavTabBar Events
 * 		 - JSDevLightNavTabBarModel
 * 
 */

package com.salvadorjhai.widgets;

import java.util.ArrayList;
import java.util.List;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;
import com.gigamole.navigationtabbar.ntb.NavigationTabBar.BadgeGravity;
import com.gigamole.navigationtabbar.ntb.NavigationTabBar.BadgePosition;
import com.gigamole.navigationtabbar.ntb.NavigationTabBar.Model;
import com.gigamole.navigationtabbar.ntb.NavigationTabBar.OnTabBarSelectedIndexListener;
import com.gigamole.navigationtabbar.ntb.NavigationTabBar.TitleMode;

import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ActivityObject;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.Events;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.keywords.Common.DesignerCustomView;
import anywheresoftware.b4a.objects.CustomViewWrapper;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;

/**
 * Wrapped Of: https://github.com/DevLight-Mobile-Agency/NavigationTabBar
 * Credits to: DevLight-Mobile-Agency
 * 
 * Usage:<code>
 * 	' Initialize TabBar and add it to activity
 * 	Dim navigationTabBar As JSDevLightNavTabBar
 * 	navigationTabBar.Initialize("")
 * 	Activity.AddView(navigationTabBar, 0dip, 0dip, 100%x, 56dip)
 * 	
 * 	' Create Models (tabs), use BitmapDrawable for the icons
 * 	Dim bd1 As BitmapDrawable
 * 	bd1.Initialize(LoadBitmap(File.DirAssets, "Novel_48px.png"))
 * 	Dim model1 As JSDevLightNavTabBarModel	
 * 	model1.Initialize(bd1, 0xFFDE5853)
 * 	model1.Title = "Heart"
 * 	model1.BadgeTitle = "NTB"	
 * 	navigationTabBar.AddModel(model1)	' Add model to TabBar
 * 	
 * 	Dim bd2 As BitmapDrawable
 * 	bd2.Initialize(LoadBitmap(File.DirAssets, "Trophy_48px.png"))
 * 	Dim model2 As JSDevLightNavTabBarModel	
 * 	model2.Initialize(bd2, 0xFFF7B970)
 * 	model2.Title = "Cup"
 * 	model2.BadgeTitle = "with"	
 * 	navigationTabBar.AddModel(model2) ' Add model to TabBar
 * 	
 * 	Dim bd3 As BitmapDrawable
 * 	bd3.Initialize(LoadBitmap(File.DirAssets, "Diploma_48px.png"))
 * 	Dim model3 As JSDevLightNavTabBarModel	
 * 	model3.Initialize(bd3, 0xFF76ACCF)
 * 	model3.Title = "Diploma"
 * 	model3.BadgeTitle = "state"	
 * 	navigationTabBar.AddModel(model3)
 * 	
 * 	Dim bd4 As BitmapDrawable
 * 	bd4.Initialize(LoadBitmap(File.DirAssets, "Flag Filled _48px.png"))
 * 	Dim model4 As JSDevLightNavTabBarModel	
 * 	model4.Initialize(bd4, 0xFFDD6396)
 * 	model4.Title = "Flag"
 * 	model4.BadgeTitle = "icon"	
 * 	navigationTabBar.AddModel(model4)
 * 	
 * 	Dim bd5 As BitmapDrawable
 * 	bd5.Initialize(LoadBitmap(File.DirAssets, "Medal First Place_48px.png"))
 * 	Dim model5 As JSDevLightNavTabBarModel	
 * 	model5.Initialize(bd5, 0xFF71D1B4)
 * 	model5.Title = "Medal"
 * 	model5.BadgeTitle = "777"	
 * 	navigationTabBar.AddModel(model5)	
 * 	
 * 	' Finalize Models by calling SetModels function
 * 	navigationTabBar.SetModels
 * 	
 *  ' Optional Configuration
 * 	navigationTabBar.TitleMode = navigationTabBar.TITLE_MODE_ACTIVE
 * 	navigationTabBar.BadgeGravity = navigationTabBar.BADGE_GRAVITY_BOTTOM
 * 	navigationTabBar.BadgePosition = navigationTabBar.BADGE_POSITION_CENTER
 * 	navigationTabBar.Typeface = Typeface.DEFAULT
 * 	navigationTabBar.IsBadged = True
 * 	navigationTabBar.IsTitled = True
 * 	navigationTabBar.IsTinted = True
 * 	navigationTabBar.IsBadgeUseTypeface = True
 * 	navigationTabBar.BadgeBgColor = Colors.RED
 * 	navigationTabBar.BadgeTitleColor = Colors.WHITE
 * 	navigationTabBar.IsSwiped = True 
 * 	navigationTabBar.BgColor = 0xFF5F5071
 * 	navigationTabBar.BadgeSize = 10
 * 	navigationTabBar.TitleSize = 10
 * 	navigationTabBar.IconSizeFraction = 0.5	
 * 
 * 	' Set active model (tab)
 * 	navigationTabBar.ModelIndex = 0
 * 	
 * 	' set view pager, like AHViewPager
 * 	'navigationTabBar.ViewPager = AHVIEWPAGER</code>
 */
@ShortName("JSDevLightNavTabBar")
@Version(0.3f)
@Author("salvadorjhai")
@DependsOn(values = { "JSDevLightNavTabBar.aar", "android-support-v4", "android-support-design" })

@Events(values = { "OnStartTabSelected(model As JSDevLightNavTabBarModel, index As int)",
		"OnEndTabSelected(model As JSDevLightNavTabBarModel, index As int)" })

@ActivityObject
public class JSDevLightNavTabBar extends ViewWrapper<NavigationTabBar> implements DesignerCustomView {

	/** The Constant BADGE_GRAVITY_TOP. */
	public static final int BADGE_GRAVITY_TOP = 0;

	/** The Constant BADGE_GRAVITY_BOTTOM. */
	public static final int BADGE_GRAVITY_BOTTOM = 1;

	/** The Constant BADGE_POSITION_LEFT. */
	public static final int BADGE_POSITION_LEFT = 0;

	/** The Constant BADGE_POSITION_CENTER. */
	public static final int BADGE_POSITION_CENTER = 1;

	/** The Constant BADGE_POSITION_RIGHT. */
	public static final int BADGE_POSITION_RIGHT = 2;

	/** The Constant TITLE_MODE_ALL. */
	public static final int TITLE_MODE_ALL = 0;

	/** The Constant TITLE_MODE_ACTIVE. */
	public static final int TITLE_MODE_ACTIVE = 1;

	/** The models. */
	private ArrayList<JSDevLightNavTabBarModel> models = new ArrayList<>();

	private String mEventName = "";
	private BA mBA = null;

	private boolean mIsHidden = false;

	@Override
	public void Initialize(BA ba, String eventName) {
		_initialize(ba, null, eventName);
	}

	@Override
	public void DesignerCreateView(PanelWrapper paramPanelWrapper, LabelWrapper paramLabelWrapper, Map paramMap) {
		CustomViewWrapper.replaceBaseWithView2(paramPanelWrapper, this.getObjectOrNull());
	}

	@Override
	@BA.Hide
	public void _initialize(BA ba, Object paramObject, String eventName) {
		this.mEventName = eventName;
		this.mBA = ba;

		try {
			// set new object
			this.setObject(new NavigationTabBar(ba.context));

			if (eventName.length() != 0) {
				this.getObjectOrNull().setOnTabBarSelectedIndexListener(new OnTabBarSelectedIndexListener() {

					@Override
					public void onStartTabSelected(Model arg0, int index) {
						String event = mEventName + "_OnStartTabSelected";
						mBA.raiseEventFromUI(this, event.toLowerCase(), new Object[] { models.get(index), index });
					}

					@Override
					public void onEndTabSelected(Model arg0, int index) {
						String event = mEventName + "_OnEndTabSelected";
						mBA.raiseEventFromUI(this, event.toLowerCase(), new Object[] { models.get(index), index });
					}
				});
			}
			
			// Inner Initialize
			super.innerInitialize(ba, eventName, true);
			
		} catch (Exception e) {
			BA.LogError(e.getMessage());
		}

	}

	/**
	 * Gets the animation duration.
	 *
	 * @return the animation duration
	 */
	public int getAnimationDuration() {
		return this.getObjectOrNull().getAnimationDuration();
	}

	/**
	 * Sets the animation duration.
	 *
	 * @param animationDuration
	 *            the new animation duration
	 */
	public void setAnimationDuration(int animationDuration) {
		this.getObjectOrNull().setAnimationDuration(animationDuration);
	}

	/**
	 * Adds the model.
	 * 
	 * @param model the model
	 * 
	 * Sample:<code>
	 * Dim model1 As JSDevLightNavTabBarModel
	 * Dim bd1 As BitmapDrawable
 	 * bd1.Initialize(LoadBitmap(File.DirAssets, "ic_home.png"))
 	 * model1.Initialize(bd1, 0xFFDE5853)
 	 * model1.Title = "Home"
 	 * navigationTabBar.AddModel(model1) ' Add model to TabBar
	 * </code>
	 */
	public void AddModel(JSDevLightNavTabBarModel model) {
		if (models == null) {
			models = new ArrayList<>();
		}
		models.add(model);
	}

	/**
	 * Gets the models.
	 *
	 * @return the list
	 */
	public List<JSDevLightNavTabBarModel> GetModels() {
		return this.models;
	}

	/**
	 * Call this after adding all your models.
	 */
	public void SetModels() {
		ArrayList<NavigationTabBar.Model> n = new ArrayList<>();
		for (int i = 0; i < models.size(); i++) {
			n.add(models.get(i).getObjectOrNull());
		}
		this.getObjectOrNull().setModels(n);
	}

	/**
	 * Gets the checks if is titled.
	 *
	 * @return the checks if is titled
	 */
	public boolean getIsTitled() {
		return this.getObjectOrNull().isTitled();
	}

	/**
	 * Sets the checks if is titled.
	 *
	 * @param IsTitled
	 *            the new checks if is titled
	 */
	public void setIsTitled(boolean IsTitled) {
		this.getObjectOrNull().setIsTitled(IsTitled);
	}

	/**
	 * Gets the checks if is badged.
	 *
	 * @return the checks if is badged
	 */
	public boolean getIsBadged() {
		return this.getObjectOrNull().isBadged();
	}

	/**
	 * Sets the checks if is badged.
	 *
	 * @param IsBadged
	 *            the new checks if is badged
	 */
	public void setIsBadged(boolean IsBadged) {
		this.getObjectOrNull().setIsBadged(IsBadged);
	}

	/**
	 * Gets the checks if is scaled.
	 *
	 * @return the checks if is scaled
	 */
	public boolean getIsScaled() {
		return this.getObjectOrNull().isScaled();
	}

	/**
	 * Sets the checks if is scaled.
	 *
	 * @param IsScaled
	 *            the new checks if is scaled
	 */
	public void setIsScaled(boolean IsScaled) {
		this.getObjectOrNull().setIsScaled(IsScaled);
	}

	/**
	 * Gets the checks if is tinted.
	 *
	 * @return the checks if is tinted
	 */
	public boolean getIsTinted() {
		return this.getObjectOrNull().isTinted();
	}

	/**
	 * Sets the checks if is tinted.
	 *
	 * @param IsTinted
	 *            the new checks if is tinted
	 */
	public void setIsTinted(boolean IsTinted) {
		this.getObjectOrNull().setIsTinted(IsTinted);
	}

	/**
	 * Gets the checks if is swiped.
	 *
	 * @return the checks if is swiped
	 */
	public boolean getIsSwiped() {
		return this.getObjectOrNull().isSwiped();
	}

	/**
	 * Sets the checks if is swiped.
	 *
	 * @param IsSwiped
	 *            the new checks if is swiped
	 */
	public void setIsSwiped(boolean IsSwiped) {
		this.getObjectOrNull().setIsSwiped(IsSwiped);
	}

	/**
	 * Gets the title size.
	 *
	 * @return the title size
	 */
	public float getTitleSize() {
		return this.getObjectOrNull().getTitleSize();
	}

	/**
	 * Sets the title size.
	 *
	 * @param modelTitleSize
	 *            the new title size
	 */
	public void setTitleSize(float modelTitleSize) {
		this.getObjectOrNull().setTitleSize(modelTitleSize);
	}

	/**
	 * Gets the checks if is badge use typeface.
	 *
	 * @return the checks if is badge use typeface
	 */
	public boolean getIsBadgeUseTypeface() {
		return this.getObjectOrNull().isBadgeUseTypeface();
	}

	/**
	 * Sets the checks if is badge use typeface.
	 *
	 * @param IsBadgeUseTypeface
	 *            the new checks if is badge use typeface
	 */
	public void setIsBadgeUseTypeface(boolean IsBadgeUseTypeface) {
		this.getObjectOrNull().setIsBadgeUseTypeface(IsBadgeUseTypeface);
	}

	/**
	 * Gets the title mode.
	 *
	 * @return the title mode
	 */
	public int getTitleMode() {
		return (this.getObjectOrNull().getTitleMode() == TitleMode.ALL) ? TITLE_MODE_ALL : TITLE_MODE_ACTIVE;
	}

	/**
	 * Sets the title mode.
	 *
	 * @param titleMode
	 *            the new title mode
	 */
	public void setTitleMode(int titleMode) {
		this.getObjectOrNull().setTitleMode((titleMode == TITLE_MODE_ALL) ? TitleMode.ALL : TitleMode.ACTIVE);
	}

	/**
	 * Gets the badge position.
	 *
	 * @return the badge position
	 */
	public int getBadgePosition() {
		if (this.getObjectOrNull().getBadgePosition() == BadgePosition.LEFT) {
			return BADGE_POSITION_LEFT;
		} else if (this.getObjectOrNull().getBadgePosition() == BadgePosition.CENTER) {
			return BADGE_POSITION_CENTER;
		} else {
			return BADGE_POSITION_RIGHT;
		}
	}

	/**
	 * Sets the badge position.
	 *
	 * @param badgePosition
	 *            the new badge position
	 */
	public void setBadgePosition(int badgePosition) {
		if (badgePosition == BADGE_POSITION_LEFT) {
			this.getObjectOrNull().setBadgePosition(BadgePosition.LEFT);
		} else if (badgePosition == BADGE_POSITION_CENTER) {
			this.getObjectOrNull().setBadgePosition(BadgePosition.CENTER);
		} else {
			this.getObjectOrNull().setBadgePosition(BadgePosition.RIGHT);
		}
	}

	/**
	 * Gets the badge gravity.
	 *
	 * @return the badge gravity
	 */
	public int getBadgeGravity() {
		return (this.getObjectOrNull().getBadgeGravity() == BadgeGravity.TOP) ? BADGE_GRAVITY_TOP
				: BADGE_GRAVITY_BOTTOM;
	}

	/**
	 * Sets the badge gravity.
	 *
	 * @param badgeGravity
	 *            the new badge gravity
	 */
	public void setBadgeGravity(int badgeGravity) {
		this.getObjectOrNull()
				.setBadgeGravity((badgeGravity == BADGE_GRAVITY_TOP) ? BadgeGravity.TOP : BadgeGravity.BOTTOM);
	}

	/**
	 * Gets the badge bg color.
	 *
	 * @return the badge bg color
	 */
	public int getBadgeBgColor() {
		return this.getObjectOrNull().getBadgeBgColor();
	}

	/**
	 * Sets the badge bg color.
	 *
	 * @param badgeBgColor
	 *            the new badge bg color
	 */
	public void setBadgeBgColor(int badgeBgColor) {
		this.getObjectOrNull().setBadgeBgColor(badgeBgColor);
	}

	/**
	 * Gets the badge title color.
	 *
	 * @return the badge title color
	 */
	public int getBadgeTitleColor() {
		return this.getObjectOrNull().getBadgeTitleColor();
	}

	/**
	 * Sets the badge title color.
	 *
	 * @param badgeTitleColor
	 *            the new badge title color
	 */
	public void setBadgeTitleColor(int badgeTitleColor) {
		this.getObjectOrNull().setBadgeTitleColor(badgeTitleColor);
	}

	/**
	 * Gets the badge size.
	 *
	 * @return the badge size
	 */
	public float getBadgeSize() {
		return this.getObjectOrNull().getBadgeSize();
	}

	/**
	 * Sets the badge size.
	 *
	 * @param badgeTitleSize
	 *            the new badge size
	 */
	public void setBadgeSize(float badgeTitleSize) {
		this.getObjectOrNull().setBadgeSize(badgeTitleSize);
	}

	/**
	 * Gets the typeface.
	 *
	 * @return the typeface
	 */
	public Typeface getTypeface() {
		return this.getObjectOrNull().getTypeface();
	}

	/**
	 * Sets the typeface.
	 *
	 * @param typeface
	 *            the new typeface
	 */
	public void setTypeface(Typeface typeface) {
		this.getObjectOrNull().setTypeface(typeface);
	}

	/**
	 * Gets the active color.
	 *
	 * @return the active color
	 */
	public int getActiveColor() {
		return this.getObjectOrNull().getActiveColor();
	}

	/**
	 * Sets the active color.
	 *
	 * @param activeColor
	 *            the new active color
	 */
	public void setActiveColor(int activeColor) {
		this.getObjectOrNull().setActiveColor(activeColor);
	}

	/**
	 * Gets the inactive color.
	 *
	 * @return the inactive color
	 */
	public int getInactiveColor() {
		return this.getObjectOrNull().getInactiveColor();
	}

	/**
	 * Sets the inactive color.
	 *
	 * @param inactiveColor
	 *            the new inactive color
	 */
	public void setInactiveColor(int inactiveColor) {
		this.getObjectOrNull().setInactiveColor(inactiveColor);
	}

	/**
	 * Gets the bg color.
	 *
	 * @return the bg color
	 */
	public int getBgColor() {
		return this.getObjectOrNull().getBgColor();
	}

	/**
	 * Sets the bg color.
	 *
	 * @param bgColor
	 *            the new bg color
	 */
	public void setBgColor(int bgColor) {
		this.getObjectOrNull().setBgColor(bgColor);
	}

	/**
	 * Gets the corners radius.
	 *
	 * @return the corners radius
	 */
	public float getCornersRadius() {
		return this.getObjectOrNull().getCornersRadius();
	}

	/**
	 * Sets the corners radius.
	 *
	 * @param cornersRadius
	 *            the new corners radius
	 */
	public void setCornersRadius(float cornersRadius) {
		this.getObjectOrNull().setCornersRadius(cornersRadius);
	}

	/**
	 * Gets the icon size fraction.
	 *
	 * @return the icon size fraction
	 */
	public float getIconSizeFraction() {
		return this.getObjectOrNull().getIconSizeFraction();
	}

	/**
	 * Sets the icon size fraction.
	 *
	 * @param iconSizeFraction
	 *            the new icon size fraction
	 */
	public void setIconSizeFraction(float iconSizeFraction) {
		this.getObjectOrNull().setIconSizeFraction(iconSizeFraction);
	}

	/**
	 * Gets the badge margin.
	 *
	 * @return the badge margin
	 */
	public float getBadgeMargin() {
		return this.getObjectOrNull().getBadgeMargin();
	}

	/**
	 * Gets the bar height.
	 *
	 * @return the bar height
	 */
	public float getBarHeight() {
		return this.getObjectOrNull().getBarHeight();
	}

	/**
	 * Sets the view pager.
	 *
	 * @param viewPager
	 *            the new view pager
	 */
	public void setViewPager(Object viewPager) {
		try {
			this.getObjectOrNull().setViewPager((ViewPager) viewPager);
		} catch (Exception e) {
			BA.LogError(e.getMessage());
		}
	}

	/**
	 * Gets the checks if is behavior enabled.
	 *
	 * @return the checks if is behavior enabled
	 */
	public boolean getIsBehaviorEnabled() {
		return this.getObjectOrNull().isBehaviorEnabled();
	}

	/**
	 * Sets the behavior enabled.
	 *
	 * @param IsBehaviorEnabled
	 *            the new behavior enabled
	 */
	public void setBehaviorEnabled(boolean IsBehaviorEnabled) {
		this.getObjectOrNull().setBehaviorEnabled(IsBehaviorEnabled);
	}

	/**
	 * Gets the model index (active model).
	 *
	 * @return the model index
	 */
	public int getModelIndex() {
		return this.getObjectOrNull().getModelIndex();
	}

	/**
	 * Sets the model index (active model).
	 *
	 * @param modelIndex
	 *            the new model index
	 */
	public void setModelIndex(int modelIndex) {
		this.getObjectOrNull().setModelIndex(modelIndex);
	}

	/**
	 * Deselect.
	 */
	public void Deselect() {
		this.getObjectOrNull().deselect();
	}

	/**
	 * Hide (ScrollDown).
	 */
	public void Hide() {
		if (!this.mIsHidden)
			this.mIsHidden = true;
			this.getObjectOrNull().hide();
	}

	/**
	 * Show (ScrollUp).
	 */
	public void Show() {
		if (this.mIsHidden)
			this.mIsHidden = false;
			this.getObjectOrNull().show();
	}
	
	/**
	 * Check if tab is hidden
	 * @return returns true if hidden (using .Hide function)
	 */
	public boolean IsHidden() {
		return this.mIsHidden;
	}

}
