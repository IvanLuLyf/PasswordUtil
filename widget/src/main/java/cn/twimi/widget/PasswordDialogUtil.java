package cn.twimi.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

public class PasswordDialogUtil {
    public static class Builder {
        private OnPasswordCompleted onCompleted;
        private int maxLength;
        private String title;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setOnCompleted(OnPasswordCompleted onCompleted) {
            this.onCompleted = onCompleted;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMaxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public AlertDialog build() {
            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.dialog_password, null);
            final AlertDialog dialog = new AlertDialog.Builder(context)
                    .setView(layout)
                    .setTitle(title)
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (onCompleted != null) {
                                onCompleted.onCompleted(null);
                            }
                        }
                    }).create();
            final PasswordView passwordView = layout.findViewById(R.id.passwordView);
            passwordView.setMaxLength(maxLength);
            passwordView.setOnCompleted(new PasswordView.OnCompleted() {
                @Override
                public void onCompleted(String password) {
                    dialog.cancel();
                    if (onCompleted != null) {
                        onCompleted.onCompleted(password);
                    }
                }
            });
            passwordView.requestFocus();
            if (dialog.getWindow() != null)
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            return dialog;
        }
    }

    public interface OnPasswordCompleted {
        void onCompleted(String password);
    }
}