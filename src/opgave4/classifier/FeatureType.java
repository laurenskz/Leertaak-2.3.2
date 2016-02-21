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

import java.util.Collection;
import java.util.TreeSet;

public class FeatureType {

    private TreeSet allowed = new TreeSet();
    private String name;
        
	public FeatureType(String name, String[] allowedValues) {
		this.name = name;
		for (int i = 0; i < allowedValues.length; i++) {
			allowed.add(allowedValues[i]);
		}
	}

	public boolean isAllowed(String value) {
		return allowed.contains(value);
	}

	public Collection allowedValues() {
		return allowed;
	}

}
