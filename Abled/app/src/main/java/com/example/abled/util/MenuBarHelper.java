package com.example.abled.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.abled.R;
import com.example.abled.activity.MainActivity;
import com.example.abled.activity.MikeActivity;
import com.example.abled.activity.SetInfoActivity;

public class MenuBarHelper {

    private Context context;

    public MenuBarHelper(Context context){
        this.context = context;
    }
    public void handleItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_item1) {
            onHomeItemSelected(context);
        } else if (itemId == R.id.menu_item2) {
            onMikeItemSelected(context);
        } else if (itemId == R.id.menu_item3) {
            onMyInfoItemSelected(context);
        }
    }

    private void onHomeItemSelected(Context context) {
        if(context.getClass() == MainActivity.class) return;

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    private void onMikeItemSelected(Context context) {
        if(context.getClass() == MikeActivity.class) return;

        Intent intent = new Intent(context, MikeActivity.class);
        context.startActivity(intent);
    }

    private void onMyInfoItemSelected(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("알림");
        builder.setMessage("정보를 변경하시겠습니까?");

        builder.setPositiveButton("예", (dialogInterface, i) -> {
            Intent intent = new Intent(context, SetInfoActivity.class);
            context.startActivity(intent);
        });
        builder.setNegativeButton("아니오", null);

        builder.show();
    }
}