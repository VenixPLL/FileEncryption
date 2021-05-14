package me.dickmeister.file.gui;

import javax.swing.*;

public class JFrameUtil {

    public static String askPassword() {
        String s = (String) JOptionPane.showInputDialog(
                null,
                "Enter master password!",
                "Folder Encryption",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

        if ((s != null) && (s.length() > 0)) {
            return s;
        }

        return null;
    }


}
