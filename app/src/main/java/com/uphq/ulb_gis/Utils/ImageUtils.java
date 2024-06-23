package com.uphq.ulb_gis.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    public static String convertImageToBase64(String imagePath) {
        File imgFile = new File(imagePath);
        if (!imgFile.exists()) {
            return null; // File does not exist
        }

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        if (bitmap == null) {
            return null; // Unable to decode bitmap
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static File compressImage(String inputPath) {
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            return null; // File does not exist
        }

        Bitmap bitmap = BitmapFactory.decodeFile(inputPath);

        // Calculate the target file size (120 KB)
        long targetSize = 120 * 1024; // 120 KB in bytes

        try {
            File outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File outputFile = File.createTempFile("compressed_image", ".jpg", outputDir);

            int quality = 90; // Initial quality

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);

            while (byteArrayOutputStream.toByteArray().length > targetSize && quality > 10) {
                byteArrayOutputStream.reset(); // Clear the previous output

                // Decrease quality by 10%
                quality -= 10;

                // Compress the bitmap with the new quality
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            }

            // Write the compressed bitmap to the file
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();

            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static long getFileSizeInKB(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.length() / 1024; // Size in KB
        }
        return 0;
    }
}


