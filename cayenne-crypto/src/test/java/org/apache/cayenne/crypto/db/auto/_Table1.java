package org.apache.cayenne.crypto.db.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.property.NumericProperty;
import org.apache.cayenne.exp.property.PropertyFactory;
import org.apache.cayenne.exp.property.StringProperty;

/**
 * Class _Table1 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Table1 extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final NumericProperty<Integer> CRYPTO_INT = PropertyFactory.createNumeric("cryptoInt", Integer.class);
    public static final StringProperty<String> CRYPTO_STRING = PropertyFactory.createString("cryptoString", String.class);
    public static final NumericProperty<Integer> PLAIN_INT = PropertyFactory.createNumeric("plainInt", Integer.class);
    public static final StringProperty<String> PLAIN_STRING = PropertyFactory.createString("plainString", String.class);

    protected Integer cryptoInt;
    protected String cryptoString;
    protected Integer plainInt;
    protected String plainString;


    public void setCryptoInt(int cryptoInt) {
        beforePropertyWrite("cryptoInt", this.cryptoInt, cryptoInt);
        this.cryptoInt = cryptoInt;
    }

    public int getCryptoInt() {
        beforePropertyRead("cryptoInt");
        if(this.cryptoInt == null) {
            return 0;
        }
        return this.cryptoInt;
    }

    public void setCryptoString(String cryptoString) {
        beforePropertyWrite("cryptoString", this.cryptoString, cryptoString);
        this.cryptoString = cryptoString;
    }

    public String getCryptoString() {
        beforePropertyRead("cryptoString");
        return this.cryptoString;
    }

    public void setPlainInt(int plainInt) {
        beforePropertyWrite("plainInt", this.plainInt, plainInt);
        this.plainInt = plainInt;
    }

    public int getPlainInt() {
        beforePropertyRead("plainInt");
        if(this.plainInt == null) {
            return 0;
        }
        return this.plainInt;
    }

    public void setPlainString(String plainString) {
        beforePropertyWrite("plainString", this.plainString, plainString);
        this.plainString = plainString;
    }

    public String getPlainString() {
        beforePropertyRead("plainString");
        return this.plainString;
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "cryptoInt":
                return this.cryptoInt;
            case "cryptoString":
                return this.cryptoString;
            case "plainInt":
                return this.plainInt;
            case "plainString":
                return this.plainString;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "cryptoInt":
                this.cryptoInt = (Integer)val;
                break;
            case "cryptoString":
                this.cryptoString = (String)val;
                break;
            case "plainInt":
                this.plainInt = (Integer)val;
                break;
            case "plainString":
                this.plainString = (String)val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.cryptoInt);
        out.writeObject(this.cryptoString);
        out.writeObject(this.plainInt);
        out.writeObject(this.plainString);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.cryptoInt = (Integer)in.readObject();
        this.cryptoString = (String)in.readObject();
        this.plainInt = (Integer)in.readObject();
        this.plainString = (String)in.readObject();
    }

}
