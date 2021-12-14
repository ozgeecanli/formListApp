package com.example.sampleapplicationproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sampleapplicationproject.ui.BaseFragment;
import com.example.sampleapplicationproject.ui.form.FragmentFormScreen;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentContract extends BaseFragment implements OnPageScrollListener {

    @BindView(R.id.contractPdfView)
    PDFView contractPdfView;
    @BindView(R.id.buttonConfirm)
    Button buttonConfirm;

    @OnClick(R.id.buttonConfirm)
    public void confirmButtonClick() {
       /* Bundle bundle = new Bundle();
        bundle.putInt("confirmContract", 1);

        fragment.setArguments(bundle);*/
        BaseFragment fragment = new FragmentFormScreen();

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
    }

    @Override
    public void onPageScrolled(int page, float positionOffset) {
        if (contractPdfView != null) {
            if (contractPdfView.getPageCount() - 1 == page && positionOffset == 1.0f) {
                if (buttonConfirm != null) {
                    buttonConfirm.setEnabled(true);
                    buttonConfirm.setAlpha(1f);
                }
            } else if (contractPdfView.getPageCount() - 1 == 0) {
                if (buttonConfirm != null) {
                    buttonConfirm.setEnabled(true);
                    buttonConfirm.setAlpha(1f);
                }
            }
        }
    }
}