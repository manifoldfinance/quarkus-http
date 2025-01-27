/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.undertow.websockets.jsr.test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author Stuart Douglas
 */

public class AddEndpointServlet implements Servlet {
    @Override
    public void init(ServletConfig c) throws ServletException {
        String websocketPath = "/foo";
        ServerContainer serverContainer = (ServerContainer) c.getServletContext().getAttribute("javax.websocket.server.ServerContainer");
        ServerEndpointConfig config = ServerEndpointConfig.Builder.create(ProgramaticEndpoint.class, websocketPath).extensions(new ArrayList<>(serverContainer.getInstalledExtensions())).build();
        try {
            serverContainer.addEndpoint(config);
        } catch (DeploymentException ex) {
            throw new ServletException("Error deploying websocket endpoint:", ex);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
    }
}
