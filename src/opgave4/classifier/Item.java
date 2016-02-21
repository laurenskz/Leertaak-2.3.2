/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package opgave4.classifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an item that can be classified.
 */
public class Item {
    private Map features = new HashMap();
    private String name;
    private Feature category;

    /**
     * constructor of item
     *
     * @param name     the name of the item
     * @param features the attributes of the item, by convention the
     *                 firts element of the array represents the
     *                 category of the item
     */
    public Item(String name, Feature[] features) {
        this.name = name;
        for (int i = 0; i < features.length; i++) {
            this.features.put(features[i].name(),
                    features[i]);
        }
    }

    public String name() {
        return name;
    }

    public String value(String featureName) {
        Feature feature = (Feature) features.get(featureName);
        if (feature != null) {
            return feature.value();
        } else {
            return null;
        }
    }

    public void setValue(String featureName, String newValue) {
        Feature feature = (Feature) features.get(featureName);
        if (feature != null) {
            feature.value(newValue);
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("")
                .append(name)
                .append("");
        int i;
        //for(i=0; i<attributes.length; ++i){
        //  if(i>0) buffer.append(", ");
        //  buffer.append(attributes[i].toString());
        //}
        return buffer.toString();
    }

}
