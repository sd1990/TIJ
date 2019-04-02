package org.songdan.tij.algorithm.node;

public class EmptyNode extends AbstractNode implements LeafNode {

    public static final EmptyNode MAP = new EmptyNode(Type.map);
    public static final EmptyNode LIST = new EmptyNode(Type.list);
    private static final long serialVersionUID = -3502318408147184859L;
    private final Type type;

    private EmptyNode(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public String toJson() {
        return type == Type.map ? "{}" : "[]";
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmptyNode other = (EmptyNode) obj;
        return type == other.type;
    }

}
