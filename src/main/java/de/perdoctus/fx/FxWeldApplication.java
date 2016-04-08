package de.perdoctus.fx;

/*
 * #%L
 * javafx-cdi-bootstrap
 * %%
 * Copyright (C) 2016 Christoph Giesche
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */


import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Extend this class instead of {@link Application}. There must be exactly one implementation. Use the included main method
 * to bootstrap your application.
 *
 * @author Christoph Giesche
 */
public abstract class FxWeldApplication {

    public static void main(final String[] args) {
        FxWeldApplicationLoader.launch(FxWeldApplicationLoader.class, args);
    }

    /**
     * This method gets called, before application is started.
     *
     * @see Application#init()
     */
    public void init() throws Exception {
    }

    /**
     * This method gets called, when application is started.
     *
     * @see Application#start(Stage)
     */
    public abstract void start(final Stage primaryStage, final Application.Parameters parameters) throws Exception;

    /**
     * This method gets called, when the application is stopped.
     *
     * @see Application#stop()
     */
    public void stop() throws Exception {
    }

}
