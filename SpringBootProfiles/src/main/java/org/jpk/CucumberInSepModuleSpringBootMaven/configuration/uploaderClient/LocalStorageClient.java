package org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient;

public class LocalStorageClient implements StorageClient{
    @Override
    public void upload(String fileName, byte[] data) {
        System.out.println("Dev, Saving file locally: " + fileName);
    }
}
