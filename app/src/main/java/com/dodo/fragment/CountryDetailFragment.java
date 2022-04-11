package com.dodo.fragment;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.caverock.androidsvg.SVG;
import com.dodo.adesso.R;
import com.dodo.model.Country;
import com.squareup.picasso.Picasso;

import java.io.InputStream;


public class CountryDetailFragment extends DialogFragment {

    private Context mContext;
    private final AppCompatActivity activity;
    private Country country;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = new CountryDetailFragment.DialogExtended(mContext);
        Window window = dialog.getWindow();

        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        dialog.setContentView(R.layout.country_detail_fragment);

        ConstraintLayout constraintLayout = dialog.findViewById(R.id.all_view);
        Button backButton = dialog.findViewById(R.id.back_button);
        Button savedButton = dialog.findViewById(R.id.fragment_saved_button);
        ImageView image = dialog.findViewById(R.id.imageView);
        TextView codeText = dialog.findViewById(R.id.country_code_text);
        Button moreInformationButton = dialog.findViewById(R.id.more_information_button);
        WebView webview = dialog.findViewById(R.id.webview);

        String code =mContext.getResources().getString(R.string.country_code) + " " +country.getCode();
        codeText.setText(code);

        image.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Picasso.get().load(country.getFlagImageUri()).into(image);

        backButton.setOnClickListener(view -> dialog.dismiss());

        String url = "https://www.wikidata.org/wiki/" + country.getWikiDataId();
        moreInformationButton.setOnClickListener(view -> {
            constraintLayout.setVisibility(View.GONE);
            webview.setVisibility(View.VISIBLE);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(url);
            dialog.dismiss();
        });

        return dialog;
    }

    /*
     * Function: KitchenDisplayAdapter()
     */
    public CountryDetailFragment(AppCompatActivity activity, Country country) {
        this.activity = activity;
        this.country = country;
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Function: show()
     */
    public void show(FragmentManager fragmentManager) {
        try {
            show(fragmentManager, "CountryDetailFragment");
        } catch (Exception exc) {
            System.out.println("Exception exc : " +exc);
        }
    }

    /**
     * Function: DialogExtended
     */
    private static class DialogExtended extends Dialog {

        DialogExtended(@NonNull Context context) {

            super(context);
        }
    }
}