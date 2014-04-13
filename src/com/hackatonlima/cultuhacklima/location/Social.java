package com.hackatonlima.cultuhacklima.location;

import android.content.Context;
import android.content.Intent;

import com.hackatonlima.cultuhacklima.R;

public class Social {
	public static void share(Context ctx, String subject,String text) {
        final Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        ctx.startActivity(Intent.createChooser(intent, ctx.getString(R.string.app_name)));
       }
}
