package org.apache.cayenne.crypto.db.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.property.BaseProperty;
import org.apache.cayenne.exp.property.PropertyFactory;

/**
 * Class _Table2 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Table2 extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final BaseProperty<byte[]> CRYPTO_BYTES = PropertyFactory.createBase("cryptoBytes", byte[].class);
    public static final BaseProperty<byte[]> PLAIN_BYTES = PropertyFactory.createBase("plainBytes", byte[].class);

    protected byte[] cryptoBytes;
    protected byte[] plainBytes;


    public void setCryptoBytes(byte[] cryptoBytes) {
        beforePropertyWrite("cryptoBytes", this.cryptoBytes, cryptoBytes);
        this.cryptoBytes = cryptoBytes;
    }

    public byte[] getCryptoBytes() {
        beforePropertyRead("cryptoBytes");
        return this.cryptoBytes;
    }

    public void setPlainBytes(byte[] plainBytes) {
        beforePropertyWrite("plainBytes", this.plainBytes, plainBytes);
        this.plainBytes = plainBytes;
    }

    public byte[] getPlainBytes() {
        beforePropertyRead("plainBytes");
        return this.plainBytes;
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "cryptoBytes":
                return this.cryptoBytes;
            case "plainBytes":
                return this.plainBytes;
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
            case "cryptoBytes":
                this.cryptoBytes = (byte[])val;
                break;
            case "plainBytes":
                this.plainBytes = (byte[])val;
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
        out.writeObject(this.cryptoBytes);
        out.writeObject(this.plainBytes);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.cryptoBytes = (byte[])in.readObject();
        this.plainBytes = (byte[])in.readObject();
    }

}
