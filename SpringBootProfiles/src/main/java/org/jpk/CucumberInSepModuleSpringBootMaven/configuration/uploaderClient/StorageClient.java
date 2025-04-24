package org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient;

public interface StorageClient {
    void upload(String fileName, byte[] data);
}
