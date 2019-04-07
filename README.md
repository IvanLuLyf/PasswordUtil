# PasswordUtil

[![](https://jitpack.io/v/IvanLuLyf/PasswordUtil.svg)](https://jitpack.io/#IvanLuLyf/PasswordUtil)
[![API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![Build Status](https://travis-ci.org/IvanLuLyf/PasswordUtil.svg?branch=master)](https://travis-ci.org/IvanLuLyf/PasswordUtil)
![GitHub](https://img.shields.io/github/license/IvanLuLyf/PasswordUtil.svg?color=blue)

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

```xml
<cn.twimi.widget.PasswordView
        android:id="@+id/passwordView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:max_length="6" />
```

```java

```
