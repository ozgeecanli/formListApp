package com.example.sampleapplicationproject.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.ui.BaseFragment;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentContract extends BaseFragment implements OnPageScrollListener {

    public static final String CONTRACT_IS_CHECKED_BUNDLE_KEY = "CONTRACT_IS_CHECKED_BUNDLE_KEY";

    @BindView(R.id.contractPdfView)
    PDFView contractPdfView;
    @BindView(R.id.buttonConfirm)
    Button buttonConfirm;
    @BindView(R.id.buttonCancel)
    Button buttonCancel;

    @OnClick(R.id.buttonCancel)
    public void cancelButtonClick() {
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentFormScreen.SCREEN_BUNDLE_KEY, Page.CONTRACTS.getPageID());
        bundle.putBoolean(CONTRACT_IS_CHECKED_BUNDLE_KEY, false);

        Fragment fragment = new FragmentFormScreen();
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();
    }

    @OnClick(R.id.buttonConfirm)
    public void confirmButtonClick() {
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentFormScreen.SCREEN_BUNDLE_KEY, Page.CONTRACTS.getPageID());
        bundle.putBoolean(CONTRACT_IS_CHECKED_BUNDLE_KEY, true);

        Fragment fragment = new FragmentFormScreen();
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contract, container, false);

        ButterKnife.bind(this, view);
        contractPdfView.fromAsset("Contract.pdf").onPageScroll(this).load();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        buttonConfirm.setVisibility(View.VISIBLE);
        buttonConfirm.setEnabled(false);
        buttonConfirm.setAlpha(.5f);

        buttonCancel.setVisibility(View.VISIBLE);
        buttonCancel.setEnabled(false);
        buttonCancel.setAlpha(.5f);
    }

    @Override
    public void onPageScrolled(int page, float positionOffset) {
        if (contractPdfView != null) {
            if (contractPdfView.getPageCount() - 1 == page && positionOffset == 1.0f) {
                if (buttonConfirm != null) {
                    buttonConfirm.setEnabled(true);
                    buttonConfirm.setAlpha(1f);
                }

                if (buttonCancel != null) {
                    buttonCancel.setEnabled(true);
                    buttonCancel.setAlpha(1f);
                }
            } else if (contractPdfView.getPageCount() - 1 == 0) {
                if (buttonConfirm != null) {
                    buttonConfirm.setEnabled(true);
                    buttonConfirm.setAlpha(1f);
                }

                if (buttonCancel != null) {
                    buttonCancel.setEnabled(true);
                    buttonCancel.setAlpha(1f);
                }
            }
        }
    }
}