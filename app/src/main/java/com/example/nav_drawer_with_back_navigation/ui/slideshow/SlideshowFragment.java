package com.example.nav_drawer_with_back_navigation.ui.slideshow;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.nav_drawer_with_back_navigation.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    ProgressBar pb;
    public WebView webViews;

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




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                pb = (ProgressBar) getView().findViewById(R.id.pbs);

                webViews = getView().findViewById(R.id.webViewS);
                CookieManager.getInstance().setAcceptCookie(true);
                webViews.setWebViewClient(new SlideshowFragment.MyWebViewClient());
                webViews.getSettings().setJavaScriptEnabled(true);
                WebSettings webSettings = webViews.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webViews.getSettings().setDomStorageEnabled(true);
                webViews.loadUrl("https://www.worldometers.info/coronavirus/");
                webViews.requestFocus();
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(webViews, true);
                } else {
                    CookieManager.getInstance().setAcceptCookie(true);
                }
            }
        });
        return root;
    }
    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }

}
