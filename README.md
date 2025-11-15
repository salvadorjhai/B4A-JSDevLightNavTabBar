# B4A-JSDevLightNavTabBar

NavigationTabBar - Navigation tab bar with colorful interactions.

Wrap of: https://github.com/DevLight-Mobile-Agency/NavigationTabBar

Sharing to you guys "as-is".

You can handle the tab select/change with 'OnStartTabSelected' OR 'OnEndTabSelected' event.

ViewPager like AHViewPager is supported.
Designer Custom View Supported

```vb
    ' Initialize TabBar and add it to activity
    Dim navigationTabBar As JSDevLightNavTabBar
    navigationTabBar.Initialize("")
    Activity.AddView(navigationTabBar, 0dip, 0dip, 100%x, 56dip)

    ' Create Models (tabs), use BitmapDrawable for the icons
    Dim bd1 As BitmapDrawable
    bd1.Initialize(LoadBitmap(File.DirAssets, "Novel_48px.png"))
    Dim model1 As JSDevLightNavTabBarModel
    model1.Initialize(bd1, 0xFFDE5853)
    model1.Title = "Heart"
    model1.BadgeTitle = "NTB"
    navigationTabBar.AddModel(model1)    ' Add model to TabBar

    Dim bd2 As BitmapDrawable
    bd2.Initialize(LoadBitmap(File.DirAssets, "Trophy_48px.png"))
    Dim model2 As JSDevLightNavTabBarModel
    model2.Initialize(bd2, 0xFFF7B970)
    model2.Title = "Cup"
    model2.BadgeTitle = "with"
    navigationTabBar.AddModel(model2) ' Add model to TabBar

    ' Finalize models (tabs) by calling SetModels function
    navigationTabBar.SetModels
```

other info;
https://www.b4x.com/android/forum/threads/jsdevlightnavtabbar-navigation-tab-bar-with-colorful-interactions.73407/


