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

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;
import java.util.Arrays;


/**
 *
 */
public class GraphQLJavaMojoTest extends AbstractMojoTestCase {

    private GraphQLJavaMojo mojo;

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private GraphQLJavaMojo lookupGraphQLJavaMojo() throws Exception {
        File pluginXml = new File(getBasedir(), "src/test/resources/maven-plugin-test.xml");
        return (GraphQLJavaMojo) lookupMojo("generate", pluginXml);
    }


    public void testSchemaFileAvailable() throws Exception {
        GraphQLJavaMojo mojo = lookupGraphQLJavaMojo();
        String filePath = "src/test/resources/" + mojo.getSchemaFile();
        File file = new File(filePath);
        assertTrue("Schema '" + mojo.getSchemaFile() + "' file missing", file.exists());
    }

    public void testMojoLookup() throws Exception {
        GraphQLJavaMojo mojo = lookupGraphQLJavaMojo();
        assertNotNull(mojo);
    }


    public void testDefaultOutputFolder() throws Exception {
        GraphQLJavaMojo mojo = lookupGraphQLJavaMojo();

        assertEquals("src/test/generated", mojo.getOutputFolder());
    }


    public void testAllParameters() throws Exception {
        GraphQLJavaMojo mojo = lookupGraphQLJavaMojo();

        assertEquals("src/test/generated", mojo.getOutputFolder());
        assertEquals("services.videa.graphql.java.generated", mojo.getPackageName());
        assertEquals("graphql-java-test.gql", mojo.getSchemaFile());
    }


    public void testGeneration() throws Exception {
        GraphQLJavaMojo mojo = lookupGraphQLJavaMojo();

        String outputFolder = mojo.getOutputFolder();

        File generationFolder = new File(outputFolder);
        FileUtils.forceMkdir(generationFolder);

        mojo.execute();

        String filePath = mojo.getOutputFolder() + "/" + mojo.getPackageName().replace(".", "/");
        File[] files = new File(filePath).listFiles();
        assertNotNull(files);
        assertTrue(files.length > 0);

        Arrays.stream(files).forEach(File::delete);

        FileUtils.forceDelete(generationFolder);
    }

}
