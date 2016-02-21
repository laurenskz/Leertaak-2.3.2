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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A node of a decision tree
 */
public class Node {
    private String label;

    private Map arcs = new HashMap();

    // leaf constructor
    public Node(String label) {
        this.label = label;
    }

    public void addChild(String arcLabel, Node child) {
        arcs.put(arcLabel, child);
    }

    public boolean isLeaf() {
        return arcs.size() == 0;
    }

    public String label() {
        return label;
    }

    public Node follow(String arcLabel) {
        return (Node) arcs.get(arcLabel);
    }

    public Map getArcs() {
        return arcs;
    }

    public String toString() {
        return toString("");
    }

    private static final String indentStep = "    ";

    private String toString(String indent) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[")
                .append(label);
        if (!isLeaf()) {
            buffer.append("\n");
            int i;
            for (Iterator iter = arcs.keySet().iterator(); iter.hasNext(); ) {
                String arcLabel = (String) iter.next();
                Node dest = (Node) arcs.get(arcLabel);
                buffer.append(indent)
                        .append("  (")
                        .append(arcLabel)
                        .append(")--> ")
                        .append(dest.toString(indent + indentStep));
            }
            buffer.append(indent);
        }
        buffer.append("]\n");
        return buffer.toString();
    }
}
