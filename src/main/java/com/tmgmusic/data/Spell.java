package com.tmgmusic.data;

import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.Writer;

public class Spell implements Jsonable
{
    private String name;
    private String audio;

    public Spell(String name, String audioFilename)
    {
        this.name = name;
        this.audio = audioFilename;
    }

    public Spell(JsonObject json)
    {
        name = json.getString(SpellKeys.KEY_NAME);
        audio = json.getString(SpellKeys.KEY_FILE);
    }

    public boolean equals(Spell rhs)
    {
        // if the objects are the same, their JSON output should also be the same
        // avoids needing to change equals() after adding/removing fields
        return this.toJson().equals(rhs.toJson());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAudio()
    {
        return audio;
    }

    public void setAudio(String filename)
    {
        this.audio = filename;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    private JsonObject getJsonObject()
    {
        final JsonObject json = new JsonObject();
        json.put("name", name);
        json.put("file", audio);
        return json;
    }

    @Override
    public String toJson()
    {
        final JsonObject json = getJsonObject();

        return json.toJson();
    }

    @Override
    public void toJson(Writer writable) throws IOException
    {
        final JsonObject json = getJsonObject();

        json.toJson(writable);
    }

    enum SpellKeys implements JsonKey
    {
        KEY_NAME("name"),
        KEY_FILE("file");

        private final Object value;

        /**
         * Instantiates a JsonKey with the provided value.
         *
         * @param value represents a valid default for the key.
         */
        SpellKeys(final Object value)
        {
            this.value = value;
        }

        @Override
        public String getKey()
        {
            return this.value.toString();
        }

        @Override
        public Object getValue()
        {
            return this.value;
        }
    }
}


