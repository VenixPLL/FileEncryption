package me.dickmeister.file.folder;

import lombok.RequiredArgsConstructor;
import me.dickmeister.file.util.EncryptionUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public class FolderDecryption {

    private final File folder;

    public void decrypt(String master) {
        try {
            var files = folder.listFiles();

            if (Objects.nonNull(files)) {
                Arrays.stream(files).forEach(f -> {
                    try {
                        System.out.println("Decrypting " + f.getName() + "...");
                        var data = Files.readAllBytes(f.toPath());
                        var encrypted = EncryptionUtil.decryptData(data, master);

                        assert encrypted != null;
                        Files.write(f.toPath(), encrypted);
                        System.out.println("Done\n");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
