package com.rkota.network.access;

import jcifs.smb.*;

public class NetworkSharedDriveExample {

    public static void main(String[] args) {

        String sharedDriveLocation = "smb:\\localhost\\Users\\admin\\OneDrive\\Desktop\\share\\sample.txt";

        // Specify the credentials
        String username = "admin";
        String password = "pwd";
        String domain = "DESKTOP-944RI4P";

        try {
            // Create a SmbFile object with the shared drive location
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domain, username, password);
            SmbFile smbFile = new SmbFile(sharedDriveLocation, auth);

            // Check if the file exists
            if (smbFile.exists()) {
                // Read the file content
                SmbFileInputStream inputStream = new SmbFileInputStream(smbFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                StringBuilder fileContent = new StringBuilder();
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileContent.append(new String(buffer, 0, bytesRead));
                }
                inputStream.close();

                // Print the file content
                System.out.println("File content:");
                System.out.println(fileContent.toString());
            } else {
                System.out.println("File does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

