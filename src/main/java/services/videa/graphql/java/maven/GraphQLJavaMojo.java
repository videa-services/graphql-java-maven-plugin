/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package services.videa.graphql.java.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import services.videa.graphql.java.GqlJavaGenerator;

//import services.videa.graphql.java.GqlJavaGenerator;

/**
 *
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GraphQLJavaMojo extends AbstractMojo {

    @Parameter(property = "generate.schemaFile", required = true)
    private String schemaFile;

    @Parameter(property = "generate.outputFolder", required = true)
    private String outputFolder;

    @Parameter(property = "generate.packageName", required = true)
    private String packageName;


    public void execute() {

        getLog().info("Running Maven Plugin: " + GraphQLJavaMojo.class.getName());
        getLog().info("Using schema file: " + schemaFile);
        getLog().info("Generating to output folder: " + outputFolder);
        getLog().info("Defining package name: " + packageName);

        getLog().info("Generating Java classes...");
        GqlJavaGenerator.generateJavaClasses(schemaFile, outputFolder, packageName);
        getLog().info("Successfully done.");
    }


    public String getSchemaFile() {
        return schemaFile;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public String getPackageName() {
        return packageName;
    }

}
