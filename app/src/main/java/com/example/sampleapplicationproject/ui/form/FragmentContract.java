package com.example.sampleapplicationproject.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.models.ContractConfirmModel;
import com.example.sampleapplicationproject.ui.BaseFragment;
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

        ContractConfirmModel confirmModel = new ContractConfirmModel();
        confirmModel.setConfirmedContract(true);

        Bundle bundle = new Bundle();
        bundle.putInt("confirmContract", 1);
        bundle.putInt("fromScreen", 2);
        bundle.putSerializable("confirmedContract", confirmModel);

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