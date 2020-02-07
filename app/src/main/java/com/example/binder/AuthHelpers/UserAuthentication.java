package com.example.binder.AuthHelpers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.binder.MemoryHelpers.SharedPreferencesUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAuthentication {

    private FirebaseAuth mAuth = null;
    private FirebaseUser mUser = null;
    private static String TAG  ="auth";
    private static Object lock = new Object();

    private UserAuthentication(){
        mAuth = FirebaseAuth.getInstance();

    }

    private static UserAuthentication instance = null;

    public static  UserAuthentication getInstance(){
        if(instance==null){
            synchronized (lock){
                if(instance == null){
                    instance =  new UserAuthentication();
                }
            }
        }
        return instance;
    }

    private boolean IsUserSignedIn(){
        boolean isUserSignedIn = false;
       mUser = mAuth.getCurrentUser();
        if(mUser!=null){
            isUserSignedIn = true;
        }
        return isUserSignedIn;
    }

    private void SignInUser(String email, String password, final UserAuthenticationListener listener){

        if(! IsUserSignedIn()){
            if(mAuth!=null){
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(listener!=null){
                                listener.onSuccess(mAuth.getCurrentUser());
                            }
                        }else{
                            if(listener!=null){
                                listener.onFailure("failed to signed in");
                            }
                        }
                    }
                });

            }
        }
    }

    private void SignUpUser(final Activity activity, String email, String password, final UserAuthenticationListener listener){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(listener!=null){
                                listener.onSuccess(mAuth.getCurrentUser());
                            }
                        }else{
                            if(listener!=null){

                                listener.onFailure("failed in signUp");
                            }
                        }
                    }
                });
    }

    public interface UserAuthenticationListener{
        void onSuccess(FirebaseUser user);
        void onFailure(String message);
    }



}
