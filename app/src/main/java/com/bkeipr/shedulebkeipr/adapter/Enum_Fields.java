package com.bkeipr.shedulebkeipr.adapter;

public class Enum_Fields {
    public enum FieldShedule {
        ID(0),
        name(1),
        num(2),
        start(3),
        end(4);

        FieldShedule(int i) {
            this.fieldCode =i;
        }
        public int getFieldCode()
        {
            return fieldCode;
        }
        private int fieldCode;

    }


}
