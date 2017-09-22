/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * REST Web Service
 *
 * @author Peter
 */
@Path("web")
public class GenericResource {

    Gson gs = new Gson();
    Service service = new Service();
    boolean cached = true;
    long start = 0l;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @Path("scrape")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        if((System.currentTimeMillis() - start) > 3600000){     //one hour
            cached = true;
        }
        if (cached) {
            start = System.currentTimeMillis();
            cached = false;
            service.callBack();
        }
        List<Group> groupList = service.getList();
        JsonArray ja = makeJson(groupList);
        return gs.toJson(ja);
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    public JsonArray makeJson(List<Group> groupList) {
        JsonArray ja = new JsonArray();
        for (int i = 0; i < groupList.size(); i++) {
            JsonObject jo = new JsonObject();
            jo.addProperty("authors", groupList.get(i).getAuthors());
            jo.addProperty("groupNr", groupList.get(i).getGroupNumber());
            jo.addProperty("className", groupList.get(i).getClassName());
            ja.add(jo);

        }
        return ja;
    }
}
