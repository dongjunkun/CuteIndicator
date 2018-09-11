[![](https://jitpack.io/v/dongjunkun/CuteIndicator.svg)](https://jitpack.io/#dongjunkun/CuteIndicator)

# Intro
a cute viewpager indicator

## Preview

![cute_indicator](/art/indicator.gif)

## Import to project

1)In you root build.grade:
~~~
allprojects {
    ...
    repositories {
        maven { url "https://jitpack.io"
        }
    }
    ...
}
~~~
2)In your app build.gradle add:
~~~~
dependencies {
	        implementation 'com.github.dongjunkun:CuteIndicator:1.0.1'
	}
~~


## Custom property explain
| property| format|explain|
|:---:|:---:|:---:|
|IndicatorColor|color|indicator color|
|IndicatorShadowColor|color|indicator shadow color|
|IndicatorSelectedWidthDimension|dimension|Indicator selected width|
|IndicatorDiaDimension|dimension|Indicator dia|
|IndicatorSpaceDimension|dimension|Indicator space|
|IndicatorShadowRadiusDimension|dimension|Indicator shadow radius|
|IndicatorIsAnimation|boolean|need animation default true|
|IndicatorIsShadow|boolean|need shadow default true|

## How to use
1) In you xml:
~~~
 <com.djk.library.CuteIndicator
        android:id="@+id/indicator"
        android:layout_gravity="center_horizontal|bottom"
        android:layout_width="wrap_content"
        android:layout_margin="10dp"
        android:layout_height="wrap_content"
        app:IndicatorColor="@android:color/white"
        app:IndicatorDiaDimension="10dp"
        app:IndicatorShadowColor="#44000000"
        app:IndicatorIsAnimation="true"
        app:IndicatorIsShadow="true"
        app:IndicatorShadowRadiusDimension="2dp"
        app:IndicatorSelectedWidthDimension="20dp"
        app:IndicatorSpaceDimension="5dp"/>
~~~
2)In you code:
~~~
 indicator.setupWithViewPager(pager)
~~~
if you wan't see more,please see demo application.


## Licence
 Copyright 2016 dongjunkun

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.