<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.trith.demoapp.presentation.login.MainActivity">

    <android.support.constraint.Guideline
        android:id="@+id/guideline2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintGuide_percent="0.35" />

    <LinearLayout
        android:gravity="center"
        android:orientation="vertical"
        android:background="@drawable/app_background"
        app:layout_constraintBottom_toTopOf="@id/guideline2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_width="0dp"
        android:layout_height="0dp">

        <TextView
            android:id="@+id/textView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:gravity="center"
            android:textAppearance="@style/TextAppearance.AppCompat.Display1"
            android:textColor="@android:color/white"
            android:textStyle="bold"
            tools:text="Welcome to MyDP!" />

        <TextView
            android:id="@+id/textView2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="24dp"
            android:gravity="center"
            android:textAppearance="@style/TextAppearance.AppCompat.Medium"
            android:textColor="@android:color/white"
            android:textStyle="bold"
            tools:text="Login" />
    </LinearLayout>

    <android.support.design.widget.TextInputLayout
        android:id="@+id/layoutEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toBottomOf="@id/guideline2">

        <android.support.design.widget.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="@string/login_hint_email"
            android:inputType="textEmailAddress" />
    </android.support.design.widget.TextInputLayout>

    <TextView
        android:id="@+id/textView3"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:layout_marginStart="16dp"
        android:text="@string/login_label_this_is_your_email"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/layoutEmail" />

    <android.support.design.widget.TextInputLayout
        android:id="@+id/layoutPw"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toBottomOf="@id/textView3">

        <android.support.design.widget.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="@string/login_hint_password"
            android:inputType="textPassword" />
    </android.support.design.widget.TextInputLayout>

    <TextView
        android:id="@+id/btnShow"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/login_button_show"
        android:textColor="@color/colorPrimaryDark"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="@id/layoutPw"
        app:layout_constraintEnd_toEndOf="@id/layoutPw"
        app:layout_constraintTop_toTopOf="@id/layoutPw" />

    <Button
        android:id="@+id/btnLogin"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="24dp"
        android:background="@drawable/app_background"
        android:text="@string/login_button_login"
        android:textColor="@android:color/white"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/layoutPw" />

    <TextView
        android:id="@+id/textView4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="24dp"
        android:text="@string/login_label_forgot_pw"
        android:textColor="@color/colorPrimaryDark"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/btnLogin" />

    <ImageView
        android:id="@+id/imgDevider"
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:layout_marginTop="16dp"
        android:background="@android:color/darker_gray"
        app:layout_constraintTop_toBottomOf="@id/textView4" />

    <TextView
        android:id="@+id/textView5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="@string/login_label_not_register_yet"
        app:layout_constraintEnd_toStartOf="@+id/textView6"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintHorizontal_chainStyle="packed"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imgDevider" />

    <TextView
        android:id="@+id/textView6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="@string/login_label_sign_up"
        android:textColor="@color/colorPrimaryDark"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/textView5"
        app:layout_constraintTop_toBottomOf="@+id/imgDevider" />

    <ProgressBar
        android:id="@+id/progressBar"
        android:visibility="gone"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</android.support.constraint.ConstraintLayout>





  <!--Login start-->
    <string name="login_hint_email">EMAIL</string>
    <string name="login_hint_password">ENTER YOUR PASSWORD</string>
    <string name="login_button_show">SHOW</string>
    <string name="login_button_login">LOG IN</string>
    <string name="login_label_forgot_pw">FORGOT PASSWORD?</string>
    <string name="login_label_not_register_yet">Not registered yet? Sign-up here to get started. </string>
    <string name="login_label_sign_up">Sign-up</string>
    <string name="login_label_this_is_your_email">This is your email</string>
    <!--Login end-->
    
    
    <?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <gradient
        android:startColor="#982df5"
        android:endColor="#022466"
        android:angle="45"/>
</shape>
