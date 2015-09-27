package com.travelgeeks.olahackathon.utilities;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class Util {


    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String readAssetFile(Context context, String fileName) {
        try {
            InputStream open = context.getAssets().open(fileName);
            Scanner scanner = new Scanner(open);
            String data = "";
            while (scanner.hasNext()) {
                data += scanner.next();
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
