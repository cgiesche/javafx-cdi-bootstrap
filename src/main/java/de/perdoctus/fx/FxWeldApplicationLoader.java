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
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;
import java.util.logging.Logger;

public final class FxWeldApplicationLoader extends Application {

    private Logger log = Logger.getLogger(getClass().getName());
    private FxWeldApplication fxWeldApplication;

    @Override
    public void init() throws Exception {
        final Weld weld = new Weld();
        final WeldContainer weldContainer = weld.initialize();
        final Instance<FxWeldApplication> fxWeldApplications = weldContainer.instance().select(FxWeldApplication.class);

        fxWeldApplication = fxWeldApplications.get();
        log.info("Initializing application.");
        fxWeldApplication.init();
    }

    @Override
    public void start(final Stage stage) throws Exception {
        log.info("Starting application.");
        fxWeldApplication.start(stage, getParameters());
    }

    @Override
    public void stop() throws Exception {
        log.info("Stopping application.");
        fxWeldApplication.stop();
    }

}
