package org.aind.exaspim;

import com.google.gson.*;

import java.io.FileReader;
import java.io.Reader;

public class RunIPDetection {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("../data/manifest/exaspim_manifest.json")) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonElement appConfigElement = jsonObject.get("spark_ip_detections");
            if (appConfigElement.isJsonArray()) {
                JsonArray jsonArray = appConfigElement.getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    AppConfigIPDetection appConfig = gson.fromJson(element, AppConfigIPDetection.class);
                    appConfig.setXmlPath("../results/bigstitcher.xml");
                    appConfig.call();
                }
            } else {
                AppConfigIPDetection appConfig = gson.fromJson(appConfigElement, AppConfigIPDetection.class);
                appConfig.setXmlPath("../results/bigstitcher.xml");
                appConfig.call();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
