package me.dickmeister;

import me.dickmeister.file.folder.FolderDecryption;
import me.dickmeister.file.folder.FolderEncryption;
import me.dickmeister.file.gui.JFrameUtil;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class FileEncryption {

    public void start() {

        var folder = this.selectFolder();
        if (Objects.nonNull(folder)) {

            var action = this.askAction();

            var password = JFrameUtil.askPassword();
            if (Objects.isNull(password)) {
                JOptionPane.showMessageDialog(null, "No password entered!");
                System.exit(0);
                return;
            }

            System.out.println("Action: " + action);
            switch (action) {
                case 1 -> decrypt(folder, password);
                case 0 -> encrypt(folder, password);
            }

        }
    }

    private void decrypt(File folder, String master) {
        System.out.println("Decrypting...");
        var folderDecryption = new FolderDecryption(folder);
        folderDecryption.decrypt(master);

    }

    private void encrypt(File folder, String master) {
        System.out.println("Encrypting...");
        var folderEncryption = new FolderEncryption(folder);
        folderEncryption.encrypt(master);
    }

    public File selectFolder() {
        var fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        var result = fileChooser.showOpenDialog(null);
        System.out.println("result : " + result);
        if (result == JFileChooser.APPROVE_OPTION) {
            var selectedFile = fileChooser.getSelectedFile();

            if (!selectedFile.isDirectory()) {
                JOptionPane.showMessageDialog(null, "Selected file is not a directory!");
                return null;
            }

            return selectedFile;

        }

        JOptionPane.showMessageDialog(null, "No folder selected!");
        return null;
    }

    private int askAction() {
        Object[] options = {"Encrypt",
                "Decrypt"};
        return JOptionPane.showOptionDialog(null,
                "What action do you want to perform?",
                "FileEncryption",
                JOptionPane.NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
    }

    public static void main(String[] args) {
        var encryption = new FileEncryption();
        encryption.start();
    }

}
