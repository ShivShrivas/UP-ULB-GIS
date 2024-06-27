package com.uphq.ulb_gis.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Calendar;
import java.util.regex.Pattern;

public class DateTextWatcher implements TextWatcher {
    private final EditText editText;
    private boolean isFormatting;
    private String previousText = "";

    public DateTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        if (isFormatting) return;

        isFormatting = true;

        String text = s.toString();
        String formatted = formatDateString(text);

        if (!text.equals(formatted)) {
            editText.setText(formatted);
            editText.setSelection(formatted.length());
        }

        previousText = formatted;
        isFormatting = false;

        // Clear if the date is invalid after formatting
        if (formatted.length() == 10 && !isValidDate(formatted)) {
            editText.setText("");
            editText.setError("Enter correct date");
        }else{
            editText.setError(null);

        }
    }

    private String formatDateString(String text) {
        text = text.replaceAll("[^\\d]", "");

        StringBuilder formatted = new StringBuilder();

        if (text.length() > 2) {
            String dayStr = text.substring(0, 2);
            int day = Integer.parseInt(dayStr);
            if (day < 1 || day > 31) {
                return previousText;
            }
            formatted.append(dayStr).append("/");
            text = text.substring(2);
        }

        if (text.length() > 2) {
            String monthStr = text.substring(0, 2);
            int month = Integer.parseInt(monthStr);
            if (month < 1 || month > 12) {
                return previousText;
            }
            formatted.append(monthStr).append("/");
            text = text.substring(2);
        }

        if (text.length() > 4) {
            String yearStr = text.substring(0, 4);
            int year = Integer.parseInt(yearStr);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (year < 1900 || year > currentYear) {
                return previousText;
            }
            formatted.append(yearStr);
        } else {
            formatted.append(text);
        }

        return formatted.toString();
    }

    private boolean isValidDate(String date) {
        String[] parts = date.split("/");

        if (parts.length != 3) return false;

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) return false;

        if (month < 1 || month > 12) return false;

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        return day >= 1 && day <= daysInMonth[month - 1];
    }

    private boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}