package com.example.nav_drawer_with_back_navigation.ui.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.nav_drawer_with_back_navigation.MainActivity;
import com.example.nav_drawer_with_back_navigation.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    ProgressBar pb;
    GalleryFragment galleryFragment;
    public WebView webViewg;

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pb.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        Log.i("In onAttach","of Gallery Fragment");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("In onCreate","of Gallery Fragment");
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        // final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //        textView.setText(s);
                pb = (ProgressBar) getView().findViewById(R.id.pbg);
                webViewg = getView().findViewById(R.id.webViewG);
                CookieManager.getInstance().setAcceptCookie(true);
                webViewg.setWebViewClient(new MyWebViewClient());
                webViewg.getSettings().setJavaScriptEnabled(true);
                WebSettings webSettings = webViewg.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webViewg.getSettings().setDomStorageEnabled(true);
                webViewg.loadUrl("https://www.covid19india.org/");
                webViewg.requestFocus();
            }
        });



        return root;
    }

    @Override
    public void onDestroy() {
        Log.i("In onDestroy","of Gallery Fragment");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i("In onDetach","of Gallery Fragment");


        super.onDetach();
    }

    public class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

    }
}