package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.mhpositions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MHPositionsConfig {

    public List<MHPositionsList> getMHPositionsLists() {
        final List<MHPositionsList> MHPositionsLists = new ArrayList<>();

		File listFiles[] = new File("conf/escenas/spring/").listFiles();


        for (File f: listFiles) {
            final String id = StringUtils.substringBetween(f.getName(), "pos-", ".yml");
            if (StringUtils.isNoneEmpty(id)) {
                MHPositionsList mhPositionsList = readFile(id, f.getAbsolutePath());
                mhPositionsList.setId(id);
                MHPositionsLists.add(mhPositionsList);

            }
        }
        return MHPositionsLists;
    }

    private MHPositionsList readFile(final String id, final String fileName){

        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();
            return mapper.readValue(new File(fileName), MHPositionsList.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
