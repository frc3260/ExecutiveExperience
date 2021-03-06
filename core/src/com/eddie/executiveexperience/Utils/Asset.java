package com.eddie.executiveexperience.Utils;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Asset implements Json.Serializable
{
    public Class<?> type;
    public String path;
    public AssetLoaderParameters parameters;

    public Asset()
    {
    }

    public Asset(Class<?> type, String path, AssetLoaderParameters parameters)
    {
        this.type = type;
        this.path = path;
        this.parameters = parameters;
    }

    @Override
    public void write(Json json)
    {
        json.writeValue("assetType", type.getName());
        json.writeValue("path", path);
        json.writeValue("parameters", parameters);
    }

    @Override
    public void read(Json json, JsonValue jsonData)
    {
        String typeString = jsonData.get("type").asString();

        switch(typeString)
        {
            case "EntityData":
                typeString = "com.eddie.executiveexperience.Entity.EntityData";
                break;
        }

        try
        {
            type = Class.forName(typeString);
        }
        catch(Exception e)
        {
            type = null;
        }

        path = jsonData.get("path").asString();

        JsonValue parametersValue = jsonData.get("parameters");
        parameters = parametersValue != null ? json.fromJson(AssetLoaderParameters.class, parametersValue.toString()) : null;
    }
}