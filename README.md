# PasswordUtil

[![](https://jitpack.io/v/IvanLuLyf/PasswordUtil.svg)](https://jitpack.io/#IvanLuLyf/PasswordUtil)
[![API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![Build Status](https://travis-ci.org/IvanLuLyf/PasswordUtil.svg?branch=master)](https://travis-ci.org/IvanLuLyf/PasswordUtil)
[![GitHub](https://img.shields.io/github/license/IvanLuLyf/PasswordUtil.svg?color=blue)](https://github.com/IvanLuLyf/PasswordUtil/blob/master/LICENSE)

This repo is made for Password Input like Wechat or Alipay.

# Configure

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency

```gradle
dependencies {
    implementation 'com.github.IvanLuLyf:PasswordUtil:1.0'
}
```

# Usage

## Using PasswordView
```xml
<cn.twimi.widget.PasswordView
        android:id="@+id/passwordView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:border_color="#AA0000"
        app:border_width="4"
        app:circle_width="8"
        app:fore_color="#880000"
        app:max_length="6" />
```

```java
PasswordView passwordView = findViewById(R.id.passwordView);
passwordView.setOnCompleted(new PasswordView.OnCompleted() {
    @Override
    public void onCompleted(String password) {
        // Your logic to process password
    }
});
```

## Using PasswordDialogUtil
```java
(new PasswordDialogUtil.Builder(MainActivity.this)).setOnCompleted(new PasswordDialogUtil.OnPasswordCompleted() {
    @Override
    public void onCompleted(String password) {
        // Your logic to process password
    }
})
.setMaxLength(8)    //Set password's max length
.setTitle("Input Password") //Set dialog title
.build()    //Build the dialog
.show();    //show the dialog
```

# Effect

<img src="image/view.png" width="320px" alt="PasswordView"><img src="image/dialog.png" width="320px" alt="PasswordDialog">
