package com.epam.resource;

import com.epam.view.InitialView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class InitialResource {

    @GET
    public InitialView getPage() {
        return new InitialView("spa.ftl");
    }

}
