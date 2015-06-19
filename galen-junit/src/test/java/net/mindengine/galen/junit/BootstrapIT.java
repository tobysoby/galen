/*******************************************************************************
* Copyright 2015 Ivan Shubin http://mindengine.net
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package net.mindengine.galen.junit;

import static java.util.Arrays.asList;
import net.mindengine.galen.api.GalenExecutor;
import net.mindengine.galen.utils.TestDevice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;

import net.mindengine.galen.api.Inject;

@RunWith(GalenRunner.class)
public class BootstrapIT {

    @Inject
    private GalenExecutor runner;

    @Test
    public void simple() throws Exception {
        runner.checkLayout(new TestDevice("small-phone", new Dimension(280, 800), asList("small-phone", "phone", "mobile")), "http://getbootstrap.com/css/",
                "/specs/bootstrap/commonLayout.spec", null);
    }

    @Test
    public void useDriver() throws Exception {
        runner.checkLayout(new TestDevice("small-phone", new Dimension(280, 800), asList("small-phone", "phone", "mobile")), "http://getbootstrap.com/css/",
                "/specs/bootstrap/commonLayout.spec", null);
        runner.getDriverInstance().get("https://google.de");
    }

}