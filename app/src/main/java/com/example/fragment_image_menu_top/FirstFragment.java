package com.example.fragment_image_menu_top;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    // 스위치와 토글 버튼의 상태를 저장할 변수
    private boolean isSwitchOn = false;
    private boolean isToggleOn = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.sample_image1);
        setHasOptionsMenu(true); // 옵션 메뉴 사용 설정

        // 텍스트뷰에 텍스트 변경 기능 추가
        TextView textView = view.findViewById(R.id.textView);
        Button buttonChangeText = view.findViewById(R.id.buttonChangeText);
        buttonChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("이것은 나무!");
            }
        });

        // 토스트 메시지 기능 추가
        Button buttonShowToast = view.findViewById(R.id.buttonShowToast);
        buttonShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "This is a toast message", Toast.LENGTH_SHORT).show();
            }
        });

        // 다이얼로그 표시 기능 추가
        Button buttonShowDialog = view.findViewById(R.id.buttonShowDialog);
        buttonShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("이사진은 아바타에 나오는 나무")
                        .setMessage("이곳에 가서 나무를 보고싶으면 100년만 기다려")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OK 버튼 클릭 시 수행할 작업
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        // 체크박스 상태 표시 기능 추가
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getActivity(), "이 사진은 열람 " + (isChecked ? "했음" : "안했음"), Toast.LENGTH_SHORT).show();
            }
        });

        // 스위치 상태 변경 기능 추가
        Switch switchButton = view.findViewById(R.id.switchButton);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSwitchOn = isChecked;
                Toast.makeText(getActivity(), "스위치가 " + (isChecked ? "켜짐" : "꺼짐"), Toast.LENGTH_SHORT).show();
                updateBackgroundColor();
            }
        });

        // 토글 버튼 상태 표시 기능 추가
        ToggleButton toggleButton = view.findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isToggleOn = isChecked;
                Toast.makeText(getActivity(), "토글버튼이 " + (isChecked ? "켜짐" : "꺼짐"), Toast.LENGTH_SHORT).show();
                updateBackgroundColor();
            }
        });

        return view;
    }

    // 옵션 메뉴 생성
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // 옵션 메뉴 선택 시 배경색 변경
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_red) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.RED);
            return true;
        } else if (id == R.id.action_blue) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            return true;
        } else if (id == R.id.action_yellow) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 스위치나 토글 버튼 상태에 따라 배경색을 흰색으로 변경
    private void updateBackgroundColor() {
        if (isSwitchOn || isToggleOn) {
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        } else {
            // 기본 배경색으로 복원
            getActivity().getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
