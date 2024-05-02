package org.aind.exaspim;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.preibisch.bigstitcher.spark.SparkInterestPointDetection;
import org.apache.commons.compress.harmony.pack200.NewAttributeBands;
import picocli.CommandLine;

import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class RunGeometricDescriptorMatching implements Callable<Void> {

    @CommandLine.Option(names = {"--section"}, description = "Json section to read as config")
    protected String jsonSection = "spark_geometric_descriptor_matchings";

    @Override
    public Void call() throws Exception {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("../data/manifest/exaspim_manifest.json")) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonElement appConfigElement = jsonObject.get(jsonSection);
            if (appConfigElement.isJsonArray()) {
                for (JsonElement element : appConfigElement.getAsJsonArray()) {
                    AppConfigGeometricDescriptorMatching appConfig = gson.fromJson(element, AppConfigGeometricDescriptorMatching.class);
                    appConfig.setXmlPath("../results/bigstitcher.xml");
                    appConfig.call();
                }
            } else {
                AppConfigGeometricDescriptorMatching appConfig = gson.fromJson(appConfigElement, AppConfigGeometricDescriptorMatching.class);
                appConfig.setXmlPath("../results/bigstitcher.xml");
                appConfig.call();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(final String... args) {
        System.out.println(Arrays.toString(args));
        System.exit(new CommandLine(new RunGeometricDescriptorMatching()).execute(args));
    }
}
