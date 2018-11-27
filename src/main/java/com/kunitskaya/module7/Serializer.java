package com.kunitskaya.module7;

import java.io.*;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;


public class Serializer {

    public <T> void serialize(String serFilePath, T instance) {
        LOGGER.info("Serializing instance of: " + instance.getClass().getSimpleName());

        try (FileOutputStream fileOutputStream = new FileOutputStream(serFilePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("Serialized successfully to: " + serFilePath);

    }

    public <T> T deserialize(String serFilePath, Class<T> type) {
        LOGGER.info("Deserializing from: " + serFilePath);
        T instance = null;
        try (
                FileInputStream fileInputStream = new FileInputStream(serFilePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            instance = type.cast(objectInputStream.readObject());

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Deserialized successfully instance of: " + type.getSimpleName());
        return instance;
    }
}
