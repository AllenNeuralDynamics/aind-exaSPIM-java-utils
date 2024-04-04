package org.aind.exaspim;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import picocli.CommandLine;

import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class RunSolver implements Callable<Void> {

    @CommandLine.Option(names = {"--section"}, description = "Json section to read as config")
    protected String jsonSection = "solver";

    @Override
    public Void call() throws Exception {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("../data/manifest/exaspim_manifest.json")) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonElement appConfigElement = jsonObject.get(jsonSection);
            AppConfigSolver appConfig = gson.fromJson(appConfigElement, AppConfigSolver.class);
            appConfig.setXmlPath("../results/bigstitcher.xml");
            appConfig.printSettings();
            appConfig.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(final String... args) {
        System.out.println(Arrays.toString(args));
        System.exit(new CommandLine(new RunSolver()).execute(args));
    }
}
