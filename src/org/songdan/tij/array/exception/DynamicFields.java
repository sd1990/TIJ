package org.songdan.tij.array.exception;


public class DynamicFields {
    private Object[][] fields;

    public DynamicFields(int initSize) {
        fields=new Object[initSize][2];
        for (Object[] objects : fields) {
            objects[0]=null;
            objects[1]=null;
        }
    }
    
    public int hasField(String id){
        if(id==null){
            throw new NullPointerException();
        }
        for (int i = 0; i < fields.length; i++) {
            Object[] objects = fields[i];
            if(objects[0].equals(id)){
                return i;
            }
        }
        return -1;
    }
    
    public Object getField(String id) throws NoSuchFieldException{
        int index = hasField(id);
        if(index!=-1){
            return fields[index][1];
        }
        throw new NoSuchFieldException();
    }
    
    public int makeField(String name,Object value){
        if(name==null){
            throw new NullPointerException();
        }
        for (int i = 0; i < fields.length; i++) {
            Object[] objects = fields[i];
            if(objects[0]==null){
                objects[0]=name;
                objects[1]=value;
                return i;
            }
        }
        Object[][] tmp = new Object[fields.length+1][2];
        System.arraycopy(fields, 0, tmp, 0, fields.length);
        fields=tmp;
        return makeField(name, value);
    }
    
}
